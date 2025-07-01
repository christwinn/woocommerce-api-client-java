/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import pl.wtx.woocommerce.api.client.model.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.ApiRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.Seek;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.OrderResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.WooCommerce;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static pl.wtx.woocommerce.crudPlusActionBuilder.defines.EndPoints.ORDERS;

public class OrderRequest extends ApiRequest {

    protected final Order order = new Order();

    private Batch batch;

    private boolean force;
    //private boolean duplicate;
    private boolean isBatch;

    public OrderRequest(Creator creator){

        order.setPaymentMethod(creator.paymentMethod);
        order.setPaymentMethodTitle(creator.paymentMethodTitle);
        order.setPaid(creator.paid);
        order.setBilling(creator.billing);
        order.setShipping(creator.shipping);
        order.setLineItems(creator.lineItems);
        order.setShippingLines(creator.shippingLines);

    }

    public OrderRequest(Reader reader){

        order.setId(reader.id);

    }

    public OrderRequest(Updater updater){

        this((Creator)updater);
        order.setId(updater.id);

    }

    public OrderRequest(Deleter deleter){

        this((Reader)deleter);
        isBatch = false;
        force = deleter.force;

    }

    public OrderRequest(Batcher batcher){

        batch = batcher.getBatch();
        force = false;
        isBatch = true;

    }

    public String endPoint(){

        return getEndPoint() +
            (order.getId() != null && order.getId() != 0
                ? ("/" + order.getId())
                : ""
            ) +
            (isBatch
                ? "/batch"
                : ""
            ) +
            (force
                ? "?force=true"
                : ""
            );

    }

    private static String getEndPoint(){

        return ORDERS;

    }

    public String toJson(){

        try {

            if (isBatch){
                return getObjectMapper().writeValueAsString(batch);
            }else{
                // covert Java object to JSON strings
                return getObjectMapper().writeValueAsString(order);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

        private String paymentMethod;
        private String paymentMethodTitle;
        private Boolean paid;
        private Billing billing;
        private Shipping shipping;
        private List<OrderLineItem> lineItems;
        private List<OrderShippingLine> shippingLines;

        T self() {
            return (T) this;
        }

        public T setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return self();
        }

        public T setPaymentMethodTitle(String paymentMethodTitle) {
            this.paymentMethodTitle = paymentMethodTitle;
            return self();
        }

        public T setPaid(Boolean paid) {
            this.paid = paid;
            return self();
        }

        public T setBilling(Billing billing) {
            this.billing = billing;
            return self();
        }

        public T setShipping(Shipping shipping) {
            this.shipping = shipping;
            return self();
        }

        public T setLineItems(List<OrderLineItem> lineItems) {
            this.lineItems = lineItems;
            return self();
        }

        public T setShippingLines(List<OrderShippingLine> shippingLines) {
            this.shippingLines = shippingLines;
            return self();
        }


        protected OrderRequest build(){
            return new OrderRequest(this);
        }


        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public OrderResponse getResponse(){
            WooCommerce woo = new WooCommerce();
            return woo.create(build());
        }



    }

    public static class Reader<T extends Reader<T>>{

        private int id;

        T self() {
            return (T) this;
        }

        public T setId(int id){
            this.id = id;
            return self();
        }

        protected OrderRequest build(){
            return new OrderRequest(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public OrderResponse getResponse(){
            if (id == 0) {
                return new OrderResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "CRUD is limited to a single object result\n" +
                            "Please set requested id\n" +
                            "Use the Searcher with no parameters to get a full list")
                );
            }else {
                WooCommerce woo = new WooCommerce();
                return woo.read(build());
            }
        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private int id;

        public T setId(int id){
            this.id = id;
            return self();
        }

        @Override
        protected OrderRequest build(){
            return new OrderRequest(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public OrderResponse getResponse(){
            WooCommerce woo = new WooCommerce();
            return woo.update(build());
        }

    }

    public static class Deleter<T extends Deleter<T>> extends Reader<T> {

        private boolean force;

        public T force(boolean force){
            this.force = force;
            return self();
        }

        @Override
        protected OrderRequest build(){
            return new OrderRequest(this);
        }

        /** Returns single Deleted ProductCategory**/
        @Override
        public OrderResponse getResponse(){
            WooCommerce woo = new WooCommerce();
            return woo.delete(build());
        }

    }

    private static class Batch{

        private final List<Order> create;
        private final List<Order> update;
        private final List<Order> delete;

        public Batch(){

            create = new ArrayList<>();
            update = new ArrayList<>();
            delete = new ArrayList<>();

        }

        public int getRecordCount(){
            return create.size() + update.size() + delete.size();
        }

        public boolean isEmpty(){
            return create.isEmpty() && update.isEmpty() && delete.isEmpty();
        }

        public void addCreator(Creator create){
            this.create.add(create.build().order);
        }

        public void addUpdater(Updater update){
            this.update.add(update.build().order);
        }

        public void addDeleter(Deleter delete){
            this.delete.add(delete.build().order);
        }

        public List<Order> getCreate(){
            return create;
        }
        public List<Order> getUpdate(){
            return update;
        }
        public List<Order> getDelete(){
            return delete;
        }

    }

    public static class Batcher<T extends Batcher>{

        private static Batch batch;

        public Batcher(){
            batch = new Batch();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator create){
            batch.addCreator(create);
            return self();
        }

        public T addUpdater(Updater update){
            batch.addUpdater(update);
            return self();
        }

        public T addDeleter(Deleter delete){
            batch.addDeleter(delete);
            return self();
        }

        private Batch getBatch(){
            return batch;
        }

        private OrderRequest build(){
            return new OrderRequest(this);
        }

        public OrderResponse getResponse(){

            if (batch.isEmpty()){

                return new OrderResponse(new ApiResponseResult(false, 0, "Nothing to do"));

            }else if (batch.getRecordCount() > 100){

                return new OrderResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "https://woocommerce.github.io/woocommerce-rest-api-docs/#batch-update-product-categories\n" +
                            "This API helps you to batch create, update and delete multiple products.\n\n" +
                            "Note: By default it's limited to up to 100 objects to be created, updated or deleted.")
                );

            }else{

                /** Returns list of amended ProductCategories **/
                return new WooCommerce().create(build());

            }

        }

    }

    /**
     *
     * Searches the Orders
     * https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products
     *
     * @param <T>
     */
    public static class Searcher<T extends Searcher> extends Seek.Searcher<T> {

        T self() {
            return (T) this;
        }

        /**
         * Limit response to resources published after a given ISO8601 compliant date.
         * @param after
         * @return
         */
        public T setAfter(LocalDate after) {
            addNameValuePair("after", after);
            return self();
        }
        /**
         * Limit response to resources published before a given ISO8601 compliant date.
         * @param before
         * @return
         */
        public T setBefore(LocalDate before) {
            addNameValuePair("before", before);
            return self();
        }

        /**
         * Limit response to resources modified after a given ISO8601 compliant date.
         * @param modifiedAfter
         * @return
         */
        public T setModifiedAfter(LocalDate modifiedAfter) {
            addNameValuePair("modified_after", modifiedAfter);
            return self();
        }
        /**
         * Limit response to resources modified before a given ISO8601 compliant date.
         * @param modifiedBefore
         * @return
         */
        public T setModifiedBefore(LocalDate modifiedBefore) {
            addNameValuePair("modified_before", modifiedBefore);
            return self();
        }

        /**
         * 	Whether to consider GMT post dates when limiting response by published or modified date.
         * @param datesAreGMT
         * @return
         */
        public T setDatesAreGMT(boolean datesAreGMT) {
            addNameValuePair("dates_are_gmt", datesAreGMT);
            return self();
        }

        /**
         * Offset the result set by a specific number of items.
         * @param offset
         * @return
         */
        public T setOffset(int offset) {
            addNameValuePair("offset", offset);
            return self();
        }

        /**
         * Limit result set to those of particular parent IDs.
         * @param parents
         * @return
         */
        public T setParents(List<Integer> parents) {
            addNameValueIntegers("parent", parents);
            return self();
        }

        /**
         * Limit result set to all items except those of a particular parent ID..
         * @param parents_exclude
         * @return
         */
        public T setParentsExclude(List<Integer> parents_exclude) {
            addNameValueIntegers("parent_exclude", parents_exclude);
            return self();
        }

        /**
         * Limit result set to orders assigned a specific status. Options: any, pending, processing, on-hold, completed, cancelled, refunded, failed and trash. Default is any.
         * @param status
         * @return
         */
        public T setStatus(List<String> statuses) {
            addNameValueStrings("status", statuses);
            return self();
        }

        /**
         * Limit result set to orders assigned a specific customer.
         * @param customerId
         * @return
         */
        public T setCustomer(int customerId) {
            addNameValuePair("customer", customerId);
            return self();
        }

        /**
         * Limit result set to orders assigned a specific product.
         * @param productId
         * @return
         */
        public T setProduct(int productId) {
            addNameValuePair("product", productId);
            return self();
        }

        /**
         * Number of decimal points to use in each resource. Default is 2.
         * @param decimalPlaces
         * @return
         */
        public T setDecimalPlaces(int decimalPlaces) {
            addNameValuePair("dp", decimalPlaces);
            return self();
        }

        /**
         * Limit result set to orders created via specific sources (e.g. checkout, store-api).
         * Multiple options can be provided as a comma-separated list.
         * @param created_via
         * @return
         */
        public T setCreatedVia(String created_via) {
            addNameValuePair("created_via", created_via);
            return self();
        }

        public OrderResponse getResponse(){

            return new OrderResponse(
                new WooCommerce().search(
                    getEndPoint(),
                    build(),
                    new TypeReference<List<Order>>(){}
                )
            );

        }

    }

}
