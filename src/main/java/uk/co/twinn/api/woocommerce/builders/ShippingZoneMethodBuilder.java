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

import uk.co.twinn.pl_wtx_woocommerce.model.shipping.ShippingZoneMethod;
import uk.co.twinn.pl_wtx_woocommerce.model.shipping.ShippingZoneMethodUpdate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.METHODS;
import static uk.co.twinn.api.woocommerce.defines.EndPoints.SHIPPING_ZONES;

public class ShippingZoneMethodBuilder  extends ApiRequest {

    protected final ShippingZoneMethod shippingZoneMethod = new ShippingZoneMethod();
    /** see notes under ShippingZoneMethodUpdate, structure to update is different! **/
    protected final ShippingZoneMethodUpdate shippingZoneMethodUpdate = new ShippingZoneMethodUpdate();

    public ShippingZoneMethodBuilder(){}

    //<editor-fold defaultstate="collapsed" desc="Private Constructors">
    private ShippingZoneMethodBuilder(ShippingZoneMethodBuilder.Creator<?> creator){

        shippingZoneMethod.setMethodId(creator.methodId);

    }

    private ShippingZoneMethodBuilder(ShippingZoneMethodBuilder.Updater<?> updater){

        shippingZoneMethodUpdate.setOrder(updater.order);
        shippingZoneMethodUpdate.setEnabled(updater.enabled);

        Set<Map.Entry<String, String>> entries = updater.values.entrySet();
        for (Map.Entry entry : entries){
            shippingZoneMethodUpdate.addSettings(
                (String)entry.getKey(),
                (String)entry.getValue()
            );
        }

    }
    //</editor-fold>

    public String toJson(){

        try {

            return getObjectMapper().writeValueAsString(shippingZoneMethod);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private String updateJson(){

        try {

            return getObjectMapper().writeValueAsString(shippingZoneMethodUpdate);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private static String endPoint(int zoneId, int methodId){

        //there is a default "Locations not covered by your other zones" with Id 0
        return SHIPPING_ZONES +
            (zoneId >= 0
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
    public static class Creator<T extends Creator<T>> extends CoreCreator<ShippingZoneMethod, T>{

        protected final int zoneId;        //string	ShippingZone name.
        protected String methodId;        //string	ShippingZoneMethod name.

        Creator(int zoneId){

            this.zoneId = zoneId;

        }
        public Creator(int zoneId, String methodId){

            this(zoneId);
            this.methodId = methodId;

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
    public static class Updater<T extends Updater<T>> extends Creator<T> {

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
                    endPoint(zoneId, instanceId),
                    create.updateJson(),
                    new TypeReference<ShippingZoneMethod>() {}
                );

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
            return super.getResponse(SHIPPING_ZONES, METHODS, new TypeReference<ShippingZoneMethod>() {});
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Deleter Builder">
    public static class Deleter extends CoreDeleter.ChildDeleterCore<ShippingZoneMethod>{

        public Deleter(int zoneId, int instanceId, boolean force){
            super(zoneId, instanceId, force);
        }

        public Deleted<ShippingZoneMethod> getResponse(){
            return super.getResponse(SHIPPING_ZONES, METHODS, new TypeReference<ShippingZoneMethod>() {});
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
    public static class ListAll<T extends ListAll<T>> extends CoreList<ShippingZoneMethod, T>{

        private final int zoneId;
        public ListAll(int zoneId){
            this.zoneId = zoneId;
        }

        public Listed<ShippingZoneMethod> getResponse(){

            if (zoneId >= 0){
                return super.getResponse(
                    endPoint(zoneId, 0),
                    "",
                    new TypeReference<List<ShippingZoneMethod>>() {}
                );
            }else {
                return new Listed<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }

        }

    }
    //</editor-fold>

}
