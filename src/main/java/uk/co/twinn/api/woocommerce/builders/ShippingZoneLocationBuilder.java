/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.ShippingZoneLocation;

import java.util.ArrayList;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class ShippingZoneLocationBuilder  extends ApiRequest {

    private List<ShippingZoneLocation> list = new ArrayList<>();

    public ShippingZoneLocationBuilder(){

    }

    private ShippingZoneLocationBuilder(ShippingZoneLocationBuilder.UpdateList<?> updater){

        this.list = updater.list;

    }

    public static String endPoint(int zoneId){

        return SHIPPINGZONES + "/" + zoneId + "/" + LOCATIONS;

    }

    private String toJson(List<ShippingZoneLocation> zoneLocations){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(zoneLocations);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This is different, takes a list of locations? and returns a list of locations
     *<br/>
     * To be completed
     *
     **/
    public static class UpdateList<T extends UpdateList<T>> {

        private final int zoneId;
        private String code;
        private String type;

        private final List<ShippingZoneLocation> list = new ArrayList<>();

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        public UpdateList(int zoneId){
            this.zoneId = zoneId;
        }

        public T setZoneLocation(ShippingZoneLocation location){
            list.add(location);
            return self();

        }
        public T setZoneLocation(String code, String type){
            list.add(new ShippingZoneLocation(code, type));
            return self();
        }

        protected ShippingZoneLocationBuilder build(){
            return new ShippingZoneLocationBuilder(this);
        }

        public UpdatedList<ShippingZoneLocation> getResponse(){

            if (zoneId >= 0 && !list.isEmpty()){

                ShippingZoneLocationBuilder create = build();

                //make the call
                return new UpdatedList<>(
                    new Rest().updateList(endPoint(zoneId), create.toJson(list), new TypeReference<List<ShippingZoneLocation>>(){})

                );
            }else {
                return new UpdatedList<>(new ApiResponseResult(false, 0, "Missing required list and/or zoneId"));
            }

        }

    }

    /**
     *
     * Searches the ShippingZoneLocations
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-locations-of-a-shipping-zone">
     * https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-locations-of-a-shipping-zone</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ShippingZoneLocationBuilder.ListAll<T>>{

        private final int zoneId;
        public ListAll(int zoneId){
            this.zoneId = zoneId;
        }

        public Listed<ShippingZoneLocation> getResponse(){

            if (zoneId > 0) {
                return new Listed<>(
                    new Rest().listAll(
                        endPoint(zoneId),
                        "",
                        new TypeReference<List<ShippingZoneLocation>>() {}
                    )
                );
            }else{
                return new Listed<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "ZoneId is required.")
                );
            }

        }

    }
}
