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
import uk.co.twinn.pl_wtx_woocommerce.model.ProductShippingClass;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS_SHIPPING_CLASSES;

public class ProductShippingApi extends ApiRequest {

    protected final ProductShippingClass productShippingClass = new ProductShippingClass();

    private boolean force;

    public ProductShippingApi(){}

    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ProductShippingApi(Creator<?> creator){

        productShippingClass.setName(creator.name);
        productShippingClass.setSlug(creator.slug);
        productShippingClass.setDescription(creator.description);

    }

    private ProductShippingApi(Updater<?> updater){

        this((Creator<?>)updater);
        productShippingClass.setId(updater.id);

    }

    private ProductShippingApi(Deleter<?> deleter){

        productShippingClass.setId(deleter.id);
        force = deleter.force;

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

        /**
         *
         * @param name Product Name
         * @return T
         */
        public T setName(String name) {
            this.name = name;
            return self();
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

        T self() {
            return (T) this;
        }

        private ProductShippingApi build(){
            return new ProductShippingApi(this);
        }

        public Created<ProductShippingClass> getResponse(){

            if (name == null || name.isEmpty()){
                return new Created<>(new ApiResponseResult(false, 0, "Name is Mandatory"));
            }else {
                ProductShippingApi create = build();
                //make the call
                return new Created<>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductShippingClass>() {})
                );
            }

        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private int id;

        public T setId(int id) {
            this.id = id;
            return self();
        }

        private ProductShippingApi build(){
            return new ProductShippingApi(this);
        }

        @Override
        public Updated<ProductShippingClass> getResponse(){
            if (id > 0){

                ProductShippingApi create = build();
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ProductShippingClass>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ReaderCore<T>{

        @Override
        T self() {return (T) this;}

        public Read<ProductShippingClass> getResponse(){
            return (Read<ProductShippingClass>)super.getResponse(PRODUCTS_SHIPPING_CLASSES, new TypeReference<ProductShippingClass>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.DeleterCore<T>{

        @Override
        T self() {return (T) this;}

        protected ProductShippingApi build(){
            return new ProductShippingApi(this);
        }

        public Deleted<ProductShippingClass> getResponse(){
            return (Deleted<ProductShippingClass>)super.getResponse(PRODUCTS_SHIPPING_CLASSES, new TypeReference<ProductShippingClass>() {});

        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatchRequest.BatchCore<T>{

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(ProductShippingApi.Creator<?> create){
            batch.addCreate(create.build().productShippingClass);
            return self();
        }

        public T addUpdater(ProductShippingApi.Updater<?> update){
            batch.addUpdate(update.build().productShippingClass);
            return self();
        }

        public T addDeleter(ProductShippingApi.Deleter<?> delete){
            batch.addDelete(delete.build().productShippingClass);
            return self();
        }

        public Batched<ProductShippingClass> getResponse(){

            return (Batched<ProductShippingClass>) super.getResponse(PRODUCTS_SHIPPING_CLASSES, batch, new TypeReference<Batch<ProductShippingClass>>(){});

        }

    }

    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T> {

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
