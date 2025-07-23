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
    public static class Https{

        private String website;
        private String apiPath;
        private String key;
        private String secret;


        public Https setWebsite(String website){
            this.website = website;
            return this;
        }

        public Https setKey(String key){
            this.key = key;
            return this;
        }
        public Https setSecret(String secret){
            this.secret = secret;
            return this;
        }
        public Https setApiPath(String apiPath){
            this.apiPath = apiPath;
            return this;
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
