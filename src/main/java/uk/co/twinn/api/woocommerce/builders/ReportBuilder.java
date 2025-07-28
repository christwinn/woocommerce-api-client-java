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
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.report.ReportListItem;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.report.ReportOrderTotalSummary;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.report.ReportSalesSummary;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.report.ReportTopSellersItem;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

/**
 * Builder may be overkill for the reports, but it fits with the rest so .... *
 */
public class ReportBuilder {

    public ReportBuilder(){}

    public static class ListAll<T extends ListAll<T>> extends CoreList<ReportListItem, T>{
        public Listed<ReportListItem> getResponse(){

            return super.getResponse(
                REPORTS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportListItem>>(){}
            );
            /*return new Listed<>(
                new Rest<List<ReportListItem>>().listAll(
                    REPORTS, //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportListItem>>(){}
                )
            );*/

        }

    }

    public static class SalesSummary<T extends SalesSummary<T>> extends CoreList<ReportSalesSummary, T>{
        public Listed<ReportSalesSummary> getResponse(){

            return super.getResponse(
                REPORTS_SALES, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportSalesSummary>>(){}
            );

            /*return new Listed<>(
                new Rest<List<ReportSalesSummary>>().listAll(
                    REPORTS_SALES, //endPoint, SET endPoint
                    "",
                    new TypeReference<List<ReportSalesSummary>>(){}
                )
            );*/

        }

    }

    public static class TopSellers<T extends TopSellers<T>> extends CoreList<ReportTopSellersItem, T>{
        public Listed<ReportTopSellersItem> getResponse(){

            return super.getResponse(
                REPORTS_TOP_SELLERS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportTopSellersItem>>(){}
            );

        }

    }

    public static class CouponTotals<T extends CouponTotals<T>> extends CoreList<ReportOrderTotalSummary, T>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_COUPONS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}

            );

        }

    }

    public static class CustomerTotals<T extends CustomerTotals<T>> extends CoreList<ReportOrderTotalSummary, T>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_CUSTOMERS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            );

        }

    }

    public static class OrderTotals<T extends OrderTotals<T>> extends CoreList<ReportOrderTotalSummary, T>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_ORDERS_TOTALS,  //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            );

        }

    }

    public static class ProductTotals<T extends ProductTotals<T>> extends CoreList<ReportOrderTotalSummary, T>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_PRODUCTS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            );

        }

    }

    public static class ReviewTotals<T extends ReviewTotals<T>> extends CoreList<ReportOrderTotalSummary, T>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_REVIEWS_TOTALS,
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            );

        }

    }

}
