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
import uk.co.twinn.api.woocommerce.core.Batch;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductCategory;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductImage;
import uk.co.twinn.api.woocommerce.builders.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCT_CATEGORIES;

public class ProductCategoryBuilder extends ApiRequest {

    protected final ProductCategory category = new ProductCategory();

    private boolean force;

    public ProductCategoryBuilder(){}

    private ProductCategoryBuilder(Creator<?> creator){

        category.setName(creator.name);
        category.setSlug(creator.slug);
        category.setParent(creator.parent);
        category.setDescription(creator.description);
        category.setDisplay(creator.display);

        if (creator.image != null && !creator.image.isEmpty()){
            ProductImage img = new ProductImage();
            img.setSrc(creator.image);
            category.setImage(img);
        }

        category.setMenuOrder(creator.menu_order);

    }

    private ProductCategoryBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        category.setId(updater.id);

    }

    private ProductCategoryBuilder(Deleter<?> deleter){

        category.setId(deleter.id);
        force = deleter.force;

    }

    public String endPoint(){

        return PRODUCT_CATEGORIES +
            (category.getId() != null && category.getId() != 0
                ? ("/" + category.getId())
                : ""
            );

    }

    public String toJson(){

        try {

                // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(category);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

        private String name;
        private String description;
        private Integer parent;

        private String slug;

        private ProductCategory.DisplayEnum display;
        private String image;
        private Integer menu_order;

        T self() {
            return (T) this;
        }

        public T setName(String name){
            this.name = name;
            return self();
        }
        public T setDescription(String description){
            this.description = description;
            return self();
        }
        public T setParent(Integer parent){
            this.parent = parent;
            return self();
        }

        public T setSlug(String slug){
            this.slug = slug;
            return self();
        }

        public T setDisplay(String display){
            this.display = ProductCategory.DisplayEnum.fromValue(display);
            return self();
        }

        public T setDisplay(ProductCategory.DisplayEnum display){
            this.display = display;
            return self();
        }

        public T setImage(String image){
            this.image = image;
            return self();
        }
        public T setMenuOrder(Integer menu_order){
            this.menu_order = menu_order;
            return self();
        }

        protected ProductCategoryBuilder build(){
            return new ProductCategoryBuilder(this);
        }

        public Created<ProductCategory> getResponse(){

            ProductCategoryBuilder create = build();
            //make the call
            return new Created<>(
                new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductCategory>(){})
            );

        }
    }

    public static class Updater<T extends Updater<T>> extends Creator<T>{

        private final int id;

        public Updater(int productCategoryId){
            this.id = productCategoryId;
        }

        /*
        public T setId(int id){
            this.id = id;
            return self();
        }*/

        @Override
        protected ProductCategoryBuilder build(){
            return new ProductCategoryBuilder(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public Updated<ProductCategory> getResponse(){
            if (id > 0) {
                ProductCategoryBuilder create = build();
                //make the call
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ProductCategory>(){})
                );

            }else {
                return new Updated<ProductCategory>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Category Id is MANDATORY!")
                );
            }

        }

    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReader.ReaderCore<T>{

        public Reader(int productCategoryId){
            super(productCategoryId);
        }

        /*@Override
        T self() {return (T) this;}*/

        public Read<ProductCategory> getResponse(){
            return (Read<ProductCategory>)super.getResponse(PRODUCT_CATEGORIES, new TypeReference<ProductCategory>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleter.DeleterCore<T>{

        public Deleter(int productCategoryId, boolean force){
            super(productCategoryId, force);
        }

        /*@Override
        T self() {return (T) this;}*/

        protected ProductCategoryBuilder build(){
            return new ProductCategoryBuilder(this);
        }

        public Deleted<ProductCategory> getResponse(){
            return (Deleted<ProductCategory>)super.getResponse(PRODUCT_CATEGORIES, new TypeReference<ProductCategory>() {});

        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<T>{

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().category);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().category);
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().category);
            return self();
        }

        /** Returns list of amended ProductCategories **/
        public Batched<ProductCategory> getResponse(){

            return (Batched<ProductCategory>) super.getResponse(PRODUCT_CATEGORIES, batch, new TypeReference<Batch<ProductCategory>>(){});

        }

    }

    /**
     *
     *
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products">Searches the ProductCategories</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T> {

        T self() {
            return (T) this;
        }

        /**
         * @param hideEmpty Whether to hide resources not assigned to any products. Default is false.
         * @return T
         */
        public T setHideEmpty(boolean hideEmpty) {
            addNameValuePair("hide_empty", hideEmpty);
            return self();
        }

        /**
         * @param parentId Limit result set to resources assigned to a specific parent.
         * @return T
         */
        public T setParentId(int parentId) {
            addNameValuePair("parent", parentId);
            return self();
        }

        /**
         * @param productId Limit result set to resources assigned to a specific product.
         * @return T
         */
        public T setProductId(int productId) {
            addNameValuePair("product", productId);
            return self();
        }

        /**
         * @param slug Limit result set to resources with a specific slug.
         * @return T
         */
        public T setSlug(String slug) {
            addNameValuePair("slug", slug);
            return self();
        }

        public Listed<ProductCategory> getResponse(){

            return new Listed<>(
                new Rest().listAll(
                    PRODUCT_CATEGORIES,
                    build(),
                    new TypeReference<List<ProductCategory>>(){}
                )
            );

        }

    }
}
