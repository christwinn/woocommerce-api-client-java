/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.AuthenticationBuilder;

public class Authentication {

    private Authentication(){}

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
    public static class Http{
        public Http nooooooGOSECURE(String letssEncryptIsFreeWhyAmIExposingDetails){
            return this;
        }

    }

    public static AuthenticationBuilder.Https Https(){

        return new AuthenticationBuilder.Https();

    }

}
