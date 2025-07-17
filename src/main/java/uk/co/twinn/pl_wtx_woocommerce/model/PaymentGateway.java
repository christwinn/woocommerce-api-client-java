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

import java.util.List;

public class PaymentGateway {

    private String id;//	string	Payment gateway ID.read-only
    private String title;//	string	Payment gateway title on checkout.
    private String description;//	string	Payment gateway description on checkout.
    private Integer order;//	integer	Payment gateway sort order.
    private Boolean enabled;//	boolean	Payment gateway enabled status.
    private String methodTitle;//	string	Payment gateway method title.read-only
    private String methodDescription; //	string	Payment gateway method description.read-only
    private List<String> methodSupports; //	array	Supported features for this payment gateway.read-only
    private PaymentGatewaySettings settings; //Payment gateway settings. See Payment gateway - Settings properties

    private String settingsUrl;
    private String connectionUrl;
    private String setupHelpText;

    private Links links;


    public PaymentGateway(){}

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

    @JsonProperty("method_supports")
    public List<String> getMethodSupports() {
        return methodSupports;
    }

    @JsonProperty("method_supports")
    public void setMethodSupports(List<String> methodSupports) {
        this.methodSupports = methodSupports;
    }

    public void setSettings( PaymentGatewaySettings settings ) {
        this.settings = settings;
    }

    public PaymentGatewaySettings getSettings(  ) {  return settings;  }

    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
    }
    @JsonProperty("_links")
    public Links getLinks( ) {
        return links;
    }

    @JsonProperty("settings_url")
    public String getSettingsUrl() {
        return settingsUrl;
    }

    @JsonProperty("settings_url")
    public void setSettingsUrl(String settingsUrl) {
        this.settingsUrl = settingsUrl;
    }

    @JsonProperty("connection_url")
    public String getConnectionUrl() {
        return connectionUrl;
    }

    @JsonProperty("connection_url")
    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    @JsonProperty("setup_help_text")
    public String getSetupHelpText() {
        return setupHelpText;
    }

    @JsonProperty("setup_help_text")
    public void setSetupHelpText(String setupHelpText) {
        this.setupHelpText = setupHelpText;
    }

    public String toJson() {

        return toJson(false);

    }

    public String toJson(boolean isPretty){
        return new JacksonObjectMapper().toJson(isPretty, this);
    }

}
