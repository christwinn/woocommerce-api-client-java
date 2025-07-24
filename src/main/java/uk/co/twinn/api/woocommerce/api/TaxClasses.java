/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.TaxClassBuilder;

public class TaxClasses {

    protected TaxClasses(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static TaxClassBuilder.Creator<?> create(String name){

        return new TaxClassBuilder.Creator<>(name);

    }

    public static TaxClassBuilder.Deleter delete(String taxClassSlug, boolean force){

        return new TaxClassBuilder.Deleter(taxClassSlug, force);

    }

    public static TaxClassBuilder.ListAll<?> listing(){

        return new TaxClassBuilder.ListAll<>();

    }
    //</editor-fold>

}
