/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package pl.wtx.woocommerce.crudPlusActionBuilder.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import pl.wtx.woocommerce.api.client.model.MetaData;
import pl.wtx.woocommerce.api.client.model.Product;
import pl.wtx.woocommerce.api.client.model.ProductCategory;
import pl.wtx.woocommerce.api.client.model.ProductImage;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.ApiRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.Seek;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.ProductCategoryResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.WooCommerce;

import java.util.ArrayList;
import java.util.List;

import static pl.wtx.woocommerce.crudPlusActionBuilder.defines.EndPoints.PRODUCTCATEGORIES;

public class ProductCategoryRequest extends ApiRequest {

    protected final ProductCategory category = new ProductCategory();

    private Batch batch;

    private boolean force;
    //private boolean duplicate;
    private boolean isBatch;

    public ProductCategoryRequest(Creator creator){

        category.setName(creator.name);
        category.setSlug(creator.slug);
        category.setParent(creator.parent);
        category.setDescription(creator.description);
        category.setDisplay(creator.display);

        if (creator.image != null && !creator.image.equals("")){
            ProductImage img = new ProductImage();
            img.setSrc(creator.image);
            category.setImage(img);
        }

        category.setMenuOrder(creator.menu_order);

    }

    public ProductCategoryRequest(Reader reader){

        category.setId(reader.id);

    }

    public ProductCategoryRequest(Updater updater){

        this((Creator)updater);
        category.setId(updater.id);

    }

    public ProductCategoryRequest(Deleter deleter){

        this((Reader)deleter);
        isBatch = false;
        force = deleter.force;

    }

    public ProductCategoryRequest(Batcher batcher){

        batch = batcher.getBatch();
        force = false;
        isBatch = true;

    }

    public String endPoint(){

        return getEndPoint() +
            (category.getId() != null && category.getId() != 0
                ? ("/" + category.getId())
                : ""
            ) +
            (isBatch
                ? "/batch"
                : ""
            ) +
            (force
                ? "?force=true"
                : ""
            );

    }

    private static String getEndPoint(){

        return PRODUCTCATEGORIES;

    }

    public String toJson(){

        try {

            if (isBatch){
                return getObjectMapper().writeValueAsString(batch);
            }else{
                // covert Java object to JSON strings
                return getObjectMapper().writeValueAsString(category);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>>{

        private String name;
        private String description;
        private Integer parent;

        private String slug;
        private List<MetaData> meta;

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

        public T setMeta(List<MetaData> meta){
            this.meta = meta;
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

        protected ProductCategoryRequest build(){
            return new ProductCategoryRequest(this);
        }


        /** Returns single Created ProductCategory, unless it is a duplicate! **/
        public ProductCategoryResponse getResponse(){
            WooCommerce woo = new WooCommerce();
            return woo.create(build());
        }
    }

    public static class Reader<T extends Reader<T>>{

        private int id;

        T self() {
            return (T) this;
        }

        public T setId(int id){
            this.id = id;
            return self();
        }

        protected ProductCategoryRequest build(){
            return new ProductCategoryRequest(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public ProductCategoryResponse getResponse(){
            if (id == 0) {
                return new ProductCategoryResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "CRUD is limited to a single object result\n" +
                            "Please set requested id\n" +
                            "Use the Searcher with no parameters to get a full list")
                );
            }else {
                WooCommerce woo = new WooCommerce();
                return woo.read(build());
            }
        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T>{

        private int id;

        public T setId(int id){
            this.id = id;
            return self();
        }

        @Override
        protected ProductCategoryRequest build(){
            return new ProductCategoryRequest(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public ProductCategoryResponse getResponse(){
            WooCommerce woo = new WooCommerce();
            return woo.update(build());
        }

    }

    public static class Deleter<T extends Deleter<T>> extends Reader<T>{

        private boolean force;

        public T force(boolean force){
            this.force = force;
            return self();
        }

        @Override
        protected ProductCategoryRequest build(){
            return new ProductCategoryRequest(this);
        }

        /** Returns single Deleted ProductCategory**/
        @Override
        public ProductCategoryResponse getResponse(){
            WooCommerce woo = new WooCommerce();
            return woo.delete(build());
        }

    }

    private static class Batch{

        private final List<ProductCategory> create;
        private final List<ProductCategory> update;
        private final List<ProductCategory> delete;

        public Batch(){

            create = new ArrayList<>();
            update = new ArrayList<>();
            delete = new ArrayList<>();

        }

        public int getRecordCount(){
            return create.size() + update.size() + delete.size();
        }

        public boolean isEmpty(){
            return create.isEmpty() && update.isEmpty() && delete.isEmpty();
        }

        public void addCreator(Creator create){
            this.create.add(create.build().category);
        }

        public void addUpdater(Updater update){
            this.update.add(update.build().category);
        }

        public void addDeleter(Deleter delete){
            this.delete.add(delete.build().category);
        }

        public List<ProductCategory> getCreate(){
            return create;
        }
        public List<ProductCategory> getUpdate(){
            return update;
        }
        public List<ProductCategory> getDelete(){
            return delete;
        }

    }

    public static class Batcher<T extends Batcher>{

        private static Batch batch;

        public Batcher(){
            batch = new Batch();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator create){
            batch.addCreator(create);
            return self();
        }

        public T addUpdater(Updater update){
            batch.addUpdater(update);
            return self();
        }

        public T addDeleter(Deleter delete){
            batch.addDeleter(delete);
            return self();
        }

        private Batch getBatch(){
            return batch;
        }

        private ProductCategoryRequest build(){
            return new ProductCategoryRequest(this);
        }

        public ProductCategoryResponse getResponse(){

            if (batch.isEmpty()){

                return new ProductCategoryResponse(new ApiResponseResult(false, 0, "Nothing to do"));

            }else if (batch.getRecordCount() > 100){

                return new ProductCategoryResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "https://woocommerce.github.io/woocommerce-rest-api-docs/#batch-update-product-categories\n" +
                            "This API helps you to batch create, update and delete multiple products.\n\n" +
                            "Note: By default it's limited to up to 100 objects to be created, updated or deleted.")
                );

            }else{

                /** Returns list of amended ProductCategories **/
                return new WooCommerce().create(build());

            }

        }

    }


    /**
     *
     * Searches the ProductCategories
     * https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products
     *
     * @param <T>
     */
    public static class Searcher<T extends Searcher> extends Seek.Searcher<T> {

        T self() {
            return (T) this;
        }

        /**
         * Whether to hide resources not assigned to any products. Default is false.
         * @param hideEmpty
         * @return
         */
        public T setHideEmpty(boolean hideEmpty) {
            addNameValuePair("hide_empty", hideEmpty);
            return self();
        }

        /**
         * Limit result set to resources assigned to a specific parent.
         * @param parentId
         * @return
         */
        public T setParentId(int parentId) {
            addNameValuePair("parent", parentId);
            return self();
        }

        /**
         * Limit result set to resources assigned to a specific product.
         * @param productId
         * @return
         */
        public T setProductId(int productId) {
            addNameValuePair("product", productId);
            return self();
        }

        /**
         * Limit result set to resources with a specific slug.
         * @param slug
         * @return
         */
        public T setSlug(String slug) {
            addNameValuePair("slug", slug);
            return self();
        }

        public ProductCategoryResponse getResponse(){

            return new ProductCategoryResponse(
                new WooCommerce().search(
                    getEndPoint(),
                    build(),
                    new TypeReference<List<ProductCategory>>(){}
                )
            );

        }

    }
}
