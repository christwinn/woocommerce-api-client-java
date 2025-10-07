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
import uk.co.twinn.api.woocommerce.response.core.BatchResult;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductCategory;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductImage;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;


import java.util.ArrayList;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCT_CATEGORIES;

public class ProductCategoryBuilder extends ApiRequest {

    protected final ProductCategory category = new ProductCategory();

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

        category.setMenuOrder(creator.menuOrder);

    }

    private ProductCategoryBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        category.setId(updater.id);

    }

    private ProductCategoryBuilder(Deleter deleter){

        category.setId(deleter.id);

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

    public static class Creator<T extends Creator<T>> extends CoreCreator<ProductCategory, T>{

        protected String name;
        private String description;
        private Integer parent;

        private String slug;

        private ProductCategory.DisplayEnum display;
        private String image;
        private Integer menuOrder;

        private Creator(){

        }
        public Creator(String name){
            this.name = name;
        }

        public Creator(ProductCategory productCategory){
            this(productCategory.getName());
            slug = productCategory.getSlug();
            parent = productCategory.getParent();
            description = productCategory.getDescription();
            display = productCategory.getDisplay();

            if (productCategory.getImage() != null &&
                productCategory.getImage().getSrc() != null &&
                !productCategory.getImage().getSrc().isEmpty()){

                image = productCategory.getImage().getSrc();

            }

            menuOrder = productCategory.getMenuOrder();

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
        public T setMenuOrder(Integer menuOrder){
            this.menuOrder = menuOrder;
            return self();
        }

        protected ProductCategoryBuilder build(){
            return new ProductCategoryBuilder(this);
        }

        public Created<ProductCategory> getResponse(){

            ProductCategoryBuilder create = build();
            return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<ProductCategory>() {});
            //make the call
            /*return new Created<>(
                new Rest<ProductCategory>().create(create.endPoint(), create.toJson())
            );*/

        }
    }

    public static class Updater<T extends Updater<T>> extends Creator<T>{

        private final int id;

        public Updater(int productCategoryId){
            this.id = productCategoryId;
        }

        public Updater(ProductCategory productCategory){
            super(productCategory);
            if (productCategory.getId() != null) {
                this.id = productCategory.getId();
            }else{
                this.id = 0;
            }
        }

        public T setName(String name){
            this.name = name;
            return self();
        }

        @Override
        protected ProductCategoryBuilder build(){
            return new ProductCategoryBuilder(this);
        }

        /** Returns single Updated ProductCategory**/
        @Override
        public Updated<ProductCategory> getResponse(){
            if (id > 0) {
                ProductCategoryBuilder create = build();
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<ProductCategory>() {});
                //make the call
                /*return new Updated<>(
                    new Rest<ProductCategory>().update(create.endPoint(), create.toJson())
                );*/

            }else {
                return new Updated<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Category Id is MANDATORY!")
                );
            }

        }

    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<ProductCategory>{

        public Reader(int productCategoryId){
            super(productCategoryId);
        }

        public Read<ProductCategory> getResponse(){
            return super.getResponse(PRODUCT_CATEGORIES, new TypeReference<ProductCategory>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<ProductCategory>{

        public Deleter(int productCategoryId, boolean force){
            super(productCategoryId, force);
        }

        protected ProductCategoryBuilder build(){
            return new ProductCategoryBuilder(this);
        }

        public Deleted<ProductCategory> getResponse(){
            return super.getResponse(PRODUCT_CATEGORIES, new TypeReference<ProductCategory>() {});

        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<ProductCategory, T>{

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            return addCreator(create.build().category);
        }

        public T addCreator(List<ProductCategory> create){
            return super.addCreate(create);
        }

        public T addCreator(ProductCategory create){
            return super.addCreate(create);
        }

        public T addUpdater(Updater<?> update){
            return addUpdater(update.build().category);
        }

        public T addUpdater(List<ProductCategory> updateList){
            return super.addUpdate(updateList);
        }

        public T addUpdater(ProductCategory update){
            return super.addUpdate(update);
        }

        public T addDeleter(Deleter delete){
            return addDeleter(delete.build().category);
        }

        public T addDeleter(List<ProductCategory> deleteList){
            for (ProductCategory delete : deleteList) {
                addDeleter(delete);
            }
            return self();
        }

        public T addDeleter(ProductCategory delete){
            return super.addDelete(delete.getId());
        }

        /** Returns list of amended ProductCategories **/
        public Batched<ProductCategory> getResponse(){

            //pre-validate
            for (int i = 0; i < batch.getCreate().size(); i++) {
                if (batch.getCreate().get(i).getName() == null ||
                    batch.getCreate().get(i).getName().isEmpty()) {
                    return super.getFailure(
                        String.format("Name is MANDATORY!, Found Create @ %s with empty name", i)
                    );
                }
                //stripping the id if is set
                if (batch.getCreate().get(i).getId() != null) {
                    batch.getCreate().get(i).setId(null);
                }
            }

            for (int i = 0; i < batch.getUpdate().size(); i++) {
                if (batch.getUpdate().get(i).getId() == null || batch.getUpdate().get(i).getId() == 0) {
                    return super.getFailure(
                        String.format("Id is MANDATORY!, Found Update @ %s with id = 0", i)
                    );
                }
            }

            //delete validation is in super
            return super.getResponse(PRODUCT_CATEGORIES, batch, new TypeReference<BatchResult<ProductCategory>>() {});

        }

    }

    /**
     *
     *
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products">Searches the ProductCategories</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ListAll<T>> extends CoreSeek.Searcher<ProductCategory, T> {

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

            return super.getResponse(
                PRODUCT_CATEGORIES,
                build(),
                new TypeReference<List<ProductCategory>>() {}
            );

            /*return new Listed<>(
                new Rest<List<ProductCategory>>().listAll(

                )
            );*/

        }

    }

    public static class ListChildCategories<T extends ListAll<T>> extends CoreSeek.SearchBase<ProductCategory, T> {

        private int parentId;
        private static final int COUNT = 50;
        private static final int FAILSAFE = 5;
        private int counter = 1;

        public ListChildCategories(int parentId){
            super();
            this.parentId = parentId;
        }

        public Listed<ProductCategory> getResponse(){

            //if in constructor, possible 'this' leakage
            addNameValuePair("parent", parentId);
            addNameValuePair("per_page", COUNT);
            addNameValuePair("page", counter);

            List<ProductCategory> cats = new ArrayList<>();
            Listed<ProductCategory> search;

            counter = 1;

            while (counter < FAILSAFE){

                search = doSearch();
                counter++;

                if (search.isSuccess()){
                    if (search.getResult().size() < COUNT){ //less than requested assume we have bottomed out
                        if (!cats.isEmpty()){
                            cats.addAll(search.getResult());
                            return new Listed<>(cats);
                        }else{
                            return search;
                        }
                    }else if (counter < FAILSAFE){
                        addNameValuePair("page", counter);//nudge the page
                        cats.addAll(search.getResult());
                        //continue loop
                    }else{
                        return new Listed<>(new ApiResponseResult<>(false,0,
                            "Failsafe triggered. called " + COUNT + ", " + FAILSAFE + " times. Do not want a perpertual loop"));
                    }
                }else{
                    return search;
                }

            }

            return new Listed<>(new ApiResponseResult<>(false,0,
                "Something has gone wrong we should have returned a result before now, " +
                    "Could be Failsafe triggered. Hunted for " + COUNT + ", " + FAILSAFE + " times. " +
                    "Do not want a perpertual loop")
            );
        }

        private Listed<ProductCategory> doSearch(){
            return super.getResponse(
                PRODUCT_CATEGORIES,
                build(),
                new TypeReference<List<ProductCategory>>() {}
            );
        }

    }
}
