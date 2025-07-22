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
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.builders.core.Batch;
import uk.co.twinn.api.woocommerce.builders.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductReview;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS_REVIEWS;

public class ProductReviewBuilder extends ApiRequest {

    protected final ProductReview productReview = new ProductReview();

    public ProductReviewBuilder(){}

    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ProductReviewBuilder(Creator<?> creator){

        productReview.setProductId(creator.productId);
        productReview.setStatus(creator.status);        //string	Product name.
        productReview.setReviewer(creator.reviewer);        //string	Product slug.
        productReview.setReviewerEmail(creator.reviewerEmail);
        productReview.setReview(creator.review);
        productReview.setRating(creator.rating);
        productReview.setVerified(creator.verified);

    }

    private ProductReviewBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        productReview.setId(updater.id);

    }

    private ProductReviewBuilder(Deleter<?> deleter){

        productReview.setId(deleter.id);

    }

    public String toJson(){

        try {

            return getObjectMapper().writeValueAsString(productReview);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public String endPoint(){

        return PRODUCTS_REVIEWS +
            (productReview.getId() != null && productReview.getId() != 0
                ? ("/" + productReview.getId())
                : ""
            );

    }

    public static class Creator<T extends Creator<?>>{

        private Integer productId;
        private ProductReview.StatusEnum status;        //string	Product name.
        private String reviewer;        //string	Product slug.
        private String reviewerEmail;
        private String review;
        private Integer rating;
        private Boolean verified;


        private Creator(){}

        public Creator(int productId){
            this.productId = productId;
        }

        public Creator(ProductReview productReview){
            this(productReview.getProductId() == null ? 0 : productReview.getProductId());
            status = productReview.getStatus();        //string	Product name.
            reviewer = productReview.getReviewer();        //string	Product slug.
            reviewerEmail = productReview.getReviewerEmail();
            review = productReview.getReview();
            rating = productReview.getRating();
            verified = productReview.getVerified();
        }
        public T setProductId(Integer productId) {
            this.productId = productId;
            return self();
        }

        /**
         * Status of the review.
         * Options: approved, hold, spam, unspam, trash and untrash.
         * Defaults to approved.
         */
        public T setStatus(String status) {
            return setStatus(ProductReview.StatusEnum.fromValue(status));
        }

        public T setStatus(ProductReview.StatusEnum status) {
            this.status = status;
            return self();
        }

        public T setReviewer(String reviewer) {
            this.reviewer = reviewer;
            return self();
        }

        public T setReviewerEmail(String reviewerEmail) {
            this.reviewerEmail = reviewerEmail;
            return self();
        }

        public T setReview(String review) {
            this.review = review;
            return self();
        }

        public T setRating(Integer rating) {
            this.rating = rating;
            return self();
        }

        public T setVerified(Boolean verified) {
            this.verified = verified;
            return self();
        }

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        private ProductReviewBuilder build(){
            return new ProductReviewBuilder(this);
        }

        public Created<ProductReview> getResponse(){

            if (productId == null || productId == 0){
                return new Created<>(new ApiResponseResult(false, 0, "Review MUST belong to a Product Id"));
            }else {
                ProductReviewBuilder create = build();
                //make the call
                return new Created<>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductReview>() {})
                );
            }

        }


    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        public Updater(int reviewId){
            this.id = reviewId;
        }

        public Updater(ProductReview productReview) {
            super(productReview);
            if (productReview.getId() != null){
                this.id = productReview.getId();
            }else{
                this.id = 0;
            }
        }

        private final int id;

        private ProductReviewBuilder build(){
            return new ProductReviewBuilder(this);
        }

        @Override
        public Updated<ProductReview> getResponse(){
            if (id > 0){

                ProductReviewBuilder create = build();
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ProductReview>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReader.ReaderCore<T>{

        public Reader(int reviewId){
            super(reviewId);
        }

        @SuppressWarnings("unchecked")
        public Read<ProductReview> getResponse(){
            return (Read<ProductReview>)super.getResponse(PRODUCTS_REVIEWS, new TypeReference<ProductReview>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleter.DeleterCore<T>{

        public Deleter(int reviewId, boolean force){
            super(reviewId, force);
        }

        protected ProductReviewBuilder build(){
            return new ProductReviewBuilder(this);
        }

        @SuppressWarnings("unchecked")
        public Deleted<ProductReview> getResponse(){
            return (Deleted<ProductReview>)super.getResponse(PRODUCTS_REVIEWS, new TypeReference<ProductReview>() {});

        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<ProductReview, T>{

        public Batcher(){
            super();
        }
        @SuppressWarnings("unchecked")
        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().productReview);
            return self();
        }
        @SuppressWarnings("unchecked")
        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().productReview);
            return self();
        }
        @SuppressWarnings("unchecked")
        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().productReview.getId());
            return self();
        }
        @SuppressWarnings("unchecked")
        public Batched<ProductReview> getResponse(){
            return (Batched<ProductReview>) super.getResponse(PRODUCTS_REVIEWS, batch, new TypeReference<BatchResult<ProductReview>>(){});
        }

    }

    public static class ListAll<T extends ProductReviewBuilder.ListAll<T>> extends Seek.Searcher<T> {

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        /**
         *
         * @param hideEmpty	boolean	Whether to hide resources not assigned to any products. Default is false.
         * @return T
         */
        public T setHideEmpty(boolean hideEmpty) {
            addNameValuePair("hide_empty", hideEmpty);
            return self();
        }
        /**
         *
         * @param product	integer	Limit result set to resources assigned to a specific product.
         * @return T
         */
        public T setProduct(int product) {
            addNameValuePair("product", product);
            return self();
        }
        /**
         *
         * @param slug	string	Limit result set to resources with a specific slug.
         * @return T
         */
        public T setSlug(String slug) {
            addNameValuePair("slug", slug);
            return self();
        }

        public Listed<ProductReview> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    PRODUCTS_REVIEWS,
                    build(),
                    new TypeReference<List<ProductReview>>(){}
                )
            );

        }

    }
}
