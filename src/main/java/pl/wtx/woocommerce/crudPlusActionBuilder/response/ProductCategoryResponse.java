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
import pl.wtx.woocommerce.api.client.model.ProductCategory;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductCategoryResponse extends ApiResponse {

    private ProductCategory category;
    private List<ProductCategory> categories = new ArrayList<>();

    public ProductCategoryResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    if (result.getData() instanceof ProductCategory) {
                        this.category = (ProductCategory) result.getData();
                    }else{
                        setCategories(result);
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
    private void setCategories(ApiResponseResult result){
        try {
            this.categories = (List<ProductCategory>) result.getData();
        }catch (Exception e){
            Logger.getLogger(ProductCategoryResponse.class.getName())
                .log(Level.SEVERE, "Failed to parse list", e);
            setError(new ErrorObject("Parse list failure"));
        }
    }

    public boolean hasCategories(){
        /*return categories != null;never null as we initialised*/
        return !categories.isEmpty();
    }

    public boolean hasCategory(){
        return category != null;
    }

    public List<ProductCategory> getCategories(){
        return categories;
    }

    /*If the id IS set then we get a singleton product*/
    public ProductCategory getCategory() throws NullPointerException{
        if (category != null) {
            return category;
        }else{
            throw new NullPointerException("Object is not initiated");
        }
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
