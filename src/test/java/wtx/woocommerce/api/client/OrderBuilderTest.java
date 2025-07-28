package wtx.woocommerce.api.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.MockResponse;
import uk.co.twinn.api.woocommerce.api.Orders;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.rest.Configuration;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.order.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OrderBuilderTest {

    private static final String SEPARATOR = "-------------------------------------------------------------------------------";

    private MockWebServer mockWebServer;

    private void logTestStart(String testName) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Starting test: " + testName);
        System.out.println(SEPARATOR + "\n");
    }

    private void logTestSummary(String testName, int orderCount, List<String> statuses) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Test completed: " + testName);
        System.out.println("Summary:");
        System.out.println("- Total orders: " + orderCount);
        System.out.println("- Statuses checked: " + String.join(", ", statuses));
        System.out.println(SEPARATOR + "\n");
    }


    @BeforeEach
    void setUp() throws IOException {

        // Initialize mock web server
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // Create and configure ApiClient with logging enabled
        //reads our config file but the overrides the website
        new Configuration(
            mockWebServer.url("").toString(),
            Configuration.getKey(),
            Configuration.getSecret()
        );

    }

    @Test
    void testListAllOrdersStatuses() throws IOException, InterruptedException {
        List<String> statuses = Arrays.asList( "on-hold", "completed");
        testListStatuses(statuses, "mockedResponse-orders-multiple-statuses.json");
    }

    @Test
    void testListReversed() throws IOException, InterruptedException {
        List<String> statuses = Arrays.asList( "on-hold", "completed");
        testListStatuses(statuses, "mockedResponse-orders-reversed-statuses.json");
    }

    void testListStatuses(List<String> statuses, String mockSource) throws IOException, InterruptedException {

        logTestStart("testListAllOrdersWithMultipleStatuses");

        // Load mock response from file
        String mockResponse = new String(Files.readAllBytes(
            Paths.get("src/test/resources/wtx/woocommerce/api/client/" + mockSource)
        ));

        System.out.println(mockResponse);

        // Set up mock response
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody(mockResponse));

        Listed<Order> response = Orders.listing()
            .setStatus(statuses)
            .getResponse();

        System.out.println("Processing TESTS");

        if (response.isSuccess()) {
            for (Order order : response.getResult()) {
                System.out.println(order.toString());
            }
        }

        // Then
        assertNotNull(response.getResult(), "Order list should not be null");
        assertFalse(response.getResult().isEmpty(), "Order list should not be empty");

        for (String status : statuses) {

            // Verify we have orders in both statuses
            boolean verified = response.getResult().stream().anyMatch(order -> status.equals(order.getStatus()));

            assertTrue(verified, String.format("Should have orders with '%s' status", status));

        }
            // Verify request URL contains correct status parameters
        String requestUrl = mockWebServer.takeRequest().getRequestUrl().toString();

        System.out.println(requestUrl);

        assertTrue(requestUrl.contains("status=on-hold,completed"),
            "Request URL should contain correct status parameters");

        logTestSummary("testListAllOrdersWithMultipleStatuses", response.getResult().size(), statuses);
    }

}
