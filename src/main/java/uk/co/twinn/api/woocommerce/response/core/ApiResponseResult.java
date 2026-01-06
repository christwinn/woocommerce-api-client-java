/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.response.core;

import java.util.Optional;

public class ApiResponseResult<T>{

    private boolean success;
    private int     status;
    private String  message = "";

    private T data;

    public ApiResponseResult(boolean success, int status, String message){
        this.success = success;
        this.message = message;
        this.status = status;
    }

    public ApiResponseResult(boolean success, int status, T data){
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

    public T getData() {
        return data;
    }

    @SuppressWarnings("unchecked")
    public Optional<T> getResult() {
        return (Optional<T>)data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
