/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.response.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.UriBuilderException;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

import java.io.IOException;

public class ApiResponse {

    private JacksonObjectMapper json = new JacksonObjectMapper();

    protected boolean success;
    private ErrorMessage error = null;

    public ApiResponse(){}

    public ApiResponse(ApiResponseResult result){

        if (!result.getSuccess()){

            //html no access to resource? apache disallowed PUT DELETE, outside our scope but return the result to the user
            switch (result.getStatusCode()){
                case 0:
                case 400: case 401: case 402: case 403: case 404: //not going to be json
                case 500: case 502: case 503: case 504: //not going to be json
                    error = new ErrorMessage(result.getMessage());
                    break;
                default:
                    try{
                        error = getObjectMapper().readValue(result.getMessage(), new TypeReference<ErrorMessage>(){});
                    }catch(UriBuilderException | IOException | IllegalArgumentException e){
                        error = new ErrorMessage(e.toString());
                    }
            }

        }else{

            success = true;

        }

    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean hasError(){
        return error != null;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    public ObjectMapper getObjectMapper(){
        return json.getObjectMapper();
    }

}
