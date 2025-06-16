package wtx.woocommerce.api.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;
import pl.wtx.woocommerce.api.client.model.*;
import pl.wtx.woocommerce.api.client.invoker.*;
import pl.wtx.woocommerce.api.client.OrdersApi;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.MockResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the OrdersApi class.
 * These tests verify the correct handling of multiple order statuses in listAllOrders method.
 */
public class OrdersApiTest {
    
    private static final String SEPARATOR = "-------------------------------------------------------------------------------";
    private MockWebServer mockWebServer;
    private ApiClient apiClient;
    private OrdersApi ordersApi;
    private JSON json;
    
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
        apiClient = new ApiClient();
        apiClient.setDebugging(true);
        apiClient.setBasePath(mockWebServer.url("/").toString());
        
        // Create OrdersApi instance with the configured client
        ordersApi = new OrdersApi(apiClient);
        
        // Initialize JSON serializer
        json = new JSON();
    }
    
    @Test
    void testListAllOrdersWithMultipleStatuses() throws IOException, ApiException, InterruptedException {
        logTestStart("testListAllOrdersWithMultipleStatuses");
        
        // Given
        List<String> statuses = Arrays.asList("on-hold", "completed");
        
        // Load mock response from file
        String mockResponse = new String(Files.readAllBytes(
            Paths.get("src/test/resources/wtx/woocommerce/api/client/mockedResponse-orders-multiple-statuses.json")
        ));
        
        // Set up mock response
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody(mockResponse));
        
        // When
        List<Order> orders = ordersApi.listAllOrders(
            null, // context
            null, // page
            100, // per_page
            null, // search
            null, // after
            null, // before
            null, // modified_after
            null, // modified_before
            null, // dates_are_gmt
            null, // exclude
            null, // include
            null, // offset
            null, // order
            null, // orderby
            null, // parent
            null, // parent_exclude
            statuses, // status
            null, // customer
            null, // product
            null  // dp
        );
        
        // Then
        assertNotNull(orders, "Order list should not be null");
        assertFalse(orders.isEmpty(), "Order list should not be empty");
        
        // Verify we have orders in both statuses
        boolean hasOnHoldOrders = orders.stream().anyMatch(order -> "on-hold".equals(order.getStatus()));
        boolean hasCompletedOrders = orders.stream().anyMatch(order -> "completed".equals(order.getStatus()));

        assertTrue(hasOnHoldOrders, "Should have orders with 'on-hold' status");
        assertTrue(hasCompletedOrders, "Should have orders with 'completed' status");
        
        // Verify request URL contains correct status parameters
        String requestUrl = mockWebServer.takeRequest().getRequestUrl().toString();
        assertTrue(requestUrl.contains("status=on-hold,completed"), 
            "Request URL should contain correct status parameters");
            
        logTestSummary("testListAllOrdersWithMultipleStatuses", orders.size(), statuses);
    }

    @Test
    void testListAllOrdersWithReversedStatuses() throws IOException, ApiException, InterruptedException {
        logTestStart("testListAllOrdersWithReversedStatuses");
        
        // Given
        List<String> statuses = Arrays.asList("completed", "on-hold");
        
        // Load mock response from file
        String mockResponse = new String(Files.readAllBytes(
            Paths.get("src/test/resources/wtx/woocommerce/api/client/mockedResponse-orders-reversed-statuses.json")
        ));
        
        // Set up mock response
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody(mockResponse));
        
        // When
        List<Order> orders = ordersApi.listAllOrders(
            null, // context
            null, // page
            100, // per_page
            null, // search
            null, // after
            null, // before
            null, // modified_after
            null, // modified_before
            null, // dates_are_gmt
            null, // exclude
            null, // include
            null, // offset
            null, // order
            null, // orderby
            null, // parent
            null, // parent_exclude
            statuses, // status
            null, // customer
            null, // product
            null  // dp
        );
        
        // Then
        assertNotNull(orders, "Order list should not be null");
        assertFalse(orders.isEmpty(), "Order list should not be empty");
        
        // Verify we have orders in both statuses
        boolean hasOnHoldOrders = orders.stream().anyMatch(order -> "on-hold".equals(order.getStatus()));
        boolean hasCompletedOrders = orders.stream().anyMatch(order -> "completed".equals(order.getStatus()));
        
        assertTrue(hasOnHoldOrders, "Should have orders with 'on-hold' status");
        assertTrue(hasCompletedOrders, "Should have orders with 'completed' status");
        
        // Verify request URL contains correct status parameters
        String requestUrl = mockWebServer.takeRequest().getRequestUrl().toString();
        assertTrue(requestUrl.contains("status=completed,on-hold"), 
            "Request URL should contain correct status parameters");
            
        logTestSummary("testListAllOrdersWithReversedStatuses", orders.size(), statuses);
    }
} 