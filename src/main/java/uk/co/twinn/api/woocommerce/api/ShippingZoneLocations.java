/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ShippingZoneLocationBuilder;

public class ShippingZoneLocations {

    private ShippingZoneLocations(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static ShippingZoneLocationBuilder.UpdateList<?> update(int shippingZoneId){
        //--breaks the mould!
        return new ShippingZoneLocationBuilder.UpdateList<>(shippingZoneId);

    }


    public static ShippingZoneLocationBuilder.ListAll<?> listing(int zoneId){

        return new ShippingZoneLocationBuilder.ListAll<>(zoneId);

    }
    //</editor-fold>
}
