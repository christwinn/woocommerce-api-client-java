/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductCategoryBuilder;

public class ProductCategories {

    private ProductCategories(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductCategoryBuilder.Creator<?> create(){

        return new ProductCategoryBuilder.Creator<>();

    }

    public static ProductCategoryBuilder.Reader<?> read(int productCategoryId){

        return new ProductCategoryBuilder.Reader<>(productCategoryId);

    }

    public static ProductCategoryBuilder.Updater<?> update(int productCategoryId){

        return new ProductCategoryBuilder.Updater<>(productCategoryId);

    }

    public static ProductCategoryBuilder.Deleter<?> delete(int productCategoryId, boolean force){

        return new ProductCategoryBuilder.Deleter<>(productCategoryId, force);

    }

    public static ProductCategoryBuilder.Batcher<?> batch(){

        return new ProductCategoryBuilder.Batcher<>();

    }
    public static ProductCategoryBuilder.ListAll<?> listing(){

        return new ProductCategoryBuilder.ListAll<>();

    }
    //</editor-fold>


}
