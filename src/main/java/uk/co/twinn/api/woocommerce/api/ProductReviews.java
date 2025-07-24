/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductReviewBuilder;
import uk.co.twinn.pl_wtx_woocommerce.model.product.ProductReview;

public class ProductReviews {

    protected ProductReviews(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductReviewBuilder.Creator<?> create(int productId){

        return new ProductReviewBuilder.Creator<>(productId);

    }

    public static ProductReviewBuilder.Creator<?> create(ProductReview productReview){

        return new ProductReviewBuilder.Creator<>(productReview);

    }

    public static ProductReviewBuilder.Reader read(int reviewId){

        return new ProductReviewBuilder.Reader(reviewId);

    }

    public static ProductReviewBuilder.Updater<?> update(int reviewId){

        return new ProductReviewBuilder.Updater<>(reviewId);

    }

    public static ProductReviewBuilder.Updater<?> update(ProductReview productReview){

        return new ProductReviewBuilder.Updater<>(productReview);

    }

    public static ProductReviewBuilder.Deleter delete(int reviewId, boolean force){

        return new ProductReviewBuilder.Deleter(reviewId, force);

    }

    public static ProductReviewBuilder.Batcher<?> batch(){

        return new ProductReviewBuilder.Batcher<>();

    }
    public static ProductReviewBuilder.ListAll<?> listing(){

        return new ProductReviewBuilder.ListAll<>();

    }
    //</editor-fold>

}
