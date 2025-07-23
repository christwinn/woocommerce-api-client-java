/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.builders.core.Seek;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class ProductCustomFieldBuilder {

    public ProductCustomFieldBuilder(){

    }

    public static class ListAll<T extends ListAll<T>> extends Seek.SearchCorePaging<String, T> {

        public Listed<String> getResponse() {

            return super.getResponse(
                PRODUCTS_CUSTOM_FIELDS_NAMES,
                build(),
                new TypeReference<List<String>>() {}
            );

            /*return new Listed<>(
                new Rest<List<String>>().listAll(
                    PRODUCTS_CUSTOM_FIELDS_NAMES, //endPoint, SET endPoint
                    build(),
                    new TypeReference<List<String>>() { }
                )
            );*/

        }

    }

}
