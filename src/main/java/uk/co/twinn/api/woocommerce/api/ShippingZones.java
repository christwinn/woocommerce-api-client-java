/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ShippingZoneBuilder;

public class ShippingZones {

    private ShippingZones(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ShippingZoneBuilder.Creator<?> create(String name){

        return new ShippingZoneBuilder.Creator<>(name);

    }

    public static ShippingZoneBuilder.Reader read(int shippingZoneId){

        return new ShippingZoneBuilder.Reader(shippingZoneId);

    }

    public static ShippingZoneBuilder.Updater<?> update(int shippingZoneId){

        return new ShippingZoneBuilder.Updater<>(shippingZoneId);

    }

    public static ShippingZoneBuilder.Deleter delete(int productId, boolean force){

        return new ShippingZoneBuilder.Deleter(productId, force);

    }

    public static ShippingZoneBuilder.ListAll<?> listing(){

        return new ShippingZoneBuilder.ListAll<>();

    }
    //</editor-fold>

}
