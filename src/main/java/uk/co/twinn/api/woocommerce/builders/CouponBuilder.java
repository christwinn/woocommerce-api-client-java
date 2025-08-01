/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.coupon.Coupon;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.global.MetaData;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.COUPONS;

public class CouponBuilder extends ApiRequest {

    private final Coupon coupon = new Coupon();

    public CouponBuilder(){}

    private CouponBuilder(Creator<?> creator){

        coupon.setCode(creator.code);
        coupon.setAmount(creator.amount);
        coupon.setDiscountType(creator.discountType);
        coupon.setDescription(creator.description);
        coupon.setDateExpires(creator.dateExpires);
        coupon.setDateExpiresGMT(creator.dateExpiresGMT);
        coupon.setIndividualUse(creator.individualUse);
        coupon.setProductIds(creator.productIds);
        coupon.setExcludedProductIds(creator.excludedProductIds);
        coupon.setUsageLimit(creator.usageLimit);
        coupon.setUsageLimitPerUser(creator.usageLimitPerUser);
        coupon.setLimitUsageToXItems(creator.limitUsageToXItems);
        coupon.setFreeShipping(creator.freeShipping);
        coupon.setProductCategories(creator.productCategories);
        coupon.setExcludedProductCategories(creator.excludedProductCategories);
        coupon.setExcludeSaleItems(creator.excludeSaleItems);
        coupon.setMinimumAmount(creator.minimumAmount);
        coupon.setMaximumAmount(creator.maximumAmount);
        coupon.setEmailRestrictions(creator.emailRestrictions);
        coupon.setMetaData(creator.metaData);

    }

    private CouponBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        coupon.setId(updater.id);

    }

    private CouponBuilder(Deleter deleter){

        coupon.setId(deleter.id);

    }

    public String endPoint(){

        return
            COUPONS +
                (coupon.getId() != null && coupon.getId() != 0
                    ? ("/" + coupon.getId())
                    : ""
                );

    }

    public String toJson(){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(coupon);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>> extends CoreCreator<Coupon, T>{

        private String code; //	string	Coupon code.mandatory
        private BigDecimal amount; //	string	The amount of discount. Should always be numeric, even if setting a percentage.
        private String discountType; //	string	Determines the type of discount that will be applied. Options: percent, fixed_cart and fixed_product. Default is fixed_cart.
        private String description; //	string	Coupon description.
        private LocalDateTime dateExpires; //	string	The date the coupon expires, in the site's timezone.
        private LocalDateTime dateExpiresGMT; //	string	The date the coupon expires, as GMT.
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
        private List<MetaData> metaData; //	array	Meta data. See Coupon - Meta data properties

        public Creator(){

        }

        public Creator(Coupon coupon){

            code = coupon.getCode();
            amount = coupon.getAmount();
            discountType = coupon.getDiscountType();
            description = coupon.getDescription();
            dateExpires = coupon.getDateExpires();
            dateExpiresGMT = coupon.getDateExpiresGMT();
            individualUse = coupon.getIndividualUse();
            productIds = coupon.getProductIds();
            excludedProductIds = coupon.getExcludedProductIds();
            usageLimit = coupon.getUsageLimit();
            usageLimitPerUser = coupon.getUsageLimitPerUser();
            limitUsageToXItems = coupon.getLimitUsageToXItems();
            freeShipping = coupon.getFreeShipping();
            productCategories = coupon.getProductCategories();
            excludedProductCategories = coupon.getExcludedProductCategories();
            excludeSaleItems = coupon.getExcludeSaleItems();
            minimumAmount = coupon.getMinimumAmount();
            maximumAmount = coupon.getMaximumAmount();
            emailRestrictions = coupon.getEmailRestrictions();
            metaData = coupon.getMetaData();

        }

        /**
         * @param code 	Coupon code.
         * @return T
         */
        public T setCode(String code) {
            this.code = code;
            return self();
        }

        /**
         * @param amount The amount of discount.
         *               Should always be numeric, even if setting a percentage.
         * @return T
         */
        public T setAmount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        /**
         * @param discountType Determines the type of discount that will be applied.
         *                     Options: percent, fixed_cart and fixed_product.
         *                     Default is fixed_cart.
         * @return T
         */
        public T setDiscountType(String discountType) {
            this.discountType = discountType;
            return self();
        }

        /**
         * @param description Coupon description.
         * @return T
         */
        public T setDescription(String description) {
            this.description = description;
            return self();
        }

        /**
         * @param dateExpires The date the coupon expires, in the site's timezone.
         * @return T
         */
        public T setDateExpires(LocalDateTime dateExpires) {
            this.dateExpires = dateExpires;
            return self();
        }

        /**
         * @param dateExpiresGMT The date the coupon expires, as GMT.
         * @return T
         */
        public T setDateExpiresGMT(LocalDateTime dateExpiresGMT) {
            this.dateExpiresGMT = dateExpiresGMT;
            return self();
        }

        /**
         * @param individualUse If true, the coupon can only be used individually.
         *                      Other applied coupons will be removed from the cart.
         *                      Default is false.
         * @return T
         */
        public T setIndividualUse(Boolean individualUse) {
            this.individualUse = individualUse;
            return self();
        }

        /**
         * @param productIds List of product IDs the coupon can be used on.
         * @return T
         */
        public T setProductIds(List<Integer> productIds) {
            this.productIds = productIds;
            return self();
        }

        /**
         * @param excludedProductIds List of product IDs the coupon cannot be used on.
         * @return T
         */
        public T setExcludedProductIds(List<Integer> excludedProductIds) {
            this.excludedProductIds = excludedProductIds;
            return self();
        }

        /**
         * @param usageLimit How many times the coupon can be used in total.
         * @return T
         */
        public T setUsageLimit(Integer usageLimit) {
            this.usageLimit = usageLimit;
            return self();
        }

        /**
         * @param usageLimitPerUser How many times the coupon can be used per customer.
         * @return T
         */
        public T setUsageLimitPerUser(Integer usageLimitPerUser) {
            this.usageLimitPerUser = usageLimitPerUser;
            return self();
        }

        /**
         * @param limitUsageToXItems Max number of items in the cart the coupon can be applied to.
         * @return T
         */
        public T setLimitUsageToXItems(Integer limitUsageToXItems) {
            this.limitUsageToXItems = limitUsageToXItems;
            return self();
        }

        /**
         * @param freeShipping  If true and if the free shipping method requires a coupon,
         *                      this coupon will enable free shipping. Default is false.
         * @return T
         */
        public T setFreeShipping(Boolean freeShipping) {
            this.freeShipping = freeShipping;
            return self();
        }

        /**
         * @param productCategories List of category IDs the coupon applies to.
         * @return T
         */
        public T setProductCategories(List<Integer> productCategories) {
            this.productCategories = productCategories;
            return self();
        }

        /**
         * @param excludedProductCategories List of category IDs the coupon does not apply to.
         * @return T
         */
        public T setExcludedProductCategories(List<Integer> excludedProductCategories) {
            this.excludedProductCategories = excludedProductCategories;
            return self();
        }

        /**
         * @param excludeSaleItems If true, this coupon will not be applied to items that have sale prices.
         *                         Default is false.
         * @return T
         */
        public T setExcludeSaleItems(Boolean excludeSaleItems) {
            this.excludeSaleItems = excludeSaleItems;
            return self();
        }

        /**
         * @param minimumAmount Minimum order amount that needs to be in the cart before coupon applies.
         * @return T
         */
        public T setMinimumAmount(BigDecimal minimumAmount) {
            this.minimumAmount = minimumAmount;
            return self();
        }

        /**
         * @param maximumAmount Maximum order amount allowed when using the coupon.
         * @return T
         */
        public T setMaximumAmount(BigDecimal maximumAmount) {
            this.maximumAmount = maximumAmount;
            return self();
        }

        /**
         * @param emailRestrictions List of email addresses that can use this coupon.
         * @return T
         */
        public T setEmailRestrictions(List<String> emailRestrictions) {
            this.emailRestrictions = emailRestrictions;
            return self();
        }

        /**
         * @param metaData	array	Meta data. See Coupon - Meta data properties
         * @return T
         */
        public T setMetaData(List<MetaData> metaData) {
            this.metaData = metaData;
            return self();
        }

        protected CouponBuilder build(){
            return new CouponBuilder(this);
        }

        /** Returns single Created Coupon, unless it is a duplicate! **/
        public Created<Coupon> getResponse(){
            if (code == null || code.isEmpty()) {
                return new Created<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Code is MANDATORY!")
                );
            }else {

                CouponBuilder create = build();
                return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<Coupon>(){});

                //make the call
                /*return new Created<>(
                    new Rest<Coupon>().create(create.endPoint(), create.toJson(), new TypeReference<Coupon>(){})
                );*/
            }
        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private final int id;

        public Updater(Coupon coupon){
            super(coupon);
            this.id = coupon.getId();
        }

        public Updater(int couponId){
            this.id = couponId;
        }

        @Override
        protected CouponBuilder build(){
            return new CouponBuilder(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public Updated<Coupon> getResponse(){
            if (id > 0) {

                CouponBuilder create = build();
                //make the call
                /*return new Updated<>(
                    new Rest<Coupon>().update(create.endPoint(), create.toJson(), new TypeReference<Coupon>(){})
                );*/
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<Coupon>(){});

            }else{
                return new Updated<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Coupon Id is MANDATORY!")
                );
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<Coupon>{

        public Reader(int couponId){
            super(couponId);
        }

        public Read<Coupon> getResponse(){
            return super.getResponse(COUPONS, new TypeReference<Coupon>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<Coupon>{

        public Deleter(int couponId, boolean force){
            super(couponId, force);
        }

        protected CouponBuilder build(){
            return new CouponBuilder(this);
        }

        public Deleted<Coupon> getResponse(){
            return super.getResponse(COUPONS, new TypeReference<Coupon>() {});
        }

    }
    //</editor-fold>

    public static class ListAll<T extends ListAll<T>> extends CoreSeek.Searcher<Coupon, T> {

        /**
         *
         * @param after Limit response to resources published after a given ISO8601 compliant date.
         * @return T
         */
        public T setAfter(LocalDate after) {
            addNameValuePair("after", after);
            return self();
        }
        /**
         *
         * @param before Limit response to resources published before a given ISO8601 compliant date.
         * @return T
         */
        public T setBefore(LocalDate before) {
            addNameValuePair("before", before);
            return self();
        }

        /**
         *
         * @param modifiedAfter Limit response to resources modified after a given ISO8601 compliant date.
         * @return T
         */
        public T setModifiedAfter(LocalDate modifiedAfter) {
            addNameValuePair("modified_after", modifiedAfter);
            return self();
        }
        /**
         *
         * @param modifiedBefore Limit response to resources modified before a given ISO8601 compliant date.
         * @return T
         */
        public T setModifiedBefore(LocalDate modifiedBefore) {
            addNameValuePair("modified_before", modifiedBefore);
            return self();
        }

        /**
         *
         * @param datesAreGMT 	Whether to consider GMT post dates when limiting response by published or modified date.
         * @return T
         */
        public T setDatesAreGMT(boolean datesAreGMT) {
            addNameValuePair("dates_are_gmt", datesAreGMT);
            return self();
        }

        /**
         *
         * @param offset Offset the result set by a specific number of items.
         * @return T
         */
        public T setOffset(int offset) {
            addNameValuePair("offset", offset);
            return self();
        }

        /**
         * @param code Limit result set to resources with a specific code.
         * @return T
         */
        public T setCode(String code) {
            addNameValuePair("code", code);
            return self();
        }

        public Listed<Coupon> getResponse(){

            return super.getResponse(COUPONS, build(), new TypeReference<List<Coupon>>() {});

            //return new Listed<>(

                /*new Rest<List<Coupon>>().listAll(
                    COUPONS,
                    build(),
                    new TypeReference<List<Coupon>>() {}
                )*/
            //);

        }

    }

    public static class Batcher<T extends Batcher<T>>  extends CoreBatch.BatchCore<Coupon, T>{

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            return addCreator(create.build().coupon);
        }

        public T addCreator(List<Coupon> create){
            return super.addCreate(create);
        }

        public T addCreator(Coupon create){
            return super.addCreate(create);
        }

        public T addUpdater(Updater<?> update){
            return addUpdater(update.build().coupon);
        }

        public T addUpdater(List<Coupon> updateList){
            return super.addUpdate(updateList);
        }

        public T addUpdater(Coupon update){
            return super.addUpdate(update);
        }

        public T addDeleter(Deleter delete){
            return addDeleter(delete.build().coupon);
        }

        public T addDeleter(List<Coupon> deleteList){
            for (Coupon delete : deleteList) {
                addDeleter(delete);
            }
            return self();
        }

        public T addDeleter(Coupon delete){
            return super.addDelete(delete.getId());
        }

        /** Returns list of amended Coupons **/
        public Batched<Coupon> getResponse(){

            //pre-validate
            for (int i = 0; i < batch.getUpdate().size(); i++){
                if (batch.getUpdate().get(i).getId() == 0){
                    return super.getFailure(
                        String.format("Id is MANDATORY!, Found Update @ %s with id = 0", i)
                    );
                }
            }

            //delete validation is in super
            return super.getResponse(COUPONS, batch, new TypeReference<BatchResult<Coupon>>() {});

        }

    }

}
