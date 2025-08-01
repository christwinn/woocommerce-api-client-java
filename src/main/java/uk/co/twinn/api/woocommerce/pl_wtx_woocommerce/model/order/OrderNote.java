/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonMappedLinks;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.global.Link;

import java.time.LocalDateTime;
import java.util.HashMap;

public class OrderNote {

    private Integer id;//	integer	Unique identifier for the resource.read-only
    private String author;//	string	Order note author.read-only
    private LocalDateTime dateCreated;//	date-time	The date the order note was created, in the site's timezone.read-only
    private LocalDateTime dateCreatedGMT;//	date-time	The date the order note was created, as GMT.read-only
    private String note;//	string	Order note content.mandatory
    private Boolean customerNote;//	boolean	If true, the note will be shown to customers and they will be notified. If false, the note will be for admin reference only. Default is false.
    private Boolean addedByUser;//	boolean	If true, this note will be attributed to the current user. If false, the note will be attributed to the system. Default is false.

    private HashMap<String, Link> links;

    public OrderNote(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    @JsonProperty("date_created")
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateCreatedGMT() {
        return dateCreatedGMT;
    }

    @JsonProperty("date_created_gmt")
    public void setDateCreatedGMT(LocalDateTime dateCreatedGMT) {
        this.dateCreatedGMT = dateCreatedGMT;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getCustomerNote() {
        return customerNote;
    }
    public Boolean isCustomerNote() {
        return getCustomerNote();
    }
    @JsonProperty("customer_note")
    public void setCustomerNote(Boolean customerNote) {
        this.customerNote = customerNote;
    }

    public Boolean getAddedByUser() {
        return addedByUser;
    }
    public Boolean isAddedByUser() {
        return getAddedByUser();
    }
    @JsonProperty("addedByUser")
    public void setAddedByUser(Boolean addedByUser) {
        this.addedByUser = addedByUser;
    }

    @JsonProperty("_links")
    @JsonDeserialize(using = JsonMappedLinks.class)
    public void setLinks(HashMap<String, Link> links) {
        this.links = links;
    }
    @JsonProperty("_links")
    public HashMap<String, Link> getLinks( ) {
        return links;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }
}
