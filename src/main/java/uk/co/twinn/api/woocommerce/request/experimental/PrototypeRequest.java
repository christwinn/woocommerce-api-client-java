/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request.experimental;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.request.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.woocommerce.WooCommerce;
import uk.co.twinn.pl_wtx_woocommerce.model.Customer;
import uk.co.twinn.pl_wtx_woocommerce.model.Model;

import java.util.List;

/* A Prototype Request to utilise for other Requests*/
public class PrototypeRequest extends ApiRequest {

    public PrototypeRequest(){}

    public static class Reader<S, T extends Reader<S, T>>{

        //set up the private variables
        protected int id;
        protected String endPoint;

        T self() {
            return (T) this;
        }

        //set up the builders to set the private variables
        public T setId(int id) {
            this.id = id;
            return self();
        }

        protected Read<S> getCustomer(String endPoint){
            this.endPoint = endPoint;
            return getResponse();
        }
        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        private Read<S> getResponse(){
            if (id <= 0) {
                return new Read<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "CRUD is limited to a single object result\n" +
                            "Please set requested id"
                    )
                );
            }else{
                return new Read<>(
                    new WooCommerce().read(endPoint, new TypeReference<S>(){})
                );
            }
        }

    }

    public static class Deleter<S, T extends Deleter<S, T>> extends Reader<S, T> {

        private boolean force;

        T self() {
            return (T) this;
        }

        public T force(boolean force){
            this.force = force;
            return self();
        }

        public Deleted<S> getResponse(String endPoint) {
            this.endPoint = endPoint;
            return getResponse();
        }
        /** Returns single Deleted ProductCategory**/
        protected Deleted<S> getResponse() {
            if (id <= 0 || !force) {
                return new Deleted<S>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Id And Force are MANDATORY!")
                );
            }else{
                return new Deleted<S>(
                    new WooCommerce().delete(endPoint, new TypeReference<S>(){})
                );
            }
        }

    }

}

