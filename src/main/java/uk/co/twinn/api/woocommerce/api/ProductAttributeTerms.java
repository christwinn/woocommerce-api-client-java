/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductAttributeTermBuilder;

public class ProductAttributeTerms {

    private ProductAttributeTerms(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductAttributeTermBuilder.Creator<?> create(){

        return new ProductAttributeTermBuilder.Creator<>();

    }

    public static ProductAttributeTermBuilder.Reader<?> read(int attributeId, int termsId){

        return new ProductAttributeTermBuilder.Reader<>(attributeId, termsId);

    }

    public static ProductAttributeTermBuilder.Updater<?> update(int attributeId, int termsId){

        return new ProductAttributeTermBuilder.Updater<>(attributeId, termsId);

    }

    public static ProductAttributeTermBuilder.Deleter<?> delete(int attributeId, int termsId, boolean force){

        return new ProductAttributeTermBuilder.Deleter<>(attributeId, termsId, force);

    }

    public static ProductAttributeTermBuilder.Batcher<?> batch(){

        return new ProductAttributeTermBuilder.Batcher<>();

    }
    public static ProductAttributeTermBuilder.ListAll<?> listing(){

        return new ProductAttributeTermBuilder.ListAll<>();

    }
    //</editor-fold>

}
