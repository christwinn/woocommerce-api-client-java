/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.response.core;

public class Data {

    private int status;
    private int resource_id;

    public Data(){}

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the resource_id
     */
    public int getResource_id() {
        return resource_id;
    }

    /**
     * @param resource_id the resource_id to set
     */
    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }
}
