/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.request.core;

import java.util.List;

public class Seek {

    public static class Searcher<T extends Searcher> extends ParameterCollector {

        T self() {
            return (T) this;
        }

        /**
         * Scope under which the request is made; determines fields present in response. Options: view and edit. Default is view.
         *
         * @param context
         * @return
         */
        public T setContext(String context) {
            addNameValuePair("context", context);
            return self();
        }

        /**
         * Current page of the collection. Default is 1.
         *
         * @param page
         * @return
         */
        public T setPage(int page) {
            if (page != 0) {
                addNameValuePair("page", page);
            }
            return self();
        }

        /**
         * Maximum number of items to be returned in result set. Default is 10.
         *
         * @param per_page
         * @return
         */
        public T setPerPage(int per_page) {
            if (per_page != 0) {
                addNameValuePair("per_page", per_page);
            }
            return self();
        }

        /**
         * Limit results to those matching a string.
         *
         * @param search
         * @return
         */
        public T setSearch(String search) {
            addNameValuePair("search", search);
            return self();
        }


        /**
         * Ensure result set excludes specific IDs.
         *
         * @param exclude
         * @return
         */
        public T setExclude(List<Integer> exclude) {
            addNameValueIntegers("exclude", exclude);
            return self();
        }

        /**
         * Limit result set to specific ids.
         *
         * @param include
         * @return
         */
        public T setInclude(List<Integer> include) {
            addNameValueIntegers("include", include);
            return self();
        }


        /**
         * Order sort attribute ascending or descending. Options: asc and desc.
         * Default is desc.
         *
         * @param order
         * @return
         */
        public T setOrder(String order) {
            addNameValuePair("order", order);
            return self();
        }

        /**
         * Sort collection by object attribute.
         * Options: date, modified, id, include, title, slug, price, popularity, rating, and menu_order.
         * Default is date.
         *
         * @param orderby
         * @return
         */
        public T setOrderby(String orderby) {
            addNameValuePair("orderby", orderby);
            return self();
        }

    }

}
