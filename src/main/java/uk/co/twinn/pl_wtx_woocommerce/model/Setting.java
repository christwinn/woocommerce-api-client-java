/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

import java.util.List;

public class Setting {

    private String id; //	string	A unique identifier that can be used to link settings together.read-only
    private String label; //	string	A human readable label for the setting used in interfaces.read-only
    private String description; //	string	A human readable description for the setting used in interfaces.read-only
    private String parentId; //	string	ID of parent grouping.read-only
    private List<String> subGroups; //	string	IDs for settings sub groups.read-only

    public Setting(){}

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

    @JsonProperty("parent_id")
    public String getParentId() {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(String parent_id) {
        this.parentId = parentId;
    }

    @JsonProperty("sub_groups")
    public List<String> getSubGroups() {
        return subGroups;
    }

    @JsonProperty("sub_groups")
    public void setSubGroups(List<String> subGroups) {
        this.subGroups = subGroups;
    }

    @JsonIgnore
    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }
}
