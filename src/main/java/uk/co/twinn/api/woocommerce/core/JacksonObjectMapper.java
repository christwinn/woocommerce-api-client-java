/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonObjectMapper {

    private ObjectMapper objectMapper;

    /*1 standard way to load the objectMapper*/
    private ObjectMapper objectMapper(){

        if (objectMapper == null) {

            objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                //.setDateFormat(new RFC3339DateFormat())
                .registerModule(new JavaTimeModule())
                //.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); excludes empty ("") strings not what we want
                //causes Php to error so... which if it is null then it is not included
                .setSerializationInclusion(JsonInclude.Include.NON_ABSENT);

            objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        }

        return objectMapper;

    }

    public ObjectMapper getObjectMapper() {
        return objectMapper();
    }

    public String toJson(Object object){

        try {

            return objectMapper().writeValueAsString(object);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
