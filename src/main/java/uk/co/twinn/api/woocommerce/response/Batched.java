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
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.response.core.ApiResponse;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.ErrorMessage;

public class Batched<T> extends ApiResponse {

    private Batch<T> batch = new Batch<>();

    public Batched(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    success = true;
                    setBatch(result.getData());
                    break;
                default:
                    success = false;
                    error = new ErrorMessage("Invalid response code");
                    break;
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void setBatch(Object data){
        /* if we are not a batch then something has gone very wrong*/
        this.batch = (Batch<T>)data;
    }

    /*If the id is NOT set then we get an array of product*/
    public Batch<T> getResult(){
        return batch;
    }

    public String toJson(){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(this);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
