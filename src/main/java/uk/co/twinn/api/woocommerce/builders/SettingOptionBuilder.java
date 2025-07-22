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
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.Batched;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.Updated;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.SettingOption;
import java.util.logging.Level;

import java.util.List;
import java.util.logging.Logger;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class SettingOptionBuilder extends ApiRequest {

    private final SettingOption settingOption = new SettingOption();

    public SettingOptionBuilder(){}

    private SettingOptionBuilder(Updater<?> updater){

        //not used for normal update but needs to be available for batch!
        settingOption.setId(updater.optionId);
        settingOption.setValue(updater.value);

    }

    public String toJson(){

        try {

            return getObjectMapper().writeValueAsString(settingOption);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Reader<T extends Reader<T>> extends CoreReader.ReaderCoreStringKey<T>{

        public Reader(String groupId, String settingId){
            super(groupId + "/" + settingId);
        }

        @SuppressWarnings("unchecked")
        public Read<SettingOption> getResponse(){
            return (Read<SettingOption>)super.getResponse(SETTINGS, new TypeReference<SettingOption>() {});

        }

    }

    public static class Updater<T extends Updater<T>>{

        private String groupId;
        private final String optionId;

        private Object value;

        public Updater(String groupId, String optionId){
            this.groupId = groupId;
            this.optionId = optionId;
        }

        public Updater(String optionId){
            this.optionId = optionId;
        }

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        public T setValue(Object value) {
            this.value = value;
            return self();
        }

        private SettingOptionBuilder build(){
            return new SettingOptionBuilder(this);
        }

        private String endPoint(){

            return SETTINGS + "/" + groupId + "/" + optionId;

        }

        public Updated<SettingOption> getResponse(){
            if (groupId != null && !groupId.isEmpty() &&
                optionId != null && !optionId.isEmpty()
            ){
                SettingOptionBuilder create = build();
                return new Updated<>(
                    new Rest().update(endPoint(), create.toJson(), new TypeReference<SettingOption>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    @SuppressWarnings("unused")
    public static class ListAll<T extends SettingBuilder.ListAll<T>>{

        private final String groupId;

        public ListAll(String groupId){
            this.groupId = groupId;
        }
        /**
         *  List the Settings
         */
        public Listed<SettingOption> getResponse(){

            if (groupId.isEmpty()){
                return new Listed<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }else {
                return new Listed<>(
                    new Rest().listAll(
                        SETTINGS + "/" + groupId,
                        "",
                        new TypeReference<List<SettingOption>>() {
                        }
                    )
                );
            }


        }

    }


    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<SettingOption, T>{

        private final String groupId;

        public Batcher(String groupId){
            super();
            this.groupId = groupId;
        }

        @SuppressWarnings("unchecked")
        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().settingOption);
            return self();
        }

        @SuppressWarnings("unchecked")
        public Batched<SettingOption> getResponse(){

            return (Batched<SettingOption>) super.getResponse(SETTINGS + "/" + groupId, batch, new TypeReference<Batch<SettingOption>>(){});

        }

    }

}
