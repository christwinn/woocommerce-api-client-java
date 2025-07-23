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
import uk.co.twinn.pl_wtx_woocommerce.model.Setting;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.SETTINGS;

public class SettingBuilder {

    private SettingBuilder(){}

    public static class ListAll extends CoreList<Setting>{

        /**
         *  List the Settings
         */
        public Listed<Setting> getResponse(){
            //return new Listed<>(new Rest<List<Setting>>().listAll(
            return super.getResponse(
                SETTINGS,
                "",
                new TypeReference<List<Setting>>() {}
            );

        }

    }

}
