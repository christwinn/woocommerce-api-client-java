/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductBrandBuilder;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductBrand;

public class ProductBrands {

    protected ProductBrands(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductBrandBuilder.Creator<?> create(String name){

        return new ProductBrandBuilder.Creator<>(name);

    }

    public static ProductBrandBuilder.Creator<?> create(ProductBrand productBrand){

        return new ProductBrandBuilder.Creator<>(productBrand);

    }

    public static ProductBrandBuilder.Reader read(int productBrandId){

        return new ProductBrandBuilder.Reader(productBrandId);

    }

    public static ProductBrandBuilder.Updater<?> update(ProductBrand productBrand){

        return new ProductBrandBuilder.Updater<>(productBrand);

    }

    public static ProductBrandBuilder.Updater<?> update(int productBrandId){

        return new ProductBrandBuilder.Updater<>(productBrandId);

    }

    public static ProductBrandBuilder.Deleter delete(int productBrandId, boolean force){

        return new ProductBrandBuilder.Deleter(productBrandId, force);

    }

    public static ProductBrandBuilder.Batcher<?> batch(){

        return new ProductBrandBuilder.Batcher<>();

    }
    public static ProductBrandBuilder.ListAll<?> listing(){

        return new ProductBrandBuilder.ListAll<>();

    }
    //</editor-fold>

}
