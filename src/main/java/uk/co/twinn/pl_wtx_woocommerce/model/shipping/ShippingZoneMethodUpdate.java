/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.pl_wtx_woocommerce.model.shipping;

import java.util.HashMap;
import java.util.LinkedHashMap;

/*
* So to update a ShippingZoneMethod we need to send in a plain <name, value> list under settings
* WooCommerce does accept the output format as input
*
* Using ShippingZoneMethod
* {"enabled":false,"settings":{"cost":{value:"10"}}}
* We need to use this, which we accomplish in here
* {"enabled":false,"settings":{"cost":"10"}}
*/
public class ShippingZoneMethodUpdate {

    private Integer order; //	integer	Shipping method sort order.

    private Boolean enabled; //	boolean	Shipping method enabled status.

    private HashMap<String, String> settings = new LinkedHashMap<>();

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public HashMap<String, String> getSettings() {
        return settings;
    }

    public void addSettings(String name, String value) {
        this.settings.put(name, value);
    }

}
