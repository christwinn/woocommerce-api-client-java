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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Links link = (Links) o;
        return Objects.equals(this.self, link.self) &&
            Objects.equals(this.collection, link.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(self);
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


    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    static {
        // a set of all properties/fields (JSON key names)
        openapiFields = new HashSet<String>();
        openapiFields.add("self");
        openapiFields.add("collection");
        //yet there are more
        // a set of required properties/fields (JSON key names)
        openapiRequiredFields = new HashSet<String>();
    }

    /**
     * Validates the JSON Element and throws an exception if issues found
     *
     * @param jsonElement JSON Element
     * @throws IOException if the JSON Element is invalid with respect to Billing
     */
    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
        if (jsonElement == null) {
            if (!Link.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
                throw new IllegalArgumentException(String.format("The required field(s) %s in Link is not found in the empty JSON string", Link.openapiRequiredFields.toString()));
            }
        }

        //System.out.println(jsonElement.getAsJsonArray());
        Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
        // check to see if the JSON string contains additional fields
        for (Map.Entry<String, JsonElement> entry : entries) {
            if (!Links.openapiFields.contains(entry.getKey())) {
                throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Link` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
            }
        }

        JsonObject jsonObj = jsonElement.getAsJsonObject();
        for (Map.Entry entry : jsonObj.entrySet()){
            String key = (String)entry.getKey();
            if ((jsonObj.get(key) != null && !jsonObj.get(key).isJsonNull())) {
                Link.validateJsonElement(jsonObj.get(key));
            }
        }

    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!Links.class.isAssignableFrom(type.getRawType())) {
                return null; // this class only serializes 'Billing' and its subtypes
            }
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<Links> thisAdapter
                = gson.getDelegateAdapter(this, TypeToken.get(Links.class));

            return (TypeAdapter<T>) new TypeAdapter<Links>() {
                @Override
                public void write(JsonWriter out, Links value) throws IOException {
                    JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
                    elementAdapter.write(out, obj);
                }

                @Override
                public Links read(JsonReader in) throws IOException {
                    JsonElement jsonElement = elementAdapter.read(in);
                    validateJsonElement(jsonElement);
                    return thisAdapter.fromJsonTree(jsonElement);
                }

            }.nullSafe();
        }
    }

    /**
     * Create an instance of Brand given a JSON string
     *
     * @param jsonString JSON string
     * @return An instance of Billing
     * @throws IOException if the JSON string is invalid with respect to Billing
     */
    public static Links fromJson(String jsonString) throws IOException {
        return JSON.getGson().fromJson(jsonString, Links.class);
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
