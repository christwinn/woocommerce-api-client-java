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
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Configuration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * Currently using HttpClients
 * maybe should pass to CompletableFutures and/or ASync
 *
 */
public class Http {

    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    private final JacksonObjectMapper json = new JacksonObjectMapper();

    private ApiResponseResult parseResponse(CloseableHttpResponse response, TypeReference<?> typeReference){

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
                        return new ApiResponseResult(
                            true,
                            sl.getStatusCode(),
                            json.getObjectMapper().readValue(r, typeReference));
                    }else {
                        return new ApiResponseResult(
                            true,
                            sl.getStatusCode(),
                            json.getObjectMapper().readValue(EntityUtils.toString(response.getEntity()), typeReference));
                    }
                default:
                    if (Configuration.isDebug()){
                        String r = EntityUtils.toString(response.getEntity());
                        Logger.getLogger(Http.class.getName()).log(
                            Level.INFO,
                            r.replace(",", ",\n")
                        );
                        return new ApiResponseResult(false, sl.getStatusCode(), r);
                    }else {
                        return new ApiResponseResult(false, sl.getStatusCode(), EntityUtils.toString(response.getEntity()));
                    }
            }

        } catch (IOException e) {

            Logger.getLogger(Http.class.getName())
                .log(Level.SEVERE, "IOException", e);

            return new ApiResponseResult(false, 0, e.toString());

        }

    }

    public ApiResponseResult create(String target, List<NameValuePair> headers, String content, TypeReference<?> typeReference){

        try {

            HttpPost post = getHttpPost(target, headers, content);

            try (CloseableHttpResponse response = httpClient.execute(post)) {

                return parseResponse(response, typeReference);

            }

        } catch (SocketTimeoutException e) {

            return new ApiResponseResult(false, 0, e.toString());

        } catch (IOException e) {

            return new ApiResponseResult(false, 0, e.toString());

        }

    }

    @NotNull
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

    public ApiResponseResult read(String target, List<NameValuePair> headers, TypeReference<?> typeReference){

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

                return parseResponse(response, typeReference);

            }

        } catch (SocketTimeoutException e) {

            return new ApiResponseResult(false, 0, e.toString());

        } catch (IOException e) {

            return new ApiResponseResult(false, 0, e.toString());

        }

    }

    public ApiResponseResult update(String target, List<NameValuePair> headers, String content, TypeReference<?> typeReference){

        try {

            HttpPut put = getHttpPut(target, headers, content);

            try (CloseableHttpResponse response = httpClient.execute(put)) {

                return parseResponse(response, typeReference);

            }

        } catch (SocketTimeoutException e) {

            return new ApiResponseResult(false, 0, e.toString());

        } catch (IOException e) {

            return new ApiResponseResult(false, 0, e.toString());

        }

    }
    @NotNull
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

    public ApiResponseResult delete(String target, List<NameValuePair> headers, TypeReference<?> typeReference){

        try {

            HttpDelete post = new HttpDelete(target);
            if (!headers.isEmpty()){
                for (NameValuePair nvp : headers){
                    post.setHeader(nvp.getName(), nvp.getValue());
                }
            }

            try (CloseableHttpResponse response = httpClient.execute(post)) {

                return parseResponse(response, typeReference);

            }

        } catch (SocketTimeoutException e) {

            return new ApiResponseResult(false, 0, e.toString());

        } catch (IOException e) {

            return new ApiResponseResult(false, 0, e.toString());

        }

    }

}
