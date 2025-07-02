/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.response;

import pl.wtx.woocommerce.api.client.model.OrderNote;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderNoteResponse extends ApiResponse {

    private List<OrderNote> orderNotes = new ArrayList<>();
    private OrderNote orderNote = null;

    public OrderNoteResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    if (result.getData() instanceof OrderNote) {
                        this.orderNote = (OrderNote) result.getData();
                    }else{
                        setOrderNotes(result);
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
    private void setOrderNotes(ApiResponseResult result){
        try {
            this.orderNotes = (List<OrderNote>) result.getData();
        }catch (Exception e){
            Logger.getLogger(OrderNoteResponse.class.getName())
                .log(Level.SEVERE, "Failed to parse list", e);
            setError(new ErrorObject("Parse list failure"));
        }
    }

    public boolean hasOrderNotes(){
        return !orderNotes.isEmpty();
    }

    public boolean hasOrderNote(){
        return orderNote != null;
    }


    public List<OrderNote> getOrderNotes(){
        return orderNotes;
    }

    /*If the id IS set then we get a singleton product*/
    public OrderNote getOrderNote() throws NullPointerException{
        if (orderNote != null) {
            return orderNote;
        }else{
            throw new NullPointerException("Object is not initiated");
        }
    }

}
