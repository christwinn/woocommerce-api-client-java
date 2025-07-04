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
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.13.0")
public class Brand {

    public static final String SERIALIZED_NAME_ID = "id";
    @SerializedName(SERIALIZED_NAME_ID)
    @javax.annotation.Nullable
    private Integer id;

    public static final String SERIALIZED_NAME_NAME = "name";
    @SerializedName(SERIALIZED_NAME_NAME)
    @javax.annotation.Nullable
    private String name;

    public static final String SERIALIZED_NAME_SLUG = "slug";
    @SerializedName(SERIALIZED_NAME_SLUG)
    @javax.annotation.Nullable
    private String slug;

    public Brand(){}

    public Brand id(@javax.annotation.Nullable Integer id) {
        this.id = id;
        return this;
    }

    /**
     * id.
     * @return id
     */
    @javax.annotation.Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@javax.annotation.Nullable Integer id) {
        this.id = id;
    }

    /**
     * name.
     * @return Name
     */
    @javax.annotation.Nullable
    public String getName() {
        return name;
    }

    public void setName(@javax.annotation.Nullable String name) {
        this.name = name;
    }


    public Brand name(@javax.annotation.Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * slug.
     * @return Slug
     */
    @javax.annotation.Nullable
    public String getSlug() {
        return slug;
    }

    public void setSlug(@javax.annotation.Nullable String slug) {
        this.slug = slug;
    }


    public Brand slug(@javax.annotation.Nullable String slug) {
        this.slug = slug;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brand brand = (Brand) o;
        return Objects.equals(this.id, brand.id) &&
            Objects.equals(this.name, brand.name) &&
            Objects.equals(this.slug, brand.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slug);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("class Brand {\n")
            .append("    id: ").append(toIndentedString(id)).append("\n")
            .append("    name: ").append(toIndentedString(name)).append("\n")
            .append("    slug: ").append(toIndentedString(slug)).append("\n")
            .append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
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
        openapiFields.add("id");
        openapiFields.add("name");
        openapiFields.add("slug");

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
            if (!Brand.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
                throw new IllegalArgumentException(String.format("The required field(s) %s in Brand is not found in the empty JSON string", Brand.openapiRequiredFields.toString()));
            }
        }

        Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
        // check to see if the JSON string contains additional fields
        for (Map.Entry<String, JsonElement> entry : entries) {
            if (!Brand.openapiFields.contains(entry.getKey())) {
                throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Brand` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
            }
        }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        if ((jsonObj.get("id") != null && !jsonObj.get("id").isJsonNull()) && !jsonObj.get("id").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("id").toString()));
        }
        if ((jsonObj.get("name") != null && !jsonObj.get("name").isJsonNull()) && !jsonObj.get("name").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
        }
        if ((jsonObj.get("slug") != null && !jsonObj.get("slug").isJsonNull()) && !jsonObj.get("slug").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `slug` to be a primitive type in the JSON string but got `%s`", jsonObj.get("slug").toString()));
        }

    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!Brand.class.isAssignableFrom(type.getRawType())) {
                return null; // this class only serializes 'Billing' and its subtypes
            }
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<Brand> thisAdapter
                = gson.getDelegateAdapter(this, TypeToken.get(Brand.class));

            return (TypeAdapter<T>) new TypeAdapter<Brand>() {
                @Override
                public void write(JsonWriter out, Brand value) throws IOException {
                    JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
                    elementAdapter.write(out, obj);
                }

                @Override
                public Brand read(JsonReader in) throws IOException {
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
    public static Brand fromJson(String jsonString) throws IOException {
        return JSON.getGson().fromJson(jsonString, Brand.class);
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
