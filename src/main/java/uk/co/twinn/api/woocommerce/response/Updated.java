/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.response;

import uk.co.twinn.api.woocommerce.exceptions.ResponseException;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;

import java.util.Optional;

public class Updated<T> extends Created<T> {

    public Updated(ApiResponseResult<T> result){

        super(result);

    }

    public Updated(Optional<ApiResponseResult<T>> result){

        this(result.orElseThrow(()->new ResponseException("No Result received")));

    }

}
