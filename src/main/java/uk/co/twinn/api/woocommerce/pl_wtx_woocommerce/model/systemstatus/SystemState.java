/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.systemstatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;
import java.util.List;
@JsonPropertyOrder({
    "environment",
    "database",
    "active_plugins",
    "inactive_plugins",
    "dropins_mu_plugins",
    "theme",
    "settings",
    "security",
    "pages",
    "post_type_counts",
    "logging"
})
public class SystemState {

    private Environment environment; //object	SystemStatusEnvironment. See System status - SystemStatusEnvironment propertiesread-only
    private Database database;    //	object	Database. See System status - Database propertiesread-only
    private List<Plugin> activePlugins;    //	array	Active plugins.read-only
    private List<Plugin>  inactivePlugins;    //undocumented
    private HashMap<String, List<Plugin>>  dropinsMuPlugins;    //undocumented
    private Theme theme;    //	object	Theme. See System status - Theme propertiesread-only
    private Settings settings;    //	object	Settings. See System status - Settings propertiesread-only
    private Security security;    //	object	Security. See System status - Security propertiesread-only
    private List<Page> pages;    //	array	WooCommerce pages.

    private List<PostTypeCount> postTypeCounts;    //undocumented
    private Logging logging;      //undocumented

    public SystemState(){}

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }


    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public String toJson() {

        //we want to see everything here, we are read-only so we show the whole caboodle
        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                //.registerModule(new JavaTimeModule()
                //show everything even nulls.
                .setSerializationInclusion(JsonInclude.Include.ALWAYS);

        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        try {

            return objectMapper.writeValueAsString(this);

        } catch (JsonProcessingException e) {

            return "Parse FAILURE!" + e;

        }

    }

    @JsonProperty("active_plugins")
    public List<Plugin> getActivePlugins() {
        return activePlugins;
    }

    @JsonProperty("active_plugins")
    public void setActivePlugins(List<Plugin> activePlugins) {
        this.activePlugins = activePlugins;
    }

    @JsonProperty("inactive_plugins")
    public List<Plugin> getInactivePlugins() {
        return inactivePlugins;
    }

    @JsonProperty("inactive_plugins")
    public void setInactivePlugins(List<Plugin> inactivePlugins) {
        this.inactivePlugins = inactivePlugins;
    }

    @JsonProperty("dropins_mu_plugins")
    public HashMap<String, List<Plugin>> getDropinsMuPlugins() {
        return dropinsMuPlugins;
    }

    @JsonProperty("dropins_mu_plugins")
    public void setDropinsMuPlugins(HashMap<String, List<Plugin>> dropinsMuPlugins) {
        this.dropinsMuPlugins = dropinsMuPlugins;
    }

    @JsonProperty("post_type_counts")
    public List<PostTypeCount> getPostTypeCounts() {
        return postTypeCounts;
    }

    @JsonProperty("post_type_counts")
    public void setPostTypeCounts(List<PostTypeCount> postTypeCounts) {
        this.postTypeCounts = postTypeCounts;
    }

    public Logging getLogging() {
        return logging;
    }

    public void setLogging(Logging logging) {
        this.logging = logging;
    }
}
