/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package pl.wtx.woocommerce.crudPlusActionBuilder.demonstration;

import pl.wtx.woocommerce.api.client.model.Billing;
import pl.wtx.woocommerce.api.client.model.Customer;
import pl.wtx.woocommerce.api.client.model.Shipping;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.CustomerRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.*;

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

        Created<Customer> response = new CustomerRequest.Creator<>()
            .setEmail("john.doe@example.com")
            .setFirstName("John")
            .setLastName("Doe")
            .setUsername("john.doe")
            .setPassword("helloworld")
            .setBilling(billing)
            .setShipping(shipping)
            .getResponse();

        if (response.isSuccess()){

            return response.getCreated();


        }else{

            System.out.println(response.getError().getMessage());

        }

        return null;

    }

    public Customer retrieveACustomer(int customerId){

        return new CustomerRequest.Reader<>()
            .setId(customerId)
            .getResponse()
            .getRead();

    }

    public List<Customer> retrieveCustomers(String customerEmail){

        return new CustomerRequest.Searcher<>()
            .setEmail(customerEmail)
            .getResponse()
            .getSearched();

    }

    public List<Customer> listAllCustomers(){

        Listed<Customer> response = new CustomerRequest.ListAll<>()
            .getResponse();

        return response.getListed();

    }

    public Customer updateACustomer(int customerId){

        Updated<Customer> response = new CustomerRequest.Updater<>()
            .setId(customerId)
            .setFirstName("James")
            .setShipping(new Shipping().firstName("James"))
            .getResponse();

        return response.getUpdated();

    }

    public Customer deleteACustomer(int customerId){

        Deleted<Customer> response = new CustomerRequest.Deleter<>()
            .setId(customerId)
            .force(true)
            .getResponse();

        return response.getDeleted();

    }

    public Batched<Customer> batchUpdateCustomers(
        List<CustomerRequest.Creator> createThese,
        List<CustomerRequest.Updater> modifyThese,
        List<CustomerRequest.Deleter> deleteThese
    ){

        return new CustomerRequest.Batcher<>()
            .addCreators(createThese)
            .addUpdaters(modifyThese)
            .addDeleters(deleteThese)
            .getResponse();

    }

    public Batched<Customer> batchUpdateCustomers(){

        return new CustomerRequest.Batcher<>()
            .addCreators(getBatchCreate())
            .getResponse();

    }

    private List<CustomerRequest.Creator> getBatchCreate() {

        List<CustomerRequest.Creator> list = new ArrayList<>();
        list.add(getJohnDoe());
        list.add(getJoaoSilva());
        return list;

    }

    private CustomerRequest.Creator getJohnDoe(){

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

        return new CustomerRequest.Creator<>()
            .setEmail("john.doe@example.com")
            .setFirstName("John")
            .setLastName("Doe")
            .setUsername("john.doe")
            .setBilling(billing)
            .setShipping(shipping);

    }

    private CustomerRequest.Creator getJoaoSilva(){

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

        return new CustomerRequest.Creator<>()
            .setEmail("joao.silva2@example.com")
            .setFirstName("Joao")
            .setLastName("Silva")
            .setUsername("joao.silva")
            .setBilling(billing)
            .setShipping(shipping);

    }

}
