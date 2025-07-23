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
import uk.co.twinn.pl_wtx_woocommerce.model.*;

import java.util.HashMap;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.METHODS;
import static uk.co.twinn.api.woocommerce.defines.EndPoints.SHIPPINGZONES;

public class ShippingZoneMethodBuilder  extends ApiRequest {

    protected final ShippingZoneMethod shippingZoneMethod = new ShippingZoneMethod();

    public ShippingZoneMethodBuilder(){}

    //<editor-fold defaultstate="collapsed" desc="Private Constructors">
    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ShippingZoneMethodBuilder(ShippingZoneMethodBuilder.Creator<?> creator){

        shippingZoneMethod.setMethodId(creator.methodId);



    }

    private ShippingZoneMethodBuilder(ShippingZoneMethodBuilder.Updater<?> updater){

        this((ShippingZoneMethodBuilder.Creator<?>)updater);
        shippingZoneMethod.setInstanceId(updater.instanceId);
        shippingZoneMethod.setOrder(updater.order);
        shippingZoneMethod.setEnabled(updater.enabled);

    }
    //</editor-fold>

    public String toJson(){

        try {

            return getObjectMapper().writeValueAsString(shippingZoneMethod);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private static String endPoint(int zoneId, int methodId){

        return SHIPPINGZONES +
            (zoneId > 0
                ? ("/" + zoneId)
                : ""
            ) +
            "/" +
            METHODS +
            (methodId > 0
                ? "/" + methodId
                : ""
            )
        ;

    }

    //<editor-fold defaultstate="collapsed" desc="Creator Builder">
    public static class Creator<T extends Creator<T>> extends CoreCreator<ShippingZoneMethod>{

        protected final int zoneId;        //string	ShippingZone name.
        protected String methodId;        //string	ShippingZoneMethod name.

        private HashMap<String, String> settingNameValue;

        public Creator(int zoneId){
            this.zoneId = zoneId;
        }
        /**
         *
         * @param methodId  	Add this shipping method to the shipping zone.
         * @return T
         */
        public T setMethodId(String methodId) {
            this.methodId = methodId;
            return self();
        }

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        public T setSettingValue(String settingName, String value){
            settingNameValue.put(settingName, value);
            return self();
        }

        private ShippingZoneMethodBuilder build(){
            return new ShippingZoneMethodBuilder(this);
        }

        public Created<ShippingZoneMethod> getResponse(){

            if (zoneId > 0) {
                ShippingZoneMethodBuilder create = build();
                return super.getCreate(
                    endPoint(zoneId, 0),
                    create.toJson(),
                    new TypeReference<ShippingZoneMethod>() {}
                );
                //make the call
                /*return new Created<>(
                    new Rest<ShippingZoneMethod>()
                        .create(
                            endPoint(zoneId, 0),
                            create.toJson()
                        )
                );*/
            }else{
                return new Created<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "ZoneId is required.")
                );
            }
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Update Builder">
    public static class Updater<T extends ShippingZoneMethodBuilder.Updater<T>> extends ShippingZoneMethodBuilder.Creator<T> {

        private final int instanceId;
        private Integer order;
        private Boolean enabled;
        private final HashMap<String, String> values = new HashMap<>();

        public Updater(int zoneId, int instanceId){
            super(zoneId);
            this.instanceId = instanceId;
        }

        public T setOrder(int order){
            this.order = order;
            return self();
        }

        public T setEnabled(boolean enabled){
            this.enabled = enabled;
            return self();
        }

        public T setSetting(String settingName, String value){
            values.put(settingName, value);
            return self();
        }

        private ShippingZoneMethodBuilder build(){
            return new ShippingZoneMethodBuilder(this);
        }

        @Override
        public Updated<ShippingZoneMethod> getResponse(){
            if (zoneId > 0 && instanceId > 0){

                ShippingZoneMethodBuilder create = build();
                return super.getUpdate(
                    endPoint(zoneId, 0),
                    create.toJson(),
                    new TypeReference<ShippingZoneMethod>() {}
                );
                /*return new Updated<>(
                    new Rest<ShippingZoneMethod>().update(endPoint(zoneId, instanceId), create.toJson())
                );*/

            }else{
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier(s)"));
            }

        }
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Reader Builder">
    public static class Reader extends CoreReader.ChildReaderCore<ShippingZoneMethod>{

        public Reader(int zoneId, int instanceId){
            super(zoneId, instanceId);
        }

        public Read<ShippingZoneMethod> getResponse(){
            return super.getResponse(SHIPPINGZONES, METHODS, new TypeReference<ShippingZoneMethod>() {});
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Deleter Builder">
    public static class Deleter extends CoreDeleter.ChildDeleterCore<ShippingZoneMethod>{

        public Deleter(int zoneId, int instanceId, boolean force){
            super(zoneId, instanceId, force);
        }

        public Deleted<ShippingZoneMethod> getResponse(){
            return super.getResponse(SHIPPINGZONES, METHODS, new TypeReference<ShippingZoneMethod>() {});
        }

    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Listing Builder">
    /**
     *
     * Searches the ShippingZoneMethods
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-shippingZoneMethods">...</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ListAll<T>> extends CoreList<ShippingZoneMethod>{

        private final int zoneId;
        public ListAll(int zoneId){
            this.zoneId = zoneId;
        }

        public Listed<ShippingZoneMethod> getResponse(){

            if (zoneId > 0){
                return new Listed<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }else {
                return super.getResponse(
                    endPoint(zoneId, 0),
                    "",
                    new TypeReference<List<ShippingZoneMethod>>() {}
                );
                /*return new Listed<>(
                    new Rest<List<ShippingZoneMethod>>().listAll(
                        endPoint(zoneId, 0),
                        ""
                    )
                );*/
            }

        }

    }
    //</editor-fold>

}
