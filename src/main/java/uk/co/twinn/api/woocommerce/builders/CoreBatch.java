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
import uk.co.twinn.api.woocommerce.exceptions.ResponseException;
import uk.co.twinn.api.woocommerce.response.Batched;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.util.List;
import java.util.Optional;

/**
 *
 * This is an Internal class, relying on package-private values to create the rest of the BatchRequests
 * can not take down to core with exposing inner functions to end user
 *
 */
class CoreBatch {

    //Require S so we can type Batch, require T, so we can call back on the inheritors
    static class BatchCore<S, T extends BatchCore<S, T>> {

        public static final int MAX_RECORDS = 100;

        protected Batch<S> batch;

        public BatchCore() {
            batch = new Batch<>();
        }

        private Batch<S> getBatch() {
            return batch;
        }

        @SuppressWarnings("unchecked")
        T self() {
            return (T) this;
        }

        T addCreate(List<S> createList){
            for (S create : createList) {
                addCreate(create);
            }
            return self();
        }
        T addCreate(S create){
            batch.addCreate(create);
            return self();
        }

        T addUpdate(List<S> updateList){
            for (S update : updateList) {
                addUpdate(update);
            }
            return self();
        }
        T addUpdate(S update){
            batch.addUpdate(update);
            return self();
        }

        T addDelete(Integer delete){
            batch.addDelete(delete);
            return self();
        }

        /**
         *
         * Empty the lists
         * @return are we empty?
         *
         */
        public boolean empty() {
            return batch.empty();
        }

        /**
         *
         * Is the list empty
         * @return are we empty?
         *
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        public int size() {
            return batch.getRecordCount();
        }

        public boolean isFull() {
            return batch.getRecordCount() >= MAX_RECORDS;
        }

        Batched<S> getResponse(String endPoint, Batch<S> batch, TypeReference<?> type) {
            return readResponse(endPoint + "/batch", batch, type);
        }

        Batched<S> getFailure(String failureMessage){
            return new Batched<>(new ApiResponseResult<>(false,0,"Reference Id is required"));
        }

        /*Product Variations, are there others? */
        Batched<S> getResponse(String endPoint, int referenceId, String secondEndPoint, Batch<S> batch, TypeReference<?> type){

            if (referenceId <= 0) {
                return getFailure("Reference Id is required");
            }else {
                return readResponse(endPoint + referenceId + "/" + secondEndPoint + "/batch", batch, type);
            }
        }


        private Batched<S> readResponse(String endPoint, Batch<S> batch, TypeReference<?> type){

            if (batch.isEmpty()){

                return getFailure("Nothing to do");

            }else if (batch.getRecordCount() > MAX_RECORDS){

                return getFailure(
                    "This API helps you to batch create, update and delete multiple products.\n\n" +
                        "Note: By default it's limited to up to 100 objects to be created, updated or deleted."
                );

            }else{

                for (int i = 0; i < batch.getDelete().size(); i++){
                    if (batch.getDelete().get(i) == 0){
                        return getFailure(
                            String.format("Id is MANDATORY!, Found Delete @ %s with id = 0", i)
                        );
                    }
                }

                return new Batched<>(
                    new Rest<BatchResult<S>>().batch(endPoint, toJson(), type)
                );

            }

        }

        Optional<BatchResult<S>> getBatched(String endPoint, Batch<S> batch, TypeReference<?> type){

            return readBatched(endPoint + "/batch", batch, type);

        }

        Optional<BatchResult<S>> getBatched(String endPoint, int referenceId, String secondEndPoint, Batch<S> batch, TypeReference<?> type){

            if (referenceId <= 0) {
                throw new ResponseException("Reference Id is required");
            }else {
                return readBatched(endPoint + referenceId + "/" + secondEndPoint + "/batch", batch, type);
            }
        }

        private Optional<BatchResult<S>> readBatched(String endPoint, Batch<S> batch, TypeReference<?> type){

            if (batch.isEmpty()){

                throw new ResponseException("Nothing to do");

            }else if (batch.getRecordCount() > MAX_RECORDS){

                throw new ResponseException(
                    "This API helps you to batch create, update and delete multiple products.\n\n" +
                        "Note: By default it's limited to up to 100 objects to be created, updated or deleted."
                );

            }else{

                for (int i = 0; i < batch.getDelete().size(); i++){
                    if (batch.getDelete().get(i) == 0){
                        throw new ResponseException(
                            String.format("Id is MANDATORY!, Found Delete @ %s with id = 0", i)
                        );
                    }
                }

                return
                    new Batched<S>(
                        new Rest<BatchResult<S>>()
                            .batched(
                                endPoint, toJson(), type
                            )
                        ).getBatched();

            }

        }

        public String toJson(){

            return new JacksonObjectMapper().toJson(batch);

        }

    }



}
