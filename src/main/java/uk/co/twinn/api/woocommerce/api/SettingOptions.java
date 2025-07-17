/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.SettingOptionBuilder;

public class SettingOptions {

    private SettingOptions(){}

    public static SettingOptionBuilder.Reader<?> read(String groupId, String settingId){

        return new SettingOptionBuilder.Reader<>(groupId, settingId);

    }

    public static SettingOptionBuilder.Updater<?> update(String groupId, String settingId){

        return new SettingOptionBuilder.Updater<>(groupId, settingId);

    }

    public static SettingOptionBuilder.ListAll<?> listing(String groupId){

        return new SettingOptionBuilder.ListAll<>(groupId);

    }

    public static SettingOptionBuilder.Batcher<?> batch(String groupId){

        return new SettingOptionBuilder.Batcher<>(groupId);

    }

}
