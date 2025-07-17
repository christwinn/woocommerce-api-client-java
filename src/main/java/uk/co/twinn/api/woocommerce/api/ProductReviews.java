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

public class ProductReviews {

    private ProductReviews(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductReviewBuilder.Creator<?> create(){

        return new ProductReviewBuilder.Creator<>();

    }

    public static ProductReviewBuilder.Reader<?> read(int reviewId){

        return new ProductReviewBuilder.Reader<>(reviewId);

    }

    public static ProductReviewBuilder.Updater<?> update(int reviewId){

        return new ProductReviewBuilder.Updater<>(reviewId);

    }

    public static ProductReviewBuilder.Deleter<?> delete(int reviewId, boolean force){

        return new ProductReviewBuilder.Deleter<>(reviewId, force);

    }

    public static ProductReviewBuilder.Batcher<?> batch(){

        return new ProductReviewBuilder.Batcher<>();

    }
    public static ProductReviewBuilder.ListAll<?> listing(){

        return new ProductReviewBuilder.ListAll<>();

    }
    //</editor-fold>

}
