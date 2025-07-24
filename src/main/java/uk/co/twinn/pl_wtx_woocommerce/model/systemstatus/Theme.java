/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model.systemstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder(
    {
        "name",
        "version",
        "version_latest",
        "author_url",
        "is_child_theme",
        "is_block_theme",
        "has_woocommerce_support",
        "has_woocommerce_file",
        "has_outdated_templates",
        "overrides",
        "parent_name",
        "parent_version",
        "parent_version_latest",
        "parent_author_url"
    }
)
public class Theme {

    private String name; //	string	Theme name.read-only
    private String version; //	string	Theme version.read-only
    private String versionLatest; //	string	Latest version of theme.read-only
    private String authorUrl; //	string	Theme author URL.read-only
    private boolean childTheme; //	boolean	Is this theme a child theme?read-only
    private boolean blockTheme; //undocumented
    private boolean hasWoocommerceSupport; //	boolean	Does the theme declare WooCommerce support?read-only
    private boolean hasWoocommerceFile; //	boolean	Does the theme have a woocommerce.php file?read-only
    private boolean outdatedTemplates; //	boolean	Does this theme have outdated templates?read-only
    private List<String> overrides;//	array; //	Template overrides.read-only
    private String parentName; //	string	Parent theme name.read-only
    private String parentVersion; //	string	Parent theme version.read-only
    private String parentVersionLatest; //undocumented
    private String parentAuthorUrl; //	string	Parent theme author URL.read-only

    public Theme(){}

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

    @JsonProperty("author_url")
    public String getAuthorUrl() {
        return authorUrl;
    }

    @JsonProperty("author_url")
    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    @JsonProperty("is_child_theme")
    public boolean isChildTheme() {
        return childTheme;
    }

    @JsonProperty("is_child_theme")
    public void setChildTheme(boolean childTheme) {
        this.childTheme = childTheme;
    }

    @JsonProperty("has_woocommerce_support")
    public boolean isHasWoocommerceSupport() {
        return hasWoocommerceSupport;
    }

    @JsonProperty("has_woocommerce_support")
    public void setHasWoocommerceSupport(boolean hasWoocommerceSupport) {
        this.hasWoocommerceSupport = hasWoocommerceSupport;
    }

    @JsonProperty("has_woocommerce_file")
    public boolean isHasWoocommerceFile() {
        return hasWoocommerceFile;
    }

    @JsonProperty("has_woocommerce_file")
    public void setHasWoocommerceFile(boolean hasWoocommerceFile) {
        this.hasWoocommerceFile = hasWoocommerceFile;
    }

    @JsonProperty("has_outdated_templates")
    public boolean isOutdatedTemplates() {
        return outdatedTemplates;
    }

    @JsonProperty("has_outdated_templates")
    public void setOutdatedTemplates(boolean outdatedTemplates) {
        this.outdatedTemplates = outdatedTemplates;
    }

    public List<String> getOverrides() {
        return overrides;
    }

    public void setOverrides(List<String> overrides) {
        this.overrides = overrides;
    }

    @JsonProperty("parent_name")
    public String getParentName() {
        return parentName;
    }

    @JsonProperty("parent_name")
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @JsonProperty("parent_version")
    public String getParentVersion() {
        return parentVersion;
    }

    @JsonProperty("parent_version")
    public void setParentVersion(String parentVersion) {
        this.parentVersion = parentVersion;
    }

    @JsonProperty("parent_author_url")
    public String getParentAuthorUrl() {
        return parentAuthorUrl;
    }

    @JsonProperty("parent_author_url")
    public void setParentAuthorUrl(String parentAuthorUrl) {
        this.parentAuthorUrl = parentAuthorUrl;
    }

    @JsonProperty("is_block_theme")
    public boolean isBlockTheme() {
        return blockTheme;
    }

    @JsonProperty("is_block_theme")
    public void setBlockTheme(boolean blockTheme) {
        this.blockTheme = blockTheme;
    }

    @JsonProperty("parent_version_latest")
    public String getParentVersionLatest() {
        return parentVersionLatest;
    }

    @JsonProperty("parent_version_latest")
    public void setParentVersionLatest(String parentVersionLatest) {
        this.parentVersionLatest = parentVersionLatest;
    }
}
