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

import java.util.List;

public class TaxRate {

    private Integer id; //integer	Unique identifier for the resource.read-only
    private ISO3166.CountryEnum country; //	string	Country ISO 3166 code. See ISO 3166 Codes (Countries) for more details
    private String state; //	string	State code.
    //postcode	string	Postcode/ZIP, it doesn't support multiple values. Deprecated as of WooCommerce 5.3, postcodes should be used instead.
    //city	string	City name, it doesn't support multiple values. Deprecated as of WooCommerce 5.3, postcodes should be used instead.
    private List<String> postcodes; //	string[]	Postcodes/ZIPs. Introduced in WooCommerce 5.3.
    private List<String> cities; //	string[]	City names. Introduced in WooCommerce 5.3.
    private String rate; //	string	Tax rate.
    private String name; //	string	Tax rate name.
    private Integer priority; //	integer	Tax priority. Only 1 matching rate per priority will be used. To define multiple tax rates for a single area you need to specify a different priority per rate. Default is 1.
    private Boolean compound; //	boolean	Whether or not this is a compound tax rate. Compound rates are applied on top of other tax rates. Default is false.
    private Boolean shipping; //	boolean	Whether or not this tax rate also gets applied to shipping. Default is true.
    private Integer order; //	integer	Indicates the order that will appear in queries.
    private String taxClass; //	string	Tax class. Default is standard.

    public TaxRate(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ISO3166.CountryEnum getCountry() {
        return country;
    }

    @JsonProperty("country")
    public String getCountryAsString() {
        return country != null ? country.getValue(): "";
    }

    public void setCountry(ISO3166.CountryEnum country) {
        this.country = country;
    }

    @JsonProperty("country")
    public void setCountryAsString(String country) {
        if (country != null && !country.isEmpty()) {
            this.country = ISO3166.CountryEnum.fromValue(country);
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getPostcodes() {
        return postcodes;
    }

    public void setPostcodes(List<String> postcodes) {
        this.postcodes = postcodes;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getCompound() {
        return compound;
    }

    public void setCompound(Boolean compound) {
        this.compound = compound;
    }

    public Boolean getShipping() {
        return shipping;
    }

    public void setShipping(Boolean shipping) {
        this.shipping = shipping;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @JsonProperty("class")
    public String getTaxClass() {
        return taxClass;
    }

    @JsonProperty("class")
    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

}
