/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

/*
 * WooCommerce REST API
 * The REST API is a powerful part of WooCommerce which lets you read and write various parts of WooCommerce data such as orders, products, coupons, customers, and shipping zones.
 *
 * The version of the OpenAPI document: v3
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package uk.co.twinn.pl_wtx_woocommerce.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uk.co.twinn.pl_wtx_woocommerce.invoker.JSON;

/**
 * ProductAttribute
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.13.0")
public class ProductAttribute {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nullable
  private Integer id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  @javax.annotation.Nullable
  private String name;

  public static final String SERIALIZED_NAME_OPTION = "option";
  @SerializedName(SERIALIZED_NAME_OPTION)
  @javax.annotation.Nullable
  private String option;

  public static final String SERIALIZED_NAME_SLUG = "slug";
  @SerializedName(SERIALIZED_NAME_SLUG)
  @javax.annotation.Nullable
  private String slug;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  @javax.annotation.Nullable
  private String type;

  public static final String SERIALIZED_NAME_ORDER_BY = "order_by";
  @SerializedName(SERIALIZED_NAME_ORDER_BY)
  @javax.annotation.Nullable
  private String orderBy;

  public static final String SERIALIZED_NAME_HAS_ARCHIVES = "has_archives";
  @SerializedName(SERIALIZED_NAME_HAS_ARCHIVES)
  @javax.annotation.Nullable
  private Boolean hasArchives;

  public ProductAttribute() {
  }

  public ProductAttribute id(@javax.annotation.Nullable Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Attribute ID.
   * @return id
   */
  @javax.annotation.Nullable
  public Integer getId() {
    return id;
  }

  public void setId(@javax.annotation.Nullable Integer id) {
    this.id = id;
  }


  public ProductAttribute name(@javax.annotation.Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Attribute name.
   * @return name
   */
  @javax.annotation.Nullable
  public String getName() {
    return name;
  }

  public void setName(@javax.annotation.Nullable String name) {
    this.name = name;
  }


  public ProductAttribute option(@javax.annotation.Nullable String option) {
    this.option = option;
    return this;
  }

  /**
   * Selected attribute term name. Applicable only for Product represents SKU (variation)
   * @return option
   */
  @javax.annotation.Nullable
  public String getOption() {
    return option;
  }

  public void setOption(@javax.annotation.Nullable String option) {
    this.option = option;
  }


  public ProductAttribute slug(@javax.annotation.Nullable String slug) {
    this.slug = slug;
    return this;
  }

  /**
   * Attribute slug.
   * @return slug
   */
  @javax.annotation.Nullable
  public String getSlug() {
    return slug;
  }

  public void setSlug(@javax.annotation.Nullable String slug) {
    this.slug = slug;
  }


  public ProductAttribute type(@javax.annotation.Nullable String type) {
    this.type = type;
    return this;
  }

  /**
   * Attribute type.
   * @return type
   */
  @javax.annotation.Nullable
  public String getType() {
    return type;
  }

  public void setType(@javax.annotation.Nullable String type) {
    this.type = type;
  }


  public ProductAttribute orderBy(@javax.annotation.Nullable String orderBy) {
    this.orderBy = orderBy;
    return this;
  }

  /**
   * Sort order.
   * @return orderBy
   */
  @javax.annotation.Nullable
  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(@javax.annotation.Nullable String orderBy) {
    this.orderBy = orderBy;
  }


  public ProductAttribute hasArchives(@javax.annotation.Nullable Boolean hasArchives) {
    this.hasArchives = hasArchives;
    return this;
  }

  /**
   * Enable/Disable attribute archives.
   * @return hasArchives
   */
  @javax.annotation.Nullable
  public Boolean getHasArchives() {
    return hasArchives;
  }

  public void setHasArchives(@javax.annotation.Nullable Boolean hasArchives) {
    this.hasArchives = hasArchives;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductAttribute productAttribute = (ProductAttribute) o;
    return Objects.equals(this.id, productAttribute.id) &&
        Objects.equals(this.name, productAttribute.name) &&
        Objects.equals(this.option, productAttribute.option) &&
        Objects.equals(this.slug, productAttribute.slug) &&
        Objects.equals(this.type, productAttribute.type) &&
        Objects.equals(this.orderBy, productAttribute.orderBy) &&
        Objects.equals(this.hasArchives, productAttribute.hasArchives);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, option, slug, type, orderBy, hasArchives);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductAttribute {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    option: ").append(toIndentedString(option)).append("\n");
    sb.append("    slug: ").append(toIndentedString(slug)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    orderBy: ").append(toIndentedString(orderBy)).append("\n");
    sb.append("    hasArchives: ").append(toIndentedString(hasArchives)).append("\n");
    sb.append("}");
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
    openapiFields.add("option");
    openapiFields.add("slug");
    openapiFields.add("type");
    openapiFields.add("order_by");
    openapiFields.add("has_archives");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to ProductAttribute
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ProductAttribute.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ProductAttribute is not found in the empty JSON string", ProductAttribute.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ProductAttribute.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ProductAttribute` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("name") != null && !jsonObj.get("name").isJsonNull()) && !jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      if ((jsonObj.get("option") != null && !jsonObj.get("option").isJsonNull()) && !jsonObj.get("option").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `option` to be a primitive type in the JSON string but got `%s`", jsonObj.get("option").toString()));
      }
      if ((jsonObj.get("slug") != null && !jsonObj.get("slug").isJsonNull()) && !jsonObj.get("slug").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `slug` to be a primitive type in the JSON string but got `%s`", jsonObj.get("slug").toString()));
      }
      if ((jsonObj.get("type") != null && !jsonObj.get("type").isJsonNull()) && !jsonObj.get("type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("type").toString()));
      }
      if ((jsonObj.get("order_by") != null && !jsonObj.get("order_by").isJsonNull()) && !jsonObj.get("order_by").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `order_by` to be a primitive type in the JSON string but got `%s`", jsonObj.get("order_by").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ProductAttribute.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ProductAttribute' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ProductAttribute> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ProductAttribute.class));

       return (TypeAdapter<T>) new TypeAdapter<ProductAttribute>() {
           @Override
           public void write(JsonWriter out, ProductAttribute value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ProductAttribute read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of ProductAttribute given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of ProductAttribute
   * @throws IOException if the JSON string is invalid with respect to ProductAttribute
   */
  public static ProductAttribute fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ProductAttribute.class);
  }

  /**
   * Convert an instance of ProductAttribute to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

