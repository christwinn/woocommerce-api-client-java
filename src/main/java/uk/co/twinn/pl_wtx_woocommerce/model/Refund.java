/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

public class Refund extends OrderRefund {
    private Integer parentId;

    public Refund(){
        super();
    }

    @JsonProperty("parent_id")
    public Integer getParentId() {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
