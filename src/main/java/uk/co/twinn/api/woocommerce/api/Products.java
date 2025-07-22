/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductBuilder;
import uk.co.twinn.pl_wtx_woocommerce.model.Product;

public class Products {

    private Products(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductBuilder.Creator<?> create(){

        return new ProductBuilder.Creator<>();

    }

    public static ProductBuilder.Creator<?> create(Product product){

        return new ProductBuilder.Creator<>(product);

    }

    public static ProductBuilder.Reader read(int productId){

        return new ProductBuilder.Reader(productId);

    }

    public static ProductBuilder.Updater<?> update(int productId){

        return new ProductBuilder.Updater<>(productId);

    }

    public static ProductBuilder.Updater<?> update(Product product){

        return new ProductBuilder.Updater<>(product);

    }

    public static ProductBuilder.Deleter delete(int productId, boolean force){

        return new ProductBuilder.Deleter(productId, force);

    }

    public static ProductBuilder.Duplicator duplicate(int productId){

        return new ProductBuilder.Duplicator(productId);

    }

    public static ProductBuilder.Batcher<?> batch(){

        return new ProductBuilder.Batcher<>();

    }
    public static ProductBuilder.ListAll<?> listing(){

        return new ProductBuilder.ListAll<>();

    }
    //</editor-fold>

}
