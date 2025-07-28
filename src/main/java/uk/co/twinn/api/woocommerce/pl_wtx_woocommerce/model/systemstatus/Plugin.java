/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.systemstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "plugin",
    "name",
    "version",
    "version_latest",
    "url",
    "author_name",
    "author_url",
    "network_activated"
})
public class Plugin {
//undocumented
    private String plugin;
    private String name;
    private String version;
    private String versionLatest;
    private String url;
    private String authorName;
    private String authorUrl;
    private Boolean networkActivated;

    public Plugin(){}


    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("version_latest")
    public String getVersionLatest() {
        return versionLatest;
    }

    @JsonProperty("version_latest")
    public void setVersionLatest(String versionLatest) {
        this.versionLatest = versionLatest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("author_name")
    public String getAuthorName() {
        return authorName;
    }

    @JsonProperty("author_name")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @JsonProperty("author_url")
    public String getAuthorUrl() {
        return authorUrl;
    }

    @JsonProperty("author_url")
    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    @JsonProperty("network_activated")
    public Boolean getNetworkActivated() {
        return networkActivated;
    }

    @JsonProperty("network_activated")
    public void setNetworkActivated(Boolean networkActivated) {
        this.networkActivated = networkActivated;
    }
}
