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

public class Logging {

    private boolean loggingEnabled;
    private String defaultHandler;
    private int retentionPeriodDays;
    private String levelThreshold;
    private String logDirectorySize;

    public Logging(){}

    @JsonProperty("logging_enabled")
    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    @JsonProperty("logging_enabled")
    public void setLoggingEnabled(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }

    @JsonProperty("default_handler")
    public String getDefaultHandler() {
        return defaultHandler;
    }

    @JsonProperty("default_handler")
    public void setDefaultHandler(String defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @JsonProperty("retention_period_days")
    public int getRetentionPeriodDays() {
        return retentionPeriodDays;
    }

    @JsonProperty("retention_period_days")
    public void setRetentionPeriodDays(int retentionPeriodDays) {
        this.retentionPeriodDays = retentionPeriodDays;
    }

    @JsonProperty("level_threshold")
    public String getLevelThreshold() {
        return levelThreshold;
    }

    @JsonProperty("level_threshold")
    public void setLevelThreshold(String levelThreshold) {
        this.levelThreshold = levelThreshold;
    }

    @JsonProperty("log_directory_size")
    public String getLogDirectorySize() {
        return logDirectorySize;
    }

    @JsonProperty("log_directory_size")
    public void setLogDirectorySize(String logDirectorySize) {
        this.logDirectorySize = logDirectorySize;
    }

}
