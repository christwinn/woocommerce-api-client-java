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

public class CrudResponse<T> extends ApiResponse{

    protected Object object = null;

    public CrudResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    this.object =  result.getData();
                    break;
                default:
                    setSuccess(false);
                    setError(new ErrorObject("Invalid response code"));
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

    public T getResult(){
        return (T)object;
    }

    public boolean hasResult(){
        return object != null;
    }

}
