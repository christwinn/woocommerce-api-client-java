package pl.wtx.woocommerce.api.client.config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpClientConfig {

    // Logging configuration.
    public static OkHttpClient configureClient(OkHttpClient defaultClient) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return defaultClient.newBuilder().addInterceptor(loggingInterceptor).build();

    }

}
