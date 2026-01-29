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
import com.fasterxml.jackson.databind.MapperFeature;
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
                .disable(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_OPTIONALS)
                //.setDateFormat(new RFC3339DateFormat())
                //handle LocalDateTime in J8
                .registerModule(new JavaTimeModule())
                //.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                // excludes empty ("") strings
                // not what we want as it causes Php to error so...
                // use NON_ABSENT which, if it is null then it is not included but includes isEmpty()
                .setSerializationInclusion(JsonInclude.Include.NON_ABSENT);

            objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        }

        return objectMapper;

    }

    public ObjectMapper getObjectMapper() {
        return objectMapper();
    }

    public String toJson(boolean makePretty, Object object){

        if (makePretty){
            return toPrettyJson(object);
        }else{
            return toJson(object);
        }

    }

    public String toJson(Object object){

        try {

            return objectMapper().writeValueAsString(object);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public String toPrettyJson(Object object){

        try {

            return objectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);

        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);

        }

    }

}
