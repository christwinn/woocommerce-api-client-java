/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.demonstration;

import uk.co.twinn.api.woocommerce.api.Customers;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.billing.Billing;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.customer.Customer;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.shipping.Shipping;
import uk.co.twinn.api.woocommerce.builders.CustomerBuilder;
import uk.co.twinn.api.woocommerce.response.*;

import java.util.ArrayList;
import java.util.List;

/*moved out of top tier!*/
public class CustomerDemo {

    public CustomerDemo(){}

    public Customer createACustomer(){

        Billing billing = new Billing()
            .firstName("John")
            .lastName("Doe")
            .company("")
            .address1("969 Market Street")
            .address2("")
            .city("San Fancisco")
            .state("CA")
            .postcode("94103")
            .email("john.doe@example.com")
            .phone("(555) 555-5555");

        Shipping shipping = new Shipping()
            .firstName("John")
            .lastName("Doe")
            .company("")
            .address1("969 Market")
            .address2("")
            .city("San Francisco")
            .state("CA")
            .postcode("94103")
            .country("US");

        Created<Customer> response = Customers.create()
            .setEmail("john.doe@example.com")
            .setFirstName("John")
            .setLastName("Doe")
            .setUsername("john.doe")
            .setPassword("helloworld")
            .setBilling(billing)
            .setShipping(shipping)
            .getResponse();

        if (response.isSuccess()){

            return response.getResult();


        }else{

            System.out.println(response.getError().getMessage());

        }

        return null;

    }

    public Customer retrieveACustomer(int customerId){

        return Customers.read(customerId)
            .getResponse()
            .getResult();

    }

    public List<Customer> retrieveCustomers(String customerEmail){

        return Customers.listing()
            .setEmail(customerEmail)
            .getResponse()
            .getResult();

    }

    public List<Customer> listAllCustomers(){

        Listed<Customer> response = Customers.listing()
            .getResponse();

        return response.getResult();

    }

    public Customer updateACustomer(int customerId){

        Updated<Customer> response = Customers.update(customerId)
            .setFirstName("James")
            .setShipping(new Shipping().firstName("James"))
            .getResponse();

        return response.getResult();

    }

    public Customer deleteACustomer(int customerId){

        Deleted<Customer> response = Customers.delete(customerId,true)
            .getResponse();

        Deleted<Customer> responseB = Customers.delete(customerId,true)
            .getResponse();

        return responseB.getResult();

    }

    public Batched<Customer> batchUpdateCustomers(
        List<CustomerBuilder.Creator<?>> createThese,
        List<CustomerBuilder.Updater<?>> modifyThese,
        List<CustomerBuilder.Deleter> deleteThese
    ){

        return Customers.batch()
            .addCreators(createThese)
            .addUpdaters(modifyThese)
            .addDeleters(deleteThese)
            .getResponse();

    }

    public Batched<Customer> batchUpdateCustomers(){

        return Customers.batch()
            .addCreators(getBatchCreate())
            .addCreators(getBatchCreate())
            .getResponse();

    }

    private List<CustomerBuilder.Creator<?>> getBatchCreate() {

        List<CustomerBuilder.Creator<?>> list = new ArrayList<>();
        list.add(getJohnDoe());
        list.add(getJoaoSilva());
        return list;

    }

    private CustomerBuilder.Creator<?> getJohnDoe(){

        Billing billing = new Billing()
            .firstName("John")
            .lastName("Doe")
            .company("")
            .address1("969 Market Street")
            .address2("")
            .city("San Fancisco")
            .state("CA")
            .postcode("94103")
            .email("john.doe@example.com")
            .phone("(555) 555-5555");

        Shipping shipping = new Shipping()
            .firstName("John")
            .lastName("Doe")
            .company("")
            .address1("969 Market")
            .address2("")
            .city("San Francisco")
            .state("CA")
            .postcode("94103")
            .country("US");

        return Customers.create()
            .setEmail("john.doe@example.com")
            .setFirstName("John")
            .setLastName("Doe")
            .setUsername("john.doe")
            .setBilling(billing)
            .setShipping(shipping);

    }

    private CustomerBuilder.Creator<?> getJoaoSilva(){

        Billing billing = new Billing()
            .firstName("Joao")
            .lastName("Silva")
            .company("")
            .address1("Av. Brasil, 43")
            .address2("")
            .city("Rio de Janeiro")
            .state("RJ")
            .postcode("12345-000")
            .country("BR")
            .email("joao.silva2@example.com")
            .phone("(555) 555-5555");

        Shipping shipping = new Shipping()
            .firstName("Joao")
            .lastName("Silva")
            .company("")
            .address1("Av. Brasil, 43")
            .address2("")
            .city("Rio de Janeiro")
            .state("RJ")
            .postcode("12345-000")
            .country("BR");

        return Customers.create()
            .setEmail("joao.silva2@example.com")
            .setFirstName("Joao")
            .setLastName("Silva")
            .setUsername("joao.silva")
            .setBilling(billing)
            .setShipping(shipping);

    }

}
