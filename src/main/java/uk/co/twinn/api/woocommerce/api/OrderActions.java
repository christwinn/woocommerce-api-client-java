/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.OrderActionBuilder;

public class OrderActions {

    protected OrderActions(){}

    public static OrderActionBuilder.SendEmail sendEmail(int customerId){

        return new OrderActionBuilder.SendEmail(customerId);

    }

}
