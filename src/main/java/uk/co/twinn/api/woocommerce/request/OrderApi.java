/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.request.core.Seek;
import uk.co.twinn.api.woocommerce.request.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.pl_wtx_woocommerce.model.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.time.LocalDate;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.ORDERS;

public class OrderApi extends ApiRequest {

    protected final Order order = new Order();

    private Batch batch;

    private boolean force;
    //private boolean duplicate;
    private boolean isBatch;

    public OrderApi(){

    }

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public Creator<?> create(){

        return new Creator<>();

    }

    public Reader<?> read(int orderId){

        return new Reader<>(orderId);

    }

    public Updater<?> update(int orderId){

        return new Updater<>(orderId);

    }

    public Deleter<?> delete(int orderId, boolean force){

        return new Deleter<>(orderId, force);

    }

    public Batcher<?> batch(){

        return new Batcher<>();

    }
    public ListAll<?> listing(){

        return new ListAll<>();

    }
    //</editor-fold>

    private OrderApi(Creator<?> creator){

        order.setPaymentMethod(creator.paymentMethod);
        order.setPaymentMethodTitle(creator.paymentMethodTitle);
        order.setPaid(creator.paid);
        order.setBilling(creator.billing);
        order.setShipping(creator.shipping);
        order.setLineItems(creator.lineItems);
        order.setShippingLines(creator.shippingLines);

    }

    private OrderApi(Reader<?> reader){

        order.setId(reader.id);

    }

    private OrderApi(Updater<?> updater){

        this((Creator)updater);
        order.setId(updater.id);

    }

    private OrderApi(Deleter<?> deleter){

        order.setId(deleter.id);
        isBatch = false;
        force = deleter.force;

    }

    public String endPoint(){

        return ORDERS +
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


        protected OrderApi build(){
            return new OrderApi(this);
        }

        public Created<Order> getResponse(){

            OrderApi create = build();
            //make the call
            return new Created<>(
                new Rest().create(create.endPoint(), create.toJson(), new TypeReference<Order>(){})
            );
        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private int id;

        public Updater(int productId){
            this.id = productId;
        }

        /*public T setId(int id){
            this.id = id;
            return self();
        }*/

        @Override
        protected OrderApi build(){
            return new OrderApi(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public Updated<Order> getResponse(){
            if (id > 0) {
                OrderApi create = build();
                //make the call
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<Order>(){})
                );
            }else{
                return new Updated<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ReaderCore<T>{

        public Reader(int orderId){
            super(orderId);
        }

        /*@Override
        T self() {return (T) this;}*/

        public Read<Order> getResponse(){
            return (Read<Order>)super.getResponse(ORDERS, new TypeReference<Order>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.DeleterCore<T>{

        public Deleter(int orderId, boolean force){
            super(orderId, force);
        }

        /*@Override
        T self() {return (T) this;}*/

        protected OrderApi build(){
            return new OrderApi(this);
        }

        public Deleted<Order> getResponse(){
            return (Deleted<Order>)super.getResponse(ORDERS, new TypeReference<Order>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Batcher">
    public static class Batcher<T extends Batcher<T>>  extends CoreBatchRequest.BatchCore<T>{

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().order);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().order);
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().order);
            return self();
        }

        /** Returns list of amended Orders **/
        public Batched<Order> getResponse(){

            return (Batched<Order>) super.getResponse(ORDERS, batch, new TypeReference<Batch<Order>>(){});

        }

    }
    //</editor-fold>

    /**
     *
     * Searches the Orders
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products">
     *     https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T> {

        T self() {
            return (T) this;
        }

        /**
         *
         * @param after Limit response to resources published after a given ISO8601 compliant date.
         * @return T
         */
        public T setAfter(LocalDate after) {
            addNameValuePair("after", after);
            return self();
        }
        /**
         *
         * @param before Limit response to resources published before a given ISO8601 compliant date.
         * @return T
         */
        public T setBefore(LocalDate before) {
            addNameValuePair("before", before);
            return self();
        }

        /**
         *
         * @param modifiedAfter Limit response to resources modified after a given ISO8601 compliant date.
         * @return T
         */
        public T setModifiedAfter(LocalDate modifiedAfter) {
            addNameValuePair("modified_after", modifiedAfter);
            return self();
        }
        /**
         *
         * @param modifiedBefore Limit response to resources modified before a given ISO8601 compliant date.
         * @return T
         */
        public T setModifiedBefore(LocalDate modifiedBefore) {
            addNameValuePair("modified_before", modifiedBefore);
            return self();
        }

        /**
         *
         * @param datesAreGMT 	Whether to consider GMT post dates when limiting response by published or modified date.
         * @return T
         */
        public T setDatesAreGMT(boolean datesAreGMT) {
            addNameValuePair("dates_are_gmt", datesAreGMT);
            return self();
        }

        /**
         *
         * @param offset Offset the result set by a specific number of items.
         * @return T
         */
        public T setOffset(int offset) {
            addNameValuePair("offset", offset);
            return self();
        }

        /**
         *
         * @param parents Limit result set to those of particular parent IDs.
         * @return T
         */
        public T setParents(List<Integer> parents) {
            addNameValueIntegers("parent", parents);
            return self();
        }

        /**
         *
         * @param parents_exclude Limit result set to all items except those of a particular parent ID
         * @return T
         */
        public T setParentsExclude(List<Integer> parents_exclude) {
            addNameValueIntegers("parent_exclude", parents_exclude);
            return self();
        }

        /**
         *
         * @param statuses Limit result set to orders assigned a specific status.
         *                 Options: any, pending, processing, on-hold, completed,
         *                      cancelled, refunded, failed and trash.
         *                      Default is any.
         * @return T
         */
        public T setStatus(List<String> statuses) {
            addNameValueStrings("status", statuses);
            return self();
        }

        /**
         *
         * @param customerId  Limit result set to orders assigned a specific customer.
         * @return T
         */
        public T setCustomer(int customerId) {
            addNameValuePair("customer", customerId);
            return self();
        }

        /**
         *
         * @param productId Limit result set to orders assigned a specific product.
         * @return T
         */
        public T setProduct(int productId) {
            addNameValuePair("product", productId);
            return self();
        }

        /**
         *
         * @param decimalPlaces Number of decimal points to use in each resource. Default is 2.
         * @return T
         */
        public T setDecimalPlaces(int decimalPlaces) {
            addNameValuePair("dp", decimalPlaces);
            return self();
        }

        /**
         *
         * @param created_via Limit result set to orders created via specific sources (e.g. checkout, store-api).
         *          Multiple options can be provided as a comma-separated list.
         * @return T
         */
        public T setCreatedVia(String created_via) {
            addNameValuePair("created_via", created_via);
            return self();
        }

        public Listed<Order> getResponse(){

            return new Listed<Order>(
                new Rest().listAll(
                    ORDERS,
                    build(),
                    new TypeReference<List<Order>>(){}
                )
            );

        }

    }

}
