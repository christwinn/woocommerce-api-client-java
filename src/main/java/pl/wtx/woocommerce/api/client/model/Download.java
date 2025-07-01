/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * This class is read-only
 */
public class Download {

    private String downloadId; //	string	Download ID (MD5).read-only
    private String downloadUrl; //	string	Download file URL.read-only
    private int productId; //	integer	Downloadable product ID.read-only
    private String productName; //	string	Product name.read-only
    private String downloadName; //	string	Downloadable file name.read-only
    private int orderId; //	integer	Order ID.read-only
    private String orderKey; //	string	Order key.read-only
    private String downloadsRemaining; //	string	Number of downloads remaining.read-only
    private LocalDateTime accessExpires; //	string	The date when download access expires, in the site's timezone.read-only
    private LocalDateTime accessExpiresGMT; //	string	The date when download access expires, as GMT.read-only
    private DownloadFile file; //	object	File details. read-onlySee Customers downloads - File properties

    public Download(){}

    public String getDownloadId() {
        return downloadId;
    }

    @JsonProperty("download_id")
    public void setDownloadId(String downloadId) {
        this.downloadId = downloadId;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    @JsonProperty("download_url")
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getProductId() {
        return productId;
    }

    @JsonProperty("product_id")
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    @JsonProperty("product_name")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDownloadName() {
        return downloadName;
    }

    @JsonProperty("download_name")
    public void setDownloadName(String downloadName) {
        this.downloadName = downloadName;
    }

    public int getOrderId() {
        return orderId;
    }

    @JsonProperty("order_id")
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderKey() {
        return orderKey;
    }

    @JsonProperty("order_key")
    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getDownloadsRemaining() {
        return downloadsRemaining;
    }

    @JsonProperty("downloads_remaining")
    public void setDownloadsRemaining(String downloadsRemaining) {
        this.downloadsRemaining = downloadsRemaining;
    }

    public LocalDateTime getAccessExpires() {
        return accessExpires;
    }

    @JsonProperty("access_expires")
    public void setAccessExpires(LocalDateTime accessExpires) {
        this.accessExpires = accessExpires;
    }

    public LocalDateTime getAccessExpiresGMT() {
        return accessExpiresGMT;
    }

    @JsonProperty("access_expires_gmt")
    public void setAccessExpiresGMT(LocalDateTime accessExpiresGMT) {
        this.accessExpiresGMT = accessExpiresGMT;
    }

    public DownloadFile getFile() {
        return file;
    }

    public void setFile(DownloadFile file) {
        this.file = file;
    }

}
