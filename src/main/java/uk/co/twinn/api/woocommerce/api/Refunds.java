/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.RefundBuilder;

public class Refunds {

    private Refunds(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static RefundBuilder.ListAll<?> listing(){

        return new RefundBuilder.ListAll<>();

    }
    //</editor-fold>

}
