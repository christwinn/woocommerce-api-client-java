/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.crudPlusActionBuilder.request;

import pl.wtx.woocommerce.crudPlusActionBuilder.response.AuthenticationResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.Configuration;

public class AuthenticationRequest {

    /**
     *  <h1>This is NOT to be supported</h1>
     *
     *  <quote>You must use OAuth 1.0a "one-legged" authentication to ensure REST API credentials cannot be intercepted by an attacker.
     *  Typically you will use any standard OAuth 1.0a library in the language of your choice to handle the authentication,
     *  or generate the necessary parameters by following the following instructions.</quote>
     *
     *  <p><h4>My Point Of View</h4>
     *      But we are still exposing our data in the clear over an insecure system.
     *      Personally if a website can NOT take secure payments it will not get my money.
     *      Therefore how can we morally support plain http?
     *      Simple. We Don't.
     *  </p>
     *
     *  <h2>Https can be done for FREE!</h2>
     *  Let's Encrypt is a Certificate Authority that provides free TLS certificates,
     *  making it easy for websites to enable HTTPS encryption and create a more secure
     *  Internet for everyone.<br/>
     *
     *  <a href="https://letsencrypt.org">Let's Encrypt is a project of the nonprofit Internet Security Research Group.</a>
     *
     */
    public static class Http<T extends Http<T>>{
        public T nooooooGOSECURE(String letssEncryptIsFreeWhyAmIExposingDetails){
            return (T) this;
        }

    }

    /**
     *
     * REST API keys
     *         Pre-generated keys can be used to authenticate use of the REST API endpoints.<br/>
     *         New keys can be generated either through the WordPress admin interface
     *
     */
    public static class Https<T extends Https<T>>{

        private String website;
        private String apiPath;
        private String key;
        private String secret;

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
        public AuthenticationResponse getResponse(){

            new Configuration.Builder()
                .website(website)
                .key(key)
                .secret(secret)
                .api(apiPath)
                .build();

            return new AuthenticationResponse(
                new ApiResponseResult(
                    true,
                    200,
                    "{\"message\": \"The Authentication Configuration has been updated with the provided values.\"}"
                )
            );

        }

    }

}
