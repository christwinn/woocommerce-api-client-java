/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.CustomerBuilder;

public class Customers {

    private Customers(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static CustomerBuilder.Creator<?> create(){

        return new CustomerBuilder.Creator<>();

    }

    public static CustomerBuilder.Reader<?> read(int customerId){

        return new CustomerBuilder.Reader<>(customerId);

    }

    public static CustomerBuilder.Updater<?> update(int customerId){

        return new CustomerBuilder.Updater<>(customerId);

    }

    public static CustomerBuilder.Deleter<?> delete(int customerId, boolean force){

        return new CustomerBuilder.Deleter<>(customerId, force);

    }

    public static CustomerBuilder.Batcher<?> batch(){

        return new CustomerBuilder.Batcher<>();

    }

    public static CustomerBuilder.ListAll<?> listing(){

        return new CustomerBuilder.ListAll<>();

    }
    //</editor-fold>

}
