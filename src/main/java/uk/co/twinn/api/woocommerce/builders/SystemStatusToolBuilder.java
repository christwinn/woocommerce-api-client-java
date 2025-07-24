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
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.Ran;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.pl_wtx_woocommerce.model.systemstatus.SystemStatusTool;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class SystemStatusToolBuilder extends ApiRequest {

    private final SystemStatusTool systemStatusTool = new SystemStatusTool();

    private SystemStatusToolBuilder(Runner<?> runner){

        systemStatusTool.setConfirm(runner.confirm);

    }

    private String toJson(){

        try {

            return getObjectMapper().writeValueAsString(systemStatusTool);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Reader Builder">
    public static class Reader extends CoreReader.ReaderCoreStringKey<SystemStatusTool>{

        public Reader(String id){
            super(id);
        }

        public Read<SystemStatusTool> getResponse(){
            return super.getResponse(SYSTEM_STATUS_TOOLS, new TypeReference<SystemStatusTool>() {});
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Runner Builder">
    public static class Runner<T extends Runner<T>> extends CoreCreator<SystemStatusTool, T>{

        private final String toolId;
        private Boolean confirm;

        public Runner(String toolId){
            this.toolId = toolId;
        }

        public Runner(SystemStatusTool systemStatusTool){
            this.toolId = systemStatusTool.getId();
            this.confirm = systemStatusTool.isConfirm();
        }

        /*Require separate confirmation*/
        public T setConfirm(boolean confirm){
            this.confirm = confirm;
            return self();
        }

        private String endPoint(){

            return SYSTEM_STATUS_TOOLS + "/" + toolId;

        }

        private SystemStatusToolBuilder build(){
            return new SystemStatusToolBuilder(this);
        }

        public Ran<SystemStatusTool> getResponse(){
            if (toolId != null && !toolId.isEmpty() &&
                confirm != null && confirm
            ){
                SystemStatusToolBuilder create = build();
                return super.getRunner(endPoint(), create.toJson(), new TypeReference<SystemStatusTool>() {});

            }else{
                return new Ran<>(new ApiResponseResult<>(false, 0, "Invalid Identifier and/or confirmation required"));
            }

        }
    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Listing Builder">
    /**
     *
     * Searches the SystemStatusTools
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/#list-all-tools-from-system-status">https://woocommerce.github.io/woocommerce-rest-api-docs/#list-all-tools-from-system-status</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ShippingMethodBuilder.ListAll<T>> extends CoreList<SystemStatusTool, T>{

        public ListAll(){}

        public Listed<SystemStatusTool> getResponse(){

            return super.getResponse(
                SYSTEM_STATUS_TOOLS,
                "",
                new TypeReference<List<SystemStatusTool>>() {}
            );


        }

    }
    //</editor-fold>

}
