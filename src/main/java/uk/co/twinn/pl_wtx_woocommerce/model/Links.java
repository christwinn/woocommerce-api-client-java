/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import uk.co.twinn.pl_wtx_woocommerce.invoker.JSON;

import java.io.IOException;
import java.util.*;

public class Links {

    public static final String SERIALIZED_NAME_SELF = "self";
    @SerializedName(SERIALIZED_NAME_SELF)
    @javax.annotation.Nullable
    private List<Link> self;

    public static final String SERIALIZED_NAME_COLLECTION = "collection";
    @SerializedName(SERIALIZED_NAME_COLLECTION)
    @javax.annotation.Nullable
    private List<Link> collection;

    public Links(){}

    public Links self(@javax.annotation.Nullable List<Link> self) {
        this.self = self;
        return this;
    }

    /**
     * id.
     * @return id
     */
    @javax.annotation.Nullable
    public List<Link> getSelf() {
        return self;
    }

    public void setSelf(@javax.annotation.Nullable List<Link> self) {

        this.self = self;

    }


    public Links collection(@javax.annotation.Nullable List<Link> collection) {
        this.collection = collection;
        return this;
    }

    /**
     * id.
     * @return id
     */
    @javax.annotation.Nullable
    public List<Link> getCollection() {
        return collection;
    }

    public void setCollection(@javax.annotation.Nullable List<Link> collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("class Links {\n")
            .append("    self: ").append(toIndentedString(self))
            .append("    collection: ").append(toIndentedString(collection))
            .append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     *
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }


    /**
     * Convert an instance of Billing to an JSON string
     *
     * @return JSON string
     */
    public String toJson() {
        return JSON.getGson().toJson(this);
    }


}
