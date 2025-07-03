/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import pl.wtx.woocommerce.api.client.model.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.ApiRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.Seek;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.WooCommerce;

import java.util.ArrayList;
import java.util.List;

import static pl.wtx.woocommerce.crudPlusActionBuilder.defines.EndPoints.CUSTOMERS;

public class CustomerRequest extends ApiRequest {

    protected final Customer customer = new Customer();

    private Batch batch;

    private boolean force;
    //private boolean duplicate;
    private boolean isBatch;

    public CustomerRequest(Creator<?> creator){

        customer.setEmail(creator.email);
        customer.setFirstName(creator.firstName);
        customer.setLastName(creator.lastName);
        customer.setUsername(creator.username);
        customer.setPassword(creator.password);
        customer.setBilling(creator.billing);
        customer.setShipping(creator.shipping);

    }

    public CustomerRequest(Reader<?> reader){

        customer.setId(reader.id);

    }

    public CustomerRequest(Updater<?> updater){

        this((Creator)updater);
        customer.setId(updater.id);

    }

    public CustomerRequest(Deleter<?> deleter){

        this((Reader)deleter);
        isBatch = false;
        force = deleter.force;

    }

    public CustomerRequest(Batcher<?> batcher){

        batch = batcher.getBatch();
        force = false;
        isBatch = true;

    }

    public String endPoint(){

        return CUSTOMERS +
            (customer.getId() != null && customer.getId() != 0
                ? ("/" + customer.getId())
                : ""
            ) +
            (isBatch
                ? "/batch"
                : ""
            ) +
            (force
                ? "?force=true"
                : ""
            );

    }

    public String toJson(){

        try {

            if (isBatch){
                return getObjectMapper().writeValueAsString(batch);
            }else{
                // covert Java object to JSON strings
                return getObjectMapper().writeValueAsString(customer);
            }

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

        protected CustomerRequest build(){
            return new CustomerRequest(this);
        }

        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<Customer> getResponse(){

            if (username != null && password != null) {
                WooCommerce woo = new WooCommerce();
                return woo.create(build());
            }else{
                return new Created<Customer>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Username and Password are required.")
                );
            }
        }


    }

    public static class Reader<T extends Reader<?>>{

        protected int id;

        T self() {
            return (T) this;
        }

        public T setId(int id){
            this.id = id;
            return self();
        }

        protected CustomerRequest build(){
            return new CustomerRequest(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Read<Customer> getResponse(){

            if (id > 0) {
                return new WooCommerce().read(build());
            }else {
                return new Read<Customer>(
                    new ApiResponseResult(
                        false,
                        0,
                        "CRUD is limited to a single object result\n" +
                            "Please set requested id\n" +
                            "Use the Searcher with no parameters to get a full list")
                );
            }

        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T>{

        private int id;

        public T setId(int id){
            this.id = id;
            return self();
        }

        @Override
        protected CustomerRequest build(){
            return new CustomerRequest(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public Updated<Customer> getResponse(){
            if (id > 0){
                return new WooCommerce().update(build());
            }else {
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }
        }

    }

    public static class Deleter<T extends Deleter<T>> extends Reader<T>{

        private boolean force;

        public T force(boolean force){
            this.force = force;
            return self();
        }

        @Override
        protected CustomerRequest build(){
            return new CustomerRequest(this);
        }

        /** Returns single Deleted ProductCategory**/
        @Override
        public Deleted<Customer> getResponse(){
            if (id > 0 && force){
                return new WooCommerce().delete(build());
            }else {
                return new Deleted<>(
                    new ApiResponseResult(false, 0,
                        "Invalid Identifier, id and force is required"));
            }
        }

    }

    public static class Batcher<T extends Batcher>{

        private Batch batch;

        public Batcher(){
            batch = new Batch();
        }

        T self() {
            return (T) this;
        }

        public T setBatch(Batch batch){
            this.batch = batch;
            return self();
        }
        public T addCreators(List<Creator> creators){
            //we need to extract the create
            for(Creator create : creators){
                addCreator(create);
            }
            return self();
        }

        public T addCreator(Creator create){
            batch.addCreate(create.build().customer);
            return self();
        }

        public T addUpdaters(List<Updater> updates){
            //we need to extract the update
            for(Updater update : updates){
                addUpdater(update);
            }
            return self();
        }

        public T addUpdater(Updater update){
            batch.addUpdate(update.build().customer);
            return self();
        }

        public T addDeleters(List<Deleter> deletes){
            //we need to extract the delete
            for(Deleter delete : deletes){
                addDeleter(delete);
            }
            return self();
        }
        public T addDeleter(Deleter delete){
            batch.addDelete(delete.build().customer);
            return self();
        }

        private Batch getBatch(){
            return batch;
        }

        private CustomerRequest build(){
            return new CustomerRequest(this);
        }

        /** Returns list of amended ProductCategories **/
        public Batched<Customer> getResponse(){

            if (batch.isEmpty()){

                return new Batched<Customer>(new ApiResponseResult(false, 0, "Nothing to do"));

            }else if (batch.getRecordCount() > 100){

                return new Batched<Customer>(
                    new ApiResponseResult(
                        false,
                        0,
                        "https://woocommerce.github.io/woocommerce-rest-api-docs/#batch-update-product-categories\n" +
                            "This API helps you to batch create, update and delete multiple products.\n\n" +
                            "Note: By default it's limited to up to 100 objects to be created, updated or deleted.")
                );

            }else{


                return new WooCommerce().batch(build());

            }

        }

    }

    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T> {

        public Listed<Customer> getResponse(){

            return new Listed<Customer>(
                new WooCommerce().listAll(
                    CUSTOMERS,
                    build(),
                    new TypeReference<List<Customer>>(){}
                )
            );

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
    public static class Searcher<T extends Searcher<T>> extends Seek.Searcher<T> {

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

        public Searched<Customer> getResponse(){

            return new Searched<Customer>(
                new WooCommerce().search(
                    CUSTOMERS,
                    build(),
                    new TypeReference<List<Customer>>(){}
                )
            );


        }

    }
}
