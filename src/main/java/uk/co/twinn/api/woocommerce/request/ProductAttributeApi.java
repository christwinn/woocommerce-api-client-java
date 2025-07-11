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
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.api.woocommerce.request.core.ApiRequest;
import uk.co.twinn.api.woocommerce.request.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductAttribute;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class ProductAttributeApi extends ApiRequest {

    protected final ProductAttribute productAttribute = new ProductAttribute();

    private boolean force;

    public ProductAttributeApi(){}

    private ProductAttributeApi(Creator<?> creator){

        productAttribute.setName(creator.name);
        productAttribute.setSlug(creator.slug);
        productAttribute.setType(creator.type);
        productAttribute.setOrderBy(creator.orderBy);
        productAttribute.setHasArchives(creator.hasArchives);

    }

    private ProductAttributeApi(Updater<?> updater){

        this((Creator<?>)updater);
        productAttribute.setId(updater.id);
        //set the specific ids required to update an object


    }

    private ProductAttributeApi(Deleter<?> deleter){

        productAttribute.setId(deleter.id);
        force = deleter.force;

    }

    public String endPoint(){

        return endPoint(productAttribute.getId() == null ? 0 : productAttribute.getId());

    }

    private static String endPoint(int id){

        return PRODUCTS_ATTRIBUTES +
            (id > 0
                ? ("/" + id)
                : ""
            );

    }

    public String toJson(){

        try {

            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(productAttribute);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

        //set up the private variables
        protected String name; //	string	Attribute name.mandatory
        protected String slug; //	string	An alphanumeric identifier for the resource unique to its type.
        protected String type; //	string	Type of attribute. By default only select is supported.
        protected String orderBy; //	string	Default sort order. Options: menu_order, name, name_num and id. Default is menu_order
        protected Boolean hasArchives; //	boolean	Enable/Disable attribute archives. Default is false.

        T self() {
            return (T) this;
        }

        public T setName(String name) {
            this.name = name;
            return self();
        }

        public T setSlug(String slug) {
            this.slug = slug;
            return self();
        }

        public T setType(String type) {
            this.type = type;
            return self();
        }

        public T setOrderBy(String orderBy) {
            this.orderBy = orderBy;
            return self();
        }

        public T setHasArchives(Boolean hasArchives) {
            this.hasArchives = hasArchives;
            return self();
        }

        protected ProductAttributeApi build(){
            return new ProductAttributeApi(this);
        }

        /** Returns single Created ProductAttribute**/
        public Created<ProductAttribute> getResponse(){
            //premliminary checks
            if (name == null && name.isEmpty()) {
                return new Created<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Name is MANDATORY!")
                );
            }else {

                ProductAttributeApi create = build();
                //make the call
                return new Created<ProductAttribute>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductAttribute>(){})
                );
            }
        }


    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        //set up the private variables
        private int id;

        T self() {
            return (T) this;
        }

        public T setId(int id) {
            this.id = id;
            return self();
        }

        protected ProductAttributeApi build(){
            return new ProductAttributeApi(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Updated<ProductAttribute> getResponse(){

            //preliminary checks
            if (id <= 0 || name == null || name.isEmpty()) {
                return new Updated<ProductAttribute>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Id and Name are MANDATORY")
                );
            }else {
                ProductAttributeApi create = build();
                //make the call
                return new Updated<ProductAttribute>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ProductAttribute>(){})
                );
            }

        }

    }


    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ReaderCore<T>{

        @Override
        T self() {return (T) this;}

        public Read<ProductAttribute> getResponse(){
            return (Read<ProductAttribute>)super.getResponse(PRODUCTS_ATTRIBUTES, new TypeReference<ProductAttribute>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.DeleterCore<T>{

        @Override
        T self() {return (T) this;}

        protected ProductAttributeApi build(){
            return new ProductAttributeApi(this);
        }

        public Deleted<ProductAttribute> getResponse(){
            return (Deleted<ProductAttribute>)super.getResponse(PRODUCTS_ATTRIBUTES, new TypeReference<ProductAttribute>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Batcher">
    public static class Batcher<T extends Batcher<T>> extends CoreBatchRequest.BatchCore<T>{

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().productAttribute);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().productAttribute);
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().productAttribute);
            return self();
        }

        /** Returns list of amended Orders **/
        public Batched<ProductAttribute> getResponse(){

            return (Batched<ProductAttribute>) super.getResponse(PRODUCTS_ATTRIBUTES, batch, new TypeReference<Batch<ProductAttribute>>(){});

        }

    }
    //</editor-fold>

    //or Seek.SearchCore<T>
    public static class ListAll<T extends ListAll<T>> extends Seek.SearchCore<T>{

        T self() {
            return (T) this;
        }

        public Listed<ProductAttribute> getResponse(){


                return new Listed<>(
                    new Rest().listAll(
                        endPoint(0), //endPoint, SET endPoint
                        build(),
                        new TypeReference<List<ProductAttribute>>(){}
                    )
                );


        }

    }
}
