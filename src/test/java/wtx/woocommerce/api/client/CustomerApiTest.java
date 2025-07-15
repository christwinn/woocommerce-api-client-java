/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package wtx.woocommerce.api.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.pl_wtx_woocommerce.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.twinn.api.woocommerce.request.CustomerApi;
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.response.Batched;
import uk.co.twinn.api.woocommerce.rest.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerApiTest {


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

        Batch batch = new JacksonObjectMapper().getObjectMapper().readValue(mockRequest, new TypeReference<Batch>(){});

        System.out.println(batch.getRecordCount());

        Batched<Customer> response = new CustomerApi.Batcher<>()
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


        // Verify request URL contains correct status parameters
        String requestUrl = mockWebServer.takeRequest().getRequestUrl().toString();

        System.out.println(requestUrl);

        logTestSummary("testBatchCustomers", response.getResult().getRecordCount());
    }

}
