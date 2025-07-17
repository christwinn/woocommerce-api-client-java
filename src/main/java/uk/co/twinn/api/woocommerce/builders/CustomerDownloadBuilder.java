/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.pl_wtx_woocommerce.model.CustomerDownload;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.CUSTOMERS;

public class CustomerDownloadBuilder {

    public static class ListAll<T extends ListAll<T>>{

        private final int customerId;

        public ListAll(int customerId){
            this.customerId = customerId;
        }

        /*T self() {
            return (T) this;
        }*/

        /*public T setCustomerId(int customerId){
            this.customerId = customerId;
            return self();
        }*/

        public Listed<CustomerDownload> getResponse(){
            if (customerId == 0) {
                return new Listed<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Retrieve customer downloads\n" +
                            "This API lets you retrieve customer downloads.")
                );
            }else {
                return new Listed<>(
                    new Rest().listAll(
                        CUSTOMERS + "/" + customerId + "/downloads",
                        "",
                        new TypeReference<List<CustomerDownload>>(){}
                    )
                );
            }
        }

    }

}
