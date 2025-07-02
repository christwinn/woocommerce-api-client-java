/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.crudPlusActionBuilder.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.wtx.woocommerce.api.client.model.OrderRefund;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderRefundResponse extends ApiResponse {

    private List<OrderRefund> orderRefunds = new ArrayList<>();
    private OrderRefund orderRefund = null;

    public OrderRefundResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    if (result.getData() instanceof OrderRefund) {
                        this.orderRefund = (OrderRefund) result.getData();
                    }else{
                        setOrderRefunds(result);
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
    private void setOrderRefunds(ApiResponseResult result){
        try {
            this.orderRefunds = (List<OrderRefund>) result.getData();
        }catch (Exception e){
            Logger.getLogger(OrderRefundResponse.class.getName())
                .log(Level.SEVERE, "Failed to parse list", e);
            setError(new ErrorObject("Parse list failure"));
        }
    }
    public boolean hasOrderRefunds(){
        return !orderRefunds.isEmpty();
    }

    public boolean hasOrderRefund(){
        return orderRefund != null;
    }

    /*If the id is NOT set then we get an array of product*/
    public List<OrderRefund> getOrderRefunds(){
        return orderRefunds;
    }

    /*If the id IS set then we get a singleton product*/
    public OrderRefund getOrderRefund(){

        return orderRefund;

    }

    public String toJson(){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(this);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
