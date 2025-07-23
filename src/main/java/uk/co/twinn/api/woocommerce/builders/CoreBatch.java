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
import uk.co.twinn.api.woocommerce.builders.core.Batch;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.response.Batched;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.Coupon;
import uk.co.twinn.pl_wtx_woocommerce.model.Customer;

/**
 *
 * This is an Internal class, relying on package-private values to create the rest of the BatchRequests
 * can not take down to core with exposing inner functions to end user
 *
 */
class CoreBatch {

    //Require S so we can type Batch, require T, so we can call back on the inheritors
    static class BatchCore<S, T extends BatchCore<S, T>>{

        public static final int MAX_RECORDS = 100;

        protected Batch<S> batch;

        public BatchCore(){
            batch = new Batch<>();
        }

        private Batch<S> getBatch(){
            return batch;
        }

        @SuppressWarnings("unchecked")
        T self() {
            return (T) this;
        }

        public boolean empty(){
            return batch.empty();
        }
        public boolean isEmpty(){
            return size() == 0;
        }

        public int size(){
            return batch.getRecordCount();
        }

        public boolean isFull(){
            return batch.getRecordCount() >= MAX_RECORDS;
        }

        Batched<S> getResponse(String endPoint, Batch<S> batch, TypeReference<?> type){
            return readResponse(endPoint + "/batch", batch, type);
        }

        /*Product Variations, are there others? */
        Batched<S> getResponse(String endPoint, int referenceId, String secondEndPoint, Batch<S> batch, TypeReference<?> type){

            if (referenceId <= 0) {
                return new Batched<>(new ApiResponseResult<>(false, 0, "Reference Id is required"));
            }else {
                return readResponse(endPoint + referenceId + "/" + secondEndPoint + "/batch", batch, type);
            }
        }

        private Batched<S> readResponse(String endPoint, Batch<S> batch, TypeReference<?> type){

            if (batch.isEmpty()){

                return new Batched<>(new ApiResponseResult<>(false, 0, "Nothing to do"));

            }else if (batch.getRecordCount() > MAX_RECORDS){

                return new Batched<>(new ApiResponseResult<>(false, 0,
                    "This API helps you to batch create, update and delete multiple products.\n\n" +
                        "Note: By default it's limited to up to 100 objects to be created, updated or deleted.")
                );

            }else{

                //System.out.println(toJson(batch));

                return new Batched<>(
                    new Rest<BatchResult<S>>().batch(endPoint, toJson(), type)
                );

            }

        }

        public String toJson(){

            return new JacksonObjectMapper().toJson(batch);

        }

    }



}
