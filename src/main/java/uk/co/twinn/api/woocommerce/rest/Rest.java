/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.co.twinn.api.woocommerce.transportation.Http;

/**
 *
 * Transferring the Json that represents our request/response!
 *
**/
public class Rest<T> {

    public Rest() {
    }

   private final Http<T> http = new Http<>();

    //set the type of response and add basic auth, requires us to be https to work
    private List<NameValuePair> getHeaders() {

        List<NameValuePair> urlParameters = new ArrayList<>();

        String base64 = Configuration.getKey() + ":" + Configuration.getSecret();
        byte[] encoded = java.util.Base64.getEncoder().encode(base64.getBytes());

        String credentials = new String(encoded);
        urlParameters.add(new BasicNameValuePair("Authorization", "Basic " + credentials));
        urlParameters.add(new BasicNameValuePair("content-type", "application/json"));

        return urlParameters;

    }

    private String getUri(String endPoint){

        Logger.getLogger(
            Rest.class.getName()).log(
                Level.INFO,
                "https://" +
                Configuration.getWebsite() +
                Configuration.getApi() +
                endPoint
        );

        return
            //Configuration.isDebug() ? "http:" :
            (!Configuration.getWebsite().startsWith("http") ? "https://" : "") +
            Configuration.getWebsite() +
            Configuration.getApi() +
            endPoint;

    }

    /*ET PHONE HOME, Making a call to the mothership*/
    /**
     *
     * Make a null content POST request, not usual oddball case
     *
     */
    public ApiResponseResult<T> create(String endPoint, TypeReference<?> type){

        try {

            CompletableFuture<ApiResponseResult<T>> future =
                CompletableFuture.supplyAsync(() ->
                    http.create(
                        getUri(endPoint),
                        getHeaders(),
                        null,
                        type
                    )
                );

            return future.get();

        }catch(InterruptedException e){

            return new ApiResponseResult<T>(false, 0, e.toString());

        }catch(ExecutionException ee){

            return new ApiResponseResult<T>(false, 0, ee.toString());

        }

    }

    public ApiResponseResult<T> create(String endPoint, String content, TypeReference<?> type){

        try {

            CompletableFuture<ApiResponseResult<T>> future =
                CompletableFuture.supplyAsync(() ->
                    http.create(
                        getUri(endPoint),
                        getHeaders(),
                        content,
                        type
                    )
                ).handle((s, t) -> s != null
                    ? s :
                    new ApiResponseResult<T>(false, 0, t.toString())
                );

            return future.get();

        }catch(InterruptedException e){

            return new ApiResponseResult<T>(false, 0, e.toString());

        }catch(ExecutionException ee){

            return new ApiResponseResult<T>(false, 0, ee.toString());

        }

    }

    public ApiResponseResult<T> read(String endPoint, TypeReference<?> type){

        try {

            CompletableFuture<ApiResponseResult<T>> future =
                CompletableFuture.supplyAsync(() ->
                    http.read(
                        getUri(endPoint),
                        getHeaders(),
                        type
                    )
                );

            return future.get();

        }catch(InterruptedException e){

            return new ApiResponseResult<T>(false, 0, e.toString());

        }catch(ExecutionException ee){

            return new ApiResponseResult<T>(false, 0, ee.toString());

        }

    }

    private ApiResponseResult<T> read(String endPoint, String parameters, TypeReference<?> type){

        try {

            CompletableFuture<ApiResponseResult<T>> future =
                CompletableFuture.supplyAsync(() ->
                    http.read(
                    getUri(endPoint)
                        + (!parameters.isEmpty()
                            ? "?" + parameters
                            : ""
                        ),
                        getHeaders(),
                        type
                    )
                );

            return future.get();

        }catch(InterruptedException e){

            return new ApiResponseResult<T>(false, 0, e.toString());

        }catch(ExecutionException ee){

            return new ApiResponseResult<T>(false, 0, ee.toString());

        }

    }

    public ApiResponseResult<T> update(String endPoint, String content, TypeReference<?> type){

        try {

            CompletableFuture<ApiResponseResult<T>> future =
                CompletableFuture.supplyAsync(() ->
                    http.update(
                        getUri(endPoint),
                        getHeaders(),
                        content,
                        type
                    )
                );

            return future.get();

        }catch(InterruptedException e){

            return new ApiResponseResult<T>(false, 0, e.toString());

        }catch(ExecutionException ee){

            return new ApiResponseResult<T>(false, 0, ee.toString());

        }

    }

    public ApiResponseResult<T> updateList(String endPoint, String content, TypeReference<?> type){

        try {

            CompletableFuture<ApiResponseResult<T>> future =
                CompletableFuture.supplyAsync(() ->
                    http.update(
                        getUri(endPoint),
                        getHeaders(),
                        content,
                        type
                    )
                );

            return future.get();

        }catch(InterruptedException e){

            return new ApiResponseResult<T>(false, 0, e.toString());

        }catch(ExecutionException ee){

            return new ApiResponseResult<T>(false, 0, ee.toString());

        }

    }

    public ApiResponseResult<T> delete(String endPoint, TypeReference<?> type){

        try {

            CompletableFuture<ApiResponseResult<T>> future =
                CompletableFuture.supplyAsync(() ->
                    http.delete(endPoint, getHeaders(), type)
                );

            return future.get();

        }catch(InterruptedException e){

            return new ApiResponseResult<T>(false, 0, e.toString());

        }catch(ExecutionException ee){

            return new ApiResponseResult<T>(false, 0, ee.toString());

        }

    }

    public ApiResponseResult<T> duplicate(String endPoint, TypeReference<?> type){

        return create(endPoint, null, type);

    }

    public ApiResponseResult<T> batch(String endPoint, String content, TypeReference<?> type){

        return create(endPoint, content, type);

    }
    /*ET PHONED HOME, Call finished*/



    /**
     *
     * @param endPoint where is the target
     * @param parameters the parameters for the search
     * @return ApiResponseResult
     */
    public ApiResponseResult<T> listAll(String endPoint, String parameters, TypeReference<?> type){

        //Logger.getLogger(Rest.class.getName()).log(Level.INFO, parameters);

        return read(endPoint, parameters, type);

    }

}
