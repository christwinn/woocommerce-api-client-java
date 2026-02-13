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
import uk.co.twinn.api.woocommerce.exceptions.ResponseException;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.Updated;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.util.Optional;

/**
 *
 * This is an Internal class, relying on package-private values to create the rest of the ReaderRequests
 * can not take down to core with exposing inner functions to end user
 *
 */
class CoreReader {

    static class ReaderNoParameters<T> {

        /** System Status */
        Read<T> getResponse(String endPoint, TypeReference<?> type){
            return readResponse(endPoint, type);
        }

        private Read<T> readResponse(String endPoint, TypeReference<?> type){

            return new Read<>(
                new Rest<T>().read(endPoint, type)
            );

        }

    }

    static class ReaderCore<T>{

        //set up the private variables
        protected final int id;

        public ReaderCore(int id){
            this.id = id;
        }

        /** this needs to be exported to inheritor but not beyond* package-private*/
        Read<T> getResponse(String endPoint, TypeReference<?> type){
            return readResponse(endPoint + "/" + id, type);
        }

        Optional<T> getRead(String endPoint, TypeReference<?> type){

            return
                new Read<T>(
                    new Rest<T>()
                        .readed(
                            endPoint+ "/" + id,
                            type
                        )
                    ).getObject();

        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        private Read<T> readResponse(String endPoint, TypeReference<?> type){
            if (id <= 0) {
                return new Read<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Read is limited to a single object result\n" +
                            "Please set requested id"
                    )
                );
            }else{
                return new Read<>(
                    new Rest<T>().read(endPoint, type)
                );
            }
        }

    }

    static class ChildReaderCore<T> extends ReaderCore<T>{

        public ChildReaderCore(int id, int childId){
            super(id);
            this.childId = childId;
        }

        //set up the private variables
        private final int childId;

        Read<T> getResponse(String endPoint, String childEndPoint, TypeReference<?> type){
            return readResponse(endPoint + "/" + id + "/" + childEndPoint + "/" + childId, type);
        }
        private Read<T> readResponse(String endPoint, TypeReference<?> type){
            if (childId <= 0) {//this is our only responsibility,
                return new Read<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Read is limited to a single object result\n" +
                            "Please set requested [child]id"
                    )
                );
            }else {
                return super.readResponse(endPoint, type);
            }
        }

    }

    static class ReaderCoreStringKey<T> {

        //set up the private variables
        protected final String key;

        public ReaderCoreStringKey(String key){
            this.key = key;
        }

        /** this needs to be exported to inheritor but not beyond* package-private*/
        Read<T> getResponse(String endPoint, TypeReference<?> type){
            return readResponse(endPoint + "/" + key, type);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        private Read<T> readResponse(String endPoint, TypeReference<?> type){
            if (key == null || key.isEmpty()) {
                return new Read<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Read is limited to a single object result\n" +
                            "Please set requested id"
                    )
                );
            }else{
                return new Read<>(
                    new Rest<T>().read(endPoint, type)
                );
            }
        }

    }

    static class ChildReaderCoreStringKey<T> extends ReaderCoreStringKey<T> {

        //set up the private variables
        protected final String childKey;

        public ChildReaderCoreStringKey(String key, String childKey){
            super(key);
            this.childKey = childKey;
        }

        /** this needs to be exported to inheritor but not beyond* package-private*/
        Read<T> getResponse(String endPoint, String childEndPoint, TypeReference<?> type){
            return readResponse(endPoint + "/" + key + "/" + childEndPoint + childKey, type);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        private Read<T> readResponse(String endPoint, TypeReference<?> type){
            if (childKey == null || childKey.isEmpty()) {
                return new Read<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Read is limited to a single object result\n" +
                            "Please set requested id"
                    )
                );
            }else{
                return super.readResponse(endPoint, type);
            }
        }

    }

}
