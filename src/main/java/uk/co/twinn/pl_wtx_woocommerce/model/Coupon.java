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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonMappedLinks;
import uk.co.twinn.api.woocommerce.response.core.ErrorMessage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Coupon {

    private Integer id; //integer	Unique identifier for the object.read-only
    private String code; //	string	Coupon code.mandatory
    private BigDecimal amount; //	string	The amount of discount. Should always be numeric, even if setting a percentage.
    private LocalDateTime dateCreated; //	date-time	The date the coupon was created, in the site's timezone.read-only
    private LocalDateTime dateCreatedGMT; //	date-time	The date the coupon was created, as GMT.read-only
    private LocalDateTime dateModified; //	date-time	The date the coupon was last modified, in the site's timezone.read-only
    private LocalDateTime dateModifiedGMT; //	date-time	The date the coupon was last modified, as GMT.read-only
    private String discountType; //	string	Determines the type of discount that will be applied. Options: percent, fixed_cart and fixed_product. Default is fixed_cart.
    private String description; //	string	Coupon description.
    private LocalDateTime dateExpires; //	string	The date the coupon expires, in the site's timezone.
    private LocalDateTime dateExpiresGMT; //	string	The date the coupon expires, as GMT.
    private Integer usageCount; //	integer	Number of times the coupon has been used already.read-only
    private Boolean individualUse; //	boolean	If true, the coupon can only be used individually. Other applied coupons will be removed from the cart. Default is false.
    private List<Integer> productIds; //	array	List of product IDs the coupon can be used on.
    private List<Integer> excludedProductIds; //	array	List of product IDs the coupon cannot be used on.
    private Integer usageLimit; //	integer	How many times the coupon can be used in total.
    private Integer usageLimitPerUser; //	integer	How many times the coupon can be used per customer.
    private Integer limitUsageToXItems; //	integer	Max number of items in the cart the coupon can be applied to.
    private Boolean freeShipping; //	boolean	If true and if the free shipping method requires a coupon, this coupon will enable free shipping. Default is false.
    private List<Integer> productCategories; //	array	List of category IDs the coupon applies to.
    private List<Integer> excludedProductCategories; //	array	List of category IDs the coupon does not apply to.
    private Boolean excludeSaleItems; //	boolean	If true, this coupon will not be applied to items that have sale prices. Default is false.
    private BigDecimal minimumAmount; //	string	Minimum order amount that needs to be in the cart before coupon applies.
    private BigDecimal maximumAmount; //	string	Maximum order amount allowed when using the coupon.
    private List<String> emailRestrictions; //	array	List of email addresses that can use this coupon.
    private List<Integer> usedBy; //	array	List of user IDs (or guest email addresses) that have used the coupon.read-only
    private List<MetaData> metaData; //	array	Meta data. See Coupon - Meta data properties

    private HashMap<String, Link> links;
    private ErrorMessage error; //batch notifier

    public Coupon(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @JsonProperty("date_created")
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateCreatedGMT() {
        return dateCreatedGMT;
    }

    @JsonProperty("date_created_gmt")
    public void setDateCreatedGMT(LocalDateTime dateCreatedGMT) {
        this.dateCreatedGMT = dateCreatedGMT;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    @JsonProperty("date_modified")
    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public LocalDateTime getDateModifiedGMT() {
        return dateModifiedGMT;
    }

    @JsonProperty("date_modified_gmt")
    public void setDateModifiedGMT(LocalDateTime dateModifiedGMT) {
        this.dateModifiedGMT = dateModifiedGMT;
    }

    public String getDiscountType() {
        return discountType;
    }

    @JsonProperty("discount_type")
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateExpires() {
        return dateExpires;
    }

    @JsonProperty("date_expires")
    public void setDateExpires(LocalDateTime dateExpires) {
        this.dateExpires = dateExpires;
    }

    public LocalDateTime getDateExpiresGMT() {
        return dateExpiresGMT;
    }

    @JsonProperty("date_expires_gmt")
    public void setDateExpiresGMT(LocalDateTime dateExpiresGMT) {
        this.dateExpiresGMT = dateExpiresGMT;
    }

    public Integer getUsageCount() {
        return usageCount;
    }

    @JsonProperty("usage_count")
    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }

    public Boolean getIndividualUse() {
        return individualUse;
    }

    @JsonProperty("individual_use")
    public void setIndividualUse(Boolean individualUse) {
        this.individualUse = individualUse;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    @JsonProperty("product_ids")
    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public List<Integer> getExcludedProductIds() {
        return excludedProductIds;
    }

    @JsonProperty("exclude_product_ids")
    public void setExcludedProductIds(List<Integer> excludedProductIds) {
        this.excludedProductIds = excludedProductIds;
    }

    public Integer getUsageLimit() {
        return usageLimit;
    }

    @JsonProperty("usage_limit")
    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    public Integer getUsageLimitPerUser() {
        return usageLimitPerUser;
    }

    @JsonProperty("usage_limit_per_user")
    public void setUsageLimitPerUser(Integer usageLimitPerUser) {
        this.usageLimitPerUser = usageLimitPerUser;
    }

    public Integer getLimitUsageToXItems() {
        return limitUsageToXItems;
    }

    @JsonProperty("limit_usage_to_x_items")
    public void setLimitUsageToXItems(Integer limitUsageToXItems) {
        this.limitUsageToXItems = limitUsageToXItems;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    @JsonProperty("free_shipping")
    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public List<Integer> getProductCategories() {
        return productCategories;
    }

    @JsonProperty("product_categories")
    public void setProductCategories(List<Integer> productCategories) {
        this.productCategories = productCategories;
    }

    public List<Integer> getExcludedProductCategories() {
        return excludedProductCategories;
    }

    @JsonProperty("excluded_product_categories")
    public void setExcludedProductCategories(List<Integer> excludedProductCategories) {
        this.excludedProductCategories = excludedProductCategories;
    }

    public Boolean getExcludeSaleItems() {
        return excludeSaleItems;
    }

    @JsonProperty("exclude_sale_items")
    public void setExcludeSaleItems(Boolean excludeSaleItems) {
        this.excludeSaleItems = excludeSaleItems;
    }

    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    @JsonProperty("minimum_amount")
    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public BigDecimal getMaximumAmount() {
        return maximumAmount;
    }

    @JsonProperty("maximum_amount")
    public void setMaximumAmount(BigDecimal maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public List<String> getEmailRestrictions() {
        return emailRestrictions;
    }

    @JsonProperty("email_restrictions")
    public void setEmailRestrictions(List<String> emailRestrictions) {
        this.emailRestrictions = emailRestrictions;
    }

    public List<Integer> getUsedBy() {
        return usedBy;
    }

    @JsonProperty("used_by")
    public void setUsedBy(List<Integer> usedBy) {
        this.usedBy = usedBy;
    }

    public List<MetaData> getMetaData() {
        return metaData;
    }

    @JsonProperty("meta_data")
    public void setMetaData(List<MetaData> metaData) {
        this.metaData = metaData;
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

    /**
     * When we batch the customers we retrieve a list of customers that have been created, updated, deleted
     * Within this list we may have record A as success and so no error
     * BUT record B may fail with exists, or something else.
     * Only way to catch the error and pass back is by adding the error message into here.
     */
    @javax.annotation.Nullable
    public ErrorMessage getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(@javax.annotation.Nullable ErrorMessage error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
