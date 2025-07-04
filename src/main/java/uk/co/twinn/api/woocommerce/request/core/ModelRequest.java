/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.woocommerce.WooCommerce;
import uk.co.twinn.pl_wtx_woocommerce.model.*;

import java.util.List;

/* A ModelRequest to utilise for other Requests*/
public class ModelRequest extends ApiRequest {

    protected final Model model = new Model();

    private boolean force;

    private Batch batch;

    public ModelRequest(){}

    private ModelRequest(Creator<?> creator){

        //Set the object parameters from creator;

    }

    private ModelRequest(Reader<?> reader){

        //set the specific ids required to read an object

    }

    private ModelRequest(Updater<?> updater){

        this((Creator<?>)updater);
        //set the specific ids required to update an object

    }

    private ModelRequest(Deleter<?> deleter){

        this((Reader<?>)deleter);
        force = deleter.force;

    }

    private ModelRequest(ListAll<?> listAller){

        //set orderRefund.setOrderId(listAller.orderId);

    }

    public String endPoint(){

        /*return STATIC_OBJECT_NAME_TO_ROOT_END_POINT +
            (id != 0
                ? ("/" + id)
                : ""
            ) +

            //if a sub method this may be required
            "/" + REFUNDS +
            (object.getId() != null && object.getId() != 0
                ? "/" + object.getId()
                : ""
            ) +

            (force
                ? "?force=true"
                : ""
            );*/

        return "";

    }

    public String toJson(){

        try {

            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(model);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

        //set up the private variables

        T self() {
            return (T) this;
        }

        /*
        set up the builders to set the private variables
        public T setShippingLines(List<OrderShippingLine> shippingLines) {
            this.shippingLines = shippingLines;
            return self();
        }
         */

        protected ModelRequest build(){
            return new ModelRequest(this);
        }

        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<Model> getResponse(){
            //premliminary checks
            /*if (orderId == 0) {*/
                return new Created<Model>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            /*}else {
                //make the call
                return new WooCommerce().create(build());
            }*/
        }



    }

    public static class Reader<T extends Reader<T>>{

        //set up the private variables

        T self() {
            return (T) this;
        }

        /*
        set up the builders to set the private variables
        public T setId(int id) {
            this.id = id;
            return self();
        }
         */

        protected ModelRequest build(){
            return new ModelRequest(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Read<Model> getResponse(){
            //preliminary checks if (orderId <= 0 || refundId <= 0) {
                return new Read<Model>(
                    new ApiResponseResult(
                        false,
                        0,
                        "CRUD is limited to a single object result\n" +
                            "Please set requested id\n" +
                            "Use the ListAll with orderId to get lst of refunds for an order")
                );
            /*}else {
                return new WooCommerce().read(build());
            }*/
        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T>{

        //set up the private variables

        T self() {
            return (T) this;
        }

        /*
        set up the builders to set the private variables
        public T setId(int id) {
            this.id = id;
            return self();
        }
         */

        protected ModelRequest build(){
            return new ModelRequest(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Updated<Model> getResponse(){
            //preliminary checks if (orderId <= 0 || refundId <= 0) {
            return new Updated<Model>(
                new ApiResponseResult(
                    false,
                    0,
                    "Id's are MANDATORY")
            );
            /*}else {
                return new WooCommerce().read(build());
            }*/
        }

    }

    public static class Deleter<T extends Deleter<T>> extends Reader<T> {

        private boolean force;

        public T force(boolean force){
            this.force = force;
            return self();
        }

        @Override
        protected ModelRequest build(){
            return new ModelRequest(this);
        }

        /** Returns single Deleted ProductCategory**/
        @Override
        public Deleted<Model> getResponse() {
            //checksif (orderId == 0 || refundId == 0) {
                return new Deleted<Model>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id And refund Id are MANDATORY!")
                );
            /*}else{
                return new WooCommerce().delete(build());
            }*/
        }

    }

    //or Seek.SearchCore<T>
    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T>{

        //if we need to set a local variable
        //sub models, eg Order->Refund require tying to an order
        // protected int orderId;

        T self() {
            return (T) this;
        }

        /**     SET THE LOCAL VARIABLES
         *
         * @param orderId Order note(s) must be tied to an Order.
         * @return T
         */
        /*public T setOrderId(int orderId){
            this.orderId = orderId;
            return self();
        }*/

        /**     ADD OPTIONAL EXTRAS
         *
         * @param extra A parameter specific to this search type
         * @return T
         */
        /*public T setRandom(int random){
            addNameValuePair("random", random);
            return self();
        }*/


        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Listed<Model> getResponse(){
            /*Preliminary checksif (orderId == 0) {
                return new Listed<OrderRefund>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }else {
            */
                //String endPoint = ORDERS + "/" + orderId + "/refunds";

                return new Listed<Model>(
                    new WooCommerce().listAll(
                        "", //endPoint, SET endPoint
                        build(),
                        new TypeReference<List<Model>>(){}
                    )
                );

            //}

        }

    }

}

