/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class Webhook {

    private Integer id; //integer	Unique identifier for the resource.read-only
    private String name; //string	A friendly name for the webhook.
    private WebhookStatusEnum status; //string	Webhook status. Options: active, paused and disabled. Default is active.
    private String topic; //string	Webhook topic.mandatory
    private String resource; //string	Webhook resource.read-only
    private String event; //string	Webhook event.read-only
    private List<String> hooks;	//array	WooCommerce action names associated with the webhook.read-only
    private String deliveryUrl; //string	The URL where the webhook payload is delivered.read-only mandatory
    private String secret; //string	Secret key used to generate a hash of the delivered webhook and provided in the request headers. This will default is a MD5 hash from the current user's ID
    private LocalDateTime dateCreated; //date-time	The date the webhook was created, in the site's timezone.read-only
    private LocalDateTime dateCreatedGMT; //date-time	The date the webhook was created, as GMT.read-only
    private LocalDateTime dateModified; //date-time	The date the webhook was last modified, in the site's timezone.read-only
    private LocalDateTime dateModifiedGMT; //date-time	The date the webhook was last modified, as GMT.read-only

    public Webhook(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebhookStatusEnum getStatus() {
        return status;
    }

    public void setStatus(WebhookStatusEnum status) {
        this.status = status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        if (status != null){
            this.status = WebhookStatusEnum.fromValue(status);
        }
    }

    @JsonProperty("status")
    public String getStatusAsString() {
        if (status != null){
            return this.status.getValue();
        }else{
            return null;
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<String> getHooks() {
        return hooks;
    }

    public void setHooks(List<String> hooks) {
        this.hooks = hooks;
    }

    @JsonProperty("delivery_url")
    public String getDeliveryUrl() {
        return deliveryUrl;
    }

    @JsonProperty("delivery_url")
    public void setDeliveryUrl(String deliveryUrl) {
        this.deliveryUrl = deliveryUrl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonProperty("date_created")
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    @JsonProperty("date_created")
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonProperty("date_created_gmt")
    public LocalDateTime getDateCreatedGMT() {
        return dateCreatedGMT;
    }

    @JsonProperty("date_created_gmt")
    public void setDateCreatedGMT(LocalDateTime dateCreatedGMT) {
        this.dateCreatedGMT = dateCreatedGMT;
    }

    @JsonProperty("date_modified")
    public LocalDateTime getDateModified() {
        return dateModified;
    }

    @JsonProperty("date_modified")
    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    @JsonProperty("date_modified_gmt")
    public LocalDateTime getDateModifiedGMT() {
        return dateModifiedGMT;
    }

    @JsonProperty("date_modified_gmt")
    public void setDateModifiedGMT(LocalDateTime dateModifiedGMT) {
        this.dateModifiedGMT = dateModifiedGMT;
    }

    public enum WebhookStatusEnum {
        ACTIVE("active"),
        PAUSED("paused"),
        DISABLED("disabled");

        private String value;

        WebhookStatusEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static WebhookStatusEnum fromValue(String value) {
            for (WebhookStatusEnum b : WebhookStatusEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }
}
