/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.builders.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dates {

    public static String localDateToIso8601(LocalDate date){

        if (date != null){

            return date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        }else{

            return "";

        }

    }

}
