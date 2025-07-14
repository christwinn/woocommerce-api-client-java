/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.request.core.Seek;
import uk.co.twinn.api.woocommerce.response.Created;
import uk.co.twinn.api.woocommerce.response.Deleted;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.request.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.Customer;
import uk.co.twinn.pl_wtx_woocommerce.model.OrderNote;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class OrderNoteApi extends ApiRequest {

    private Integer orderId; //write only

    private final OrderNote orderNote = new OrderNote();

    //private boolean force;

    public OrderNoteApi(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public Creator<?> create(){

        return new Creator<>();

    }

    public Reader<?> read(int orderId, int noteId){

        return new Reader<>(orderId, noteId);

    }

    public Deleter<?> delete(int orderId, int noteId, boolean force){

        return new Deleter<>(orderId, noteId, force);

    }

    public ListAll<?> listing(){

        return new ListAll<>();

    }
    //</editor-fold>

    private OrderNoteApi(Creator<?> creator){

        orderId = creator.orderId;
        orderNote.setNote(creator.note);
        orderNote.setCustomerNote(creator.customerNote);
        orderNote.setAddedByUser(creator.addedByUser);

    }

    public String endPoint(){

        return
           endPoint((orderId != null ? orderId : 0), orderNote.getId());

    }

    private static String endPoint(int orderId, int noteId){

        return ORDERS +
            "/" +
            orderId
            +
            NOTES +
            (noteId > 0
                ? ("/" + noteId)
                : ""
            );

    }

    public String toJson(){

        try {
                // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(orderNote);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

        private String note;
        private Boolean customerNote;
        private Boolean addedByUser;

        protected int orderId;

        T self() {
            return (T) this;
        }

        public T setOrderId(int orderId){
            this.orderId = orderId;
            return self();
        }

        /**
         *
         * @param note Order note content.
         * @return T
         */
        public T setNote(String note) {
            this.note = note;
            return self();
        }

        /**
         *
         * @param customerNote  If true, the note will be shown to customers and they will be notified.
         *           If false, the note will be for admin reference only.
         *           Default is false.
         * @return T
         */
        public T setCustomerNote(Boolean customerNote) {
            this.customerNote = customerNote;
            return self();
        }

        /**
         *
         * @param addedByUser If true, this note will be attributed to the current user.
         *           If false, the note will be attributed to the system.
         *           Default is false.
         * @return T
         */
        public T setAddedByUser(Boolean addedByUser) {
            this.addedByUser = addedByUser;
            return self();
        }

        protected OrderNoteApi build(){
            return new OrderNoteApi(this);
        }


        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<OrderNote> getResponse(){
            if (orderId == 0) {
                return new Created<OrderNote>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id MANDATORY!\nUse Lister to ListAll")
                );
            }else {
                OrderNoteApi create = build();
                //make the call
                return new Created<>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<OrderNote>(){})
                );
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ChildReaderCore<T>{

        public Reader(int orderId, int noteId){
            super(orderId, noteId);
        }

        /*@Override
        T self() {return (T) this;}*/

        /*public T setOrderId(int orderId){
            super.setId(orderId);
            return self();
        }

        public T setNoteId(int noteId){
            super.setChildId(noteId);
            return self();
        }*/

        public Read<OrderNote> getResponse(){
            return (Read<OrderNote>)super.getResponse(ORDERS, NOTES, new TypeReference<OrderNote>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.ChildDeleterCore<T>{

        public Deleter(int orderId, int noteId, boolean force){
            super(orderId, noteId, force);
        }

       /* @Override
        T self() {return (T) this;}

        public T setOrderId(int orderId){
            super.setId(orderId);
            return self();
        }

        public T setNoteId(int noteId){
            super.setChildId(noteId);
            return self();
        }*/

        public Deleted<OrderNote> getResponse(){
            return (Deleted<OrderNote>)super.getResponse(ORDERS, NOTES, new TypeReference<OrderNote>() {});

        }

    }
    //</editor-fold>



    public static class ListAll<T extends ListAll<T>> extends Seek.SearchCore<T> {

        protected int orderId;

        T self() {
            return (T) this;
        }

        /**
         *
         * @param type Limit result to customers or internal notes.
         *             Options: any, customer and internal.
         *             Default is any.
         * @return T
         */
        public T setType(String type) {
            addNameValuePair("type", type);
            return self();
        }

        /**
         *
         * @param orderId Order note(s) must be tied to an Order.
         * @return  T
         */
        public T setOrderId(int orderId){
            this.orderId = orderId;
            return self();
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */

        public Listed<OrderNote> getResponse(){
            if (orderId == 0) {
                return new Listed<OrderNote>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }else {
                return new Listed<OrderNote>(
                    new Rest().listAll(
                        endPoint(orderId, 0),
                        build(),
                        new TypeReference<List<Customer>>(){}
                    )
                );
            }
        }

    }

}
