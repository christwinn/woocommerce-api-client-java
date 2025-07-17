/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonPaymentGatewaySettings;

import java.util.HashMap;

@JsonDeserialize(using = JsonPaymentGatewaySettings.class)
public class PaymentGatewaySettings {

    private HashMap<String, PaymentGatewaySetting> settings;

    public PaymentGatewaySettings(){
        /*required for deserialiser even though never used!*/
    }

    public PaymentGatewaySettings(HashMap<String, PaymentGatewaySetting> settings){
        this.settings = settings;
    }

    public HashMap<String, PaymentGatewaySetting> getSettings(){
        return settings;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }
}
