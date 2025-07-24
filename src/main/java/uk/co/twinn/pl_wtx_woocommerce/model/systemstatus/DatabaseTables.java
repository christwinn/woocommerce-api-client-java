/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model.systemstatus;

import java.util.HashMap;

public class DatabaseTables {


    private HashMap<String, DatabaseTable> woocommerce;

    private HashMap<String, DatabaseTable> other;

    public DatabaseTables(){}


    public HashMap<String, DatabaseTable> getWoocommerce() {
        return woocommerce;
    }

    public void setWoocommerce(HashMap<String, DatabaseTable> woocommerce) {
        this.woocommerce = woocommerce;
    }

    public HashMap<String, DatabaseTable> getOther() {
        return other;
    }

    public void setOther(HashMap<String, DatabaseTable> other) {
        this.other = other;
    }
}
