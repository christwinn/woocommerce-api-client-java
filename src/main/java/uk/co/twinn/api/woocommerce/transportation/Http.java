/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.transportation;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.annotation.Nonnull;

import uk.co.twinn.api.woocommerce.builders.CoreParameterCollector;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Configuration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * Currently using HttpClients
 * maybe should pass to CompletableFutures and/or ASync
 *
 */
public class Http<T> {

    private static CloseableHttpClient getHttpClient(){

        int connectTimeout = 5;
        int requestTimeout = 60;
        int socketTimeout = 5;

        RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(connectTimeout * 1000)
            .setConnectionRequestTimeout(requestTimeout * 1000)
            .setSocketTimeout(socketTimeout * 1000).build();

        return HttpClientBuilder.create().setDefaultRequestConfig(config).build();

    }

    private static final CloseableHttpClient httpClient = getHttpClient();

    private final JacksonObjectMapper json = new JacksonObjectMapper();

    public Http() {
    }



    @SuppressWarnings("unchecked")
    private ApiResponseResult<T> parseResponse(String content, int statusCode, TypeReference<?> type) {
        try {
            return new ApiResponseResult<>(
                true,
                statusCode,
                (T) json.getObjectMapper().readValue(content, type)
            );
        }catch(IOException e){
            Logger.getLogger(Http.class.getName()).log(
                Level.SEVERE,
                "ParseResponseFailure",
                e
            );
            return new ApiResponseResult<>(false, 0, e.toString());
        }

    }

    private ApiResponseResult<T> parseResponse(CloseableHttpResponse response, TypeReference<?> type){

        Logger.getLogger(Http.class.getName()).log(Level.INFO,"parseResponse");

        try {

            StatusLine sl = response.getStatusLine();

            switch (sl.getStatusCode()) {
                case 200:
                case 201:
                    if (Configuration.isDebug()){
                        String r = EntityUtils.toString(response.getEntity());
                        Logger.getLogger(Http.class.getName()).log(
                            Level.INFO,
                            r.replace("},{", "},\n{")
                        );
                        return parseResponse(r, sl.getStatusCode(), type);
                        /*return new ApiResponseResult<>(
                            true,
                            sl.getStatusCode(),
                            (T)json.getObjectMapper().readValue(r, type)
                        );*/
                    }else {
                        return parseResponse(EntityUtils.toString(response.getEntity()), sl.getStatusCode(), type);
                        /*return new ApiResponseResult<>(
                            true,
                            sl.getStatusCode(),
                            (T)json.getObjectMapper().readValue(EntityUtils.toString(response.getEntity()), type));*/
                    }
                default:
                    if (Configuration.isDebug()){
                        String r = EntityUtils.toString(response.getEntity());
                        Logger.getLogger(Http.class.getName()).log(
                            Level.INFO,
                            r.replace(",", ",\n")
                        );
                        return new ApiResponseResult<>(false, sl.getStatusCode(), r);
                    }else {
                        return new ApiResponseResult<>(false, sl.getStatusCode(), EntityUtils.toString(response.getEntity()));
                    }
            }

        } catch (IOException e) {

            Logger.getLogger(Http.class.getName())
                .log(Level.SEVERE, "IOException", e);

            return new ApiResponseResult<>(false, 0, e.toString());

        }

    }

    public ApiResponseResult<T> create(String target, List<NameValuePair> headers, String content, TypeReference<?> type){

        try {

            HttpPost post = getHttpPost(target, headers, content);

            try (CloseableHttpResponse response = httpClient.execute(post)) {

                return parseResponse(response, type);

            }

        } catch (IOException e) {

            Logger.getLogger(Http.class.getName())
                .log(Level.SEVERE, "create->IOException", e
            );

            return new ApiResponseResult<>(false, 0, e.toString());

        }

    }

    @Nonnull
    private static HttpPost getHttpPost(String target, List<NameValuePair> headers, String content) throws UnsupportedEncodingException {

        HttpPost post = new HttpPost(target);

        if (!headers.isEmpty()){
            for (NameValuePair nvp : headers){
                post.setHeader(nvp.getName(), nvp.getValue());
            }
        }
            /*if (!entity.isEmpty()){
                post.setEntity(new UrlEncodedFormEntity(entity, "UTF-8"));
            }else*/
        if (content != null){
            if (!content.isEmpty()){
                post.setEntity(new StringEntity(content));
            }
        }

        return post;

    }

    public ApiResponseResult<T> read(String target, List<NameValuePair> headers, TypeReference<?> type){

        Logger.getLogger(Http.class.getName()).log(Level.INFO,
            String.format("Requesting %s", target)
        );

        try {

            HttpGet get = new HttpGet(target);

            if (!headers.isEmpty()){
                for (NameValuePair nvp : headers){
                    get.setHeader(nvp.getName(), nvp.getValue());
                }
            }

            try (CloseableHttpResponse response = httpClient.execute(get)) {

                return parseResponse(response, type);

            }

        } catch (IOException e) {

            Logger.getLogger(Http.class.getName())
                .log(Level.SEVERE, "read->IOException", e
            );

            return new ApiResponseResult<>(false, 0, e.toString());

        }

    }

    public ApiResponseResult<T> update(String target, List<NameValuePair> headers, String content, TypeReference<?> type){

        try {

            HttpPut put = getHttpPut(target, headers, content);

            try (CloseableHttpResponse response = httpClient.execute(put)) {

                return parseResponse(response, type);

            }


        } catch (IOException e) {

            Logger.getLogger(Http.class.getName())
                .log(Level.SEVERE, "update->IOException", e
            );

            return new ApiResponseResult<>(false, 0, e.toString());

        }

    }
    @Nonnull
    private static HttpPut getHttpPut(String target, List<NameValuePair> headers, String content) throws UnsupportedEncodingException {

        HttpPut put = new HttpPut(target);

        if (!headers.isEmpty()){
            for (NameValuePair nvp : headers){
                put.setHeader(nvp.getName(), nvp.getValue());
            }
        }
            /*if (!entity.isEmpty()){
                post.setEntity(new UrlEncodedFormEntity(entity, "UTF-8"));
            }else*/
        if (content != null){
            if (!content.isEmpty()){
                put.setEntity(new StringEntity(content));
            }
        }

        return put;

    }

    public ApiResponseResult<T> delete(String target, List<NameValuePair> headers, TypeReference<?> type){

        try {

            HttpDelete post = new HttpDelete(target);
            if (!headers.isEmpty()){
                for (NameValuePair nvp : headers){
                    post.setHeader(nvp.getName(), nvp.getValue());
                }
            }

            try (CloseableHttpResponse response = httpClient.execute(post)) {

                return parseResponse(response, type);

            }

        } catch (IOException e) {

            Logger.getLogger(Http.class.getName())
                .log(Level.SEVERE, "delete->IOException", e
            );

            return new ApiResponseResult<>(false, 0, e.toString());

        }

    }

}
