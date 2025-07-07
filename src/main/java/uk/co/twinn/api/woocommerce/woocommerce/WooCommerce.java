/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.woocommerce;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import uk.co.twinn.api.woocommerce.request.*;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.co.twinn.api.woocommerce.transportation.Http;
import uk.co.twinn.pl_wtx_woocommerce.model.*;

public class WooCommerce {

    public WooCommerce() {
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
            WooCommerce.class.getName()).log(
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

        Logger.getLogger(WooCommerce.class.getName()).log(Level.INFO, parameters);

        return read(endPoint, parameters, type);

    }

    //<editor-fold desc="Coupon">
    public Created<Coupon> create(CouponRequest request) {
        return new Created<Coupon>(
            create(request.endPoint(), request.toJson(), new TypeReference<Coupon>(){})
        );
    }

    public Read<Coupon> read(CouponRequest request){

        return new Read<Coupon>(
            read(request.endPoint(), new TypeReference<Coupon>(){})
        );

    }
    public Updated<Coupon> update(CouponRequest request){
        return new Updated<Coupon>(
            update(request.endPoint(), request.toJson(), new TypeReference<Coupon>(){})
        );

    }
    public Deleted<Coupon> delete(CouponRequest request){
        return new Deleted<Coupon>(
            delete(request.endPoint(), new TypeReference<Coupon>(){})
        );

    }

    public Batched<Coupon> batch(CouponRequest batch){
        return new Batched<Coupon>(
            create(batch.endPoint(), batch.toJson(), new TypeReference<Batch<Coupon>>(){})
        );
    }
    //</editor-fold>

    //<editor-fold desc="Customer">
    public Created<Customer> create(CustomerRequest request) {
        return new Created<Customer>(
            create(request.endPoint(), request.toJson(), new TypeReference<Customer>(){})
        );
    }

    public Read<Customer> read(CustomerRequest request){
        return new Read<Customer>(
            read(request.endPoint(), new TypeReference<Customer>(){})
        );
    }
    public Updated<Customer> update(CustomerRequest request){
        return new Updated<Customer>(
            update(request.endPoint(), request.toJson(), new TypeReference<Customer>(){})
        );

    }
    public Deleted<Customer> delete(CustomerRequest request){
        return new Deleted<Customer>(
            delete(request.endPoint(), new TypeReference<Customer>(){})
        );

    }

    public Batched<Customer> batch(CustomerRequest batch){

        return new Batched<Customer>(
            create(batch.endPoint(), batch.toJson(), new TypeReference<Batch<Customer>>(){})

        );

    }
    //</editor-fold>

    //<editor-fold desc="Orders">
    public Created<Order> create(OrderRequest request) {
        return new Created<Order>(
            create(request.endPoint(), request.toJson(), new TypeReference<Order>(){})
        );
    }

    public Read<Order> read(OrderRequest request){

        return new Read<Order>(
            read(request.endPoint(), new TypeReference<Order>(){})
        );

    }
    public Updated<Order> update(OrderRequest request){
        return new Updated<Order>(
            update(request.endPoint(), request.toJson(), new TypeReference<Order>(){})
        );

    }
    public Deleted<Order> delete(OrderRequest request){
        return new Deleted<Order>(
            delete(request.endPoint(), new TypeReference<Order>(){})
        );

    }

    public Batched<Order> batch(OrderRequest batch){
        return new Batched<Order>(
            create(batch.endPoint(), batch.toJson(), new TypeReference<List<Order>>(){})
        );
    }
    //</editor-fold>

    //<editor-fold desc="OrderNotes">
    public Created<OrderNote> create(OrderNoteRequest request) {
        return new Created<OrderNote>(
            create(request.endPoint(), request.toJson(), new TypeReference<OrderNote>(){})
        );
    }

    public Read<OrderNote> read(OrderNoteRequest request){

        return new Read<OrderNote>(
            read(request.endPoint(), new TypeReference<OrderNote>(){})
        );

    }
    //no update available
    public Deleted<OrderNote> delete(OrderNoteRequest request){
        return new Deleted<OrderNote>(
            delete(request.endPoint(), new TypeReference<OrderNote>(){})
        );

    }
    //</editor-fold>

    //<editor-fold desc="OrderRefunds">
    public Created<OrderRefund> create(OrderRefundRequest request) {
        return new Created<OrderRefund>(
            create(request.endPoint(), request.toJson(), new TypeReference<OrderRefund>(){})
        );
    }

    public Read<OrderRefund> read(OrderRefundRequest request){

        return new Read<OrderRefund>(
            read(request.endPoint(), new TypeReference<OrderRefund>(){})
        );

    }
    //no update available
    public Deleted<OrderRefund> delete(OrderRefundRequest request){
        return new Deleted<OrderRefund>(
            delete(request.endPoint(), new TypeReference<OrderRefund>(){})
        );

    }

    //</editor-fold>

    //<editor-fold desc="ProductCategory">
    public Created<ProductCategory> create(ProductCategoryRequest request){

        return new Created<ProductCategory>(
            create(request.endPoint(), request.toJson(),new TypeReference<ProductCategory>(){})
        );

    }

    public Read<ProductCategory> read(ProductCategoryRequest request){

        return new Read<ProductCategory>(
            read(
                request.endPoint(),
                new TypeReference<ProductCategory>(){})
        );

    }
    public Updated<ProductCategory> update(ProductCategoryRequest request){

        if (Configuration.isDebug()){
            Logger.getLogger(
                WooCommerce.class.getName()).log(
                Level.INFO,
                request.toJson()
            );
        }
        return new Updated<ProductCategory>(
            update(request.endPoint(), request.toJson(), new TypeReference<ProductCategory>(){})
        );

    }
    public Deleted<ProductCategory> delete(ProductCategoryRequest request){

        return new Deleted<ProductCategory>(
            delete(request.endPoint(), new TypeReference<ProductCategory>(){})
        );

    }

    public Batched<ProductCategory> batch(ProductCategoryRequest batch){
        return new Batched<ProductCategory>(
            create(batch.endPoint(), batch.toJson(),new TypeReference<List<ProductCategory>>(){})
        );
    }
    //</editor-fold>

    //<editor-fold desc="Product">
    public Created<Product> create(ProductRequest request){

        return new Created<Product>(
            create(request.endPoint(), request.toJson(), new TypeReference<Product>(){})
        );

    }
    /*public ProductResponse create(ProductRequest request) {
        return new ProductResponse(
            create(request.endPoint(), request.toJson(), new TypeReference<Product>(){})
        );
    }*/

    public Read<Product> read(ProductRequest request){

        return new Read<Product>(
            read(request.endPoint(), new TypeReference<Product>(){})

        );

    }

    /*public ProductResponse read(ProductRequest request){

        return new ProductResponse(
            read(request.endPoint(), new TypeReference<Product>(){})
        );

    }*/
    public Updated<Product> update(ProductRequest request){
        return new Updated<Product>(
            update(request.endPoint(), request.toJson(), new TypeReference<Product>(){})
        );

    }
    public Deleted<Product> delete(ProductRequest request){
        return new Deleted<Product>(
            delete(request.endPoint(), new TypeReference<Product>(){})
        );

    }

    public Duplicated duplicate(ProductRequest batch){

        return new Duplicated<Product>(
            create(batch.endPoint(), batch.toJson(), new TypeReference<Batch<Product>>(){})
        );

    }

    public Batched batch(ProductRequest batch){

        return new Batched<Product>(
            create(batch.endPoint(), batch.toJson(), new TypeReference<Batch<Product>>(){})
        );

    }
    //</editor-fold>

}
