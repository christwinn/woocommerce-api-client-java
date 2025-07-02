/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.crudPlusActionBuilder.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import pl.wtx.woocommerce.api.client.model.Coupon;
import pl.wtx.woocommerce.api.client.model.MetaData;
import pl.wtx.woocommerce.api.client.model.Order;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.ApiRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.Seek;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.CouponResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.OrderResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.WooCommerce;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.wtx.woocommerce.crudPlusActionBuilder.defines.EndPoints.*;

public class CouponRequest extends ApiRequest {

    private final Coupon coupon = new Coupon();

    private Batch batch;
    private boolean force;

    private CouponRequest(@SuppressWarnings("rawtypes") Creator creator){

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
        coupon.setEmailRestrictions(creator.emailRestrictions);
        coupon.setMetaData(creator.metaData);

    }

    private CouponRequest(Reader reader){

        coupon.setId(reader.couponId);

    }

    private CouponRequest(Updater updater){

        this((Creator)updater);
        coupon.setId(updater.id);

    }

    private CouponRequest(Deleter deleter){

        this((Reader)deleter);
        force = deleter.force;

    }

    private CouponRequest(Batcher batcher){

        batch = batcher.getBatch();
        force = false;

    }

    public String endPoint(){

        return
            getEndPoint() +
                (coupon.getId() != null && coupon.getId() != 0
                    ? ("/" + coupon.getId())
                    : ""
                ) +
                (batch != null
                    ? "/batch"
                    : ""
                ) +
                (force
                    ? "?force=true"
                    : ""
                );

    }

    private static String getEndPoint(){

        return COUPONS;

    }

    public String toJson(){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(coupon);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

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

        protected CouponRequest build(){
            return new CouponRequest(this);
        }

        /** Returns single Created Coupon, unless it is a duplicate! **/
        public CouponResponse getResponse(){
            if (code == null || code.isEmpty()) {
                return new CouponResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "Code is MANDATORY!")
                );
            }else {
                return new WooCommerce().create(build());
            }
        }

    }

    public static class Reader<T extends Reader<T>>{

        protected int couponId;

        T self() {
            return (T) this;
        }

        public T setCouponId(int couponId){
            this.couponId = couponId;
            return self();
        }

        protected CouponRequest build(){
            return new CouponRequest(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public CouponResponse getResponse(){

            if (couponId > 0) {
                return new WooCommerce().read(build());
            }else{
                return new CouponResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "Coupon Id is MANDATORY!\nUse Lister to ListAll")
                );
            }

        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private int id;

        public T setId(int id){
            this.id = id;
            return self();
        }

        @Override
        protected CouponRequest build(){
            return new CouponRequest(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public CouponResponse getResponse(){
            if (id > 0) {
                return new WooCommerce().update(build());
            }else{
                return new CouponResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "Coupon Id is MANDATORY!")
                );
            }
        }

    }

    public static class Deleter<T extends Deleter<T>> extends Reader<T> {

        private boolean force;

        public T force(boolean force){
            this.force = force;
            return self();
        }

        @Override
        protected CouponRequest build(){
            return new CouponRequest(this);
        }

        /** Returns single Deleted ProductCategory**/
        @Override
        public CouponResponse getResponse(){
            if (couponId == 0 || !force) {
                return new CouponResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "Coupon Id AND use of the Force is MANDATORY!")
                );
            }else {
                return new WooCommerce().delete(build());
            }
        }

    }

    public static class ListAll<T extends ListAll<T>>{

        public CouponResponse getResponse(){

            return new Searcher<>().getResponse();

        }

    }

    public static class Searcher<T extends Searcher> extends Seek.Searcher<T> {

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

        public CouponResponse getResponse(){

            return new CouponResponse(
                new WooCommerce().search(
                    getEndPoint(),
                    build(),
                    new TypeReference<List<Coupon>>(){}
                )
            );

        }

    }

    private static class Batch{

        private final List<Coupon> create;
        private final List<Coupon> update;
        private final List<Coupon> delete;

        public Batch(){

            create = new ArrayList<>();
            update = new ArrayList<>();
            delete = new ArrayList<>();

        }

        public int getRecordCount(){
            return create.size() + update.size() + delete.size();
        }

        public boolean isEmpty(){
            return create.isEmpty() && update.isEmpty() && delete.isEmpty();
        }

        public void addCreator(Creator create){
            this.create.add(create.build().coupon);
        }

        public void addUpdater(Updater update){
            this.update.add(update.build().coupon);
        }

        public void addDeleter(Deleter delete){
            this.delete.add(delete.build().coupon);
        }

        public List<Coupon> getCreate(){
            return create;
        }
        public List<Coupon> getUpdate(){
            return update;
        }
        public List<Coupon> getDelete(){
            return delete;
        }

    }

    public static class Batcher<T extends Batcher>{

        private static Batch batch;

        public Batcher(){
            batch = new Batch();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator create){
            batch.addCreator(create);
            return self();
        }

        public T addUpdater(Updater update){
            batch.addUpdater(update);
            return self();
        }

        public T addDeleter(Deleter delete){
            batch.addDeleter(delete);
            return self();
        }

        private Batch getBatch(){
            return batch;
        }

        private CouponRequest build(){
            return new CouponRequest(this);
        }

        /** Returns list of amended Coupons **/
        public CouponResponse getResponse(){

            if (batch.isEmpty()){

                return new CouponResponse(new ApiResponseResult(false, 0, "Nothing to do"));

            }else if (batch.getRecordCount() > 100){

                return new CouponResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "https://woocommerce.github.io/woocommerce-rest-api-docs/#batch-update-coupons\n" +
                            "This API helps you to batch create, update and delete multiple products.\n\n" +
                            "Note: By default it's limited to up to 100 objects to be created, updated or deleted.")
                );

            }else{

                return new WooCommerce().create(build());

            }

        }

    }

}
