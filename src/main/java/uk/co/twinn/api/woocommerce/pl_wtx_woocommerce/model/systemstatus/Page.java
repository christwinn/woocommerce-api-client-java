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

@JsonPropertyOrder(
    {   "page_name",
        "page_id",
        "page_set",
        "page_exists",
        "page_visible",
        "shortcode",
        "block",
        "shortcode_required",
        "shortcode_present",
        "block_present",
        "block_required"
    }
)
public class Page {

    private String pageName;
    private int pageId;
    private boolean pageSet;
    private boolean pageExists;
    private boolean pageVisible;
    private String shortcode;
    private String block;
    private boolean shortcodeRequired;
    private boolean shortcodePresent;

    private boolean blockPresent;
    private boolean blockRequired;

    public Page(){}

    @JsonProperty("page_name")
    public String getPageName() {
        return pageName;
    }

    @JsonProperty("page_name")
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @JsonProperty("page_id")
    public int getPageId() {
        return pageId;
    }

    @JsonProperty("page_id")
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @JsonProperty("page_set")
    public boolean isPageSet() {
        return pageSet;
    }

    @JsonProperty("page_set")
    public void setPageSet(boolean pageSet) {
        this.pageSet = pageSet;
    }

    @JsonProperty("page_exists")
    public boolean isPageExists() {
        return pageExists;
    }

    @JsonProperty("page_exists")
    public void setPageExists(boolean pageExists) {
        this.pageExists = pageExists;
    }

    @JsonProperty("page_visible")
    public boolean isPageVisible() {
        return pageVisible;
    }

    @JsonProperty("page_visible")
    public void setPageVisible(boolean pageVisible) {
        this.pageVisible = pageVisible;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    @JsonProperty("shortcode_required")
    public boolean isShortcodeRequired() {
        return shortcodeRequired;
    }

    @JsonProperty("shortcode_required")
    public void setShortcodeRequired(boolean shortcodeRequired) {
        this.shortcodeRequired = shortcodeRequired;
    }

    @JsonProperty("shortcode_present")
    public boolean isShortcodePresent() {
        return shortcodePresent;
    }

    @JsonProperty("shortcode_present")
    public void setShortcodePresent(boolean shortcodePresent) {
        this.shortcodePresent = shortcodePresent;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    @JsonProperty("block_present")
    public boolean isBlockPresent() {
        return blockPresent;
    }

    @JsonProperty("block_present")
    public void setBlockPresent(boolean blockPresent) {
        this.blockPresent = blockPresent;
    }

    @JsonProperty("block_required")
    public boolean isBlockRequired() {
        return blockRequired;
    }

    @JsonProperty("block_required")
    public void setBlockRequired(boolean blockRequired) {
        this.blockRequired = blockRequired;
    }

}
