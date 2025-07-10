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
import uk.co.twinn.api.woocommerce.response.Listed;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListResponse<T> extends ApiResponse {

    protected List<T> listed = new ArrayList<>();

    public ListResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    setList(result);
                    break;
                default:
                    setSuccess(false);
                    setError(new ErrorObject("Invalid response code"));
                    break;
            }
        }

    }

    private void setList(ApiResponseResult result){
        try {
            this.listed = (List<T>) result.getData();
        }catch (Exception e){
            Logger.getLogger(Listed.class.getName())
                .log(Level.SEVERE, "Failed to parse list", e);
            setError(new ErrorObject("Parse list failure"));
        }
    }

    @Override
    public boolean isSuccess() {
        return super.success && listed != null;
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
