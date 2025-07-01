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
import pl.wtx.woocommerce.crudPlusActionBuilder.response.CustomerResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.OrderActionsResponse;
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

    public CustomerRequest(Creator creator){

        customer.setEmail(creator.email);
        customer.setFirstName(creator.firstName);
        customer.setLastName(creator.lastName);
        customer.setUsername(creator.username);
        customer.setPassword(creator.password);
        customer.setBilling(creator.billing);
        customer.setShipping(creator.shipping);

    }

    public CustomerRequest(Reader reader){

        customer.setId(reader.id);

    }

    public CustomerRequest(Updater updater){

        this((Creator)updater);
        customer.setId(updater.id);

    }

    public CustomerRequest(Deleter deleter){

        this((Reader)deleter);
        isBatch = false;
        force = deleter.force;

    }

    public CustomerRequest(Batcher batcher){

        batch = batcher.getBatch();
        force = false;
        isBatch = true;

    }

    public String endPoint(){

        return getEndPoint() +
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

    private static String getEndPoint(){

        return CUSTOMERS;

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
        public CustomerResponse getResponse(){

            if (username != null && password != null) {
                WooCommerce woo = new WooCommerce();
                return woo.create(build());
            }else{
                return new CustomerResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "Username and Password are required.")
                );
            }
        }


    }

    public static class Reader<T extends Reader<T>>{

        private int id;

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
        public CustomerResponse getResponse(){
            if (id == 0) {
                return new CustomerResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "CRUD is limited to a single object result\n" +
                            "Please set requested id\n" +
                            "Use the Searcher with no parameters to get a full list")
                );
            }else {
                WooCommerce woo = new WooCommerce();
                return woo.read(build());
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
        public CustomerResponse getResponse(){
            WooCommerce woo = new WooCommerce();
            return woo.update(build());
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
        public CustomerResponse getResponse(){
            WooCommerce woo = new WooCommerce();
            return woo.delete(build());
        }

    }

    private static class Batch{

        private final List<Customer> create;
        private final List<Customer> update;
        private final List<Customer> delete;

        public Batch(){

            create = new ArrayList<>();
            update = new ArrayList<>();
            delete = new ArrayList<>();

        }

        public int getRecordCount(){
            return create.size() + update.size() + delete.size();
        }

        public boolean isEmpty(){
            return create.isEmpty() && update.isEmpty() && delete.isEmpty();
        }

        public void addCreator(Creator create){
            this.create.add(create.build().customer);
        }

        public void addUpdater(Updater update){
            this.update.add(update.build().customer);
        }

        public void addDeleter(Deleter delete){
            this.delete.add(delete.build().customer);
        }

        public void addCreator(List<Creator> create) {
            for (Creator creator : create){
                this.create.add(creator.build().customer);
            }
        }

        public void addUpdater(List<Updater> updates) {
            for (Updater updater : updates){
                this.update.add(updater.build().customer);
            }
        }

        public void addDeleter(List <Deleter> deletes){
            for (Deleter deleter : deletes){
                this.delete.add(deleter.build().customer);
            }
        }

        public List<Customer> getCreate(){
            return create;
        }
        public List<Customer> getUpdate(){
            return update;
        }
        public List<Customer> getDelete(){
            return delete;
        }

    }

    public static class Batcher<T extends Batcher>{

        private static Batch batch;

        public Batcher(){
            batch = new Batch();
        }

        T self() {
            return (T) this;
        }

        public T addCreators(List<Creator> creators){
            batch.addCreator(creators);
            return self();
        }

        public T addCreator(Creator create){
            batch.addCreator(create);
            return self();
        }

        public T addUpdaters(List<Updater> updates){
            batch.addUpdater(updates);
            return self();
        }

        public T addUpdater(Updater update){
            batch.addUpdater(update);
            return self();
        }

        public T addDeleters(List<Deleter> deletes){
            batch.addDeleter(deletes);
            return self();
        }
        public T addDeleter(Deleter delete){
            batch.addDeleter(delete);
            return self();
        }

        private Batch getBatch(){
            return batch;
        }

        private CustomerRequest build(){
            return new CustomerRequest(this);
        }

        public CustomerResponse getResponse(){

            if (batch.isEmpty()){

                return new CustomerResponse(new ApiResponseResult(false, 0, "Nothing to do"));

            }else if (batch.getRecordCount() > 100){

                return new CustomerResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "https://woocommerce.github.io/woocommerce-rest-api-docs/#batch-update-product-categories\n" +
                            "This API helps you to batch create, update and delete multiple products.\n\n" +
                            "Note: By default it's limited to up to 100 objects to be created, updated or deleted.")
                );

            }else{

                /** Returns list of amended ProductCategories **/
                return new WooCommerce().create(build());

            }

        }

    }

    /**
     *
     * Searches the Customers
     * https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products
     *
     * @param <T>
     */
    public static class Searcher<T extends Searcher> extends Seek.Searcher<T> {

        T self() {
            return (T) this;
        }

        /**
         * Offset the result set by a specific number of items.
         * @param offset
         * @return
         */
        public T setOffset(int offset) {
            addNameValuePair("offset", offset);
            return self();
        }

        /**
         * Limit result set to resources with a specific email.
         * @param email
         * @return
         */
        public T setEmail(String email) {
            addNameValuePair("email", email);
            return self();
        }

        /**
         * Limit result set to resources with a specific role. Options: all, administrator, editor, author, contributor, subscriber, customer and shop_manager. Default is customer.
         * @param role
         * @return
         */
        public T setRole(String role) {
            addNameValuePair("role", role);
            return self();
        }

        public CustomerResponse getResponse(){

            return new CustomerResponse(
                new WooCommerce().search(
                    getEndPoint(),
                    build(),
                    new TypeReference<List<Customer>>(){}
                )
            );


        }

    }
}
