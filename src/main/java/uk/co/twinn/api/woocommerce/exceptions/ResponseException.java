/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.exceptions;

import java.io.Serializable;

public class ResponseException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1234567L;

    public ResponseException(String cause){
        super(cause);
    }

    public ResponseException(){
        super("An Error has occurred");
    }

    public ResponseException(Exception cause){
        super(cause);
    }

}

