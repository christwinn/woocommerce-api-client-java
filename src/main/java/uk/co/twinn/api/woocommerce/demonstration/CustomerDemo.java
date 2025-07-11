/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.demonstration;

import uk.co.twinn.pl_wtx_woocommerce.model.Billing;
import uk.co.twinn.pl_wtx_woocommerce.model.Customer;
import uk.co.twinn.pl_wtx_woocommerce.model.Shipping;
import uk.co.twinn.api.woocommerce.request.CustomerApi;
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

        Created<Customer> response = new CustomerApi.Creator<>()
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

        /*Read<Customer> customer = new CustomerTestRequest.Reader<>()
            .setId(customerId)
            .getResponse() //returns object not Customer using full paths cast issues
            .getResult();*/

        return new CustomerApi.Reader<>()
            .setId(customerId)
            .getResponse()
            .getResult();

    }

    public List<Customer> retrieveCustomers(String customerEmail){

        return new CustomerApi.ListAll<>()
            .setEmail(customerEmail)
            .getResponse()
            .getResult();

    }

    public List<Customer> listAllCustomers(){

        Listed<Customer> response = new CustomerApi.ListAll<>()
            .getResponse();

        return response.getResult();

    }

    public Customer updateACustomer(int customerId){

        Updated<Customer> response = new CustomerApi.Updater<>()
            .setId(customerId)
            .setFirstName("James")
            .setShipping(new Shipping().firstName("James"))
            .getResponse();

        return response.getResult();

    }

    public Customer deleteACustomer(int customerId){

        Deleted<Customer> response = new CustomerApi.Deleter<>()
            .setId(customerId)
            .setForce(true)
            .getResponse();

        return response.getResult();

    }

    public Batched<Customer> batchUpdateCustomers(
        List<CustomerApi.Creator<?>> createThese,
        List<CustomerApi.Updater<?>> modifyThese,
        List<CustomerApi.Deleter<?>> deleteThese
    ){

        return new CustomerApi.Batcher<>()
            .addCreators(createThese)
            .addUpdaters(modifyThese)
            .addDeleters(deleteThese)
            .getResponse();

    }

    public Batched<Customer> batchUpdateCustomers(){

        return new CustomerApi.Batcher<>()
            .addCreators(getBatchCreate())
            .getResponse();

    }

    private List<CustomerApi.Creator<?>> getBatchCreate() {

        List<CustomerApi.Creator<?>> list = new ArrayList<>();
        list.add(getJohnDoe());
        list.add(getJoaoSilva());
        return list;

    }

    private CustomerApi.Creator<?> getJohnDoe(){

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

        return new CustomerApi.Creator<>()
            .setEmail("john.doe@example.com")
            .setFirstName("John")
            .setLastName("Doe")
            .setUsername("john.doe")
            .setBilling(billing)
            .setShipping(shipping);

    }

    private CustomerApi.Creator<?> getJoaoSilva(){

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

        return new CustomerApi.Creator<>()
            .setEmail("joao.silva2@example.com")
            .setFirstName("Joao")
            .setLastName("Silva")
            .setUsername("joao.silva")
            .setBilling(billing)
            .setShipping(shipping);

    }

}
