/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ShippingMethodBuilder;

public class ShippingMethods {

    protected ShippingMethods(){}

    public static ShippingMethodBuilder.Reader read(int shippingMethodId){

        return new ShippingMethodBuilder.Reader(shippingMethodId);

    }

    public static ShippingMethodBuilder.ListAll<?> listing(){

        return new ShippingMethodBuilder.ListAll<>();

    }

}
