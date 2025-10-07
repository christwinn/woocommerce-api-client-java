/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product;

//import com.google.gson.annotations.SerializedName;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonMappedLinks;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.global.Link;

import java.util.HashMap;

public class ProductBrand {

    private Integer id;

    private String name;

    private String slug;

    private Integer parent;
    private String description;
    private String display;

    private ProductImage image;

    private Integer menuOrder;

    private Integer count;

    private HashMap<String, Link> links = new HashMap<>();

    public ProductBrand(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public ProductImage getImage() {
        return image;
    }

    public void setImage(ProductImage image) {
        this.image = image;
    }

    @JsonProperty("menu_order")
    public Integer getMenuOrder() {
        return menuOrder;
    }

    @JsonProperty("menu_order")
    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("_links")
    public HashMap<String, Link> getLinks() {
        return links;
    }

    @JsonDeserialize(using = JsonMappedLinks.class)
    @JsonProperty("_links")
    public void setLinks(HashMap<String, Link> links) {
        this.links = links;
    }

    /**
     * Convert an instance of this to a JSON string
     *
     * @return JSON string
     */

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
