/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.crudPlusActionBuilder.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.wtx.woocommerce.api.client.model.Customer;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ListResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listed<T> extends ListResponse {

    public Listed(ApiResponseResult result){

        super(result);

    }

    /*If the id is NOT set then we get an array of product*/
    public List<T> getListed(){
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
