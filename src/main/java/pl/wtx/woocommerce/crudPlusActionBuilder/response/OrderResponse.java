/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.response;

import pl.wtx.woocommerce.api.client.model.Customer;
import pl.wtx.woocommerce.api.client.model.Order;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse extends ApiResponse {

    private List<Order> orders = new ArrayList<>();
    private Order order;

    public OrderResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    if (result.getData() instanceof Order) {
                        this.order = (Order) result.getData();
                    }else{
                        setOrders(result);
                    }
                    break;
                default:
                    setSuccess(false);
                    setError(new ErrorObject("Invalid response code"));
                    break;
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void setOrders(ApiResponseResult result){
        this.orders = (List<Order>) result.getData();
    }

    public boolean hasOrders(){
        return !orders.isEmpty();
    }

    public boolean hasOrder(){
        return order != null;
    }

    /*If the Id is NOT set then we get an array of product*/
    public List<Order> getOrders(){
        return orders;
    }

    /*If the Id IS set then we get a singleton product*/
    public Order getOrder() throws NullPointerException{
        if (order != null) {
            return order;
        }else{
            throw new NullPointerException("Object is not initiated");
        }
    }

}
