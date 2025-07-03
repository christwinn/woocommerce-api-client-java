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

    public static class SearchCore<T extends SearchCore<?>> extends ParameterCollector {

        T self() {
            return (T) this;
        }

        /**
         * @param context Scope under which the request is made; determines fields present in response. Options: view and edit. Default is view.
         * @return T
         */
        public T setContext(String context) {
            addNameValuePair("context", context);
            return self();
        }

    }

    public static class Searcher<T extends Searcher<T>> extends SearchCore<T> {

        /**
         *
         *
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
         *
         *
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
         *
         *
         * @param search Limit results to those matching a string.
         * @return T
         */
        public T setSearch(String search) {
            addNameValuePair("search", search);
            return self();
        }


        /**
         *
         *
         * @param exclude Ensure result set excludes specific IDs.
         * @return T
         */
        public T setExclude(List<Integer> exclude) {
            addNameValueIntegers("exclude", exclude);
            return self();
        }

        /**
         *
         *
         * @param include Limit result set to specific ids.
         * @return T
         */
        public T setInclude(List<Integer> include) {
            addNameValueIntegers("include", include);
            return self();
        }


        /**
         *
         *
         * @param order Order sort attribute ascending or descending. Options: asc and desc.
         *          Default is desc.
         * @return T
         */
        public T setOrder(String order) {
            addNameValuePair("order", order);
            return self();
        }

        /**
         *
         *
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

}
