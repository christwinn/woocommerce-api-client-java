/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.systemstatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Security {

    private boolean secureConnection;//	boolean	Is the connection to your store secure?read-only
    private boolean hideErrors; //	boolean	Hide errors from visitors?read-only

    public Security(){}

    @JsonProperty("secure_connection")
    public boolean isSecureConnection() {
        return secureConnection;
    }

    @JsonProperty("secure_connection")
    public void setSecureConnection(boolean secureConnection) {
        this.secureConnection = secureConnection;
    }

    @JsonProperty("hide_errors")
    public boolean isHideErrors() {
        return hideErrors;
    }

    @JsonProperty("hide_errors")
    public void setHideErrors(boolean hideErrors) {
        this.hideErrors = hideErrors;
    }

}
