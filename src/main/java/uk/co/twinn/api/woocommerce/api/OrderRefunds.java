/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.OrderRefundBuilder;

public class OrderRefunds {

    private OrderRefunds(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static OrderRefundBuilder.Creator<?> create(){

        return new OrderRefundBuilder.Creator<>();

    }

    public static OrderRefundBuilder.Reader<?> read(int orderId, int refundId){

        return new OrderRefundBuilder.Reader<>(orderId, refundId);

    }

    public static OrderRefundBuilder.Deleter<?> delete(int orderId, int refundId, boolean force){

        return new OrderRefundBuilder.Deleter<>(orderId, refundId, force);

    }

    public static OrderRefundBuilder.ListAll<?> listing(){

        return new OrderRefundBuilder.ListAll<>();

    }
    //</editor-fold>


}
