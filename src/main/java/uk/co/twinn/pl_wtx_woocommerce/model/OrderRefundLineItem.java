/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

import java.math.BigDecimal;

public class OrderRefundLineItem extends OrderLineItem {

    public OrderRefundLineItem() {
    }

    /*Not to be in json but we are reusing the fields so overriding to hide!
    * SAMPLE DATA REQUIRED !
    * REF: https://woocommerce.github.io/woocommerce-rest-api-docs/#create-a-refund
    * appears to require sending in refund_tax
    * but appears to return total_tax
    * I have no refunds! STANLEY's in a Pickle!
    * */
    @JsonIgnore
    @Override
    public BigDecimal getTotal() {
        return super.getTotal();
    }

    @JsonIgnore
    @Override
    public BigDecimal getTotalTax() {
        return super.getTotalTax();
    }

    public BigDecimal getRefundTax() {
        return getTotalTax();
    }

    @JsonProperty("refund_tax")
    public void setRefundTax(@javax.annotation.Nullable BigDecimal totalTax) {
        setTotalTax(totalTax);
    }

    public BigDecimal getRefundTotal() {
        return getTotal();
    }

    @JsonProperty("refund_total")
    public void setRefundTotal(@javax.annotation.Nullable BigDecimal total) {
        setTotal(total);
    }

    @Override
    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
