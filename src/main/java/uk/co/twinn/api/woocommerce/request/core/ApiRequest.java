/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.request.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

public class ApiRequest {

    public ApiRequest(){}

    protected ObjectMapper getObjectMapper(){
        return JacksonObjectMapper.getObjectMapper();
    }

}
