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

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.SHIPPINGZONES;

public class ShippingZoneBuilder extends ApiRequest {

    protected final ShippingZone shippingZone = new ShippingZone();

    public ShippingZoneBuilder(){

    }

    private ShippingZoneBuilder(ShippingZoneBuilder.Creator<?> creator){

        shippingZone.setName(creator.name);
        shippingZone.setOrder(creator.order);

    }

    private ShippingZoneBuilder(ShippingZoneBuilder.Updater<?> updater){

        this((ShippingZoneBuilder.Creator<?>)updater);
        shippingZone.setId(updater.id);
    }

    private ShippingZoneBuilder(ShippingZoneBuilder.Deleter deleter){

        shippingZone.setId(deleter.id);

    }

    public String endPoint(){

        return SHIPPINGZONES +
            (shippingZone.getId() != null && shippingZone.getId() != 0
                ? ("/" + shippingZone.getId())
                : ""
            );

    }

    public String toJson(){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(shippingZone);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends ShippingZoneBuilder.Creator<T>> extends CoreCreator<ShippingZone>{

        protected String name;
        private Integer order;

        public Creator(String name){
            this.name = name;
        }

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        public T setOrder(Integer order) {
            this.order = order;
            return self();
        }

        protected ShippingZoneBuilder build(){
            return new ShippingZoneBuilder(this);
        }

        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<ShippingZone> getResponse(){

            if (name != null && !name.isEmpty()) {
                ShippingZoneBuilder create = build();
                //make the call
                /*return new Created<>(
                    new Rest<ShippingZone>().create(create.endPoint(), create.toJson())
                );*/
                return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<ShippingZone>() {});
            }else{
                return new Created<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Name is required.")
                );
            }
        }


    }

    public static class Updater<T extends ShippingZoneBuilder.Updater<T>> extends ShippingZoneBuilder.Creator<T> {

        private final int id;

        public Updater(int zoneId){
            super("");
            this.id = zoneId;
        }

        public T setName(String name){
            this.name = name;
            return self();
        }

        @Override
        protected ShippingZoneBuilder build(){
            return new ShippingZoneBuilder(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public Updated<ShippingZone> getResponse(){
            if (id > 0){
                ShippingZoneBuilder create = build();
                //make the call
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<ShippingZone>() {});
                /*return new Updated<>(
                    new Rest<ShippingZone>().update(create.endPoint(), create.toJson())
                );*/
            }else {
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<ShippingZone>{

        public Reader(int shippingZoneId){
            super(shippingZoneId);
        }

        public Read<ShippingZone> getResponse(){
            return super.getResponse(SHIPPINGZONES, new TypeReference<ShippingZone>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<ShippingZone>{

        public Deleter(int shippingZoneId, boolean force){
            super(shippingZoneId, force);
        }

        protected ShippingZoneBuilder build(){
            return new ShippingZoneBuilder(this);
        }

        public Deleted<ShippingZone> getResponse(){
            return super.getResponse(SHIPPINGZONES, new TypeReference<ShippingZone>() {});

        }

    }
    //</editor-fold>


    /**
     *
     * Searches the ShippingZones
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-shipping-zones">
     * https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-shipping-zones</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ListAll<T>> extends CoreList<ShippingZone>{

        public Listed<ShippingZone> getResponse(){

            return super.getResponse(
                SHIPPINGZONES,
                "",
                new TypeReference<List<ShippingZone>>() {}
            );
            /*return new Listed<>(
                new Rest<List<ShippingZone>>().listAll(
                    SHIPPINGZONES,
                    ""
                )
            );*/


        }

    }
}
