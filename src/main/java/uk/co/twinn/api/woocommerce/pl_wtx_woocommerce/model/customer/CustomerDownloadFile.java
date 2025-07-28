/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.customer;

import uk.co.twinn.api.woocommerce.core.JacksonObjectMapper;

public class CustomerDownloadFile {

    private String name;
    private String file;

    public CustomerDownloadFile(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String toJson() {
        return new JacksonObjectMapper().toJson(this);
    }
}
