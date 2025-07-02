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
import pl.wtx.woocommerce.api.client.model.CustomerDownload;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDownloadsResponse extends ApiResponse {

    private List<CustomerDownload> downloads = new ArrayList<>();

    public CustomerDownloadsResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    //pretty sure we always get an array
                    if (result.getData() instanceof CustomerDownload) {
                        downloads.clear();
                        CustomerDownload download = (CustomerDownload) result.getData();
                        downloads.add(download);
                    }else{
                        setDownloads(result);
                    }
                    break;
                default:
                    setSuccess(false);
                    setError(new ErrorObject("Invalid response code"));
                    break;
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void setDownloads(ApiResponseResult result){
        try {
            this.downloads = (List<CustomerDownload>) result.getData();
        }catch (Exception e){
            Logger.getLogger(CustomerDownloadsResponse.class.getName())
                .log(Level.SEVERE, "Failed to parse list", e);
            setError(new ErrorObject("Parse list failure"));
        }

    }

    public boolean hasDownloads(){
        return !downloads.isEmpty();
    }

    public List<CustomerDownload> getDownloads(){
        return downloads;
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
