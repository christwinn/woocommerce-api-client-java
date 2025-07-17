/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.TaxRateBuilder;

public class TaxRates {

    private TaxRates(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static TaxRateBuilder.Creator<?> create(){

        return new TaxRateBuilder.Creator<>();

    }

    public static TaxRateBuilder.Reader<?> read(int taxRateId){

        return new TaxRateBuilder.Reader<>(taxRateId);

    }

    public static TaxRateBuilder.Updater<?> update(int taxRateId){

        return new TaxRateBuilder.Updater<>(taxRateId);

    }

    public static TaxRateBuilder.Deleter<?> delete(int taxRateId, boolean force){

        return new TaxRateBuilder.Deleter<>(taxRateId, force);

    }

    public static TaxRateBuilder.Batcher<?> batch(){

        return new TaxRateBuilder.Batcher<>();

    }
    public static TaxRateBuilder.ListAll<?> listing(){

        return new TaxRateBuilder.ListAll<>();

    }
    //</editor-fold>

}
