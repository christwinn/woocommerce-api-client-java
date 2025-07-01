/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import pl.wtx.woocommerce.api.client.model.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import pl.wtx.woocommerce.crudPlusActionBuilder.transportation.Http;

public class WooCommerce {

    public WooCommerce() {
        /*apiClient.setUsername(Configuration.getKey());
        apiClient.setPassword(Configuration.getSecret());*/
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
                Configuration.isDebug() ? "http:" : "https://" +
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

    private String getTarget(){
        return (!Configuration.getWebsite().startsWith("http") ? "https://" : "") +
            Configuration.getWebsite();
    }

    private String getEndPoint(String endPoint){
        return Configuration.getApi() +
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

    private ApiResponseResult read(String endPoint, TypeReference<?> type){

        return http.read(
            getUri(endPoint),
            getHeaders(),
            type
        );

    }

    private ApiResponseResult read(String endPoint, String parameters, TypeReference<?> type){

        return http.read(
            getUri(endPoint)
                + (!parameters.equals("")
                ? "?" + parameters
                : "")
            ,
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

    private <T> ApiResponseResult delete(String endPoint, TypeReference<?> type){

        return delete(endPoint, type);

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
     * Searchers go through here
     *
     * @param endPoint
     * @param parameters
     * @param type
     * @return
     */
    public ApiResponseResult search(String endPoint, String parameters, TypeReference<?> type){

        Logger.getLogger(WooCommerce.class.getName()).log(Level.INFO, parameters);

        return read(endPoint, parameters, type);

    }

    //<editor-fold desc="Customer">
    public CustomerResponse create(CustomerRequest request) {
        return new CustomerResponse(
            create(request.endPoint(), request.toJson(), new TypeReference<Customer>(){})
        );
    }

    public CustomerResponse read(CustomerRequest request){

        return new CustomerResponse(
            read(request.endPoint(), new TypeReference<Customer>(){})
        );

    }
    public CustomerResponse update(CustomerRequest request){
        return new CustomerResponse(
            update(request.endPoint(), request.toJson(), new TypeReference<Customer>(){})
        );

    }
    public CustomerResponse delete(CustomerRequest request){
        return new CustomerResponse(
            delete(request.endPoint(), new TypeReference<Customer>(){})
        );

    }

    public CustomerResponse batch(CustomerRequest batch){

        return new CustomerResponse(
            create(batch.endPoint(), batch.toJson(), new TypeReference<List<Customer>>(){})
        );

    }
    //</editor-fold>

    //<editor-fold desc="Customer Downloads">
    public CustomerDownloadsResponse read(CustomerDownloadsRequest request){

        return new CustomerDownloadsResponse(
            read(request.endPoint(), new TypeReference<List<Download>>(){})
        );

    }
    //</editor-fold>

    //<editor-fold desc="Orders">
    public OrderResponse create(OrderRequest request) {
        return new OrderResponse(
            create(request.endPoint(), request.toJson(), new TypeReference<Order>(){})
        );
    }

    public OrderResponse read(OrderRequest request){

        return new OrderResponse(
            read(request.endPoint(), new TypeReference<Order>(){})
        );

    }
    public OrderResponse update(OrderRequest request){
        return new OrderResponse(
            update(request.endPoint(), request.toJson(), new TypeReference<Order>(){})
        );

    }
    public OrderResponse delete(OrderRequest request){
        return new OrderResponse(
            delete(request.endPoint(), new TypeReference<Order>(){})
        );

    }

    public OrderResponse batch(OrderRequest batch){

        return new OrderResponse(
            create(batch.endPoint(), batch.toJson(), new TypeReference<List<Order>>(){})
        );

    }
    //</editor-fold>

    //<editor-fold desc="OrderNotes">
    public OrderNoteResponse create(OrderNoteRequest request) {
        return new OrderNoteResponse(
            create(request.endPoint(), request.toJson(), new TypeReference<OrderNote>(){})
        );
    }

    public OrderNoteResponse read(OrderNoteRequest request){

        return new OrderNoteResponse(
            read(request.endPoint(), new TypeReference<OrderNote>(){})
        );

    }
    //no update available
    public OrderNoteResponse delete(OrderNoteRequest request){
        return new OrderNoteResponse(
            delete(request.endPoint(), new TypeReference<OrderNote>(){})
        );

    }

    public OrderNoteResponse listAll(OrderNoteRequest listAll){

        return new OrderNoteResponse(
            read(listAll.endPoint(), new TypeReference<List<OrderNote>>(){})
        );

    }
    //</editor-fold>

    //<editor-fold desc="OrderRefunds">
    public OrderRefundResponse create(OrderRefundRequest request) {
        return new OrderRefundResponse(
            create(request.endPoint(), request.toJson(), new TypeReference<OrderRefund>(){})
        );
    }

    public OrderRefundResponse read(OrderRefundRequest request){

        return new OrderRefundResponse(
            read(request.endPoint(), new TypeReference<OrderRefund>(){})
        );

    }
    //no update available
    public OrderRefundResponse delete(OrderRefundRequest request){
        return new OrderRefundResponse(
            delete(request.endPoint(), new TypeReference<OrderRefund>(){})
        );

    }

    public OrderRefundResponse listAll(OrderRefundRequest listall){

        return new OrderRefundResponse(
            read(listall.endPoint(), new TypeReference<List<OrderRefund>>(){})
        );

    }
    //</editor-fold>

    //<editor-fold desc="ProductCategory">
    public ProductCategoryResponse create(ProductCategoryRequest request){

        return new ProductCategoryResponse(
            create(request.endPoint(), request.toJson(),new TypeReference<ProductCategory>(){})
        );

        /*try{

            return new ProductCategoryResponse(
                apiClient.execute(
                    create(
                        getEndPoint(request.endPoint()),
                        request.toJson()
                    ),
                    new TypeToken<ProductCategory>(){}.getType()
                )
            );

        }catch(ApiException e) {

            return new ProductCategoryResponse(
                new ApiResponseResult(false, 0, e.getMessage())
            );

        }*/

    }

    public ProductCategoryResponse read(ProductCategoryRequest request){

        return new ProductCategoryResponse(
            read(
                request.endPoint(),
                new TypeReference<ProductCategory>(){})
        );

    }
    public ProductCategoryResponse update(ProductCategoryRequest request){

        return new ProductCategoryResponse(
            update(request.endPoint(), request.toJson(), new TypeReference<ProductCategory>(){})
        );

    }
    public ProductCategoryResponse delete(ProductCategoryRequest request){

        return new ProductCategoryResponse(
            delete(request.endPoint(), new TypeReference<ProductCategory>(){})
        );

    }
    //</editor-fold>

    //<editor-fold desc="Product">
    public ProductResponse create(ProductRequest request) {
        return new ProductResponse(
            create(request.endPoint(), request.toJson(), new TypeReference<Product>(){})
        );
    }

    public ProductResponse read(ProductRequest request){

        return new ProductResponse(
            read(request.endPoint(), new TypeReference<Product>(){})
        );

    }
    public ProductResponse update(ProductRequest request){
        return new ProductResponse(
            update(request.endPoint(), request.toJson(), new TypeReference<Product>(){})
        );

    }
    public ProductResponse delete(ProductRequest request){
        return new ProductResponse(
            delete(request.endPoint(), new TypeReference<Product>(){})
        );

    }

    public ProductResponse batch(ProductRequest batch){

        return new ProductResponse(
            create(batch.endPoint(), batch.toJson(), new TypeReference<List<Product>>(){})
        );

    }
    //</editor-fold>

}
