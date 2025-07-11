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
                    setSuccess(true);
                    this.batch = (Batch<T>)result.getData();
                    break;
                default:
                    setSuccess(false);
                    setError(new ErrorMessage("Invalid response code"));
                    break;
            }
        }

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
