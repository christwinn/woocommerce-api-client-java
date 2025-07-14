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
import uk.co.twinn.api.woocommerce.response.Duplicated;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

/**
 *
 * This is an Internal class, relying on package-private values to create the rest of the DuplicatorRequests
 * can not take down to core with exposing inner functions to end user
 *
 */
class CoreDuplicatorRequest {

    static class DuplicatorCore<T extends DuplicatorCore<?>>{

        protected int id;

        public DuplicatorCore(int id){
            this.id = id;
        }

        /*T self() {
            return (T) this;
        }*/


        /*public T setId(int id) {
            this.id = id;
            return self();
        }*/

        Duplicated<?> getResponse(String endPoint, TypeReference<?> type){
            return readResponse(endPoint + "/" + id + "/duplicate", type);
        }

        private Duplicated<?> readResponse(String endPoint, TypeReference<?> type){
            if (id <= 0) {
                return new Duplicated<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Duplicate is limited to a single object result\n" +
                            "Please set requested id!"
                    )
                );
            }else{
                return new Duplicated<>(
                    new Rest().duplicate(endPoint, type)
                );
            }

        }

    }

}
