/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.response;

import uk.co.twinn.api.woocommerce.response.core.ApiResponse;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.ErrorMessage;
import uk.co.twinn.api.woocommerce.response.core.Msg;


public class Message extends ApiResponse<Msg> {

    private Msg message = null;

    public Message(ApiResponseResult<Msg> result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    success = true;
                    if (result.getMessage() != null) {
                        this.message = new Msg(result.getMessage());
                    }else{
                        this.message = new Msg("Success but failed to decipher the Message, incorrect object type");
                    }
                    break;
                default:
                    success = false;
                    error = new ErrorMessage("Invalid response code");
                    break;
            }
        }

    }

    public Msg getMsg() {
        return message;
    }

    public String getMessage() {
        if (getMsg() != null) {
            return getMsg().getMessage();
        }else{
            return "";
        }

    }

}
