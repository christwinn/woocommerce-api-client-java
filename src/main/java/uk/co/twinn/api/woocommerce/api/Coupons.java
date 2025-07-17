/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.CouponBuilder;

public class Coupons {

    private Coupons(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static CouponBuilder.Creator<?> create(){

        return new CouponBuilder.Creator<>();

    }

    public static CouponBuilder.Reader<?> read(int CouponId){

        return new CouponBuilder.Reader<>(CouponId);

    }

    public static CouponBuilder.Updater<?> update(int CouponId){

        return new CouponBuilder.Updater<>(CouponId);

    }

    public static CouponBuilder.Deleter<?> delete(int CouponId, boolean force){

        return new CouponBuilder.Deleter<>(CouponId, force);

    }

    public static CouponBuilder.Batcher<?> batch(){

        return new CouponBuilder.Batcher<>();

    }

    public static CouponBuilder.ListAll<?> listing(){

        return new CouponBuilder.ListAll<>();

    }
    //</editor-fold>
}
