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
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductCategory;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;

import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductReview;

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

    private ProductReviewBuilder(Deleter deleter){

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

    public static class Creator<T extends Creator<T>> extends CoreCreator<ProductReview, T>{

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

        private ProductReviewBuilder build(){
            return new ProductReviewBuilder(this);
        }

        public Created<ProductReview> getResponse(){

            if (productId == null || productId == 0){
                return new Created<>(new ApiResponseResult<>(false, 0, "Review MUST belong to a Product Id"));
            }else {
                ProductReviewBuilder create = build();
                return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<ProductReview>() {});
                //make the call
                /*return new Created<>(
                    new Rest<ProductReview>().create(create.endPoint(), create.toJson())
                );*/
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
                /*return new Updated<>(
                    new Rest<ProductReview>().update(create.endPoint(), create.toJson())
                );*/
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<ProductReview>() {});

            }else{
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<ProductReview>{

        public Reader(int reviewId){
            super(reviewId);
        }

        public Read<ProductReview> getResponse(){
            return super.getResponse(PRODUCTS_REVIEWS, new TypeReference<ProductReview>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<ProductReview>{

        public Deleter(int reviewId, boolean force){
            super(reviewId, force);
        }

        protected ProductReviewBuilder build(){
            return new ProductReviewBuilder(this);
        }

        public Deleted<ProductReview> getResponse(){
            return super.getResponse(PRODUCTS_REVIEWS, new TypeReference<ProductReview>() {});
        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<ProductReview, T>{

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            addCreator(create.build().productReview);
            return self();
        }

        public T addUpdater(Updater<?> update){
            addUpdater(update.build().productReview);
            return self();
        }

        public T addDeleter(Deleter delete){
            addDeleter(delete.build().productReview);
            return self();
        }


        /*
         * these could go in CoreBatch with List<S>, etc.,
         * but then the ide pushes them down the parameter list
         * leaving here purely for end-user nicety
         **/
        public T addCreator(List<ProductReview> createList){
            for (ProductReview create : createList) {
                addCreator(create);
            }
            return self();
        }
        public T addUpdater(List<ProductReview> updateList){
            for (ProductReview update : updateList) {
                addUpdater(update);
            }
            return self();
        }
        public T addDeleter(List<ProductReview> deleteList){
            for (ProductReview delete : deleteList) {
                addDeleter(delete);
            }
            return self();
        }

        public T addCreator(ProductReview create){
            batch.addCreate(create);
            return self();
        }
        public T addUpdater(ProductReview update){
            batch.addUpdate(update);
            return self();
        }
        public T addDeleter(ProductReview delete){
            batch.addDelete(delete.getId());
            return self();
        }

        /** Returns list of amended ProductCategories **/
        public Batched<ProductReview> getResponse(){

            //pre-validate
            for (int i = 0; i < batch.getCreate().size(); i++) {
                if (batch.getCreate().get(i).getProductId() == null ||
                    batch.getCreate().get(i).getProductId() == 0) {
                    return super.getFailure(
                        String.format("Product Id is MANDATORY!, Found Create @ %s with ProductId == 0 or null", i)
                    );
                }
                //stripping the id if is set
                if (batch.getCreate().get(i).getId() != null) {
                    batch.getCreate().get(i).setId(null);
                }
            }

            for (int i = 0; i < batch.getUpdate().size(); i++) {
                if (batch.getUpdate().get(i).getId() == 0) {
                    return super.getFailure(
                        String.format("Id is MANDATORY!, Found Update @ %s with id = 0", i)
                    );
                }
            }

            return super.getResponse(PRODUCTS_REVIEWS, batch, new TypeReference<BatchResult<ProductReview>>() {});

        }

    }

    public static class ListAll<T extends ProductReviewBuilder.ListAll<T>> extends CoreSeek.Searcher<ProductReview, T> {

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

            return super.getResponse(
                PRODUCTS_REVIEWS,
                build(),
                new TypeReference<List<ProductReview>>() {}
            );

            /*return new Listed<>(
                new Rest<List<ProductReview>>().listAll(

                )
            );*/

        }

    }
}
