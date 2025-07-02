/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.response.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.ws.rs.core.UriBuilderException;

import java.io.IOException;

public class ApiResponse {

    private boolean success;
    private ErrorObject error;

    public ApiResponse(){}

    public ApiResponse(ApiResponseResult result){

        if (!result.getSuccess()){

            //html no access to resource? apache disallowed PUT DELETE, outside our scope but return the result to the user
            switch (result.getStatusCode()){
                case 0:
                case 400: case 401: case 402: case 403: case 404: //not going to be json
                case 500: case 502: case 503: case 504: //not going to be json
                    error = new ErrorObject(result.getMessage());
                    break;
                default:
                    try{
                        error = objectMapper().readValue(result.getMessage(), new TypeReference<ErrorObject>(){});
                    }catch(UriBuilderException | IOException | IllegalArgumentException e){
                        error = new ErrorObject(e.toString());
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

    public ErrorObject getError() {
        return error;
    }

    public void setError(ErrorObject error) {
        this.error = error;
    }

    public ObjectMapper getObjectMapper(){
        return objectMapper();
    }

    private ObjectMapper objectMapper(){

        ObjectMapper objectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            //.setDateFormat(new RFC3339DateFormat())
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        return objectMapper;

    }

}
