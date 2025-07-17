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
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.builders.core.Seek;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.Refund;

import java.time.LocalDate;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.REFUNDS;

public class RefundBuilder extends ApiRequest {

    public RefundBuilder(){}

    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T>{

        T self() {
            return (T) this;
        }

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
         * @param dp Number of decimal points to use in each resource. Default is 2.
         * @return T
         */
        public T setDp(Integer dp) {
            addNameValuePair("dp", dp);
            return self();
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Listed<Refund> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    REFUNDS,
                    build(),
                    new TypeReference<List<Refund>>(){}
                )
            );

        }

    }

}
