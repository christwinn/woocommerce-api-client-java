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

public class ProductTags {

    private ProductTags(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductTagBuilder.Creator<?> create(){

        return new ProductTagBuilder.Creator<>();

    }

    public static ProductTagBuilder.Reader<?> read(int productTagId){

        return new ProductTagBuilder.Reader<>(productTagId);

    }

    public static ProductTagBuilder.Updater<?> update(int productTagId){

        return new ProductTagBuilder.Updater<>(productTagId);

    }

    public static ProductTagBuilder.Deleter<?> delete(int productTagId, boolean force){

        return new ProductTagBuilder.Deleter<>(productTagId, force);

    }

    public static ProductTagBuilder.Batcher<?> batch(){

        return new ProductTagBuilder.Batcher<>();

    }
    public static ProductTagBuilder.ListAll<?> listing(){

        return new ProductTagBuilder.ListAll<>();

    }
    //</editor-fold>

}
