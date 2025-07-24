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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonMappedLinks;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonMappedSettings;
import uk.co.twinn.pl_wtx_woocommerce.model.global.Link;
import uk.co.twinn.pl_wtx_woocommerce.model.global.MappedSetting;

import java.util.HashMap;

@JsonPropertyOrder(
    {"id", "instance_id", "title", "order", "enabled", "method_id", "method_title","method_description","settings"}
)
public class ShippingZoneMethod{

    private Integer id; //	not documented but appearing and appears to match instanceId
    private Integer instanceId; //	integer	Shipping method instance ID.read-only
    private String title; //	string	Shipping method customer facing title.read-only

    private Integer order; //	integer	Shipping method sort order.
    private Boolean enabled; //	boolean	Shipping method enabled status.
    private String methodId; //	string	Shipping method ID.read-onlymandatory
    private String methodTitle; //	string	Shipping method title.read-only
    private String methodDescription; //	string	Shipping method description.read-only
    //private ShippingZoneMethodSettings settings; //	object	Shipping method settings. See Shipping method - Settings properties
    private HashMap<String, MappedSetting> settings;
    private HashMap<String, Link> links;

    public ShippingZoneMethod(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("instance_id")
    public Integer getInstanceId() {
        return instanceId;
    }

    @JsonProperty("instance_id")
    public void setInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    @JsonProperty("method_id")
    public String getMethodId() {
        return methodId;
    }

    @JsonProperty("method_id")
    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }
    @JsonProperty("method_title")
    public String getMethodTitle() {
        return methodTitle;
    }

    @JsonProperty("method_title")
    public void setMethodTitle(String methodTitle) {
        this.methodTitle = methodTitle;
    }

    @JsonProperty("method_description")
    public String getMethodDescription() {
        return methodDescription;
    }

    @JsonProperty("method_description")
    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
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

    public HashMap<String, MappedSetting> getSettings() {
        return settings;
    }

    @JsonDeserialize(using = JsonMappedSettings.class)
    //public void setSettings(ShippingZoneMethodSettings settings) {
    public void setSettings(HashMap<String, MappedSetting> settings) {
        this.settings = settings;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
