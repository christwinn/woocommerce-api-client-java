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
import uk.co.twinn.api.woocommerce.response.Deleted;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

/**
 *
 * This is an Internal class, relying on package-private values to create the rest of the DeleterRequests
 * can not take down to core with exposing inner functions to end user
 *
 */
class CoreDeleter {

    static class DeleterCore<T>{

        protected final int id;
        protected final boolean force;

        public DeleterCore(int id, boolean force){
            this.id = id;
            this.force = force;
        }

        Deleted<T> getResponse(String endPoint, TypeReference<T> type){
            return readResponse(endPoint + "/" + id + "?force=" + force, type);
        }

        private Deleted<T> readResponse(String endPoint, TypeReference<T> type){
            if (id <= 0 || !force) {
                return new Deleted<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Delete is limited to a single object result\n" +
                            "Please set requested id AND set the Force!"
                    )
                );
            }else{
                return new Deleted<>(
                    new Rest<T>().delete(endPoint, type)
                );
            }

        }

    }

    static class ChildDeleterCore<T> extends DeleterCore<T> {

        //set up the private variables
        protected final int childId;

        public ChildDeleterCore(int id, int childId, boolean force){
            super(id, force);
            this.childId = childId;
        }

        Deleted<T> getResponse(String endPoint, String childEndPoint, TypeReference<T> type){
            return readResponse(endPoint + "/" + id + "/" + childEndPoint + "/" + childId, type);
        }
        protected Deleted<T> readResponse(String endPoint, TypeReference<T> type){
            if (childId <= 0) {//this is our only responsibility,
                return new Deleted<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Delete is limited to a single object result\n" +
                            "Please set requested [child] id"
                    )
                );
            }else {
                return super.readResponse(endPoint, type);
            }
        }

    }

    static class DeleterCoreStringKey<T>{

        protected final String key;
        protected final boolean force;

        public DeleterCoreStringKey(String key, boolean force){
            this.key = key;
            this.force = force;
        }

        Deleted<T> getResponse(String endPoint, TypeReference<T> type){
            return readResponse(endPoint + "/" + key + "?force=" + force, type);
        }

        private Deleted<T> readResponse(String endPoint, TypeReference<T> type){
            if (key == null || key.isEmpty() || !force) {
                return new Deleted<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Delete is limited to a single object result\n" +
                            "Please set requested id AND set the Force!"
                    )
                );
            }else{
                return new Deleted<>(
                    new Rest<T>().delete(endPoint, type)
                );
            }

        }

    }

    static class ChildDeleterCoreStringKey<T> extends DeleterCoreStringKey<T>{

        private final String childKey;

        public ChildDeleterCoreStringKey(String key, String childKey, boolean force){
            super(key, force);
            this.childKey = childKey;
        }

        Deleted<T> getResponse(String endPoint, String childEndPoint, TypeReference<T> type){
            return readResponse(endPoint + "/" + key + "/" + childEndPoint + childKey + "?force=" + force, type);
        }

        private Deleted<T> readResponse(String endPoint, TypeReference<T> type){
            if (childKey == null || childKey.isEmpty()) {
                return new Deleted<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Delete is limited to a single object result\n" +
                            "Please set requested [child]Id!"
                    )
                );
            }else{
                return super.getResponse(endPoint, type);
            }

        }

    }

}
