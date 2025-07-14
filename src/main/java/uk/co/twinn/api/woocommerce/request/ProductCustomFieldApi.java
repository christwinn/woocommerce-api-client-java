/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.request.core.Seek;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class ProductCustomFieldApi {

    public ProductCustomFieldApi(){

    }

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public ListAll<?> listing(){

        return new ListAll<>();

    }
    //</editor-fold>

    public static class ListAll<T extends ListAll<T>> extends Seek.SearchCorePaging<T> {

        T self() {
            return (T) this;
        }

        public Listed<String> getResponse() {

            return new Listed<>(
                new Rest().listAll(
                    PRODUCTS_CUSTOM_FIELDS_NAMES, //endPoint, SET endPoint
                    build(),
                    new TypeReference<List<String>>() {
                    }
                )
            );


        }

    }

}
