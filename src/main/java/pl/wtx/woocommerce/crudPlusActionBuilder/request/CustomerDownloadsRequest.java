/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.crudPlusActionBuilder.request;

import pl.wtx.woocommerce.crudPlusActionBuilder.response.CustomerDownloadsResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.WooCommerce;

import static pl.wtx.woocommerce.crudPlusActionBuilder.defines.EndPoints.CUSTOMERS;

public class CustomerDownloadsRequest {

    //not utilising a customer object to store the id!
    private final Integer customerId;

    public CustomerDownloadsRequest(Reader reader){
        this.customerId = reader.customerId;
    }

    public String endPoint(){

        return getEndPoint() + "/" + Integer.toString(customerId) + "/downloads";

    }

    private static String getEndPoint(){

        return CUSTOMERS;

    }

    public static class Reader<T extends Reader<T>>{

        private int customerId;

        T self() {
            return (T) this;
        }

        public T setCustomerId(int customerId){
            this.customerId = customerId;
            return self();
        }

        protected CustomerDownloadsRequest build(){
            return new CustomerDownloadsRequest(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public CustomerDownloadsResponse getResponse(){
            if (customerId == 0) {
                return new CustomerDownloadsResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "Retrieve customer downloads\n" +
                            "This API lets you retrieve customer downloads permissions.")
                );
            }else {
                WooCommerce woo = new WooCommerce();
                return woo.read(build());
            }
        }

    }

}
