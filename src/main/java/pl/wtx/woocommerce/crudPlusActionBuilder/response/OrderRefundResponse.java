/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.crudPlusActionBuilder.response;

import pl.wtx.woocommerce.api.client.model.Order;
import pl.wtx.woocommerce.api.client.model.OrderRefund;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;

public class OrderRefundResponse extends ApiResponse {

    private List<OrderRefund> orderRefunds = new ArrayList<>();
    private OrderRefund orderRefund;

    public OrderRefundResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    if (result.getData() instanceof OrderRefund) {
                        this.orderRefund = (OrderRefund) result.getData();
                    }else{
                        this.orderRefunds = (List<OrderRefund>) result.getData();
                    }
                    break;
                default:
                    setSuccess(false);
                    setError(new ErrorObject("Invalid response code"));
                    break;
            }
        }

    }

    public boolean hasOrderRefunds(){
        return !orderRefunds.isEmpty();
    }

    public boolean hasOrderRefund(){
        return orderRefund != null;
    }

    /*If the Id is NOT set then we get an array of product*/
    public List<OrderRefund> getOrderRefunds(){
        return orderRefunds;
    }

    /*If the Id IS set then we get a singleton product*/
    public OrderRefund getOrderRefund() throws NullPointerException{
        if (orderRefund != null) {
            return orderRefund;
        }else{
            throw new NullPointerException("Object is not initiated");
        }
    }
}
