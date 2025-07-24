/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductVariationBuilder;
import uk.co.twinn.pl_wtx_woocommerce.model.product.ProductVariation;

public class ProductVariations {

    protected ProductVariations(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductVariationBuilder.Creator<?> create(int productId){

        return new ProductVariationBuilder.Creator<>(productId);

    }

    public static ProductVariationBuilder.Creator<?> create(int productId, ProductVariation productVariation){

        return new ProductVariationBuilder.Creator<>(productId, productVariation);

    }

    public static ProductVariationBuilder.Reader read(int productId, int variationId){

        return new ProductVariationBuilder.Reader(productId, variationId);

    }

    public static ProductVariationBuilder.Updater<?> update(int productId, int variationId){

        return new ProductVariationBuilder.Updater<>(productId, variationId);

    }

    public static ProductVariationBuilder.Updater<?> update(int productId, ProductVariation productVariation){

        return new ProductVariationBuilder.Updater<>(productId, productVariation);

    }

    public static ProductVariationBuilder.Deleter delete(int productId, int variationId, boolean force){

        return new ProductVariationBuilder.Deleter(productId, variationId, force);

    }

    public static ProductVariationBuilder.Batcher<?> batch(int productId){

        return new ProductVariationBuilder.Batcher<>(productId);

    }
    public static ProductVariationBuilder.ListAll<?> listing(){

        return new ProductVariationBuilder.ListAll<>();

    }
    //</editor-fold>

}
