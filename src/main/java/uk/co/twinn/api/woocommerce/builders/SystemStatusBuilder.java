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
import uk.co.twinn.api.woocommerce.exceptions.ResponseException;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductBrand;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.systemstatus.SystemState;

import java.util.Optional;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS_BRANDS;
import static uk.co.twinn.api.woocommerce.defines.EndPoints.SYSTEM_STATUS;

public class SystemStatusBuilder {

    //<editor-fold defaultstate="collapsed" desc="Reader Builder">
    public static class Reader extends CoreReader.ReaderNoParameters<SystemState>{

        public Reader(){
            super();
        }

        public Read<SystemState> getResponse(){
            return super.getResponse(SYSTEM_STATUS, new TypeReference<SystemState>() {});
        }

        public Optional<SystemState> getRead() throws ResponseException {

            return super.getRead(SYSTEM_STATUS, new TypeReference<SystemState>() {});

        }

    }
    //</editor-fold>

}
