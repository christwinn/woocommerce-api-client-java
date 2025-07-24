/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductTagBuilder;
import uk.co.twinn.pl_wtx_woocommerce.model.product.ProductTag;

public class ProductTags {

    protected ProductTags(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductTagBuilder.Creator<?> create(String name){

        return new ProductTagBuilder.Creator<>(name);

    }

    public static ProductTagBuilder.Creator<?> create(ProductTag productTag){

        return new ProductTagBuilder.Creator<>(productTag);

    }

    public static ProductTagBuilder.Reader read(int productTagId){

        return new ProductTagBuilder.Reader(productTagId);

    }

    public static ProductTagBuilder.Updater<?> update(int productTagId){

        return new ProductTagBuilder.Updater<>(productTagId);

    }

    public static ProductTagBuilder.Updater<?> update(ProductTag productTag){

        return new ProductTagBuilder.Updater<>(productTag);

    }

    public static ProductTagBuilder.Deleter delete(int productTagId, boolean force){

        return new ProductTagBuilder.Deleter(productTagId, force);

    }

    public static ProductTagBuilder.Batcher<?> batch(){

        return new ProductTagBuilder.Batcher<>();

    }
    public static ProductTagBuilder.ListAll<?> listing(){

        return new ProductTagBuilder.ListAll<>();

    }
    //</editor-fold>

}
