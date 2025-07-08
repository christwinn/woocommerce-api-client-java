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

public class OrderNoteRequest extends ApiRequest {

    private final OrderNote orderNote = new OrderNote();

    private boolean force;

    public OrderNoteRequest(Creator<?> creator){

        orderNote.setOrderId(creator.orderId);
        orderNote.setNote(creator.note);
        orderNote.setCustomerNote(creator.customerNote);
        orderNote.setAddedByUser(creator.addedByUser);

    }

    public OrderNoteRequest(ListAll<?> listAller){

        orderNote.setOrderId(listAller.orderId);

    }

    public String endPoint(){

        return
            getEndPoint() +
            (orderNote.getOrderId() != null && orderNote.getOrderId() != 0
                ? ("/" + orderNote.getOrderId())
                : ""
            ) +

            "/" + NOTES +

            (orderNote.getId() != null && orderNote.getId() != 0
                ? ("/" + orderNote.getId())
                : ""
            ) +
            (force
                ? "?force=true"
                : ""
            );

    }

    private static String getEndPoint(){
        //we are a subcall of orders, build in endPoint
        return ORDERS;

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

        protected OrderNoteRequest build(){
            return new OrderNoteRequest(this);
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
                return new Rest().create(build());
            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends ReaderRequest.ChildReaderCore<T>{

        @Override
        public T self() {return (T) this;}

        public T setNoteId(int noteId){
            super.setChildId(noteId);
            return self();
        }
        public Read<OrderNote> getResponse(){
            return (Read<OrderNote>)super.getResponse(ORDERS, NOTES, new TypeReference<OrderNote>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends DeleterRequest.ChildDeleterCore<T>{

        @Override
        public T self() {return (T) this;}

        public T setNoteId(int noteId){
            super.setChildId(noteId);
            return self();
        }

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
                        getEndPoint(),
                        build(),
                        new TypeReference<List<Customer>>(){}
                    )
                );
            }
        }

    }

}
