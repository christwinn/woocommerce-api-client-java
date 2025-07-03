/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.crudPlusActionBuilder.request;

import com.fasterxml.jackson.core.type.TypeReference;
import pl.wtx.woocommerce.api.client.model.CustomerDownload;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.Listed;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.WooCommerce;

import java.util.List;

import static pl.wtx.woocommerce.crudPlusActionBuilder.defines.EndPoints.CUSTOMERS;

public class CustomerDownloadsRequest {

    //not utilising a customer object to store the id!
    private final Integer customerId;

    public CustomerDownloadsRequest(Reader reader){
        this.customerId = reader.customerId;
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
        public Listed<CustomerDownload> getResponse(){
            if (customerId == 0) {
                return new Listed<CustomerDownload>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Retrieve customer downloads\n" +
                            "This API lets you retrieve customer downloads permissions.")
                );
            }else {
                return new Listed<CustomerDownload>(
                    new WooCommerce().listAll(
                        CUSTOMERS + "/" + customerId + "/downloads",
                        "",
                        new TypeReference<List<CustomerDownload>>(){}
                    )
                );
            }
        }

    }

}
