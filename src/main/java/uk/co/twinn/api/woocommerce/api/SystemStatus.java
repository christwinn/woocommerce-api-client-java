/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;
import uk.co.twinn.api.woocommerce.builders.SystemStatusBuilder;

public class SystemStatus {

    protected SystemStatus(){}

    public static SystemStatusBuilder.Reader read(){

        return new SystemStatusBuilder.Reader();

    }

}
