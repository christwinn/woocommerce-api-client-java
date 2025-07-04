/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.pl_wtx_woocommerce.model.CustomerDownload;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.woocommerce.WooCommerce;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.CUSTOMERS;

public class CustomerDownloadsRequest {

    public static class Reader<T extends Reader<T>>{

        private int customerId;

        T self() {
            return (T) this;
        }

        public T setCustomerId(int customerId){
            this.customerId = customerId;
            return self();
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
