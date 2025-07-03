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
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;

public class Updated<T> extends Created<T> {

    public Updated(ApiResponseResult result){

        super(result);

    }

    public T getUpdated(){
        return (T) object;
    }

    public boolean hasUpdated(){
        return object != null;
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
