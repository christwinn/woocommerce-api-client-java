/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.*;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

/**
 * Builder may be overkill for the reports, but it fits with the rest so .... *
 */
public class ReportBuilder {

    public ReportBuilder(){}

    public static class ListAll<T extends ListAll<T>>{
        public Listed<ReportListItem> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    REPORTS, //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportListItem>>(){}
                )
            );

        }

    }

    public static class SalesSummary<T extends SalesSummary<T>>{
        public Listed<ReportSalesSummary> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    REPORTS_SALES, //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportSalesSummary>>(){}
                )
            );

        }

    }

    public static class TopSellers<T extends TopSellers<T>>{
        public Listed<ReportTopSellersItem> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    REPORTS_TOP_SELLERS, //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportTopSellersItem>>(){}
                )
            );

        }

    }

    public static class CouponTotals<T extends CouponTotals<T>>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    REPORTS_COUPONS_TOTALS, //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportOrderTotalSummary>>(){}
                )
            );

        }

    }

    public static class CustomerTotals<T extends CustomerTotals<T>>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    REPORTS_CUSTOMERS_TOTALS, //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportOrderTotalSummary>>(){}
                )
            );

        }

    }

    public static class OrderTotals<T extends OrderTotals<T>>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    REPORTS_ORDERS_TOTALS,  //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportOrderTotalSummary>>(){}
                )
            );

        }

    }

    public static class ProductTotals<T extends ProductTotals<T>>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    REPORTS_PRODUCTS_TOTALS, //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportOrderTotalSummary>>(){}
                )
            );

        }

    }

    public static class ReviewTotals<T extends ReviewTotals<T>>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    REPORTS_REVIEWS_TOTALS,
                    "",
                    new TypeReference<List<ReportOrderTotalSummary>>(){}
                )
            );

        }

    }

}
