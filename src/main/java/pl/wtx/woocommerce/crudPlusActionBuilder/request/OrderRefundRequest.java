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
import pl.wtx.woocommerce.crudPlusActionBuilder.response.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.WooCommerce;

import java.math.BigDecimal;
import java.util.List;

import static pl.wtx.woocommerce.crudPlusActionBuilder.defines.EndPoints.*;

public class OrderRefundRequest extends ApiRequest {

    protected final OrderRefund orderRefund = new OrderRefund();

    private boolean force;

    public OrderRefundRequest(Creator<?> creator){

        orderRefund.setAmount(creator.amount);
        orderRefund.setReason(creator.reason);
        orderRefund.setRefundedBy(creator.refundedBy);
        orderRefund.setMetaData(creator.metaData);
        orderRefund.setLineItems(creator.lineItems);
        orderRefund.setShippingLines(creator.shippingLines);
        orderRefund.setFeeLines(creator.feeLines);
        orderRefund.setApiRefund(creator.apiRefund);
        orderRefund.setApiRestock(creator.apiRestock);

    }

    public OrderRefundRequest(Reader<?> reader){

        orderRefund.setOrderId(reader.orderId);
        orderRefund.setId(reader.refundId);

    }

    public OrderRefundRequest(Deleter<?> deleter){

        this((Reader<?>)deleter);
        force = deleter.force;

    }

    public OrderRefundRequest(ListAll<?> listAller){

        orderRefund.setOrderId(listAller.orderId);

    }

    public String endPoint(){

        return ORDERS +
            (orderRefund.getOrderId() != null && orderRefund.getOrderId() != 0
                ? ("/" + orderRefund.getOrderId())
                : ""
            ) +
            "/" + REFUNDS +
            (orderRefund.getId() != null && orderRefund.getId() != 0
                ? "/" + orderRefund.getId()
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
            return getObjectMapper().writeValueAsString(orderRefund);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

        private int orderId;

        private BigDecimal amount;
        //string	Total refund amount.
        // Optional. If this parameter is provided, it will take precedence over line item totals,
        // even when total of line items does not matches with this amount.
        private String reason;//	string	Reason for refund.
        private Integer refundedBy;//	integer	User ID of user who created the refund.
        private List<MetaData> metaData;//	array	Meta data. See Order refund - Meta data properties
        private List<OrderRefundLineItem> lineItems;//	array	Line items data. See Order refund - Line items properties

        private List<OrderShippingLine> shippingLines;//	array	Shipping lines data. See Order refund - Shipping lines properties
        private List<OrderFeeLine> feeLines;//	array	Fee lines data. See Order refund - Fee lines properties
        private Boolean apiRefund;//	boolean	When true, the payment gateway API is used to generate the refund. Default is true.write-only
        private Boolean apiRestock;//	boolean	When true, the selected line items are restocked Default is true.write-only

        T self() {
            return (T) this;
        }

        /**
         *
         * @param orderId Order note(s) must be tied to an Order.
         * @return T
         */
        public T setOrderId(int orderId){
            this.orderId = orderId;
            return self();
        }

        public T setAmount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        public T setReason(String reason) {
            this.reason = reason;
            return self();
        }

        public T setRefundedBy(Integer refundedBy) {
            this.refundedBy = refundedBy;
            return self();
        }

        public T setMetaData(List<MetaData> metaData) {
            this.metaData = metaData;
            return self();
        }

        public T setFeeLines(List<OrderFeeLine> feeLines) {
            this.feeLines = feeLines;
            return self();
        }

        public T setApiRefund(Boolean apiRefund) {
            this.apiRefund = apiRefund;
            return self();
        }

        public T setApiRestock(Boolean apiRestock) {
            this.apiRestock = apiRestock;
            return self();
        }

        public T setLineItems(List<OrderRefundLineItem> lineItems) {
            this.lineItems = lineItems;
            return self();
        }

        public T setShippingLines(List<OrderShippingLine> shippingLines) {
            this.shippingLines = shippingLines;
            return self();
        }


        protected OrderRefundRequest build(){
            return new OrderRefundRequest(this);
        }


        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<OrderRefund> getResponse(){
            if (orderId == 0) {
                return new Created<OrderRefund>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }else {
                return new WooCommerce().create(build());
            }
        }



    }

    public static class Reader<T extends Reader<T>>{

        protected int orderId;
        protected int refundId;

        T self() {
            return (T) this;
        }

        /**
         *
         * @param orderId Order note(s) must be tied to an Order.
         * @return T
         */
        public T setOrderId(int orderId){
            this.orderId = orderId;
            return self();
        }

        public T setRefundId(int refundId){
            this.refundId = refundId;
            return self();
        }

        protected OrderRefundRequest build(){
            return new OrderRefundRequest(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Read<OrderRefund> getResponse(){
            if (orderId <= 0 || refundId <= 0) {
                return new Read<OrderRefund>(
                    new ApiResponseResult(
                        false,
                        0,
                        "CRUD is limited to a single object result\n" +
                            "Please set requested id\n" +
                            "Use the ListAll with orderId to get lst of refunds for an order")
                );
            }else {
                return new WooCommerce().read(build());
            }
        }

    }

    public static class Deleter<T extends Deleter<T>> extends Reader<T> {

        private boolean force;

        public T force(boolean force){
            this.force = force;
            return self();
        }

        @Override
        protected OrderRefundRequest build(){
            return new OrderRefundRequest(this);
        }

        /** Returns single Deleted ProductCategory**/
        @Override
        public Deleted<OrderRefund> getResponse() {
            if (orderId == 0 || refundId == 0) {
                return new Deleted<OrderRefund>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id And refund Id are MANDATORY!")
                );
            }else{
                return new WooCommerce().delete(build());
            }
        }

    }

    public static class ListAll<T extends ListAll<T>>{

        protected int orderId;

        T self() {
            return (T) this;
        }

        /**
         *
         * @param orderId Order note(s) must be tied to an Order.
         * @return T
         */
        public T setOrderId(int orderId){
            this.orderId = orderId;
            return self();
        }

        protected OrderRefundRequest build(){
            return new OrderRefundRequest(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Listed<OrderRefund> getResponse(){
            if (orderId == 0) {
                return new Listed<OrderRefund>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }else {

                String endPoint = ORDERS + "/" + orderId + "/refunds";

                return new Listed<OrderRefund>(
                    new WooCommerce().listAll(
                        endPoint,
                        "",
                        new TypeReference<List<OrderRefund>>(){}
                    )
                );

            }
        }

    }

    public static class Searcher<T extends Searcher<T>> extends Seek.Searcher<T>{

        protected int orderId;

        T self() {
            return (T) this;
        }

        /**
         *
         * @param orderId Order note(s) must be tied to an Order.
         * @return T
         */
        public T setOrderId(int orderId){
            this.orderId = orderId;
            return self();
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Searched<OrderRefund> getResponse(){
            if (orderId == 0) {
                return new Searched<OrderRefund>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }else {

                String endPoint = ORDERS + "/" + orderId + "/refunds";

                return new Searched<OrderRefund>(
                    new WooCommerce().listAll(
                        endPoint,
                        build(),
                        new TypeReference<List<OrderRefund>>(){}
                    )
                );

            }
        }

    }

}
