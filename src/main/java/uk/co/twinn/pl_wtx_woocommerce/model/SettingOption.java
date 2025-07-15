/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

public class SettingOption {

    private String id; //	string	A unique identifier for the setting.read-only
    private String label; //	string	A human readable label for the setting used in interfaces.read-only
    private String description; //	string	A human readable description for the setting used in interfaces.read-only
    private Object value; //	mixed; //	Setting value.
    private Object defaultSetting; //	mixed	Default value for the setting.read-only
    private String tip; //	string	Additional help text shown to the user about the setting.read-only
    private String placeholder; //	string	Placeholder text to be displayed in text inputs.read-only
    private String type; //	string	Type of setting. Options: text, email, number, color, password, textarea, select, multiselect, radio, image_width and checkbox.read-only
    private Object options; //	object	Array of options (key value pairs) for inputs such as select, multiselect, and radio buttons.read-only
    private String groupId; //	string	An identifier for the group this setting belongs to.read-only

    private Links links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getDefaultSetting() {
        return defaultSetting;
    }

    public void setDefaultSetting(Object defaultSetting) {
        this.defaultSetting = defaultSetting;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getOptions() {
        return options;
    }

    public void setOptions(Object options) {
        this.options = options;
    }

    @JsonProperty("group_id")
    public String getGroupId() {
        return groupId;
    }

    @JsonProperty("group_id")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }
    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }
}
