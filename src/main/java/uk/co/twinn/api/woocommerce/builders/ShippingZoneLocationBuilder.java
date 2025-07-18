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
import uk.co.twinn.pl_wtx_woocommerce.model.ShippingZone;
import uk.co.twinn.pl_wtx_woocommerce.model.ShippingZoneLocation;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class ShippingZoneLocationBuilder  extends ApiRequest {

    private int zoneId;

    protected final ShippingZoneLocation shippingZoneLocation = new ShippingZoneLocation();

    public ShippingZoneLocationBuilder(){

    }

    /*private ShippingZoneLocationBuilder(ShippingZoneLocationBuilder.Updater<?> updater){

        shippingZoneLocation.setCode(updater.code);
        shippingZoneLocation.setType(updater.type);

    }*/

    public String endPoint(){

        return SHIPPINGZONES +
            (zoneId != 0
                ? ("/" + zoneId)
                : ""
            )
            +
            LOCATIONS;

    }

    public String toJson(){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(shippingZoneLocation);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This is different, takes a list of locations? and returns a list of locations
     *
     * To be completed
     *
     *
    public static class Updater<T extends ShippingZoneLocationBuilder.Updater<T>> {

        private final int zoneId;
        private String code;
        private String type;

        T self() {
            return (T) this;
        }

        public Updater(int zoneId){
            this.zoneId = zoneId;
        }

        public T setCode(String code){
            this.code = code;
            return self();
        }

        public T setType(String type){
            this.type = type;
            return self();
        }

        protected ShippingZoneLocationBuilder build(){
            return new ShippingZoneLocationBuilder(this);
        }

        @Override
        public Updated<ShippingZoneLocation> getResponse(){

                ShippingZoneLocationBuilder create = build();
                //make the call
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ShippingZoneLocation>(){})
                );

            return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));

        }

    }*/

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
                return new Listed<ShippingZoneLocation>(
                    new Rest().listAll(
                        SHIPPINGZONES + "/" + zoneId + "/" + LOCATIONS,
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
