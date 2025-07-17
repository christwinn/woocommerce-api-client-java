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

public class Orders {

    private Orders(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static OrderBuilder.Creator<?> create(){

        return new OrderBuilder.Creator<>();

    }

    public static OrderBuilder.Reader<?> read(int orderId){

        return new OrderBuilder.Reader<>(orderId);

    }

    public static OrderBuilder.Updater<?> update(int orderId){

        return new OrderBuilder.Updater<>(orderId);

    }

    public static OrderBuilder.Deleter<?> delete(int orderId, boolean force){

        return new OrderBuilder.Deleter<>(orderId, force);

    }

    public static OrderBuilder.Batcher<?> batch(){

        return new OrderBuilder.Batcher<>();

    }
    public static OrderBuilder.ListAll<?> listing(){

        return new OrderBuilder.ListAll<>();

    }
    //</editor-fold>
}
