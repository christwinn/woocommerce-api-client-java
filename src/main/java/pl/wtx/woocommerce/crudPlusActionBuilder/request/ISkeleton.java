/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.crudPlusActionBuilder.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import pl.wtx.woocommerce.api.client.model.Billing;
import pl.wtx.woocommerce.api.client.model.Customer;
import pl.wtx.woocommerce.api.client.model.Shipping;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.ApiRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.Seek;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.WooCommerce;

import java.util.List;

import static pl.wtx.woocommerce.crudPlusActionBuilder.defines.EndPoints.CUSTOMERS;

public interface ISkeleton {

        public String endPoint();

        public String toJson();

        public interface Creator<T extends Creator<T>>{}

        public interface Reader<T extends Reader<?>>{}

        public interface Updater<T extends Updater<T>> extends Creator<T> {}

        public interface Deleter<T extends Deleter<T>> extends Reader<T> {}

        public interface Batcher<T extends Batcher>{}

        public interface ListAll<T extends ListAll> {}

        public interface Searcher<T extends Searcher> {}

}
