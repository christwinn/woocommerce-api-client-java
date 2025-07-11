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
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;
import uk.co.twinn.api.woocommerce.response.Batched;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

/**
 *
 * This is an Internal class, relying on package-private values to create the rest of the BatchRequests
 * can not take down to core with exposing inner functions to end user
 *
 */
class CoreBatchRequest {

    static class BatchCore<T extends BatchCore>{

        public static int MAX_RECORDS = 100;

        protected Batch batch;

        public BatchCore(){
            batch = new Batch();
        }

        private Batch getBatch(){
            return batch;
        }

        public boolean empty(){
            return batch.empty();
        }

        public int size(){
            return batch.getRecordCount();
        }

        public boolean isFull(){
            return batch.getRecordCount() >= MAX_RECORDS;
        }

        Batched<?> getResponse(String endPoint, Batch<?> batch, TypeReference<?> type){
            return readResponse(endPoint + "/batch", batch, type);
        }

        /*Product Variations, are there others? */
        Batched<?> getResponse(String endPoint, int referenceId, String secondEndPoint, Batch<?> batch, TypeReference<?> type){

            if (referenceId <= 0) {
                return new Batched<>(new ApiResponseResult(false, 0, "Reference Id is required"));
            }else {
                return readResponse(endPoint + referenceId + "/" + secondEndPoint + "/batch", batch, type);
            }
        }

        private Batched<?> readResponse(String endPoint, Batch<?> batch, TypeReference<?> type){

            if (batch.isEmpty()){

                return new Batched<>(new ApiResponseResult(false, 0, "Nothing to do"));

            }else if (batch.getRecordCount() > MAX_RECORDS){

                return new Batched<>(new ApiResponseResult(false, 0,
                    "This API helps you to batch create, update and delete multiple products.\n\n" +
                        "Note: By default it's limited to up to 100 objects to be created, updated or deleted.")
                );

            }else{

                //System.out.println(toJson(batch));

                return new Batched<>(
                    new Rest().batch(endPoint, toJson(), type)
                );

            }

        }

        public String toJson(){

            return new JacksonObjectMapper().toJson(batch);

        }

    }



}
