/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model.global;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.NameValuePair;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

import java.util.List;

/** We already have a setting class! MappedSetting used in 'settings' HashMap as we never fully know the full of object names that may exist but each setting follows the
 * structure below
 **/
public class MappedSetting {

    private String id; //	string	A unique identifier for the setting.read-only
    private String label; //	string	A human readable label for the setting used in interfaces.read-only
    private String description; //	string	A human readable description for the setting used in interfaces.read-only
    private String type; //	string	Type of setting. Options: text, email, number, color, password, textarea, select, multiselect, radio, image_width and checkbox.read-only
    private Object value; //	string	could be a string	could be array	of strings Setting value.
    private Object defaultValue; //	could be a string	could be array of strings Default value for the setting.read-only
    private String tip; //	string	Additional help text shown to the user about the setting.read-only
    private String placeholder; //	string	Placeholder text to be displayed in text inputs.read-only

    private  List<NameValuePair> options; //undocumented

    public MappedSetting(){

    }


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public MappedSetting value(Object value){
        this.value = value;
        return this;
    }
    @JsonProperty("default")
    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
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

    public List<NameValuePair> getOptions() {
        return options;
    }

    public void setOptions( List<NameValuePair> options ) {
        this.options = options;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }

}
