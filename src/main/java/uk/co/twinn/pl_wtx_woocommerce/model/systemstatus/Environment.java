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

@JsonPropertyOrder(
    {
        "home_url",
        "site_url",
        "store_id",
        "version",
        "log_directory",
        "log_directory_writable",
        "wp_version",
        "wp_multisite",
        "wp_memory_limit",
        "wp_debug_mode",
        "wp_cron",
        "language",
        "external_object_cache",
        "server_info",
        "server_architecture",
        "php_version",
        "php_post_max_size",
        "php_max_execution_time",
        "php_max_input_vars",
        "curl_version",
        "suhosin_installed",
        "max_upload_size",
        "mysql_version",
        "mysql_version_string",
        "default_timezone",
        "fsockopen_or_curl_enabled",
        "soapclient_enabled",
        "domdocument_enabled",
        "gzip_enabled",
        "mbstring_enabled",
        "remote_post_successful",
        "remote_post_response",
        "remote_get_successful",
        "remote_get_response"
    }
)
public class Environment {

    private String homeUrl;//	string	Home URL.read-only
    private String siteUrl;//	string	Site URL.read-only
    private String storeId;//	undocumented

    private String version;//	string	WooCommerce version.read-only
    private String logDirectory;//	string	Log directory.read-only
    private boolean logDirectoryWritable;//	boolean	Is log directory writable?read-only
    private String wpVersion;//	string	WordPress version.read-only
    private boolean wpMultisite;//	boolean	Is WordPress multisite?read-only
    private int wpMemoryLimit;//	integer	WordPress memory limit.read-only
    private boolean wpDebugMode;//	boolean	Is WordPress debug mode active?read-only
    private boolean wpCron;//	boolean	Are WordPress cron jobs enabled?read-only
    private String language;//	string	WordPress language.read-only
    private String externalObjectCache;//	undocumented
    private String serverInfo;//	string	Server info.read-only
    private String serverArchitecture;//	undocumented
    private String phpVersion;//	string	PHP version.read-only
    private int phpPostMaxSize;//	integer	PHP post max size.read-only
    private int phpMaxExecutionTime;//	integer	PHP max execution time.read-only
    private int phpMaxInputVars;//	integer	PHP max input vars.read-only
    private String curlVersion;//	string	cURL version.read-only
    private boolean suhosinInstalled;//	boolean	Is SUHOSIN installed?read-only
    private int maxUploadSize;//	integer	Max upload size.read-only
    private String mysqlVersion;//	string	MySQL version.read-only
    private String mysqlVersionString;//undocumented
    private String defaultTimezone;//	string	Default timezone.read-only
    private boolean fsockopenOrCurlEnabled;//	boolean	Is fsockopen/cURL enabled?read-only
    private boolean soapClientEnabled;//	boolean	Is SoapClient class enabled?read-only
    private boolean domDocumentEnabled;//	boolean	Is DomDocument class enabled?read-only
    private boolean gzipEnabled;//	boolean	Is GZip enabled?read-only
    private boolean mbstringEnabled;//	boolean	Is mbstring enabled?read-only
    private boolean remotePostSuccessful;//	boolean	Remote POST successful?read-only
    private String remotePostResponse;//	string	Remote POST response.read-only
    private boolean remoteGetSuccessful;//	boolean	Remote GET successful?read-only
    private String remoteGetResponse;//	string	Remote GET response.read-only

    public Environment(){}

    @JsonProperty("home_url")
    public String getHomeUrl() {
        return homeUrl;
    }

    @JsonProperty("home_url")
    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    @JsonProperty("site_url")
    public String getSiteUrl() {
        return siteUrl;
    }

    @JsonProperty("site_url")
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("log_directory")
    public String getLogDirectory() {
        return logDirectory;
    }

    @JsonProperty("log_directory")
    public void setLogDirectory(String logDirectory) {
        this.logDirectory = logDirectory;
    }

    @JsonProperty("log_directory_writable")
    public boolean isLogDirectoryWritable() {
        return logDirectoryWritable;
    }

    @JsonProperty("log_directory_writable")
    public void setLogDirectoryWritable(boolean logDirectoryWritable) {
        this.logDirectoryWritable = logDirectoryWritable;
    }

    @JsonProperty("wp_version")
    public String getWpVersion() {
        return wpVersion;
    }

    @JsonProperty("wp_version")
    public void setWpVersion(String wpVersion) {
        this.wpVersion = wpVersion;
    }

    @JsonProperty("wp_multisite")
    public boolean isWpMultisite() {
        return wpMultisite;
    }

    @JsonProperty("wp_multisite")
    public void setWpMultisite(boolean wpMultisite) {
        this.wpMultisite = wpMultisite;
    }

    @JsonProperty("wp_memory_limit")
    public int getWpMemoryLimit() {
        return wpMemoryLimit;
    }

    @JsonProperty("wp_memory_limit")
    public void setWpMemoryLimit(int wpMemoryLimit) {
        this.wpMemoryLimit = wpMemoryLimit;
    }

    @JsonProperty("wp_debug_mode")
    public boolean isWpDebugMode() {
        return wpDebugMode;
    }

    @JsonProperty("wp_debug_mode")
    public void setWpDebugMode(boolean wpDebugMode) {
        this.wpDebugMode = wpDebugMode;
    }

    @JsonProperty("wp_cron")
    public boolean isWpCron() {
        return wpCron;
    }

    @JsonProperty("wp_cron")
    public void setWpCron(boolean wpCron) {
        this.wpCron = wpCron;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("server_info")
    public String getServerInfo() {
        return serverInfo;
    }

    @JsonProperty("server_info")
    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }

    @JsonProperty("php_version")
    public String getPhpVersion() {
        return phpVersion;
    }

    @JsonProperty("php_version")
    public void setPhpVersion(String phpVersion) {
        this.phpVersion = phpVersion;
    }

    @JsonProperty("php_post_max_size")
    public int getPhpPostMaxSize() {
        return phpPostMaxSize;
    }

    @JsonProperty("php_post_max_size")
    public void setPhpPostMaxSize(int phpPostMaxSize) {
        this.phpPostMaxSize = phpPostMaxSize;
    }

    @JsonProperty("php_max_execution_time")
    public int getPhpMaxExecutionTime() {
        return phpMaxExecutionTime;
    }

    @JsonProperty("php_max_execution_time")
    public void setPhpMaxExecutionTime(int phpMaxExecutionTime) {
        this.phpMaxExecutionTime = phpMaxExecutionTime;
    }

    @JsonProperty("php_max_input_vars")
    public int getPhpMaxInputVars() {
        return phpMaxInputVars;
    }

    @JsonProperty("php_max_input_vars")
    public void setPhpMaxInputVars(int phpMaxInputVars) {
        this.phpMaxInputVars = phpMaxInputVars;
    }

    @JsonProperty("curl_version")
    public String getCurlVersion() {
        return curlVersion;
    }

    @JsonProperty("curl_version")
    public void setCurlVersion(String curlVersion) {
        this.curlVersion = curlVersion;
    }

    @JsonProperty("suhosin_installed")
    public boolean isSuhosinInstalled() {
        return suhosinInstalled;
    }

    @JsonProperty("suhosin_installed")
    public void setSuhosinInstalled(boolean suhosinInstalled) {
        this.suhosinInstalled = suhosinInstalled;
    }

    @JsonProperty("max_upload_size")
    public int getMaxUploadSize() {
        return maxUploadSize;
    }

    @JsonProperty("max_upload_size")
    public void setMaxUploadSize(int maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    @JsonProperty("mysql_version")
    public String getMysqlVersion() {
        return mysqlVersion;
    }

    @JsonProperty("mysql_version")
    public void setMysqlVersion(String mysqlVersion) {
        this.mysqlVersion = mysqlVersion;
    }

    @JsonProperty("default_timezone")
    public String getDefaultTimezone() {
        return defaultTimezone;
    }

    @JsonProperty("default_timezone")
    public void setDefaultTimezone(String defaultTimezone) {
        this.defaultTimezone = defaultTimezone;
    }

    @JsonProperty("fsockopen_or_curl_enabled")
    public boolean isFsockopenOrCurlEnabled() {
        return fsockopenOrCurlEnabled;
    }

    @JsonProperty("fsockopen_or_curl_enabled")
    public void setFsockopenOrCurlEnabled(boolean fsockopenOrCurlEnabled) {
        this.fsockopenOrCurlEnabled = fsockopenOrCurlEnabled;
    }

    @JsonProperty("soapclient_enabled")
    public boolean isSoapClientEnabled() {
        return soapClientEnabled;
    }

    @JsonProperty("soapclient_enabled")
    public void setSoapClientEnabled(boolean soapClientEnabled) {
        this.soapClientEnabled = soapClientEnabled;
    }

    @JsonProperty("domdocument_enabled")
    public boolean isDomDocumentEnabled() {
        return domDocumentEnabled;
    }

    @JsonProperty("domdocument_enabled")
    public void setDomDocumentEnabled(boolean domDocumentEnabled) {
        this.domDocumentEnabled = domDocumentEnabled;
    }

    @JsonProperty("gzip_enabled")
    public boolean isGzipEnabled() {
        return gzipEnabled;
    }

    @JsonProperty("gzip_enabled")
    public void setGzipEnabled(boolean gzipEnabled) {
        this.gzipEnabled = gzipEnabled;
    }

    @JsonProperty("mbstring_enabled")
    public boolean isMbstringEnabled() {
        return mbstringEnabled;
    }

    @JsonProperty("mbstring_enabled")
    public void setMbstringEnabled(boolean mbstringEnabled) {
        this.mbstringEnabled = mbstringEnabled;
    }

    @JsonProperty("remote_post_successful")
    public boolean isRemotePostSuccessful() {
        return remotePostSuccessful;
    }

    @JsonProperty("remote_post_successful")
    public void setRemotePostSuccessful(boolean remotePostSuccessful) {
        this.remotePostSuccessful = remotePostSuccessful;
    }

    @JsonProperty("remote_post_response")
    public String getRemotePostResponse() {
        return remotePostResponse;
    }

    @JsonProperty("remote_post_response")
    public void setRemotePostResponse(String remotePostResponse) {
        this.remotePostResponse = remotePostResponse;
    }

    @JsonProperty("remote_get_successful")
    public boolean isRemoteGetSuccessful() {
        return remoteGetSuccessful;
    }

    @JsonProperty("remote_get_successful")
    public void setRemoteGetSuccessful(boolean remoteGetSuccessful) {
        this.remoteGetSuccessful = remoteGetSuccessful;
    }

    @JsonProperty("remote_get_response")
    public String getRemoteGetResponse() {
        return remoteGetResponse;
    }

    @JsonProperty("remote_get_response")
    public void setRemoteGetResponse(String remoteGetResponse) {
        this.remoteGetResponse = remoteGetResponse;
    }

    @JsonProperty("store_id")
    public String getStoreId() {
        return storeId;
    }

    @JsonProperty("store_id")
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @JsonProperty("external_object_cache")
    public String getExternalObjectCache() {
        return externalObjectCache;
    }

    @JsonProperty("external_object_cache")
    public void setExternalObjectCache(String externalObjectCache) {
        this.externalObjectCache = externalObjectCache;
    }

    @JsonProperty("mysql_version_string")
    public String getMysqlVersionString() {
        return mysqlVersionString;
    }

    @JsonProperty("mysql_version_string")
    public void setMysqlVersionString(String mysqlVersionString) {
        this.mysqlVersionString = mysqlVersionString;
    }

    @JsonProperty("server_architecture")
    public String getServerArchitecture() {
        return serverArchitecture;
    }

    @JsonProperty("server_architecture")
    public void setServerArchitecture(String serverArchitecture) {
        this.serverArchitecture = serverArchitecture;
    }

}
