/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.response.core;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CrudResponse<T> extends ApiResponse<T>{

    protected Object object = null;

    public CrudResponse(ApiResponseResult<T> result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    success = true;
                    this.object =  result.getData();
                    break;
                default:
                    success = false;
                    error = new ErrorMessage("Invalid response code");
                    break;
            }
        }

    }

    @Override
    public boolean isSuccess() {
        return super.success && object != null;
    }

    public String toJson(){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(this);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @SuppressWarnings ("unchecked")
    public T getResult(){
        /*T is set by the call*/
        return (T)object;
    }

    public boolean hasResult(){
        return object != null;
    }

}
