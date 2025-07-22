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

public class Deleted<T> extends Read<T> {

    public Deleted(ApiResponseResult result){

        super(result);

    }

}
