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
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.util.List;
import java.util.Optional;

public class CoreList<S, T> {

    @SuppressWarnings("unchecked")
    T self() {
        return (T) this;
    }

    Listed<S> getResponse(String endPoint, String parameters, TypeReference<?> type){

        return new Listed<>(
            new Rest<List<S>>().listAll(
                endPoint,
                parameters,
                type
            )
        );

    }

    Optional<List<S>> getListed(String endPoint, String parameters, TypeReference<?> type){

        return
            new Listed<S>(
                new Rest<List<S>>()
                    .listed(
                        endPoint,
                        parameters,
                        type
                    )
                ).getList();

    }

}
