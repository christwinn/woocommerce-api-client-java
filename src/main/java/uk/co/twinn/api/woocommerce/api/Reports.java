/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.ReportBuilder;

public class Reports {

    private Reports(){}

    public static ReportBuilder.ListAll<?> listAll(){

        return new ReportBuilder.ListAll<>();

    }

    public static ReportBuilder.SalesSummary<?> salesSummary(){

        return new ReportBuilder.SalesSummary<>();

    }

    public static ReportBuilder.TopSellers<?> topSellers(){

        return new ReportBuilder.TopSellers<>();

    }

    public static ReportBuilder.CouponTotals<?> couponTotals(){

        return new ReportBuilder.CouponTotals<>();

    }

    public static ReportBuilder.CustomerTotals<?> customerTotals(){

        return new ReportBuilder.CustomerTotals<>();

    }

    public static ReportBuilder.OrderTotals<?> orderTotals(){

        return new ReportBuilder.OrderTotals<>();

    }

    public static ReportBuilder.ProductTotals<?> productTotals(){

        return new ReportBuilder.ProductTotals<>();

    }

    public static ReportBuilder.ReviewTotals<?> reviewTotals(){

        return new ReportBuilder.ReviewTotals<>();

    }

}
