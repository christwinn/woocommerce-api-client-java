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
import org.jetbrains.annotations.Nullable;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

public class Link {

    public static final String SERIALIZED_NAME_HREF = "href";
    //@SerializedName(SERIALIZED_NAME_HREF)
    @Nullable
    private String href;

    public Link(){}

    public Link href(@Nullable String href) {
        this.href = href;
        return this;
    }

    /**
     * id.
     * @return id
     */
    @Nullable
    public String getHref() {
        return href;
    }

    public void setHref(@Nullable String href) {
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
