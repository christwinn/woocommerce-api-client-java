/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.WebhookBuilder;

public class Webhooks {

    protected Webhooks(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static WebhookBuilder.Creator<?> create(String topic, String delivery_url){

        return new WebhookBuilder.Creator<>(topic, delivery_url);

    }

    public static WebhookBuilder.Reader read(int webhookId){

        return new WebhookBuilder.Reader(webhookId);

    }

    public static WebhookBuilder.Updater<?> update(int webhookId){

        return new WebhookBuilder.Updater<>(webhookId);

    }

    public static WebhookBuilder.Deleter delete(int webhookId, boolean force){

        return new WebhookBuilder.Deleter(webhookId, force);

    }

    public static WebhookBuilder.Batcher<?> batch(){

        return new WebhookBuilder.Batcher<>();

    }

    public static WebhookBuilder.ListAll<?> listing(){

        return new WebhookBuilder.ListAll<>();

    }
    //</editor-fold>

}
