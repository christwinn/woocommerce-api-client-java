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
import uk.co.twinn.api.woocommerce.response.Created;
import uk.co.twinn.api.woocommerce.response.Deleted;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;

import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.order.OrderNote;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class OrderNoteBuilder extends ApiRequest {

    private Integer orderId; //write only

    private final OrderNote orderNote = new OrderNote();

    //private boolean force;

    public OrderNoteBuilder(){}

    private OrderNoteBuilder(Creator<?> creator){

        orderId = creator.orderId;
        orderNote.setId(0);
        orderNote.setNote(creator.note);
        orderNote.setCustomerNote(creator.customerNote);
        orderNote.setAddedByUser(creator.addedByUser);

    }

    public String endPoint(){

        return
           endPoint((orderId != null ? orderId : 0), orderNote.getId() == null ? 0 : orderNote.getId());

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

    public static class Creator<T extends Creator<T>> extends CoreCreator<OrderNote, T>{

        private String note;
        private Boolean customerNote;
        private Boolean addedByUser;

        protected final int orderId;

        public Creator(int orderId){
            this.orderId = orderId;
        }

        public Creator(int orderId, OrderNote orderNote){
            this(orderId);
            note = orderNote.getNote();
            customerNote = orderNote.getCustomerNote();
            addedByUser = orderNote.getAddedByUser();
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
         * @param customerNote  If true, the note will be shown to customers, and they will be notified.
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

        protected OrderNoteBuilder build(){
            return new OrderNoteBuilder(this);
        }


        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public Created<OrderNote> getResponse(){
            if (orderId == 0) {
                return new Created<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Order Id MANDATORY!\nUse Lister to ListAll")
                );
            }else {

                OrderNoteBuilder create = build();
                return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<OrderNote>() {});

                //make the call
                /*return new Created<>(
                    new Rest<OrderNote>().create(create.endPoint(), create.toJson())
                );*/

            }
        }

    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ChildReaderCore<OrderNote>{

        public Reader(int orderId, int noteId){
            super(orderId, noteId);
        }

        public Read<OrderNote> getResponse(){
            return super.getResponse(ORDERS, NOTES, new TypeReference<OrderNote>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.ChildDeleterCore<OrderNote>{

        public Deleter(int orderId, int noteId, boolean force){
            super(orderId, noteId, force);
        }

        public Deleted<OrderNote> getResponse(){
            return super.getResponse(ORDERS, NOTES, new TypeReference<OrderNote>() {});
        }

    }
    //</editor-fold>



    public static class ListAll<T extends ListAll<T>> extends CoreSeek.SearchContext<OrderNote, T> {

        private final int orderId;

        public ListAll(int orderId){
            this.orderId = orderId;
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
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */

        public Listed<OrderNote> getResponse(){
            if (orderId == 0) {
                return new Listed<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Order Id is MANDATORY!")
                );
            }else {

                return super.getResponse(
                    endPoint(orderId, 0),
                    build(),
                    new TypeReference<List<OrderNote>>() {}
                );

                /*return new Listed<>(
                    new Rest<List<OrderNote>>().listAll(

                    )
                );*/
            }
        }

    }

}
