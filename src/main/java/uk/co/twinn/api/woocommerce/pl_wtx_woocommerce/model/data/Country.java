/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonMappedLinks;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.global.Link;

import java.util.HashMap;
import java.util.List;
@JsonPropertyOrder({
    "code",
    "name",
    "currency_code",
    "currency_pos",
    "decimal_sep",
    "dimension_unit",
    "num_decimals",
    "thousand_sep",
    "weight_unit",
    "states"
})
public class Country {

    private String code; //	string	ISO3166 alpha-2 country code.read-only
    private String currencyCode; //	string	Default ISO4127 alpha-3 currency code for the country.read-only
    private String currencyPos; //	string	Currency symbol position for this country.read-only
    private String decimalSep; //	string	Decimal separator for displayed prices for this country.read-only
    private String dimensionUnit; //	string	The unit lengths are defined in for this country.read-only
    private String name; //string	Full name of country.read-only
    private Integer numDecimals; //	integer	Number of decimal points shown in displayed prices for this country.read-only
    private List<State> states; //	array	List of states in this country. See Continents - Countries - States propertiesread-only
    private String thousandSeparator; //	string	Thousands separator for displayed prices in this country.read-only
    private String weightUnit; //	string	The unit weights are defined in for this country.read-only

    private HashMap<String, Link> links;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currency_code")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("currency_pos")
    public String getCurrencyPos() {
        return currencyPos;
    }

    @JsonProperty("currency_pos")
    public void setCurrencyPos(String currencyPos) {
        this.currencyPos = currencyPos;
    }

    @JsonProperty("decimal_sep")
    public String getDecimalSep() {
        return decimalSep;
    }

    @JsonProperty("decimal_sep")
    public void setDecimalSep(String decimalSep) {
        this.decimalSep = decimalSep;
    }

    @JsonProperty("dimension_unit")
    public String getDimensionUnit() {
        return dimensionUnit;
    }

    @JsonProperty("dimension_unit")
    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("num_decimals")
    public Integer getNumDecimals() {
        return numDecimals;
    }

    @JsonProperty("num_decimals")
    public void setNumDecimals(Integer numDecimals) {
        this.numDecimals = numDecimals;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    @JsonProperty("thousand_sep")
    public String getThousandSeparator() {
        return thousandSeparator;
    }

    @JsonProperty("thousand_sep")
    public void setThousandSeparator(String thousandSeparator) {
        this.thousandSeparator = thousandSeparator;
    }

    @JsonProperty("weight_unit")
    public String getWeightUnit() {
        return weightUnit;
    }

    @JsonProperty("weight_unit")
    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
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
