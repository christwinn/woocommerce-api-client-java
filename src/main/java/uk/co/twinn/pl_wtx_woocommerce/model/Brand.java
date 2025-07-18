/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model;

//import com.google.gson.annotations.SerializedName;
import javax.annotation.Nullable;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.13.0")
public class Brand {

    public static final String SERIALIZED_NAME_ID = "id";
    //@SerializedName(SERIALIZED_NAME_ID)
    @Nullable
    private Integer id;

    public static final String SERIALIZED_NAME_NAME = "name";
    //@SerializedName(SERIALIZED_NAME_NAME)
    @Nullable
    private String name;

    public static final String SERIALIZED_NAME_SLUG = "slug";
    //@SerializedName(SERIALIZED_NAME_SLUG)
    @Nullable
    private String slug;

    public Brand(){}

    public Brand id(@Nullable Integer id) {
        this.id = id;
        return this;
    }

    /**
     * id.
     * @return id
     */
    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    /**
     * name.
     * @return Name
     */
    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }


    public Brand name(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * slug.
     * @return Slug
     */
    @Nullable
    public String getSlug() {
        return slug;
    }

    public void setSlug(@Nullable String slug) {
        this.slug = slug;
    }


    public Brand slug(@Nullable String slug) {
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

    /**
     * Convert an instance of Billing to an JSON string
     *
     * @return JSON string
     */

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }
}
