/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.Updated;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.PaymentGateway;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PAYMENT_GATEWAYS;

public class PaymentGatewayBuilder extends ApiRequest {

    private final PaymentGateway paymentGateway = new PaymentGateway();

    public PaymentGatewayBuilder(){}

    private PaymentGatewayBuilder(Updater<?> updater){

        paymentGateway.setId(updater.gatewayId);
        paymentGateway.setTitle(updater.title);
        paymentGateway.setDescription(updater.description);
        paymentGateway.setOrder(updater.order);
        paymentGateway.setEnabled(updater.enabled);

    }

    private String endPoint(){

        return PAYMENT_GATEWAYS +
            (paymentGateway.getId() != null && !paymentGateway.getId().isEmpty()
                ? ("/" + paymentGateway.getId())
                : ""
            );

    }

    public String toJson(){

        try {

            return getObjectMapper().writeValueAsString(paymentGateway);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Updater<T extends Updater<T>>{

        private final String gatewayId;
        private String title;
        private String description;
        private Integer order;
        private Boolean enabled;

        public Updater(String gatewayId){
            this.gatewayId = gatewayId;
        }

        @SuppressWarnings("unchecked")
        T self() {
            return (T) this;
        }

        public T setTitle(String title) {
            this.title = title;
            return self();
        }

        public T setDescription(String description) {
            this.description = description;
            return self();
        }

        public T setOrder(int order) {
            this.order = order;
            return self();
        }

        public T setEnable(boolean enabled) {
            this.enabled = enabled;
            return self();
        }

        private PaymentGatewayBuilder build(){
            return new PaymentGatewayBuilder(this);
        }

        public Updated<PaymentGateway> getResponse(){
            if (gatewayId != null && !gatewayId.isEmpty()){

                PaymentGatewayBuilder create = build();
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<PaymentGateway>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    public static class Reader<T extends Reader<T>> extends CoreReader.ReaderCoreStringKey<T>{

        public Reader(String gatewayId){
            super(gatewayId);
        }

        @SuppressWarnings("unchecked")
        public Read<PaymentGateway> getResponse(){
            return (Read<PaymentGateway>)super.getResponse(PAYMENT_GATEWAYS, new TypeReference<PaymentGateway>() {});

        }

    }

    public static class ListAll<T extends ListAll<T>>{

        /**
         *  List the Payment Gateways
         */
        public Listed<PaymentGateway> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    PAYMENT_GATEWAYS,
                    "",
                    new TypeReference<List<PaymentGateway>>(){}
                )
            );


        }

    }

}
