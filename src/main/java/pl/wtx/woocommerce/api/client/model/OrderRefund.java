/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderRefund {

    private Integer orderId;

    private Integer id;//	integer	Unique identifier for the resource.read-only
    private LocalDateTime dateCreated;//	date-time	The date the order refund was created, in the site's timezone.read-only
    private LocalDateTime dateCreatedGMT;//	date-time	The date the order refund was created, as GMT.read-only
    private BigDecimal amount;//	string	Total refund amount. Optional. If this parameter is provided, it will take precedence over line item totals, even when total of line items does not matches with this amount.
    private String reason;//	string	Reason for refund.
    private Integer refundedBy;//	integer	User ID of user who created the refund.
    private Boolean refundedPayment;//	boolean	If the payment was refunded via the API. See api_refund.read-only
    private List<MetaData> metaData;//	array	Meta data. See Order refund - Meta data properties
    private List<OrderRefundLineItem> lineItems;//	array	Line items data. See Order refund - Line items properties
    private List<OrderTaxLine> taxLines;//	array	Tax lines data. See Order refund - Tax lines propertiesread-only
    private List<OrderShippingLine> shippingLines;//	array	Shipping lines data. See Order refund - Shipping lines properties
    private List<OrderFeeLine> feeLines;//	array	Fee lines data. See Order refund - Fee lines properties
    private Boolean apiRefund;//	boolean	When true, the payment gateway API is used to generate the refund. Default is true.write-only
    private Boolean apiRestock;//	boolean	When true, the selected line items are restocked Default is true.write-only

    public OrderRefund(){}

    @JsonIgnore
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    @JsonProperty("date_created")
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateCreatedGMT() {
        return dateCreatedGMT;
    }

    @JsonProperty("date_created_gmt")
    public void setDateCreatedGMT(LocalDateTime dateCreatedGMT) {
        this.dateCreatedGMT = dateCreatedGMT;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getRefundedBy() {
        return refundedBy;
    }
    @JsonProperty("refunded_by")
    public void setRefundedBy(Integer refundedBy) {
        this.refundedBy = refundedBy;
    }

    public Boolean getRefundedPayment() {
        return refundedPayment;
    }

    @JsonProperty("refunded_payment")
    public void setRefundedPayment(Boolean refundedPayment) {
        this.refundedPayment = refundedPayment;
    }

    public List<MetaData> getMetaData() {
        return metaData;
    }

    @JsonProperty("meta_data")
    public void setMetaData(List<MetaData> metaData) {
        this.metaData = metaData;
    }

    public List<OrderRefundLineItem> getLineItems() {
        return lineItems;
    }

    @JsonProperty("line_items")
    public void setLineItems(List<OrderRefundLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<OrderTaxLine> getTaxLines() {
        return taxLines;
    }

    @JsonProperty("tax_lines")
    public void setTaxLines(List<OrderTaxLine> taxLines) {
        this.taxLines = taxLines;
    }

    public List<OrderShippingLine> getShippingLines() {
        return shippingLines;
    }

    @JsonProperty("shipping_lines")
    public void setShippingLines(List<OrderShippingLine> shippingLines) {
        this.shippingLines = shippingLines;
    }

    public List<OrderFeeLine> getFeeLines() {
        return feeLines;
    }

    @JsonProperty("fee_lines")
    public void setFeeLines(List<OrderFeeLine> feeLines) {
        this.feeLines = feeLines;
    }

    public Boolean getApiRefund() {
        return apiRefund;
    }

    @JsonProperty("api_refund") //writeonly!
    public void setApiRefund(Boolean apiRefund) {
        this.apiRefund = apiRefund;
    }

    public Boolean getApiRestock() {
        return apiRestock;
    }

    @JsonProperty("api_restock") //writeonly!
    public void setApiRestock(Boolean apiRestock) {
        this.apiRestock = apiRestock;
    }
}
