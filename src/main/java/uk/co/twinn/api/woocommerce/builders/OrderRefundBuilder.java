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
import uk.co.twinn.api.woocommerce.builders.core.Seek;
import uk.co.twinn.api.woocommerce.response.Created;
import uk.co.twinn.api.woocommerce.response.Deleted;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.*;

import java.math.BigDecimal;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class OrderRefundBuilder extends ApiRequest {

    protected final OrderRefund orderRefund = new OrderRefund();

    private boolean force;

    public OrderRefundBuilder(){

    }

    private OrderRefundBuilder(Creator<?> creator){

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

    private OrderRefundBuilder(ListAll<?> listAller){

        orderRefund.setOrderId(listAller.orderId);

    }

    public static String endPoint(int orderId){

        return ORDERS +
            (orderId > 0
                ? "/" + orderId
                : ""
            ) +
            "/" + REFUNDS;

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

        private final int orderId;

        private BigDecimal amount;
        //string	Total refund amount.
        // Optional. If this parameter is provided, it will take precedence over line item totals,
        // even when total of line items does not match with this amount.
        private String reason;//	string	Reason for refund.
        private Integer refundedBy;//	integer	User ID of user who created the refund.
        private List<MetaData> metaData;//	array	Meta data. See Order refund - Meta data properties
        private List<OrderRefundLineItem> lineItems;//	array	Line items data. See Order refund - Line items properties

        private List<OrderShippingLine> shippingLines;//	array	Shipping lines data. See Order refund - Shipping lines properties
        private List<OrderFeeLine> feeLines;//	array	Fee lines data. See Order refund - Fee lines properties
        private Boolean apiRefund;//	boolean	When true, the payment gateway API is used to generate the refund. Default is true.write-only
        private Boolean apiRestock;//	boolean	When true, the selected line items are restocked Default is true.write-only

        public Creator(int orderId){
            this.orderId = orderId;
        }

        public Creator(int orderId, OrderRefund orderRefund){

            this(orderId);

            amount = orderRefund.getAmount();
            reason = orderRefund.getReason();
            refundedBy = orderRefund.getRefundedBy();
            metaData = orderRefund.getMetaData();
            lineItems = orderRefund.getLineItems();
            shippingLines = orderRefund.getShippingLines();
            feeLines = orderRefund.getFeeLines();
            apiRefund = orderRefund.getApiRefund();
            apiRestock = orderRefund.getApiRestock();
        }

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
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


        protected OrderRefundBuilder build(){
            return new OrderRefundBuilder(this);
        }


        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<OrderRefund> getResponse(){
            if (orderId == 0) {
                return new Created<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }else {
                OrderRefundBuilder create = build();
                //make the call
                return new Created<>(
                    new Rest().create(endPoint(orderId), create.toJson(), new TypeReference<OrderRefund>(){})
                );
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReader.ChildReaderCore<T>{

        public Reader(int orderId, int refundId){
            super(orderId, refundId);
        }

        @SuppressWarnings("unchecked")
        public Read<OrderRefund> getResponse(){
            return (Read<OrderRefund>)super.getResponse(ORDERS, REFUNDS, new TypeReference<OrderRefund>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleter.ChildDeleterCore<T>{

        public Deleter(int orderId, int refundId, boolean force){
            super(orderId, refundId, force);
        }

        @SuppressWarnings("unchecked")
        public Deleted<OrderRefund> getResponse(){
            return (Deleted<OrderRefund>)super.getResponse(ORDERS, REFUNDS, new TypeReference<OrderRefund>() {});

        }

    }
    //</editor-fold>

    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T>{

        private final int orderId;

        public ListAll(int orderId){
            this.orderId = orderId;
        }

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Listed<OrderRefund> getResponse(){
            if (orderId <= 0) {
                return new Listed<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }else {

                return new Listed<>(
                    new Rest().listAll(
                        endPoint(orderId),
                        build(),
                        new TypeReference<List<OrderRefund>>(){}
                    )
                );

            }
        }

    }

}
