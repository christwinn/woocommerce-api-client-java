/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ShippingZoneMethodBuilder;

public class ShippingZoneMethods {

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ShippingZoneMethodBuilder.Creator<?> create(int shippingZoneId){
        //--breaks the mould!
        return new ShippingZoneMethodBuilder.Creator<>(shippingZoneId);

    }

    public static ShippingZoneMethodBuilder.Reader<?> read(int shippingZoneId, int instanceId){
        //--breaks the mould!
        return new ShippingZoneMethodBuilder.Reader<>(shippingZoneId, instanceId);

    }
    public static ShippingZoneMethodBuilder.Updater<?> update(int shippingZoneId, int instanceId){
        //--breaks the mould!
        return new ShippingZoneMethodBuilder.Updater<>(shippingZoneId, instanceId);

    }

    public static ShippingZoneMethodBuilder.Deleter<?> delete(int shippingZoneId, int instanceId, boolean force){
        //--breaks the mould!
        return new ShippingZoneMethodBuilder.Deleter<>(shippingZoneId, instanceId, force);

    }


    public static ShippingZoneMethodBuilder.ListAll<?> listing(int zoneId){

        return new ShippingZoneMethodBuilder.ListAll<>(zoneId);

    }
    //</editor-fold>
}
