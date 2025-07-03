package wtx.woocommerce.api.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wtx.woocommerce.api.client.model.*;
import pl.wtx.woocommerce.api.client.invoker.*;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.MockResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.OrderRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.Searched;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OrderRequestTest {

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
    void testListAllOrdersStatuses() throws IOException, ApiException, InterruptedException {
        List<String> statuses = Arrays.asList( "on-hold", "completed");
        testListStatuses(statuses, "mockedResponse-orders-multiple-statuses.json");
    }

    @Test
    void testListReversed() throws IOException, ApiException, InterruptedException {
        List<String> statuses = Arrays.asList( "on-hold", "completed");
        testListStatuses(statuses, "mockedResponse-orders-reversed-statuses.json");
    }

    void testListStatuses(List<String> statuses, String mockSource) throws IOException, ApiException, InterruptedException {

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

        Searched<Order> response = new OrderRequest.Searcher<>()
            .setStatus(statuses)
            .getResponse();

        System.out.println("Processing TESTS");

        if (response.isSuccess()) {
            for (Order order : response.getSearched()) {
                System.out.println(order.toString());
            }
        }

        // Then
        assertNotNull(response.getSearched(), "Order list should not be null");
        assertFalse(response.getSearched().isEmpty(), "Order list should not be empty");

        for (String status : statuses) {

            // Verify we have orders in both statuses
            boolean verified = response.getSearched().stream().anyMatch(order -> status.equals(order.getStatus()));

            assertTrue(verified, String.format("Should have orders with '%s' status", status));

        }
            // Verify request URL contains correct status parameters
        String requestUrl = mockWebServer.takeRequest().getRequestUrl().toString();

        System.out.println(requestUrl);

        assertTrue(requestUrl.contains("status=on-hold,completed"),
            "Request URL should contain correct status parameters");

        logTestSummary("testListAllOrdersWithMultipleStatuses", response.getSearched().size(), statuses);
    }

}
