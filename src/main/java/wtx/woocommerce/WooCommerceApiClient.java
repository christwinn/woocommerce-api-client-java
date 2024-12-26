package wtx.woocommerce;

import wtx.woocommerce.api.client.config.OkHttpClientConfig;
import wtx.woocommerce.api.client.config.GsonConfig;
import wtx.woocommerce.api.client.invoker.ApiClient;

public class WooCommerceApiClient extends ApiClient {

    // Default setup of the API Client to provide ignoring not recognized fields in the response.
    public WooCommerceApiClient() {
        getJSON().setGson(GsonConfig.createGson());
        setUserAgent("WooCommerceApiClient (by wtx-labs)");
    }

    // Default configuration improved with logging API communication messages.
    public WooCommerceApiClient(boolean enableLoggingApiMessages) {

        getJSON().setGson(GsonConfig.createGson());
        setUserAgent("WooCommerceApiClient (by wtx-labs)");

        if (enableLoggingApiMessages) {
            setHttpClient(OkHttpClientConfig.configureClient(getHttpClient()));
        }

    }

}
