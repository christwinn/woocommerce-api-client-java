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
import uk.co.twinn.api.woocommerce.request.core.ApiRequest;
import uk.co.twinn.api.woocommerce.request.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductReview;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS_REVIEWS;

public class ProductReviewApi extends ApiRequest {

    protected final ProductReview productReview = new ProductReview();

    private boolean force;

    public ProductReviewApi(){}

    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ProductReviewApi(Creator<?> creator){

        productReview.setProductId(creator.productId);
        productReview.setStatus(creator.status);        //string	Product name.
        productReview.setReviewer(creator.reviewer);        //string	Product slug.
        productReview.setReviewerEmail(creator.reviewerEmail);
        productReview.setReview(creator.review);
        productReview.setRating(creator.rating);
        productReview.setVerified(creator.verified);

    }

    private ProductReviewApi(Updater<?> updater){

        this((Creator<?>)updater);
        productReview.setId(updater.id);

    }

    private ProductReviewApi(Deleter<?> deleter){

        productReview.setId(deleter.id);
        force = deleter.force;

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

        T self() {
            return (T) this;
        }

        private ProductReviewApi build(){
            return new ProductReviewApi(this);
        }

        public Created<ProductReview> getResponse(){

            if (productId == null || productId == 0){
                return new Created<>(new ApiResponseResult(false, 0, "Review MUST belong to a Product Id"));
            }else {
                ProductReviewApi create = build();
                //make the call
                return new Created<>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductReview>() {})
                );
            }

        }


    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private int id;

        public T setId(int id) {
            this.id = id;
            return self();
        }

        private ProductReviewApi build(){
            return new ProductReviewApi(this);
        }

        @Override
        public Updated<ProductReview> getResponse(){
            if (id > 0){

                ProductReviewApi create = build();
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ProductReview>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ReaderCore<T>{

        @Override
        T self() {return (T) this;}

        public Read<ProductReview> getResponse(){
            return (Read<ProductReview>)super.getResponse(PRODUCTS_REVIEWS, new TypeReference<ProductReview>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.DeleterCore<T>{

        @Override
        T self() {return (T) this;}

        protected ProductReviewApi build(){
            return new ProductReviewApi(this);
        }

        public Deleted<ProductReview> getResponse(){
            return (Deleted<ProductReview>)super.getResponse(PRODUCTS_REVIEWS, new TypeReference<ProductReview>() {});

        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatchRequest.BatchCore<T>{

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().productReview);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().productReview);
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().productReview);
            return self();
        }

        public Batched<ProductReview> getResponse(){

            return (Batched<ProductReview>) super.getResponse(PRODUCTS_REVIEWS, batch, new TypeReference<Batch<ProductReview>>(){});

        }

    }

    public static class ListAll<T extends ProductReviewApi.ListAll<T>> extends Seek.Searcher<T> {

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
