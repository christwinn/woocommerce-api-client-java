/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import uk.co.twinn.api.woocommerce.response.Message;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Configuration;

public class AuthenticationBuilder {



    /**
     *
     * REST API keys
     *         Pre-generated keys can be used to authenticate use of the REST API endpoints.<br/>
     *         New keys can be generated through the WordPress admin interface
     *
     */
    public static class Https<T extends Https<T>>{

        private String website;
        private String apiPath;
        private String key;
        private String secret;

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        public T setWebsite(String website){
            this.website = website;
            return self();
        }

        public T setKey(String key){
            this.key = key;
            return self();
        }
        public T setSecret(String secret){
            this.secret = secret;
            return self();
        }
        public T setApiPath(String apiPath){
            this.apiPath = apiPath;
            return self();
        }

        /**
         *  Stores the provided details for later use by the system
         *  This only needs to be done once in our application
         */
        public Message getResponse(){

            new Configuration.Builder()
                .website(website)
                .key(key)
                .secret(secret)
                .api(apiPath)
                .build();

            return new Message(
                new ApiResponseResult<>(
                    true,
                    200,
                    "{\"message\": \"The Authentication Configuration has been updated with the provided values.\"}"
                )
            );

        }

    }

}
