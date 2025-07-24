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
import uk.co.twinn.pl_wtx_woocommerce.model.payment.PaymentGateway;

public class PaymentGateways {

    protected PaymentGateways(){}

    public static PaymentGatewayBuilder.Updater<?> update(String gatewayId){

        return new PaymentGatewayBuilder.Updater<>(gatewayId);

    }

    public static PaymentGatewayBuilder.Updater<?> update(PaymentGateway paymentGateway){

        return new PaymentGatewayBuilder.Updater<>(paymentGateway);

    }

    public static PaymentGatewayBuilder.Reader read(String gatewayId){

        return new PaymentGatewayBuilder.Reader(gatewayId);

    }

    public static PaymentGatewayBuilder.ListAll<?> listing(){

        return new PaymentGatewayBuilder.ListAll<>();

    }

}
