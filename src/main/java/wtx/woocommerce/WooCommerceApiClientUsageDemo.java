package wtx.woocommerce;

import java.util.List;

import wtx.woocommerce.api.client.CustomersApi;
import wtx.woocommerce.api.client.invoker.ApiException;
import wtx.woocommerce.api.client.model.Customer;

public class WooCommerceApiClientUsageDemo {

    public static void main(String[] args) {

        System.out.println(">>> Start running the WooCommerceApiClientUsageDemo...");

        // Use WooCommerceApiClient(true) if you need to log API communication messages.
        WooCommerceApiClient apiClient = new WooCommerceApiClient();

        // TODO: Set your host and credentials!
        apiClient.setBasePath("https://your-woocommerce-shop.com/wp-json/wc/v3");
        apiClient.setUsername("TODO_SET_API_USERNAME");
        apiClient.setPassword("TODO_SET_API_PASSWORD");

        CustomersApi customersApi = new CustomersApi(apiClient);

        try {

            List<Customer> customers = customersApi.listAllCustomers(null, null, null, null, null, null, null, null, null, null, null, null, null);

            // Example list of customer emails:
            for (Customer customer : customers) {
                System.out.println("Customer: " + customer.getEmail());
            }

        } catch (ApiException e) {
            System.err.println("Error occurred during API call: " + e);
        }

        System.out.println("<<< The WooCommerceApiClientUsageDemo has been finished.");

    }

}
