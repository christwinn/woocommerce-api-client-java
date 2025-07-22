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
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.builders.core.Seek;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.pl_wtx_woocommerce.model.Billing;
import uk.co.twinn.pl_wtx_woocommerce.model.Customer;
import uk.co.twinn.pl_wtx_woocommerce.model.Shipping;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

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

    private CustomerBuilder(Deleter<?> deleter){

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

    public static class Creator<T extends Creator<T>>{

        private String email;
        private String firstName;
        private String lastName;
        private String username;
        private String password;

        private Billing billing;
        private Shipping shipping;

        public Creator(){

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

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        public T setEmail(String email) {
            this.email = email;
            return self();
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

        public T setPassword(String password) {
            this.password = password;
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
                return new Created<>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<Customer>(){})
                );
            }else{
                return new Created<>(
                    new ApiResponseResult(
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

        public Updater(uk.co.twinn.pl_wtx_woocommerce.model.Customer customer){
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
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<Customer>(){})
                );
            }else {
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReader.ReaderCore<T>{

        public Reader(int id){
            super(id);
        }

        @SuppressWarnings("unchecked")
        public Read<Customer> getResponse(){
            return (Read<Customer>)super.getResponse(CUSTOMERS, new TypeReference<Customer>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleter.DeleterCore<T>{

        public Deleter(int customerId, boolean force){
            super(customerId, force);
        }

        protected CustomerBuilder build(){
            return new CustomerBuilder(this);
        }

        @SuppressWarnings("unchecked")
        public Deleted<Customer> getResponse(){
            return (Deleted<Customer>)super.getResponse(CUSTOMERS, new TypeReference<Customer>() {});

        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<Customer, T>{

        public Batcher(){
            super();
        }

        //T self() {
            //return (T) this;
        //}

        /** for testing only **/
        public T setBatch(Batch<Customer> batch){
            this.batch = batch;
            return self();
        }

        public T addCreators(List<Creator<?>> creators){
            //we need to extract the creator
            for(Creator<?> create : creators){
                addCreator(create);
            }
            return self();
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().customer);
            return self();
        }

        public T addUpdaters(List<Updater<?>> updates){
            //we need to extract the update
            for(Updater<?> update : updates){
                addUpdater(update);
            }
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().customer);
            return self();
        }

        public T addDeleters(List<Deleter<?>> deletes){
            //we need to extract the deleter
            for(Deleter<?> delete : deletes){
                addDeleter(delete);
            }
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().customer);
            return self();
        }

        /** Returns list of amended ProductCategories **/
        @SuppressWarnings("unchecked")
        public Batched<Customer> getResponse(){

            return (Batched<Customer>) super.getResponse(CUSTOMERS, batch, new TypeReference<Batch<Customer>>(){});

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
    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T> {

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

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

            return new Listed<>(
                new Rest().listAll(
                    CUSTOMERS,
                    build(),
                    new TypeReference<List<Customer>>(){}
                )
            );


        }

    }
}
