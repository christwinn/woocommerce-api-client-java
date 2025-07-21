/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductAttributeBuilder;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductAttribute;

public class ProductAttributes {

    private ProductAttributes(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductAttributeBuilder.Creator<?> create(String name){

        return new ProductAttributeBuilder.Creator<>(name);

    }

    public static ProductAttributeBuilder.Creator<?> create(ProductAttribute productAttribute){

        return new ProductAttributeBuilder.Creator<>(productAttribute);

    }

    public static ProductAttributeBuilder.Reader<?> read(int productAttributeId){

        return new ProductAttributeBuilder.Reader<>(productAttributeId);

    }

    public static ProductAttributeBuilder.Updater<?> update(int productAttributeId){

        return new ProductAttributeBuilder.Updater<>(productAttributeId);

    }

    public static ProductAttributeBuilder.Updater<?> update(ProductAttribute productAttribute){

        return new ProductAttributeBuilder.Updater<>(productAttribute);

    }

    public static ProductAttributeBuilder.Deleter<?> delete(int productAttributeId, boolean force){

        return new ProductAttributeBuilder.Deleter<>(productAttributeId, force);

    }

    public static ProductAttributeBuilder.Batcher<?> batch(){

        return new ProductAttributeBuilder.Batcher<>();

    }
    public static ProductAttributeBuilder.ListAll<?> listing(){

        return new ProductAttributeBuilder.ListAll<>();

    }
    //</editor-fold>

}
