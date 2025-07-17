/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.builders.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Seek {

    public static class SearchCore<T extends SearchCore<?>> extends ParameterCollector {

        T self() {
            return (T) this;
        }

        /**
         * @param context Scope under which the request is made; determines fields present in response.
         *                Options: view and edit.
         *                Default is view.
         * @return T
         */
        public T setContext(String context) {
            addNameValuePair("context", context);
            return self();
        }

    }

    /***
     * Provides
     *  Context
     *
     *  Page, PerPage, Search, Order
     *
     */
    public static class SearchCorePaging<T extends SearchCorePaging<T>> extends SearchCore<T> {
        /**
         * @param page Current page of the collection. Default is 1.
         * @return T
         */
        public T setPage(int page) {
            if (page != 0) {
                addNameValuePair("page", page);
            }
            return self();
        }

        /**
         * @param per_page Maximum number of items to be returned in result set. Default is 10.
         * @return T
         */
        public T setPerPage(int per_page) {
            if (per_page != 0) {
                addNameValuePair("per_page", per_page);
            }
            return self();
        }

        /**
         * @param search Limit results to those matching a string.
         * @return T
         */
        public T setSearch(String search) {
            addNameValuePair("search", search);
            return self();
        }

        /**
         * @param order Order sort attribute ascending or descending. Options: asc and desc.
         *          Default is desc.
         * @return T
         */
        public T setOrder(String order) {
            addNameValuePair("order", order);
            return self();
        }

    }

    /***
     * Provides
     *  Context<br>
     *
     *  Page, PerPage, Search, Order<br>
     *
     *  Exclude, Include, OrderBy<br>
     */
    public static class Searcher<T extends Searcher<T>> extends SearchCorePaging<T> {

        /**
         * @param exclude Ensure result set excludes specific IDs.
         * @return T
         */
        public T setExclude(List<Integer> exclude) {
            addNameValueIntegers("exclude", exclude);
            return self();
        }

        /**
         * @param include Limit result set to specific ids.
         * @return T
         */
        public T setInclude(List<Integer> include) {
            addNameValueIntegers("include", include);
            return self();
        }

        /**
         * @param orderby Sort collection by object attribute.
         *           Options: date, modified, id, include, title, slug, price, popularity, rating, and menu_order.
         *           Default is date.
         * @return T
         */
        public T setOrderby(String orderby) {
            addNameValuePair("orderby", orderby);
            return self();
        }

    }

    /***
     * Provides
     *  Context<br>
     *
     *  Page, PerPage, Search, Order<br>
     *
     *  Exclude, Include, OrderBy<br>
     *
     *  Offset, after, before
     */
    public static class DatesSearcher<T extends DatesSearcher<T>> extends Searcher<T> {

        /**
         * @param offset Offset the result set by a specific number of items.
         * @return T
         */
        public T setOffset(int offset) {
            addNameValuePair("offset", offset);
            return self();
        }

        /**
         * @param after Limit response to resources published after a given ISO8601 compliant date.
         * @return T
         */
        public T setAfter(LocalDate after) {
            addNameValuePair("after", after);
            return self();
        }

        /**
         * @param before Limit response to resources published before a given ISO8601 compliant date.
         * @return T
         */
        public T setBefore(LocalDate before) {
            addNameValuePair("before", before);
            return self();
        }

    }

    public static class StockSearcher<T extends StockSearcher<T>> extends DatesSearcher<T> {

        /**
         * @param parent Limit result set to those of particular parent IDs.
         * @return T
         */
        public T setParent(List<Integer> parent) {
            addNameValueIntegers("parent", parent);
            return self();
        }

        /**
         * @param parent_exclude Limit result set to all items except those of a particular parent ID.
         * @return T
         */
        public T setParentExclude(List<Integer> parent_exclude) {
            addNameValueIntegers("parent_exclude", parent_exclude);
            return self();
        }

        /**
         *
         * @param slug Limit result set to products with a specific slug.
         * @return T
         */
        public T setSlug(String slug) {
            addNameValuePair("slug", slug);
            return self();
        }
        /**
         *
         * @param status Limit result set to products assigned a specific status.
         *           Options: any, draft, pending, private and publish. Default is any.
         * @return T
         */
        public T setStatus(String status) {
            addNameValuePair("status", status);
            return self();
        }

        /**
         *
         * @param include_status Limit result set to products with any of the specified statuses.
         *           Multiple statuses can be provided as a comma-separated list.
         *           Takes precedence over the status parameter.
         *           Options: any, future, trash, draft, pending, private, and publish.
         * @return T
         */
        public T setIncludeStatus(String include_status) {
            addNameValuePair("include_status", include_status);
            return self();
        }
        /**
         *
         * @param exclude_status Exclude products from result set with any of the specified statuses.
         *           Multiple statuses can be provided as a comma-separated list.
         *           Takes precedence over the include_status parameter.
         *           Options: future, trash, draft, pending, private, and publish.
         * @return T
         */
        public T setExcludeStatus(String exclude_status) {
            addNameValuePair("exclude_status", exclude_status);
            return self();
        }

        /**
         *
         * @param sku Limit result set to products with a specific SKU.
         * @return T
         */
        public T setSku(String sku) {
            addNameValuePair("sku", sku);
            return self();
        }

        /**
         *
         * @param tax_class Limit result set to products with a specific tax class.
         *          Default options: standard, reduced-rate and zero-rate.
         * @return T
         */
        public T setTaxClass(String tax_class) {
            addNameValuePair("tax_class", tax_class);
            return self();
        }

        /**
         *
         * @param on_sale Limit result set to products on sale.
         * @return T
         */
        public T setOnSale(boolean on_sale) {
            addNameValuePair("on_sale", on_sale);
            return self();
        }
        /**
         *
         * @param min_price Limit result set to products based on a minimum price.
         * @return T
         */
        public T setMinPrice(BigDecimal min_price) {
            addNameValuePair("min_price", min_price.toString());
            return self();
        }
        /**
         *
         * @param max_price Limit result set to products based on a maximum price.
         * @return T
         */
        public T setMaxPrice(BigDecimal max_price) {
            addNameValuePair("max_price", max_price.toString());
            return self();
        }

        /**
         *
         * @param stock_status Limit result set to products with specified stock status.
         *           Options: instock, outofstock and onbackorder.
         * @return T
         */
        public T setStockStatus(String stock_status) {
            addNameValuePair("stock_status", stock_status);
            return self();
        }
        /**
         *
         * @param virtual Limit result set to virtual products.
         * @return T
         */
        public T setVirtual(boolean virtual) {
            addNameValuePair("virtual", virtual);
            return self();
        }
        /**
         *
         * @param downloadable Limit result set to downloadable products.
         * @return T
         */
        public T setDownloadable(boolean downloadable) {
            addNameValuePair("downloadable", downloadable);
            return self();
        }

    }

}
