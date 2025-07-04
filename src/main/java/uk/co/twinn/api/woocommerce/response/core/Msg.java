/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.response.core;

public class Msg extends ErrorObject {

    public Msg(){
        super();
    }

    public Msg(String message){
        super( message );
    }

    public void setMessage(String message){

        super.setMessage(message);

    }
}
