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
import uk.co.twinn.pl_wtx_woocommerce.model.ProductCategory;

public class ProductCategories {

    private ProductCategories(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductCategoryBuilder.Creator<?> create(String name){

        return new ProductCategoryBuilder.Creator<>(name);

    }

    public static ProductCategoryBuilder.Creator<?> create(ProductCategory productCategory){

        return new ProductCategoryBuilder.Creator<>(productCategory);

    }

    public static ProductCategoryBuilder.Reader read(int productCategoryId){

        return new ProductCategoryBuilder.Reader(productCategoryId);

    }

    public static ProductCategoryBuilder.Updater<?> update(ProductCategory productCategory){

        return new ProductCategoryBuilder.Updater<>(productCategory);

    }

    public static ProductCategoryBuilder.Updater<?> update(int productCategoryId){

        return new ProductCategoryBuilder.Updater<>(productCategoryId);

    }

    public static ProductCategoryBuilder.Deleter delete(int productCategoryId, boolean force){

        return new ProductCategoryBuilder.Deleter(productCategoryId, force);

    }

    public static ProductCategoryBuilder.Batcher<?> batch(){

        return new ProductCategoryBuilder.Batcher<>();

    }
    public static ProductCategoryBuilder.ListAll<?> listing(){

        return new ProductCategoryBuilder.ListAll<>();

    }
    //</editor-fold>


}
