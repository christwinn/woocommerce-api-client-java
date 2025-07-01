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
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ErrorObject;

import java.util.ArrayList;
import java.util.List;

public class CustomerResponse extends ApiResponse {

    private List<Customer> customers = new ArrayList<>();
    private Customer customer;

    public CustomerResponse(ApiResponseResult result){

        super(result);

        if (result.getSuccess()){
            switch (result.getStatusCode()){
                case 200: case 201:
                    setSuccess(true);
                    if (result.getData() instanceof Customer) {
                        this.customer = (Customer) result.getData();
                    }else{
                        this.customers = (List<Customer>) result.getData();
                    }
                    break;
                default:
                    setSuccess(false);
                    setError(new ErrorObject("Invalid response code"));
                    break;
            }
        }

    }

    public boolean hasCustomers(){
        return !customers.isEmpty();
    }

    public boolean hasCustomer(){
        return customer != null;
    }

    /*If the Id is NOT set then we get an array of product*/
    public List<Customer> getCustomers(){
        return customers;
    }

    /*If the Id IS set then we get a singleton product*/
    public Customer getCustomer() throws NullPointerException{
        if (customer != null) {
            return customer;
        }else{
            throw new NullPointerException("Object is not initiated");
        }
    }

}
