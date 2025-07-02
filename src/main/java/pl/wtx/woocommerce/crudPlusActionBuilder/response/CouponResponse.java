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
import pl.wtx.woocommerce.api.client.model.Coupon;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CouponResponse extends ApiResponse {

    private List<Coupon> coupons = new ArrayList<>();
    private Coupon coupon = null;

    public CouponResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    if (result.getData() instanceof Coupon) {
                        this.coupon = (Coupon) result.getData();
                    }else{
                        setCoupons(result);
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
    private void setCoupons(ApiResponseResult result){
        try {
            this.coupons = (List<Coupon>) result.getData();
        }catch (Exception e){
            Logger.getLogger(CouponResponse.class.getName())
                .log(Level.SEVERE, "Failed to parse list", e);
            setError(new ErrorObject("Parse list failure"));
        }
    }

    public boolean hasCoupons(){
        return !coupons.isEmpty();
    }

    public boolean hasCoupon(){
        return coupon != null;
    }

    /*If the id is NOT set then we get an array of product*/
    public List<Coupon> getCoupons(){
        return coupons;
    }

    /*If the id IS set then we get a singleton product*/
    public Coupon getCoupon(){

        return coupon;

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
