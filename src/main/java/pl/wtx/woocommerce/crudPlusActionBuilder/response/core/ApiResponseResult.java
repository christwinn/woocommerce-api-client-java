/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.response.core;

public class ApiResponseResult {

    private boolean success;
    private int     status;
    private String  message = "";

    private Object data;

    public ApiResponseResult(boolean success, int status, String message){
        this.success = success;
        this.message = message;
        this.status = status;
    }

    public ApiResponseResult(boolean success, int status, Object data){
        this.success = success;
        this.data = data;
        this.status = status;
    }

    public ApiResponseResult(){

    }

    public boolean getSuccess() {
        return success;
    }

    public int getStatusCode() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
