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
import uk.co.twinn.api.woocommerce.response.core.ApiResponse;
import uk.co.twinn.api.woocommerce.response.core.ErrorMessage;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listed<T> extends ApiResponse {

    protected List<T> listed = new ArrayList<>();

    public Listed(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    success = true;
                    setList(result);
                    break;
                default:
                    success = false;
                    error = new ErrorMessage("Invalid response code");
                    break;
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void setList(ApiResponseResult result){
        try {
            this.listed = (List<T>) result.getData();
        }catch (Exception e){
            Logger.getLogger(Listed.class.getName())
                .log(Level.SEVERE, "Failed to parse list", e);
            this.error = new ErrorMessage("Parse list failure");
        }
    }

    public boolean isSuccess() {
        return super.success && listed != null;
    }

    public List<T> getResult(){
        return listed;
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
