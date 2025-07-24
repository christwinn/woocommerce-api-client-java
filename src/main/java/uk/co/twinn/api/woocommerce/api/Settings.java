/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.SettingBuilder;

public class Settings {

    protected Settings(){}

    public static SettingBuilder.ListAll<?> listing(){

        return new SettingBuilder.ListAll<>();

    }
}
