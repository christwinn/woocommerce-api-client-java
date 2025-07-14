/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.response.core;

import org.jetbrains.annotations.NotNull;

public class ErrorMessage {
    private String code;
    private String message;
    private ErrorData data;

    public ErrorMessage(){}

    public ErrorMessage(String message){
        this.message = message;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    @NotNull
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public ErrorData getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ErrorData data) {
        this.data = data;
    }

}
