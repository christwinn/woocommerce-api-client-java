/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.pl_wtx_woocommerce.model.Coupon;
import uk.co.twinn.pl_wtx_woocommerce.model.MetaData;
import uk.co.twinn.api.woocommerce.request.core.ApiRequest;
import uk.co.twinn.api.woocommerce.request.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.COUPONS;

public class CouponApi extends ApiRequest {

    private final Coupon coupon = new Coupon();

    private boolean force;

    public CouponApi(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public Creator<?> create(){

        return new Creator<>();

    }

    public Reader<?> read(int couponId){

        return new Reader<>(couponId);

    }

    public Updater<?> update(int couponId){

        return new Updater<>(couponId);

    }

    public Deleter<?> delete(int couponId, boolean force){

        return new Deleter<>(couponId, force);

    }

    public Batcher<?> batch(){

        return new Batcher<>();

    }
    public ListAll<?> listing(){

        return new ListAll<>();

    }
    //</editor-fold>

    private CouponApi(Creator<?> creator){

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

    private CouponApi(Updater<?> updater){

        this((Creator)updater);
        coupon.setId(updater.id);

    }

    private CouponApi(Deleter<?> deleter){

        coupon.setId(deleter.id);
        force = deleter.force;

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

    public static class Creator<T extends Creator<?>>{

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


        T self() {
            return (T) this;
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

        protected CouponApi build(){
            return new CouponApi(this);
        }

        /** Returns single Created Coupon, unless it is a duplicate! **/
        public Created<Coupon> getResponse(){
            if (code == null || code.isEmpty()) {
                return new Created<Coupon>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Code is MANDATORY!")
                );
            }else {
                CouponApi create = build();
                //make the call
                return new Created<>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<Coupon>(){})
                );
            }
        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private int id;

        public Updater(int couponId){
            this.id = couponId;
        }

        /*public T setId(int id){
            this.id = id;
            return self();
        }*/

        @Override
        protected CouponApi build(){
            return new CouponApi(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public Updated<Coupon> getResponse(){
            if (id > 0) {
                CouponApi create = build();
                //make the call
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<Coupon>(){})
                );

            }else{
                return new Updated<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Coupon Id is MANDATORY!")
                );
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ReaderCore<T>{

        public Reader(int couponId){
            super(couponId);
        }

        public Read<Coupon> getResponse(){
            return (Read<Coupon>)super.getResponse(COUPONS, new TypeReference<Coupon>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.DeleterCore<T>{

        public Deleter(int couponId, boolean force){
            super(couponId, force);
        }

        protected CouponApi build(){
            return new CouponApi(this);
        }

        public Deleted<Coupon> getResponse(){
            return (Deleted<Coupon>)super.getResponse(COUPONS, new TypeReference<Coupon>() {});

        }

    }
    //</editor-fold>

    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T> {

        T self() {
            return (T) this;
        }

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

            return new Listed<Coupon>(
                new Rest().listAll(
                    COUPONS,
                    build(),
                    new TypeReference<List<Coupon>>(){}
                )
            );

        }

    }

    public static class Batcher<T extends Batcher<T>>  extends CoreBatchRequest.BatchCore<T>{

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().coupon);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().coupon);
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().coupon);
            return self();
        }

        /** Returns list of amended Coupons **/
        public Batched<Coupon> getResponse(){

            return (Batched<Coupon>) super.getResponse(COUPONS, batch, new TypeReference<Batch<Coupon>>(){});

        }

    }

}
