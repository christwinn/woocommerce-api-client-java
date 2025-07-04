/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.request.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class ParameterCollector<T> {

    //includeExclude is a list! while most parameters are single values
    private final HashMap<String, List<String>> urlParameters = new HashMap<>();

    protected void addNameValuePair(String key, String value){
        if (value != null && !value.isEmpty()){
            urlParameters.put(key, Collections.singletonList(encodeValue(value)));
        }
    }

    protected void addNameValuePair(String key, int value){
        addNameValuePair(key, Integer.toString(value));
    }

    protected void addNameValuePair(String key, boolean value){
        addNameValuePair(key, value ? "true" : "false");
    }

    protected void addNameValuePair(String key, LocalDate value){
        addNameValuePair(key, Dates.localDateToIso8601(value));
    }

    protected void addNameValueStrings(String key, List<String> value){

        urlParameters.put(
            key,
            Arrays
                .stream(value.toArray())
                .map(param -> encodeValue((String)param))
                .collect(Collectors.toList())
        );

    }

    protected void addNameValueIntegers(String key, List<Integer> value){

        urlParameters.put(
            key,
            value
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList())
        );

    }

    /*https://www.baeldung.com/java-url-encoding-decoding*/
    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ParameterCollector.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    protected String build(){

        /*NOT WP "&" + key + "="*/
        String encodedURL = urlParameters.keySet().stream()
            .map(
                key ->
                    urlParameters.get(key).stream()
                        .collect(
                            joining(
                                ",",
                                key + "=",
                                ""
                            )
                        )
            ).collect(joining("&", "", ""));

        Logger.getLogger(ParameterCollector.class.getName()).log(Level.INFO, encodedURL);

        return encodedURL;

    }

}
