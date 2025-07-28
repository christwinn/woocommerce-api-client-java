/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.global;

//import com.google.gson.annotations.SerializedName;

import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

/**
 * HAL - Hypertext Application Language
 * <br/>
 * Link used in '_links' HashMap as we never fully know the full list of object names that may exist
 * but each Link follows the structure below ! to be completed. require targetHints 'allow'
 *
 **/
public class Link {

    @javax.annotation.Nullable
    private String href;

    public Link(){}

    public Link href(@javax.annotation.Nullable String href) {
        this.href = href;
        return this;
    }

    /**
     * id.
     * @return id
     */
    @javax.annotation.Nullable
    public String getHref() {
        return href;
    }

    public void setHref(@javax.annotation.Nullable String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("class Link {\n")
            .append("    href: ").append(toIndentedString(href))
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
     * Convert an instance of this to an JSON string
     *
     * @return JSON string
     */

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
