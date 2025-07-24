/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.SystemStatusToolBuilder;

public class SystemStatusTools {

    protected SystemStatusTools(){}

    public static SystemStatusToolBuilder.Reader read(String toolId){

        return new SystemStatusToolBuilder.Reader(toolId);

    }

    public static SystemStatusToolBuilder.ListAll<?> listing(){

        return new SystemStatusToolBuilder.ListAll<>();

    }

    public static SystemStatusToolBuilder.Runner run(String toolId){

        return new SystemStatusToolBuilder.Runner(toolId);

    }

}
