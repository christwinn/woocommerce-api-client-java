/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ProductCustomFieldBuilder;

public class ProductCustomFields {

    protected ProductCustomFields(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ProductCustomFieldBuilder.ListAll<?> listing(){

        return new ProductCustomFieldBuilder.ListAll<>();

    }
    //</editor-fold>

}
