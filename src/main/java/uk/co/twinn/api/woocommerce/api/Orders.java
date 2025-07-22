/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.OrderBuilder;
import uk.co.twinn.pl_wtx_woocommerce.model.Order;

public class Orders {

    private Orders(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static OrderBuilder.Creator<?> create(){

        return new OrderBuilder.Creator<>();

    }

    public static OrderBuilder.Creator<?> create(Order order){

        return new OrderBuilder.Creator<>(order);

    }

    public static OrderBuilder.Reader<?> read(int orderId){

        return new OrderBuilder.Reader<>(orderId);

    }

    public static OrderBuilder.Updater<?> update(int orderId){

        return new OrderBuilder.Updater<>(orderId);

    }

    public static OrderBuilder.Updater<?> update(Order order){

        return new OrderBuilder.Updater<>(order);

    }

    public static OrderBuilder.Deleter<?> delete(int orderId, boolean force){

        return new OrderBuilder.Deleter<>(orderId, force);

    }

    public static OrderBuilder.Batcher<Order, ?> batch(){

        return new OrderBuilder.Batcher<>();

    }
    public static OrderBuilder.ListAll<?> listing(){

        return new OrderBuilder.ListAll<>();

    }
    //</editor-fold>
}
