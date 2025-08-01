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


package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product;

import java.util.HashMap;
import java.util.Objects;

//import com.google.gson.annotations.SerializedName;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.core.deserialisers.JsonMappedLinks;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.global.Link;

/**
 * ProductTag
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.13.0")
public class ProductTag {
  public static final String SERIALIZED_NAME_ID = "id";
  //@SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nullable
  private Integer id;

  public static final String SERIALIZED_NAME_NAME = "name";
  //@SerializedName(SERIALIZED_NAME_NAME)
  @javax.annotation.Nonnull
  private String name;

  public static final String SERIALIZED_NAME_SLUG = "slug";
  //@SerializedName(SERIALIZED_NAME_SLUG)
  @javax.annotation.Nullable
  private String slug;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  //@SerializedName(SERIALIZED_NAME_DESCRIPTION)
  @javax.annotation.Nullable
  private String description;

  public static final String SERIALIZED_NAME_COUNT = "count";
  //@SerializedName(SERIALIZED_NAME_COUNT)
  @javax.annotation.Nullable
  private Integer count;

  private HashMap<String, Link> links;

  public ProductTag() {
  }

  public ProductTag id(@javax.annotation.Nullable Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Tag ID.
   * @return id
   */
  @javax.annotation.Nullable
  public Integer getId() {
    return id;
  }

  public void setId(@javax.annotation.Nullable Integer id) {
    this.id = id;
  }


  public ProductTag name(@javax.annotation.Nonnull String name) {
    this.name = name;
    return this;
  }

  /**
   * Tag name. read-only
   * @return name
   */
  @javax.annotation.Nonnull
  public String getName() {
    return name;
  }

  public void setName(@javax.annotation.Nonnull String name) {
    this.name = name;
  }


  public ProductTag slug(@javax.annotation.Nullable String slug) {
    this.slug = slug;
    return this;
  }

  /**
   * Tag slug. read-only
   * @return slug
   */
  @javax.annotation.Nullable
  public String getSlug() {
    return slug;
  }

  public void setSlug(@javax.annotation.Nullable String slug) {
    this.slug = slug;
  }


  public ProductTag description(@javax.annotation.Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * HTML description of the resource
   * @return description
   */
  @javax.annotation.Nullable
  public String getDescription() {
    return description;
  }

  public void setDescription(@javax.annotation.Nullable String description) {
    this.description = description;
  }


  public ProductTag count(@javax.annotation.Nullable Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Number of published products for the resource
   * @return count
   */
  @javax.annotation.Nullable
  public Integer getCount() {
    return count;
  }

  public void setCount(@javax.annotation.Nullable Integer count) {
    this.count = count;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductTag productTag = (ProductTag) o;
    return Objects.equals(this.id, productTag.id) &&
        Objects.equals(this.name, productTag.name) &&
        Objects.equals(this.slug, productTag.slug) &&
        Objects.equals(this.description, productTag.description) &&
        Objects.equals(this.count, productTag.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, slug, description, count);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductTag {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    slug: ").append(toIndentedString(slug)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
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

  /**
   * Convert an instance of ProductTag to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
      return new JacksonObjectMapper().toJson(this);
  }
}

