/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.billing.Billing;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.order.Order;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.order.OrderLineItem;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.order.OrderShippingLine;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.shipping.Shipping;


import java.time.LocalDate;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.ORDERS;

public class OrderBuilder extends ApiRequest {

    protected final Order order = new Order();

    private boolean force;
    //private boolean duplicate;
    private boolean isBatch;

    private OrderBuilder(){

    }

    private OrderBuilder(Creator<?> creator){

        order.setMetaData(null);
        order.setTaxLines(null);
        order.setFeeLines(null);
        order.setCouponLines(null);
        order.setRefunds(null);

        order.setPaymentMethod(creator.paymentMethod);
        order.setPaymentMethodTitle(creator.paymentMethodTitle);
        order.setPaid(creator.paid);
        order.setBilling(creator.billing);
        order.setShipping(creator.shipping);
        order.setLineItems(creator.lineItems);
        order.setShippingLines(creator.shippingLines);

    }

    private OrderBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        //order.setId(updater.id); this must be set in the url if here we get denied
        order.setStatus(updater.status);



    }

    private OrderBuilder(Deleter deleter){

        order.setId(deleter.id);
        isBatch = false;
        force = deleter.force;

    }

    public String endPoint(){

        return endPoint(order.getId() != null ? order.getId() : 0);

    }

    private String endPoint(int orderId){

        return ORDERS +
            (orderId != 0
                ? ("/" + orderId)
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

                // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(order);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>> extends CoreCreator<Order, T>{

        private String paymentMethod;
        private String paymentMethodTitle;
        private Boolean paid;
        private Billing billing;
        private Shipping shipping;
        private List<OrderLineItem> lineItems;
        private List<OrderShippingLine> shippingLines;

        public Creator(){}

        public Creator(Order order){
            paymentMethod = order.getPaymentMethod();
            paymentMethodTitle = order.getPaymentMethodTitle();
            paid = order.getSetPaid();
            billing = order.getBilling();
            shipping = order.getShipping();
            lineItems = order.getLineItems().isEmpty() ? null : order.getLineItems();
            shippingLines = order.getShippingLines().isEmpty() ? null : order.getShippingLines();
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


        protected OrderBuilder build(){
            return new OrderBuilder(this);
        }

        public Created<Order> getResponse(){

            OrderBuilder create = build();

            return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<Order>() {});

            /*return new Created<>(
                new Rest<Order>().create(create.endPoint(), create.toJson())
            );*/
        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private final int id;
        private String status;

        public Updater(Order order){
            super(order);
            if (order.getId() != null) {
                this.id = order.getId();
            }else{
                this.id = 0;
            }
        }

        public Updater(int id){
            this.id = id;
        }

        public T setStatus(String status) {
            this.status = status;
            return self();
        }

        @Override
        protected OrderBuilder build(){
            return new OrderBuilder(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public Updated<Order> getResponse(){
            if (id > 0) {

                OrderBuilder create = build();
                //make the call
                /*return new Updated<>(
                    new Rest<Order>().update(create.endPoint(), create.toJson())
                );*/
                return super.getUpdate(create.endPoint(id), create.toJson(), new TypeReference<Order>() {});

            }else{
                return new Updated<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<Order>{

        public Reader(int orderId){
            super(orderId);
        }

        public Read<Order> getResponse(){
            return super.getResponse(ORDERS, new TypeReference<Order>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<Order>{

        public Deleter(int orderId, boolean force){
            super(orderId, force);
        }

        protected OrderBuilder build(){
            return new OrderBuilder(this);
        }

        public Deleted<Order> getResponse(){
            return super.getResponse(ORDERS, new TypeReference<Order>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Batcher">
    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<Order, T>{

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            return addCreator(create.build().order);
        }

        public T addCreator(List<Order> create){
            return super.addCreate(create);
        }

        public T addCreator(Order create){
            return super.addCreate(create);
        }

        public T addUpdater(Updater<?> update){
            return addUpdater(update.build().order);
        }

        public T addUpdater(List<Order> updateList){
            return super.addUpdate(updateList);
        }

        public T addUpdater(Order update){
            return super.addUpdate(update);
        }

        public T addDeleter(Deleter delete){
            return addDeleter(delete.build().order);
        }

        public T addDeleter(List<Order> deleteList){
            for (Order delete : deleteList) {
                addDeleter(delete);
            }
            return self();
        }

        public T addDeleter(Order delete){
            return super.addDelete(delete.getId());
        }

        /** Returns list of amended Orders **/
        public Batched<Order> getResponse(){

            for (Order order : batch.getCreate()) {
                //stripping the id if is set
                if (order.getId() != null) {
                    order.setId(null);
                }
            }

            //pre-validate
            for (int i = 0; i < batch.getUpdate().size(); i++){
                if (batch.getUpdate().get(i).getId() == null || batch.getUpdate().get(i).getId() == 0){
                    return super.getFailure(
                        String.format("Id is MANDATORY!, Found Update @ %s with id = 0", i)
                    );
                }
            }

            //delete validation is in super
            return super.getResponse(ORDERS, batch, new TypeReference<Order>() {});

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
    public static class ListAll<T extends ListAll<T>> extends CoreSeek.Searcher<Order, T> {

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

            return super.getResponse(
                ORDERS,
                build(),
                new TypeReference<List<Order>>() {}
            );

            /*return new Listed<>(
                new Rest<List<Order>>().listAll(
                    ORDERS,
                    build()
                )
            );*/

        }

    }

}
