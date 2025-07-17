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

public class TaxClass {

    private String slug;
    private String name;

    private Links links;

    public TaxClass(){}

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
