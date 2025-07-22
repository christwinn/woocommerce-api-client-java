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

public class Duplicated<T> extends Created<T>{

    public Duplicated(ApiResponseResult result){

        super(result);

    }


}
