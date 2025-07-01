/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.response;

import pl.wtx.woocommerce.api.client.model.Download;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;

public class CustomerDownloadsResponse extends ApiResponse {

    private List<Download> downloads = new ArrayList<>();

    public CustomerDownloadsResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    //pretty sure we always get an array
                    if (result.getData() instanceof Download) {
                        downloads.clear();
                        Download download = (Download) result.getData();
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
        this.downloads = (List<Download>) result.getData();
    }

    public boolean hasDownloads(){
        return !downloads.isEmpty();
    }

    public List<Download> getDownloads(){
        return downloads;
    }

}
