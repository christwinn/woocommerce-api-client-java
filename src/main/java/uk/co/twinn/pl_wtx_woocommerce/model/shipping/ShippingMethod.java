/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model.shipping;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonMappedLinks;
import uk.co.twinn.pl_wtx_woocommerce.model.global.Link;

import java.util.HashMap;

public class ShippingMethod {

    private String id; //	string	Method ID.read-only
    private String title; //	string	Shipping method title.read-only
    private String description; //string	Shipping method description.read-on

    private HashMap<String, Link> links;

    public ShippingMethod(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("_links")
    @JsonDeserialize(using = JsonMappedLinks.class)
    public void setLinks(HashMap<String, Link> links) {
        this.links = links;
    }
    @JsonProperty("_links")
    public HashMap<String, Link> getLinks( ) {
        return links;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
