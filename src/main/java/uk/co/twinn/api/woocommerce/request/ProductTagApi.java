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
import uk.co.twinn.pl_wtx_woocommerce.model.ProductTag;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS_TAGS;

public class ProductTagApi extends ApiRequest {

    protected final ProductTag productTag = new ProductTag();

    private boolean force;

    public ProductTagApi(){}

    //<editor-fold defaultstate="collapsed" desc="Fluent Convenience Methods">
    public Creator<?> create(){

        return new Creator<>();

    }

    public Reader<?> read(int productTagId){

        return new Reader<>(productTagId);

    }

    public Updater<?> update(int productTagId){

        return new Updater<>(productTagId);

    }

    public Deleter<?> delete(int productTagId, boolean force){

        return new Deleter<>(productTagId, force);

    }

    public Batcher<?> batch(){

        return new Batcher<>();

    }
    public ListAll<?> listing(){

        return new ListAll<>();

    }
    //</editor-fold>

    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ProductTagApi(Creator<?> creator){

        productTag.setName(creator.name);
        productTag.setSlug(creator.slug);
        productTag.setDescription(creator.description);

    }

    private ProductTagApi(Updater<?> updater){

        this((ProductTagApi.Creator<?>)updater);
        productTag.setId(updater.id);

    }

    private ProductTagApi(Deleter<?> deleter){

        productTag.setId(deleter.id);
        force = deleter.force;

    }

    public String toJson(){

        try {

            return getObjectMapper().writeValueAsString(productTag);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public String endPoint(){

        return PRODUCTS_TAGS +
            (productTag.getId() != null && productTag.getId() != 0
                ? ("/" + productTag.getId())
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

        private ProductTagApi build(){
            return new ProductTagApi(this);
        }

        public Created<ProductTag> getResponse(){

            if (name == null || name.isEmpty()){
                return new Created<>(new ApiResponseResult(false, 0, "Name is Mandatory"));
            }else {
                ProductTagApi create = build();
                //make the call
                return new Created<>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductTag>() {})
                );
            }

        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private final int id;

        public Updater(int productTagId){
            this.id = productTagId;
        }

        /*public T setId(int id) {
            this.id = id;
            return self();
        }*/

        private ProductTagApi build(){
            return new ProductTagApi(this);
        }

        @Override
        public Updated<ProductTag> getResponse(){
            if (id > 0){

                ProductTagApi create = build();
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ProductTag>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ReaderCore<T>{

        public Reader(int productTagId){
            super(productTagId);
        }

        /*@Override
        T self() {return (T) this;}*/

        public Read<ProductTag> getResponse(){
            return (Read<ProductTag>)super.getResponse(PRODUCTS_TAGS, new TypeReference<ProductTag>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.DeleterCore<T>{

        public Deleter(int productTagId, boolean force){
            super(productTagId, force);
        }

        /*@Override
        T self() {return (T) this;}*/

        protected ProductTagApi build(){
            return new ProductTagApi(this);
        }

        public Deleted<ProductTag> getResponse(){
            return (Deleted<ProductTag>)super.getResponse(PRODUCTS_TAGS, new TypeReference<ProductTag>() {});

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

        public T addCreator(ProductTagApi.Creator<?> create){
            batch.addCreate(create.build().productTag);
            return self();
        }

        public T addUpdater(ProductTagApi.Updater<?> update){
            batch.addUpdate(update.build().productTag);
            return self();
        }

        public T addDeleter(ProductTagApi.Deleter<?> delete){
            batch.addDelete(delete.build().productTag);
            return self();
        }

        public Batched<ProductTag> getResponse(){

            return (Batched<ProductTag>) super.getResponse(PRODUCTS_TAGS, batch, new TypeReference<Batch<ProductTag>>(){});

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

        public Listed<ProductTag> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    PRODUCTS_TAGS,
                    build(),
                    new TypeReference<List<ProductTag>>(){}
                )
            );

        }

    }
}
