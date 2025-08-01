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
import uk.co.twinn.api.woocommerce.response.Batched;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.Updated;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.setting.SettingOption;

import java.util.List;

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

    public static class Reader extends CoreReader.ReaderCoreStringKey<SettingOption>{

        public Reader(String groupId, String settingId){
            super(groupId + "/" + settingId);
        }

        public Read<SettingOption> getResponse(){
            return super.getResponse(SETTINGS, new TypeReference<SettingOption>() {});
        }

    }

    public static class Updater<T extends Updater<T>> extends CoreCreator<SettingOption, T>{

        private String groupId;
        private final String optionId;

        private Object value;

        public Updater(String groupId, String optionId){
            this.groupId = groupId;
            this.optionId = optionId;
        }

        /*Use for Batch will fail as normal update*/
        public Updater(String BATCH_ONLY_optionId){
            this.optionId = BATCH_ONLY_optionId;
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
                /*return new Updated<>(
                    new Rest<SettingOption>().update(endPoint(), create.toJson())
                );*/
                return super.getUpdate(endPoint(), create.toJson(), new TypeReference<SettingOption>() {});

            }else{
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }

        }
    }


    public static class ListAll<T extends ListAll<T>> extends CoreList<SettingOption, T>{

        private final String groupId;

        public ListAll(String groupId){
            this.groupId = groupId;
        }
        /**
         *  List the Settings
         */
        public Listed<SettingOption> getResponse(){

            if (groupId.isEmpty()){
                return new Listed<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }else {
                return super.getResponse(
                    SETTINGS + "/" + groupId,
                    "",
                    new TypeReference<List<SettingOption>>(){}
                );
                /*return new Listed<>(
                    new Rest<List<SettingOption>>().listAll(
                        SETTINGS + "/" + groupId,
                        ""
                    )
                );*/
            }


        }

    }


    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<SettingOption, T>{

        private final String groupId;

        public Batcher(String groupId){
            super();
            this.groupId = groupId;
        }

        public T addUpdater(Updater<?> update){
            return addUpdater(update.build().settingOption);
        }

        public T addUpdater(List<SettingOption> updateList){
            return super.addUpdate(updateList);
        }

        public T addUpdater(SettingOption update){
            return super.addUpdate(update);
        }

        public Batched<SettingOption> getResponse(){

            //pre-validate
            for (int i = 0; i < batch.getUpdate().size(); i++){
                if (batch.getUpdate().get(i).getId() == null ||
                    batch.getUpdate().get(i).getId().isEmpty()
                ){
                    return super.getFailure(
                        String.format("Id is MANDATORY!, Found Update @ %s with empty id", i)
                    );
                }
            }

            //delete validation is in super should be an empty list as no interface
            return super.getResponse(
                SETTINGS + "/" + groupId,
                batch,
                new TypeReference<BatchResult<SettingOption>>(){}
            );

        }

    }

}
