/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.CustomerDownloadBuilder;

public class CustomerDownloads {

    private CustomerDownloads(){}

    public static CustomerDownloadBuilder.ListAll listing(int customerId){

        return new CustomerDownloadBuilder.ListAll(customerId);

    }

}
