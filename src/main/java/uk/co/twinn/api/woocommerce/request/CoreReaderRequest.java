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
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

/**
 *
 * This is an Internal class, relying on package-private values to create the rest of the ReaderRequests
 * can not take down to core with exposing inner functions to end user
 *
 */
class CoreReaderRequest {

    static class ReaderCore<T extends ReaderCore> {

        //set up the private variables
        protected int id;
        /*package private*/
        String endPoint;

        T self() {return (T) this;}

        /**
         * @param id set the id for the record to read
         * @return
         */
        public T setId(int id) {
            this.id = id;
            return self();
        }

        /** this needs to be exported to inheritor but not beyond* package-private*/
        Read<?> getResponse(String endPoint, TypeReference<?> type){
            return readResponse(endPoint + "/" + id, type);
        }


        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        private Read<?> readResponse(String endPoint, TypeReference<?> type){
            if (id <= 0) {
                return new Read<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Read is limited to a single object result\n" +
                            "Please set requested id"
                    )
                );
            }else{
                return new Read<>(
                    new Rest().read(endPoint, type)
                );
            }
        }

    }

    static class ChildReaderCore<T extends ChildReaderCore> extends ReaderCore<T>{

        //set up the private variables
        private int childId;

        T self() {
            return (T) this;
        }
        /**
         *
         * @param childId we are protected so we display a nice setX to consumer
         * @return T
         */
        protected T setChildId(int childId) {
            this.childId = childId;
            return self();
        }

        Read<?> getResponse(String endPoint, String childEndPoint, TypeReference<?> type){
            return readResponse(endPoint + "/" + id + "/" + childEndPoint + "/" + childId, type);
        }
        private Read<?> readResponse(String endPoint, TypeReference<?> type){
            if (childId <= 0) {//this is our only responsibility,
                return new Read<>(
                    new ApiResponseResult(
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

}
