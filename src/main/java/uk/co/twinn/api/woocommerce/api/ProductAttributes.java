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

public class ProductAttributes {

    private ProductAttributes(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductAttributeBuilder.Creator<?> create(){

        return new ProductAttributeBuilder.Creator<>();

    }

    public static ProductAttributeBuilder.Reader<?> read(int productAttributeId){

        return new ProductAttributeBuilder.Reader<>(productAttributeId);

    }

    public static ProductAttributeBuilder.Updater<?> update(int productAttributeId){

        return new ProductAttributeBuilder.Updater<>(productAttributeId);

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
