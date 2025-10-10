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
import uk.co.twinn.api.woocommerce.response.Created;
import uk.co.twinn.api.woocommerce.response.Ran;
import uk.co.twinn.api.woocommerce.response.Updated;
import uk.co.twinn.api.woocommerce.response.UpdatedList;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreCreator<S, T> {

    @SuppressWarnings("unchecked")
    T self() {
        return (T) this;
    }

    Created<S> getCreate(String endPoint, String parameters, TypeReference<?> type){

        return new Created<>(
            new Rest<S>().create(
                endPoint,
                parameters,
                type
            )
        );

    }

    Updated<S> getUpdate(String endPoint, String parameters, TypeReference<?> type){

        Logger.getLogger(CoreCreator.class.getName()).log(Level.INFO,String.format("Updating: %s with %s", endPoint, parameters));
        return new Updated<>(
            new Rest<S>().update(
                endPoint,
                parameters,
                type
            )
        );

    }

    UpdatedList<S> getUpdateList(String endPoint, String parameters, TypeReference<?> type){

        return new UpdatedList<>(
            new Rest<List<S>>().updateList(
                endPoint,
                parameters,
                type
            )
        );

    }

    Ran<S> getRunner(String endPoint, String parameters, TypeReference<?> type){

        return new Ran<>(
            new Rest<S>().update(
                endPoint,
                parameters,
                type
            )
        );

    }

}
