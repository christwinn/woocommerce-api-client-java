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
import uk.co.twinn.api.woocommerce.builders.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductShippingClass;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS_SHIPPING_CLASSES;

public class ProductShippingClassBuilder extends ApiRequest {

    protected final ProductShippingClass productShippingClass = new ProductShippingClass();

    public ProductShippingClassBuilder(){}

    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ProductShippingClassBuilder(Creator<?> creator){

        productShippingClass.setName(creator.name);
        productShippingClass.setSlug(creator.slug);
        productShippingClass.setDescription(creator.description);

    }

    private ProductShippingClassBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        productShippingClass.setId(updater.id);

    }

    private ProductShippingClassBuilder(Deleter deleter){

        productShippingClass.setId(deleter.id);

    }

    public String toJson(){

        try {

            return getObjectMapper().writeValueAsString(productShippingClass);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public String endPoint(){

        return PRODUCTS_SHIPPING_CLASSES +
            (productShippingClass.getId() != null && productShippingClass.getId() != 0
                ? ("/" + productShippingClass.getId())
                : ""
            );

    }

    public static class Creator<T extends Creator<?>>{

        private String name;        //string	Product name.
        private String slug;        //string	Product slug.
        private String description;

        private Creator(){}

        public Creator(String name){
            this.name = name;
        }

        public Creator(ProductShippingClass productShippingClass){
            this(productShippingClass.getName());
            this.slug = productShippingClass.getSlug();
            this.description = productShippingClass.getDescription();
        }

        /**
         *
         * @param slug Product slug.
         * @return T
         */
        public T setSlug(String slug) {
            this.slug = slug;
            return self();
        }

        /**
         *
         * @param description HTML description of the resource.
         * @return T
         */
        public T setDescription(String description) {
            this.description = description;
            return self();
        }

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        private ProductShippingClassBuilder build(){
            return new ProductShippingClassBuilder(this);
        }

        public Created<ProductShippingClass> getResponse(){

            if (name == null || name.isEmpty()){
                return new Created<>(new ApiResponseResult(false, 0, "Name is Mandatory"));
            }else {
                ProductShippingClassBuilder create = build();
                //make the call
                return new Created<>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductShippingClass>() {})
                );
            }

        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private final int id;

        public Updater(int shippingClassId){
            this.id = shippingClassId;
        }

        public Updater(ProductShippingClass productShippingClass){
            super(productShippingClass);
            if (productShippingClass.getId() != null) {
                this.id = productShippingClass.getId();
            }else{
                this.id = 0;
            }
        }

        private ProductShippingClassBuilder build(){
            return new ProductShippingClassBuilder(this);
        }

        @Override
        public Updated<ProductShippingClass> getResponse(){
            if (id > 0){

                ProductShippingClassBuilder create = build();
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ProductShippingClass>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore{

        public Reader(int shippingClassId){
            super(shippingClassId);
        }

        @SuppressWarnings("unchecked")
        public Read<ProductShippingClass> getResponse(){
            return (Read<ProductShippingClass>)super.getResponse(PRODUCTS_SHIPPING_CLASSES, new TypeReference<ProductShippingClass>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore{

        public Deleter(int shippingClassId, boolean force){
            super(shippingClassId, force);
        }

        protected ProductShippingClassBuilder build(){
            return new ProductShippingClassBuilder(this);
        }

        @SuppressWarnings("unchecked")
        public Deleted<ProductShippingClass> getResponse(){
            return (Deleted<ProductShippingClass>)super.getResponse(PRODUCTS_SHIPPING_CLASSES, new TypeReference<ProductShippingClass>() {});

        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<ProductShippingClass, T>{

        public Batcher(){
            super();
        }

        public T addCreator(ProductShippingClassBuilder.Creator<?> create){
            batch.addCreate(create.build().productShippingClass);
            return self();
        }
        public T addUpdater(ProductShippingClassBuilder.Updater<?> update){
            batch.addUpdate(update.build().productShippingClass);
            return self();
        }
        public T addDeleter(ProductShippingClassBuilder.Deleter delete){
            batch.addDelete(delete.build().productShippingClass.getId());
            return self();
        }

        @SuppressWarnings("unchecked")
        public Batched<ProductShippingClass> getResponse(){
            return (Batched<ProductShippingClass>) super.getResponse(PRODUCTS_SHIPPING_CLASSES, batch, new TypeReference<BatchResult<ProductShippingClass>>(){});
        }

    }

    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T> {

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        /**
         *
         * @param hideEmpty	boolean	Whether to hide resources not assigned to any products. Default is false.
         * @return T
         */
        public T setHideEmpty(boolean hideEmpty) {
            addNameValuePair("hide_empty", hideEmpty);
            return self();
        }
        /**
         *
         * @param product	integer	Limit result set to resources assigned to a specific product.
         * @return T
         */
        public T setProduct(int product) {
            addNameValuePair("product", product);
            return self();
        }
        /**
         *
         * @param slug	string	Limit result set to resources with a specific slug.
         * @return T
         */
        public T setSlug(String slug) {
            addNameValuePair("slug", slug);
            return self();
        }

        public Listed<ProductShippingClass> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    PRODUCTS_SHIPPING_CLASSES,
                    build(),
                    new TypeReference<List<ProductShippingClass>>(){}
                )
            );

        }

    }
}
