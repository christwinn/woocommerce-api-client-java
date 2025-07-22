/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.api;

import uk.co.twinn.api.woocommerce.builders.OrderNoteBuilder;
import uk.co.twinn.pl_wtx_woocommerce.model.OrderNote;

public class OrderNotes {

    private OrderNotes(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public static OrderNoteBuilder.Creator<?> create(int orderId){

        return new OrderNoteBuilder.Creator<>(orderId);

    }

    public static OrderNoteBuilder.Creator<?> create(int orderId, OrderNote orderNote){

        return new OrderNoteBuilder.Creator<>(orderId, orderNote);

    }

    public static OrderNoteBuilder.Reader read(int orderId, int noteId){

        return new OrderNoteBuilder.Reader(orderId, noteId);

    }

    public static OrderNoteBuilder.Deleter delete(int orderId, int noteId, boolean force){

        return new OrderNoteBuilder.Deleter(orderId, noteId, force);

    }

    public OrderNoteBuilder.ListAll<?> listing(){

        return new OrderNoteBuilder.ListAll<>();

    }
    //</editor-fold>


}
