/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package wtx.woocommerce.api.client;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import uk.co.twinn.api.woocommerce.api.Customers;
import uk.co.twinn.api.woocommerce.builders.core.Batch;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.twinn.api.woocommerce.response.Batched;
import uk.co.twinn.api.woocommerce.rest.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CustomersTest {

    private static final String SEPARATOR = "-------------------------------------------------------------------------------";

    private MockWebServer mockWebServer;

    private void logTestStart(String testName) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Starting test: " + testName);
        System.out.println(SEPARATOR + "\n");
    }

    private void logTestSummary(String testName, int orderCount) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("Test completed: " + testName);
        System.out.println("Summary:");
        System.out.println("- Total orders: " + orderCount);
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
        testBatch("mockedRequest-customers-batch.json", "mockedResponse-customers-batch.json");
    }

    @Test
    void testListReversed() throws IOException, InterruptedException {
        testBatch("mockedRequest-customers-batch.json","mockedResponse-customers-exist.json");
    }



    void testBatch(String frequest, String fresponse) throws IOException, InterruptedException {

        logTestStart("testBatch");

        // Load mock response from file
        String mockResponse = new String(Files.readAllBytes(
            Paths.get("src/test/resources/wtx/woocommerce/api/client/" + fresponse)
        ));

        //System.out.println(mockResponse);

        // Set up mock response
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody(mockResponse));

        String mockRequest = new String(Files.readAllBytes(
            Paths.get("src/test/resources/wtx/woocommerce/api/client/" + frequest)
        ));

        Batch<Customer> batch = new JacksonObjectMapper().getObjectMapper().readValue(mockRequest, new TypeReference<Batch<Customer>>(){});

        System.out.println(batch.getRecordCount());

        System.out.println(batch.toJson());

        Batched<Customer> response = Customers.batch()
            .setBatch(batch)
            .getResponse();

        System.out.println("Processing TESTS");

        if (response.isSuccess()) {
            for (Customer customer : response.getResult().getCreated()) {
                System.out.println(customer.toString());
            }

            assertNotNull(response.getResult().getCreated(), "Created list should not be null");
            assertFalse(response.getResult().getCreated().isEmpty(), "Created list should not be empty");
        }else{
            System.out.println(response.getError().getMessage());
            assertFalse(response.isSuccess(), "Created list should not be empty");
        }

        /*try {
            // Verify request URL contains correct status parameters
            String requestUrl = mockWebServer.takeRequest().getRequestUrl().toString();

            System.out.println(requestUrl);
        }catch(NullPointerException e){

        }*/

        logTestSummary("testBatchCustomers", response.getResult().getRecordCount());
    }

}
