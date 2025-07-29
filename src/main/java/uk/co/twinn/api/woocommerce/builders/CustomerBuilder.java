/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.builders.core.Batch;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.billing.Billing;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.customer.Customer;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.shipping.Shipping;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;


import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.CUSTOMERS;

public class CustomerBuilder extends ApiRequest {

    protected final Customer customer = new Customer();

    public CustomerBuilder(){

    }

    private CustomerBuilder(Creator<?> creator){

        customer.setEmail(creator.email);
        customer.setFirstName(creator.firstName);
        customer.setLastName(creator.lastName);
        customer.setUsername(creator.username);
        customer.setPassword(creator.password);
        customer.setBilling(creator.billing);
        customer.setShipping(creator.shipping);

    }

    private CustomerBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        this.customer.setId(updater.id);

    }

    private CustomerBuilder(Deleter deleter){

        customer.setId(deleter.id);

    }

    public String endPoint(){

        return CUSTOMERS +
            (customer.getId() != null && customer.getId() != 0
                ? ("/" + customer.getId())
                : ""
            );

    }

    public String toJson(){

        try {
                // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(customer);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>> extends CoreCreator<Customer, T>{

        private String email;
        private String firstName;
        private String lastName;
        private String username;
        private String password;

        private Billing billing;
        private Shipping shipping;

        private Creator(){}

        public Creator(String email, String password){

            this.email = email;
            this.password = password;

        }

        /** !Traditionally set a customer, passes through here only setting that, that we can */
        public Creator(Customer customer){
            email = customer.getEmail();
            firstName = customer.getFirstName();
            lastName = customer.getLastName();
            username = customer.getUsername();
            password = customer.getPassword();
            billing = customer.getBilling();
            shipping = customer.getShipping();
        }

        public T setFirstName(String firstName) {
            this.firstName = firstName;
            return self();
        }

        public T setLastName(String lastName) {
            this.lastName = lastName;
            return self();
        }

        public T setUsername(String username) {
            this.username = username;
            return self();
        }

        public T setBilling(Billing billing) {
            this.billing = billing;
            return self();
        }

        public T setShipping(Shipping shipping) {
            this.shipping = shipping;
            return self();
        }

        protected CustomerBuilder build(){
            return new CustomerBuilder(this);
        }

        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<Customer> getResponse(){

            if (username != null && password != null) {
                CustomerBuilder create = build();
                //make the call
                /*return new Created<>(
                    new Rest<Customer>().create(create.endPoint(), create.toJson())
                );*/
                return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<Customer>() {});

            }else{
                return new Created<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Username and Password are required.")
                );
            }
        }


    }

    public static class Updater<T extends Updater<T>> extends Creator<T>{

        private final int id;

        public Updater(int productId){
            super();
            this.id = productId;
        }

        public Updater(Customer customer){
            super(customer);
            if (customer.getId() != null) {
                this.id = customer.getId();
            }else{
                this.id = 0;
            }
        }

        @Override
        protected CustomerBuilder build(){
            return new CustomerBuilder(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public Updated<Customer> getResponse(){
            if (id > 0){

                CustomerBuilder create = build();
                //make the call
                /*return new Updated<>(
                    new Rest<Customer>().update(create.endPoint(), create.toJson())
                );*/
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<Customer>() {});

            }else {
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<Customer>{

        public Reader(int id){
            super(id);
        }

        public Read<Customer> getResponse(){
            return super.getResponse(CUSTOMERS, new TypeReference<Customer>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<Customer>{

        public Deleter(int customerId, boolean force){
            super(customerId, force);
        }

        protected CustomerBuilder build(){
            return new CustomerBuilder(this);
        }

        public Deleted<Customer> getResponse(){
            return super.getResponse(CUSTOMERS, new TypeReference<Customer>() {});
        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<Customer, T>{

        public Batcher(){
            super();
        }

        /** for testing only **/
        public T setBatch(Batch<Customer> batch){
            this.batch = batch;
            return self();
        }

        public T addCreator(Creator<?> create){
            return addCreator(create.build().customer);
        }

        public T addCreator(List<Customer> create){
            return super.addCreate(create);
        }

        public T addCreator(Customer create){
            return super.addCreate(create);
        }

        public T addUpdater(Updater<?> update){
            return addUpdater(update.build().customer);
        }

        public T addUpdater(List<Customer> updateList){
            return super.addUpdate(updateList);
        }

        public T addUpdater(Customer update){
            return super.addUpdate(update);
        }

        public T addDeleter(Deleter delete){
            return addDeleter(delete.build().customer);
        }

        public T addDeleter(List<Customer> deleteList){
            for (Customer delete : deleteList) {
                addDeleter(delete);
            }
            return self();
        }

        public T addDeleter(Customer delete){
            return super.addDelete(delete.getId());
        }

        /** Returns list of amended ProductCategories **/
        public Batched<Customer> getResponse(){

            for (int i = 0; i < batch.getCreate().size(); i++) {
                if (batch.getCreate().get(i).getEmail() == null ||
                    batch.getCreate().get(i).getEmail().isEmpty()) {
                    return super.getFailure(
                        String.format("Email is MANDATORY!, Found Create @ %s with empty name", i)
                    );
                }else if (batch.getCreate().get(i).getPassword() == null ||
                    batch.getCreate().get(i).getPassword().isEmpty()) {
                    return super.getFailure(
                        String.format("Password is MANDATORY!, Found Create @ %s with empty name", i)
                    );
                }
                //stripping the id if is set
                if (batch.getCreate().get(i).getId() != null) {
                    batch.getCreate().get(i).setId(null);
                }
            }

            //pre-validate
            for (int i = 0; i < batch.getUpdate().size(); i++){
                if (batch.getUpdate().get(i).getId() == null || batch.getUpdate().get(i).getId() == 0){
                    return super.getFailure(
                        String.format("Id is MANDATORY!, Found Update @ %s with id = 0", i)
                    );
                }
            }

            return super.getResponse(CUSTOMERS, batch, new TypeReference<BatchResult<Customer>>() {});

        }

    }


    /**
     *
     * Searches the Customers
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-customers">
     * https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-customers</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ListAll<T>> extends CoreSeek.Searcher<Customer, T> {

        /**
         *
         * @param offset Offset the result set by a specific number of items.
         * @return T
         */
        public T setOffset(int offset) {
            addNameValuePair("offset", offset);
            return self();
        }

        /**
         *
         * @param email Limit result set to resources with a specific email.
         * @return T
         */
        public T setEmail(String email) {
            addNameValuePair("email", email);
            return self();
        }

        /**
         *
         * @param role Limit result set to resources with a specific role.
         *             Options: all, administrator, editor, author, contributor,
         *                  subscriber, customer and shop_manager.
         *             Default is customer.
         * @return T
         */
        public T setRole(String role) {
            addNameValuePair("role", role);
            return self();
        }

        public Listed<Customer> getResponse(){

            return super.getResponse(CUSTOMERS, build(), new TypeReference<List<Customer>>() {});

        }

    }
}
