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
import uk.co.twinn.api.woocommerce.WooCommerce;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;

import uk.co.twinn.pl_wtx_woocommerce.model.webhook.Webhook;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.WEBHOOKS;

public class WebhookBuilder extends ApiRequest {

    protected final Webhook webhook = new Webhook();

    public WebhookBuilder(){}

    private WebhookBuilder(Creator<?> creator){

        webhook.setName(creator.name);
        webhook.setStatus(creator.status);
        webhook.setTopic(creator.topic);
        webhook.setDeliveryUrl(creator.deliveryUrl);
        webhook.setSecret(creator.secret);

    }

    private WebhookBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        webhook.setId(updater.id);

    }

    private WebhookBuilder(Deleter deleter){

        webhook.setId(deleter.id);

    }

    public String endPoint(){

        return WEBHOOKS +
            (webhook.getId() != null && webhook.getId() != 0
                ? ("/" + webhook.getId())
                : ""
            );

    }

    public String toJson(){

        try {

            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(webhook);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>> extends CoreCreator<Webhook, T>{

        private String name; //string	A friendly name for the webhook.
        private Webhook.WebhookStatusEnum status; //string	Webhook status. Options: active, paused and disabled. Default is active.
        protected String topic; //string	Webhook topic.mandatory
        private String deliveryUrl; //string	The URL where the webhook payload is delivered.read-only(?) mandatory
        private String secret; //string	Secret key used to generate a hash of the delivered webhook and provided in the request headers. This will default is a MD5 hash from the current user's ID

        private Creator(){

        }

        public Creator(String topic, String deliveryUrl){
            this.topic = topic;
            this.deliveryUrl = deliveryUrl;
        }

        /**
         *
         * @param name Webhook name.
         * @return T
         */
        public T setName(String name) {
            this.name = name;
            return self();
        }

        /**
         *
         * @param status Webhook status.
         * @return T
         */
        public T setStatus(Webhook.WebhookStatusEnum status) {
            this.status = status;
            return self();
        }

        /**
         *
         * @param status Webhook status as string.
         * @return T
         */
        public T setStatusAsString(String status) {
            if (status != null && !status.isEmpty()) {
                this.status = Webhook.WebhookStatusEnum.valueOf(status);
            }
            return self();
        }

        /**
         *
         * @param secret "Secret key used to generate a hash of the delivered webhook and provided in the request headers.
         *               This will default is a MD5 hash from the current user's ID"
         * @return T
         */
        public T setSecret(String secret) {
            this.secret = secret;
            return self();
        }

        protected WebhookBuilder build(){
            return new WebhookBuilder(this);
        }

        /** Returns single Created Object, unless it is a duplicate! **/
        public Created<Webhook> getResponse(){

            WebhookBuilder create = build();
            return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<Webhook>() {});
            //make the call
            /*return new Created<>(
                new Rest<Webhook>().create(create.endPoint(), create.toJson())
            );*/

        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private final int id;

        public Updater(int webhookId){
            this.id = webhookId;
        }

        public T setTopic(String topic){
            this.topic = topic;
            return self();
        }

        //deliveryUrl is declared as read-only, therefore not updateable?

        protected WebhookBuilder build(){
            return new WebhookBuilder(this);
        }

        @Override
        public Updated<Webhook> getResponse(){
            if (id > 0){

                WebhookBuilder create = build();
                /*return new Updated<>(
                    new Rest<Webhook>().update(create.endPoint(), create.toJson())
                );*/
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<Webhook>() {});

            }else{
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<Webhook>{

        public Reader(int webhookId){
            super(webhookId);
        }

        public Read<Webhook> getResponse(){
            return super.getResponse(WEBHOOKS, new TypeReference<Webhook>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<Webhook>{

        public Deleter(int webhookId, boolean force){
            super(webhookId, force);
        }

        protected WebhookBuilder build(){
            return new WebhookBuilder(this);
        }

        public Deleted<Webhook> getResponse(){
            return super.getResponse(WEBHOOKS, new TypeReference<Webhook>() {});
        }

    }

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<Webhook, T>{

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().webhook);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().webhook);
            return self();
        }

        public T addDeleter(Deleter delete){
            batch.addDelete(delete.build().webhook.getId());
            return self();
        }

        public Batched<Webhook> getResponse(){

            return super.getResponse(WEBHOOKS, batch, new TypeReference<BatchResult<Webhook>>() {});

        }

    }

    public static class ListAll<T extends ListAll<T>> extends CoreSeek.DatesSearcher<Webhook, T>{

        /**
         *
         * @param status Limit result set to webhooks assigned a specific status.
         *               Options: all, active, paused and disabled.
         *               Default is all.
         * @return T
         */
        public T setStatus(String status){
            addNameValuePair("status", status);
            return self();
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Listed<Webhook> getResponse(){

            return super.getResponse(
                WEBHOOKS,
                build(),
                new TypeReference<List<Webhook>>() {}
            );

            /*return new Listed<>(
                new Rest<List<Webhook>>().listAll(
                    WEBHOOKS,
                    build()
                )
            );*/


        }

    }

}
