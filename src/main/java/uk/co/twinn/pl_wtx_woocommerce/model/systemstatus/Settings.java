/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model.systemstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;

@JsonPropertyOrder(
    {   "api_enabled",
        "force_ssl",
        "currency",
        "currency_symbol",
        "currency_position",
        "thousand_separator",
        "decimal_separator",
        "number_of_decimals",
        "geolocation_enabled",
        "taxonomies",
        "product_visibility_terms",
        "woocommerce_com_connected",
        "enforce_approved_download_dirs",
        "order_datastore",
        "HPOS_enabled",
        "HPOS_sync_enabled",
        "enabled_features"
    }
)

public class Settings {

    private boolean apiEnabled;//	boolean	REST API enabled?read-only
    private boolean forceSsl;//	boolean	SSL forced?read-only
    private String currency;//	string	Currency.read-only
    private String currencySymbol;//	string	Currency symbol.read-only
    private String currencyPosition;//	string	Currency position.read-only
    private String thousandSeparator;//	string	Thousand separator.read-only
    private String decimalSeparator;//	string	Decimal separator.read-only
    private int numberOfDecimals;//	integer	Number of decimals.read-only
    private boolean geolocationEnabled;//	boolean	Geolocation enabled?read-only
    private HashMap<String, String> taxonomies;//	array	Taxonomy terms for product/order statuses.read-only
    private HashMap<String, String> productVisibilityTerms		;//undocumented
    private String woocommerceComConnected;//undocumented
    private boolean enforceApprovedDownloadDirs;//undocumented
    private String orderDatastore;//undocumented
    private boolean HposEnabled;//undocumented
    private boolean HposSyncEnabled;//undocumented
    private List<String> enabledFeatures;//undocumented

    public Settings(){}

    @JsonProperty("api_enabled")
    public boolean isApiEnabled() {
        return apiEnabled;
    }

    @JsonProperty("api_enabled")
    public void setApiEnabled(boolean apiEnabled) {
        this.apiEnabled = apiEnabled;
    }

    @JsonProperty("force_ssl")
    public boolean isForceSsl() {
        return forceSsl;
    }

    @JsonProperty("force_ssl")
    public void setForceSsl(boolean forceSsl) {
        this.forceSsl = forceSsl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("currency_symbol")
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    @JsonProperty("currency_symbol")
    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    @JsonProperty("currency_position")
    public String getCurrencyPosition() {
        return currencyPosition;
    }

    @JsonProperty("currency_position")
    public void setCurrencyPosition(String currencyPosition) {
        this.currencyPosition = currencyPosition;
    }

    @JsonProperty("thousand_separator")
    public String getThousandSeparator() {
        return thousandSeparator;
    }

    @JsonProperty("thousand_separator")
    public void setThousandSeparator(String thousandSeparator) {
        this.thousandSeparator = thousandSeparator;
    }

    @JsonProperty("decimal_separator")
    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    @JsonProperty("decimal_separator")
    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    @JsonProperty("number_of_decimals")
    public int getNumberOfDecimals() {
        return numberOfDecimals;
    }

    @JsonProperty("number_of_decimals")
    public void setNumberOfDecimals(int numberOfDecimals) {
        this.numberOfDecimals = numberOfDecimals;
    }

    @JsonProperty("geolocation_enabled")
    public boolean isGeolocationEnabled() {
        return geolocationEnabled;
    }

    @JsonProperty("geolocation_enabled")
    public void setGeolocationEnabled(boolean geolocationEnabled) {
        this.geolocationEnabled = geolocationEnabled;
    }

    public HashMap<String, String> getTaxonomies() {
        return taxonomies;
    }

    public void setTaxonomies(HashMap<String, String> taxonomies) {
        this.taxonomies = taxonomies;
    }

    @JsonProperty("product_visibility_terms")
    public HashMap<String, String> getProductVisibilityTerms() {
        return productVisibilityTerms;
    }

    @JsonProperty("product_visibility_terms")
    public void setProductVisibilityTerms(HashMap<String, String> productVisibilityTerms) {
        this.productVisibilityTerms = productVisibilityTerms;
    }

    @JsonProperty("woocommerce_com_connected")
    public String isWoocommerceComConnected() {
        return woocommerceComConnected;
    }

    @JsonProperty("woocommerce_com_connected")
    public void setWoocommerceComConnected(String woocommerceComConnected) {
        this.woocommerceComConnected = woocommerceComConnected;
    }

    @JsonProperty("enforce_approved_download_dirs")
    public boolean isEnforceApprovedDownloadDirs() {
        return enforceApprovedDownloadDirs;
    }

    @JsonProperty("enforce_approved_download_dirs")
    public void setEnforceApprovedDownloadDirs(boolean enforceApprovedDownloadDirs) {
        this.enforceApprovedDownloadDirs = enforceApprovedDownloadDirs;
    }

    @JsonProperty("order_datastore")
    public String getOrderDatastore() {
        return orderDatastore;
    }

    @JsonProperty("order_datastore")
    public void setOrderDatastore(String orderDatastore) {
        this.orderDatastore = orderDatastore;
    }

    @JsonProperty("HPOS_enabled")
    public boolean isHposEnabled() {
        return HposEnabled;
    }

    @JsonProperty("HPOS_enabled")
    public void setHposEnabled(boolean hposEnabled) {
        HposEnabled = hposEnabled;
    }

    @JsonProperty("HPOS_sync_enabled")
    public boolean isHposSyncEnabled() {
        return HposSyncEnabled;
    }

    @JsonProperty("HPOS_sync_enabled")
    public void setHposSyncEnabled(boolean hposSyncEnabled) {
        HposSyncEnabled = hposSyncEnabled;
    }

    @JsonProperty("enabled_features")
    public List<String> getEnabledFeatures() {
        return enabledFeatures;
    }

    @JsonProperty("enabled_features")
    public void setEnabledFeatures(List<String> enabledFeatures) {
        this.enabledFeatures = enabledFeatures;
    }

}
