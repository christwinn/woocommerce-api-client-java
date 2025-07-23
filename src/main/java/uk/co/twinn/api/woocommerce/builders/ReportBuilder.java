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
import uk.co.twinn.pl_wtx_woocommerce.model.*;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

/**
 * Builder may be overkill for the reports, but it fits with the rest so .... *
 */
public class ReportBuilder {

    public ReportBuilder(){}

    public static class ListAll extends CoreList<ReportListItem>{
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

    public static class SalesSummary extends CoreList<ReportSalesSummary>{
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

    public static class TopSellers extends CoreList<ReportTopSellersItem>{
        public Listed<ReportTopSellersItem> getResponse(){

            return super.getResponse(
                REPORTS_TOP_SELLERS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportTopSellersItem>>(){}
            );

        }

    }

    public static class CouponTotals extends CoreList<ReportOrderTotalSummary>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_COUPONS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}

            );

        }

    }

    public static class CustomerTotals extends CoreList<ReportOrderTotalSummary>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_CUSTOMERS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            );

        }

    }

    public static class OrderTotals extends CoreList<ReportOrderTotalSummary>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_ORDERS_TOTALS,  //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            );

        }

    }

    public static class ProductTotals extends CoreList<ReportOrderTotalSummary>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_PRODUCTS_TOTALS, //endPoint, SET endPoint
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            );

        }

    }

    public static class ReviewTotals extends CoreList<ReportOrderTotalSummary>{
        public Listed<ReportOrderTotalSummary> getResponse(){

            return super.getResponse(
                REPORTS_REVIEWS_TOTALS,
                "",
                new TypeReference<List<ReportOrderTotalSummary>>(){}
            );

        }

    }

}
