/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.PaymentGatewayBuilder;

public class PaymentGateways {

    private PaymentGateways(){}

    public static PaymentGatewayBuilder.Updater<?> update(String gatewayId){

        return new PaymentGatewayBuilder.Updater<>(gatewayId);

    }

    public static PaymentGatewayBuilder.Reader<?> read(String gatewayId){

        return new PaymentGatewayBuilder.Reader<>(gatewayId);

    }

    public static PaymentGatewayBuilder.ListAll<?> listing(){

        return new PaymentGatewayBuilder.ListAll<>();

    }

}
