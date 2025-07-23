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
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;

import uk.co.twinn.pl_wtx_woocommerce.model.ProductAttribute;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class ProductAttributeBuilder extends ApiRequest {

    protected final ProductAttribute productAttribute = new ProductAttribute();

    public ProductAttributeBuilder(){}

    private ProductAttributeBuilder(Creator<?> creator){

        productAttribute.setName(creator.name);
        productAttribute.setSlug(creator.slug);
        productAttribute.setType(creator.type);
        productAttribute.setOrderBy(creator.orderBy);
        productAttribute.setHasArchives(creator.hasArchives);

    }

    private ProductAttributeBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        productAttribute.setId(updater.id);
        //set the specific ids required to update an object


    }

    private ProductAttributeBuilder(Deleter deleter){

        productAttribute.setId(deleter.id);

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

    public static class Creator<T extends Creator<T>> extends CoreCreator<ProductAttribute, T>{

        //set up the private variables
        protected String name; //	string	Attribute name.mandatory
        protected String slug; //	string	An alphanumeric identifier for the resource unique to its type.
        protected String type; //	string	Type of attribute. By 'default' only select is supported.
        protected String orderBy; //	string	Default sort order. Options: menu_order, name, name_num and id. Default is menu_order
        protected Boolean hasArchives; //	boolean	Enable/Disable attribute archives. Default is false.

        private Creator(){
        }

        public Creator(String name){
            this.name = name;
        }

        public Creator(ProductAttribute productAttribute) {
            this(productAttribute.getName());
            this.slug = productAttribute.getSlug();
            this.type = productAttribute.getType();
            this.orderBy = productAttribute.getOrderBy();
            this.hasArchives = productAttribute.getHasArchives();
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

        protected ProductAttributeBuilder build(){
            return new ProductAttributeBuilder(this);
        }

        /** Returns single Created ProductAttribute**/
        public Created<ProductAttribute> getResponse(){
            //premliminary checks
            if (name == null || name.isEmpty()) {
                return new Created<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Name is MANDATORY!")
                );
            }else {

                ProductAttributeBuilder create = build();
                return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<ProductAttribute>(){});
                //make the call
                /*return new Created<>(
                    new Rest<ProductAttribute>().create(create.endPoint(), create.toJson())
                );*/
            }
        }


    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        //set up the private variables
        private int id;

        public Updater(int productAttributeId){
            super();
            this.id = productAttributeId;
        }

        public Updater(ProductAttribute productAttribute) {
            super(productAttribute);
            if (productAttribute.getId() != null) {
                this.id = productAttribute.getId();
            }else{
                this.id = 0;
            }
        }

        public T setId(int id) {
            this.id = id;
            return self();
        }

        protected ProductAttributeBuilder build(){
            return new ProductAttributeBuilder(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Updated<ProductAttribute> getResponse(){

            //preliminary checks
            if (id <= 0 || name == null || name.isEmpty()) {
                return new Updated<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Id and Name are MANDATORY")
                );
            }else {
                ProductAttributeBuilder create = build();
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<ProductAttribute>(){});
                //make the call
                /*return new Updated<>(
                    new Rest<ProductAttribute>().update(create.endPoint(), create.toJson())
                );*/
            }

        }

    }


    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<ProductAttribute>{

        public Reader(int productAttributeId){
            super(productAttributeId);
        }

        public Read<ProductAttribute> getResponse(){
            return super.getResponse(PRODUCTS_ATTRIBUTES, new TypeReference<ProductAttribute>(){});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<ProductAttribute>{

        public Deleter(int productAttributeId, boolean force){
            super(productAttributeId, force);
        }

        protected ProductAttributeBuilder build(){
            return new ProductAttributeBuilder(this);
        }

        public Deleted<ProductAttribute> getResponse(){
            return super.getResponse(PRODUCTS_ATTRIBUTES, new TypeReference<ProductAttribute>(){});

        }

    }
    //</editor-fold>

    //<editor-fold name="Batcher">
    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<ProductAttribute, T>{

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().productAttribute);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().productAttribute);
            return self();
        }

        public T addDeleter(Deleter delete){
            batch.addDelete(delete.build().productAttribute.getId());
            return self();
        }

        /** Returns list of amended Orders **/
        public Batched<ProductAttribute> getResponse(){

            return super.getResponse(PRODUCTS_ATTRIBUTES, batch, new TypeReference<ProductAttribute>(){});

        }

    }
    //</editor-fold>

    //or Seek.SearchCore<T>
    public static class ListAll<T extends ListAll<T>> extends CoreSeek.SearchCore<ProductAttribute, T>{

        public Listed<ProductAttribute> getResponse(){

            return super.getResponse(
                endPoint(0), //endPoint, SET endPoint
                build(),
                new TypeReference<List<ProductAttribute>>(){}
            );

            /*    return new Listed<>(
                    new Rest<List<ProductAttribute>>().listAll(
                        endPoint(0), //endPoint, SET endPoint
                        build()
                    )
                );*/


        }

    }

}
