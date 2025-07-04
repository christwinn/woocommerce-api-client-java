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
import uk.co.twinn.api.woocommerce.response.core.ListResponse;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;

import java.util.List;

public class Listed<T> extends ListResponse {

    public Listed(ApiResponseResult result){

        super(result);

    }

    /*If the id is NOT set then we get an array of product*/
    public List<T> getResult(){
        return listed;
    }

    @Override
    public String toJson(){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(this);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
