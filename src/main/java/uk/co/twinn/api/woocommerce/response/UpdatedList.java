/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.response;

import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;

import java.util.List;

public class UpdatedList<T> extends Listed<T>{

    public UpdatedList(ApiResponseResult<List<T>> result){

        super(result);

    }

}
