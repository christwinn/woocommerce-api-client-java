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
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.request.*;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.co.twinn.api.woocommerce.transportation.Http;
import uk.co.twinn.pl_wtx_woocommerce.model.*;

public class Rest {

    public Rest() {
    }

   private final Http http = new Http();

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
    private ApiResponseResult create(String endPoint, String content, TypeReference<?> type){

        return http.create(
            getUri(endPoint),
            getHeaders(),
            content,
            type
        );

    }

    public ApiResponseResult read(String endPoint, TypeReference<?> type){

        return http.read(
            getUri(endPoint),
            getHeaders(),
            type
        );

    }

    private ApiResponseResult read(String endPoint, String parameters, TypeReference<?> type){

        return http.read(
        getUri(endPoint)
            + (!parameters.isEmpty()
                ? "?" + parameters
                : ""
            ),
            getHeaders(),
            type
        );

    }

    private ApiResponseResult update(String endPoint, String content, TypeReference<?> type){

        return http.update(
            getUri(endPoint),
            getHeaders(),
            content,
            type
        );

    }

    public ApiResponseResult delete(String endPoint, TypeReference<?> type){

        return http.delete(endPoint, getHeaders(), type);

    }

    public ApiResponseResult duplicate(String endPoint, TypeReference<?> type){

        return create(endPoint, null, type);

    }

    public ApiResponseResult batch(String endPoint, String content, TypeReference<?> type){

        return create(endPoint, content, type);

    }
    /*ET PHONED HOME, Call finished*/

    /**
     *
     * Make a null content POST request, not usual oddball case
     *
     */
    public ApiResponseResult create(String endPoint, TypeReference<?> type){

        return http.create(
            getUri(endPoint),
            getHeaders(),
            null,
            type
        );

    }

    /**
     *
     * @param endPoint where is the target
     * @param parameters the parameters for the search
     * @param type return list type
     * @return ApiResponseResult
     */
    public ApiResponseResult listAll(String endPoint, String parameters, TypeReference<?> type){

        Logger.getLogger(Rest.class.getName()).log(Level.INFO, parameters);

        return read(endPoint, parameters, type);

    }

    //<editor-fold desc="Coupon">
    public Created<Coupon> create(CouponRequest request) {
        return new Created<Coupon>(
            create(request.endPoint(), request.toJson(), new TypeReference<Coupon>(){})
        );
    }

    public Updated<Coupon> update(CouponRequest request){
        return new Updated<Coupon>(
            update(request.endPoint(), request.toJson(), new TypeReference<Coupon>(){})
        );

    }

    //</editor-fold>

    //<editor-fold desc="Customer">
    public Created<Customer> create(CustomerRequest request) {
        return new Created<Customer>(
            create(request.endPoint(), request.toJson(), new TypeReference<Customer>(){})
        );
    }

    public Updated<Customer> update(CustomerRequest request){
        return new Updated<Customer>(
            update(request.endPoint(), request.toJson(), new TypeReference<Customer>(){})
        );

    }

    //</editor-fold>

    //<editor-fold desc="Orders">
    public Created<Order> create(OrderRequest request) {
        return new Created<Order>(
            create(request.endPoint(), request.toJson(), new TypeReference<Order>(){})
        );
    }

    public Updated<Order> update(OrderRequest request){
        return new Updated<Order>(
            update(request.endPoint(), request.toJson(), new TypeReference<Order>(){})
        );

    }

    //</editor-fold>

    //<editor-fold desc="OrderNotes">
    public Created<OrderNote> create(OrderNoteRequest request) {
        return new Created<OrderNote>(
            create(request.endPoint(), request.toJson(), new TypeReference<OrderNote>(){})
        );
    }
    //</editor-fold>

    //<editor-fold desc="OrderRefunds">
    public Created<OrderRefund> create(OrderRefundRequest request) {
        return new Created<OrderRefund>(
            create(request.endPoint(), request.toJson(), new TypeReference<OrderRefund>(){})
        );
    }
    //</editor-fold>

    //<editor-fold desc="ProductCategory">
    public Created<ProductCategory> create(ProductCategoryRequest request){

        return new Created<ProductCategory>(
            create(request.endPoint(), request.toJson(),new TypeReference<ProductCategory>(){})
        );

    }

    public Updated<ProductCategory> update(ProductCategoryRequest request){

        if (Configuration.isDebug()){
            Logger.getLogger(
                Rest.class.getName()).log(
                Level.INFO,
                request.toJson()
            );
        }
        return new Updated<ProductCategory>(
            update(request.endPoint(), request.toJson(), new TypeReference<ProductCategory>(){})
        );

    }

    //</editor-fold>

    //<editor-fold desc="Product">
    public Created<Product> create(ProductRequest request){

        return new Created<Product>(
            create(request.endPoint(), request.toJson(), new TypeReference<Product>(){})
        );

    }

    public Updated<Product> update(ProductRequest request){
        return new Updated<Product>(
            update(request.endPoint(), request.toJson(), new TypeReference<Product>(){})
        );

    }

    //</editor-fold>

}
