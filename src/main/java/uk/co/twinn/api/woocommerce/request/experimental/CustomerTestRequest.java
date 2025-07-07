/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request.experimental;

import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.pl_wtx_woocommerce.model.Customer;

public class CustomerTestRequest {

    public CustomerTestRequest(Reader<Customer, ?> reader){

        //customer.setId(reader.id);

    }

    public static class Reader<Customer,T extends Reader<Customer, T>> extends PrototypeRequest.Reader<Customer, T>{

        //Hmm, works until here... returns Read<Object> not Read<Customer>
        //could full path but can not expect user to by typing full class path
        public Read<Customer> getResponse(){
            //unnecessary cast, but it is not casting to Customer
            return super.getCustomer("");

        }

    }

    /*public static class Deleter<T extends Deleter<T>>{

        private int id;
        private boolean force;

        T self() {
            return (T) this;
        }

        public T setId(int id){
            this.id = id;
            return self();
        }

        public T force(boolean force){
            this.force = force;
            return self();
        }

        protected CustomerRequest build(){
            return new CustomerRequest(this);
        }

        /// Returns single Deleted ProductCategory
        public Deleted<Customer> getResponse(){
            if (id > 0 && force){
                return new WooCommerce().delete(build());
            }else {
                return new Deleted<>(
                    new ApiResponseResult(false, 0,
                        "Invalid Identifier, id and force is required"));
            }
        }

    }*/
}
