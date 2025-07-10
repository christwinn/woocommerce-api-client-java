/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import uk.co.twinn.api.woocommerce.response.core.CrudResponse;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;

public class Read<T> extends CrudResponse<T> {

    public Read(ApiResponseResult result){

        super(result);

    }


}
