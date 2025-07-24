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
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.pl_wtx_woocommerce.model.shipping.ShippingMethod;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.SHIPPING_METHODS;


public class ShippingMethodBuilder {

    //<editor-fold defaultstate="collapsed" desc="Reader Builder">
    public static class Reader extends CoreReader.ReaderCore<ShippingMethod>{

        public Reader(int shippingMethodId){
            super(shippingMethodId);
        }

        public Read<ShippingMethod> getResponse(){
            return super.getResponse(SHIPPING_METHODS, new TypeReference<ShippingMethod>() {});
        }

    }
    //</editor-fold>


    //<editor-fold  defaultstate="collapsed" desc="Listing Builder">
    /**
     *
     * Searches the ShippingMethods
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-shipping-methods">https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-shipping-methods</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ListAll<T>> extends CoreList<ShippingMethod, T>{

        public ListAll(){}

        public Listed<ShippingMethod> getResponse(){

            return super.getResponse(
                SHIPPING_METHODS,
                "",
                new TypeReference<List<ShippingMethod>>() {}
            );


        }

    }
    //</editor-fold>

}
