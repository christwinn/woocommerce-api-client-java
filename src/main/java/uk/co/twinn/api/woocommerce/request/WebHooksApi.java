/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.request.core.ApiRequest;
import uk.co.twinn.api.woocommerce.request.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.Webhook;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.WEBHOOKS;

public class WebHooksApi extends ApiRequest {

    protected final Webhook webhook = new Webhook();

    private boolean force;

    public WebHooksApi(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public Creator<?> create(String topic, String delivery_url){

        return new Creator<>(topic, delivery_url);

    }

    public Reader<?> read(int webhookId){

        return new Reader<>(webhookId);

    }

    public Updater<?> update(int webhookId){

        return new Updater<>(webhookId);

    }

    public Deleter<?> delete(int webhookId, boolean force){

        return new Deleter<>(webhookId, force);

    }

    public Batcher<?> batch(){

        return new Batcher<>();

    }
    public ListAll<?> listing(){

        return new ListAll<>();

    }
    //</editor-fold>

    private WebHooksApi(Creator<?> creator){

        webhook.setName(creator.name);
        webhook.setStatus(creator.status);
        webhook.setTopic(creator.topic);
        webhook.setDeliveryUrl(creator.deliveryUrl);
        webhook.setSecret(creator.secret);

    }

    private WebHooksApi(Updater<?> updater){

        this((Creator<?>)updater);
        webhook.setId(updater.id);

    }

    private WebHooksApi(Deleter<?> deleter){

        webhook.setId(deleter.id);
        force = deleter.force;

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

    public static class Creator<T extends Creator<T>>{

        private String name; //string	A friendly name for the webhook.
        private Webhook.WebhookStatusEnum status; //string	Webhook status. Options: active, paused and disabled. Default is active.
        protected String topic; //string	Webhook topic.mandatory
        private String deliveryUrl; //string	The URL where the webhook payload is delivered.read-only(?) mandatory
        private String secret; //string	Secret key used to generate a hash of the delivered webhook and provided in the request headers. This will default is a MD5 hash from the current user's ID

        Creator(){

        }

        public Creator(String topic, String deliveryUrl){
            this.topic = topic;
            this.deliveryUrl = deliveryUrl;
        }

        T self() {
            return (T) this;
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

        protected WebHooksApi build(){
            return new WebHooksApi(this);
        }

        /** Returns single Created Object, unless it is a duplicate! **/
        public Created<Webhook> getResponse(){

            WebHooksApi create = build();
            //make the call
            return new Created<>(
                new Rest().create(create.endPoint(), create.toJson(), new TypeReference<Webhook>(){})
            );

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

        protected WebHooksApi build(){
            return new WebHooksApi(this);
        }

        @Override
        public Updated<Webhook> getResponse(){
            if (id > 0){

                WebHooksApi create = build();
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<Webhook>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ReaderCore<T>{

        public Reader(int webhookId){
            super(webhookId);
        }

        public Read<Webhook> getResponse(){
            return (Read<Webhook>)super.getResponse(WEBHOOKS, new TypeReference<Webhook>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.DeleterCore<T>{

        public Deleter(int webhookId, boolean force){
            super(webhookId, force);
        }

        protected WebHooksApi build(){
            return new WebHooksApi(this);
        }

        public Deleted<Webhook> getResponse(){
            return (Deleted<Webhook>)super.getResponse(WEBHOOKS, new TypeReference<Webhook>() {});

        }

    }

    public static class Batcher<T extends Batcher<T>> extends CoreBatchRequest.BatchCore<T>{

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().webhook);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().webhook);
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().webhook);
            return self();
        }

        public Batched<Webhook> getResponse(){

            return (Batched<Webhook>) super.getResponse(WEBHOOKS, batch, new TypeReference<Batch<Webhook>>(){});

        }

    }

    public static class ListAll<T extends ListAll<T>> extends Seek.DatesSearcher<T>{

        T self() {
            return (T) this;
        }

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

            return new Listed<>(
                new Rest().listAll(
                    WEBHOOKS,
                    build(),
                    new TypeReference<List<Webhook>>(){}
                )
            );


        }

    }

}
