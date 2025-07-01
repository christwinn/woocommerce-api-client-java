/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.response;

import pl.wtx.woocommerce.api.client.model.ProductCategory;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryResponse extends ApiResponse {

    private ProductCategory category;
    private List<ProductCategory> categories = new ArrayList<>();

    /**
     * Error Reporting
     * @param result
     */
    public ProductCategoryResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    if (result.getData() instanceof ProductCategory) {
                        this.category = (ProductCategory) result.getData();
                    }else{
                        this.categories = (List<ProductCategory>) result.getData();
                    }
                    break;
                default:
                    setSuccess(false);
                    setError(new ErrorObject("Invalid response code"));
                    break;
            }
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

    /*If the Id IS set then we get a singleton product*/
    public ProductCategory getCategory() throws NullPointerException{
        if (category != null) {
            return category;
        }else{
            throw new NullPointerException("Object is not initiated");
        }
    }

    /*muddies the watres
    Alias of getProduct. Create, Update, Delete return a single Product, added for naming convention
    public ProductCategory getCreated(){
        return getCategory();
    }
    Alias of getProduct. Create, Update, Delete return a single Product, added for naming convention
    public ProductCategory getUpdated(){
        return getCreated();
    }
    Alias of getProduct. Create, Update, Delete return a single Product, added for naming convention
    public ProductCategory getDeleted(){
        return getCreated();
    }*/

    /*public String toJson(){

        try {

            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(categories != null ? categories : category);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }*/

}
