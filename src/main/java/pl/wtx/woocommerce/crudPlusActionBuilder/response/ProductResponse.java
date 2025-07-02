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
import pl.wtx.woocommerce.api.client.model.Product;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductResponse extends ApiResponse {

    private List<Product> products = new ArrayList<>();
    private Product product;

    public ProductResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    if (result.getData() instanceof Product) {
                        this.product = (Product) result.getData();
                    }else{
                        setProducts(result);
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
    private void setProducts(ApiResponseResult result){
        try {
            this.products = (List<Product>) result.getData();
        }catch (Exception e){
            Logger.getLogger(ProductResponse.class.getName())
                .log(Level.SEVERE, "Failed to parse list", e);
            setError(new ErrorObject("Parse list failure"));
        }
    }

    public boolean hasProducts(){
        return !products.isEmpty();
    }

    public boolean hasProduct(){
        return product != null;
    }

    /*If the Id is NOT set then we get an array of product*/
    public List<Product> getProducts(){
        return products;
    }

    /*If the Id IS set then we get a singleton product*/
    public Product getProduct() throws NullPointerException{
        if (product != null) {
            return product;
        }else{
            throw new NullPointerException("Object is not initiated");
        }
    }

    /* muddies the waters
    public Product getCreated(){
        return getProduct();
    }
    public Product getUpdated(){
        return getCreated();
    }
    public Product getDeleted(){
        return getCreated();
    }*/

    public String toJson(){

        try {
            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(this);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
