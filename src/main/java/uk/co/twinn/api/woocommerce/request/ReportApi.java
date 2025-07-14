/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.ReportListItem;
import uk.co.twinn.pl_wtx_woocommerce.model.ReportOrderTotalSummary;
import uk.co.twinn.pl_wtx_woocommerce.model.ReportSalesSummary;
import uk.co.twinn.pl_wtx_woocommerce.model.ReportTopSellersItem;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

/**
 * An anomoly on the design we do not use internal builders as they are pure pre-defined reads
 * Fits in to WooCommerce.Reports().getList() except no getResponse()
 * does not fit new Reports.Listed<>.getList().getResponse()
 *
 */
public class ReportApi {

    public ReportApi(){}

    public Listed<ReportListItem> getList(){

            return new Listed<ReportListItem>(
                new Rest().listAll(
                    REPORTS, //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportListItem>>(){}
                )
            );

    }

    public Listed<ReportSalesSummary> getSalesSummary(){

        return new Listed<ReportSalesSummary>(
            new Rest().listAll(
                REPORTS_SALES, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportSalesSummary>>(){}
            )
        );

    }

    public Listed<ReportTopSellersItem> getTopSellers(){

        return new Listed<>(
            new Rest().listAll(
                REPORTS_TOP_SELLERS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportTopSellersItem>>(){}
            )
        );

    }

    public Listed<ReportOrderTotalSummary> getCouponsTotals(){

        return new Listed<>(
            new Rest().listAll(
                REPORTS_COUPONS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            )
        );

    }

    public Listed<ReportOrderTotalSummary> getCustomersTotals(){

        return new Listed<>(
            new Rest().listAll(
                REPORTS_CUSTOMERS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            )
        );

    }

    public Listed<ReportOrderTotalSummary> getOrdersTotals(){

        return new Listed<>(
            new Rest().listAll(
                REPORTS_ORDERS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            )
        );

    }

    public Listed<ReportOrderTotalSummary> getProductsTotals(){

        return new Listed<>(
            new Rest().listAll(
                REPORTS_PRODUCTS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            )
        );

    }

    public Listed<ReportOrderTotalSummary> getReviewsTotals(){

        return new Listed<>(
            new Rest().listAll(
                REPORTS_REVIEWS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            )
        );

    }


}
