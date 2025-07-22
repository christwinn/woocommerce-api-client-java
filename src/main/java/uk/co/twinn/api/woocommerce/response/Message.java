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


public class Message extends ApiResponse {

    private Msg message = null;

    public Message(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    success = true;
                    if (result.getData() instanceof Msg) {
                        this.message = (Msg) result.getData();
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

    public Msg getMessage() {
        return message;
    }

}
