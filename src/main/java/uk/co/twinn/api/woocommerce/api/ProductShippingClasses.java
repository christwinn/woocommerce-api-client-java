/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductShippingClassBuilder;

public class ProductShippingClasses {

    private ProductShippingClasses(){ }

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductShippingClassBuilder.Creator<?> create(){

        return new ProductShippingClassBuilder.Creator<>();

    }

    public static ProductShippingClassBuilder.Reader<?> read(int shippingClassId){

        return new ProductShippingClassBuilder.Reader<>(shippingClassId);

    }

    public static ProductShippingClassBuilder.Updater<?> update(int shippingClassId){

        return new ProductShippingClassBuilder.Updater<>(shippingClassId);

    }

    public static ProductShippingClassBuilder.Deleter<?> delete(int shippingClassId, boolean force){

        return new ProductShippingClassBuilder.Deleter<>(shippingClassId, force);

    }

    public static ProductShippingClassBuilder.Batcher<?> batch(){

        return new ProductShippingClassBuilder.Batcher<>();

    }
    public static ProductShippingClassBuilder.ListAll<?> listing(){

        return new ProductShippingClassBuilder.ListAll<>();

    }
    //</editor-fold>

}
