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
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductTag;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;

import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductShippingClass;

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

    public static class Creator<T extends Creator<T>> extends CoreCreator<ProductShippingClass, T>{

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

        private ProductShippingClassBuilder build(){
            return new ProductShippingClassBuilder(this);
        }

        public Created<ProductShippingClass> getResponse(){

            if (name == null || name.isEmpty()){
                return new Created<>(new ApiResponseResult<>(false, 0, "Name is Mandatory"));
            }else {
                ProductShippingClassBuilder create = build();
                return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<ProductShippingClass>() {});
                //make the call
                /*return new Created<>(
                    new Rest<ProductShippingClass>().create(create.endPoint(), create.toJson())
                );*/
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
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<ProductShippingClass>() {});
                /*return new Updated<>(
                    new Rest<ProductShippingClass>().update(create.endPoint(), create.toJson())
                );*/
            }else{
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<ProductShippingClass>{

        public Reader(int shippingClassId){
            super(shippingClassId);
        }

        public Read<ProductShippingClass> getResponse(){
            return super.getResponse(PRODUCTS_SHIPPING_CLASSES, new TypeReference<ProductShippingClass>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<ProductShippingClass>{

        public Deleter(int shippingClassId, boolean force){
            super(shippingClassId, force);
        }

        protected ProductShippingClassBuilder build(){
            return new ProductShippingClassBuilder(this);
        }

        public Deleted<ProductShippingClass> getResponse(){
            return super.getResponse(PRODUCTS_SHIPPING_CLASSES, new TypeReference<ProductShippingClass>() {});
        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<ProductShippingClass, T>{

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            addCreator(create.build().productShippingClass);
            return self();
        }
        public T addUpdater(Updater<?> update){
            addUpdater(update.build().productShippingClass);
            return self();
        }
        public T addDeleter(Deleter delete){
            addDeleter(delete.build().productShippingClass);
            return self();
        }


        /*
         * these could go in CoreBatch with List<S>, etc.,
         * but then the ide pushes them down the parameter list
         * leaving here purely for end-user nicety
         **/
        public T addCreator(List<ProductShippingClass> createList){
            for (ProductShippingClass create : createList) {
                addCreator(create);
            }
            return self();
        }
        public T addUpdater(List<ProductShippingClass> updateList){
            for (ProductShippingClass update : updateList) {
                addUpdater(update);
            }
            return self();
        }
        public T addDeleter(List<ProductShippingClass> deleteList){
            for (ProductShippingClass delete : deleteList) {
                addDeleter(delete);
            }
            return self();
        }

        public T addCreator(ProductShippingClass create){
            batch.addCreate(create);
            return self();
        }
        public T addUpdater(ProductShippingClass update){
            batch.addUpdate(update);
            return self();
        }
        public T addDeleter(ProductShippingClass delete){
            batch.addDelete(delete.getId());
            return self();
        }

        public Batched<ProductShippingClass> getResponse(){

            //pre-validate
            for (int i = 0; i < batch.getCreate().size(); i++) {
                if (batch.getCreate().get(i).getName() == null ||
                    batch.getCreate().get(i).getName().isEmpty()) {
                    return super.getFailure(
                        String.format("Name is MANDATORY!, Found Create @ %s with empty name", i)
                    );
                }
                //stripping the id if is set
                if (batch.getCreate().get(i).getId() != null){
                    batch.getCreate().get(i).setId(null);
                }
            }

            for (int i = 0; i < batch.getUpdate().size(); i++){
                if (batch.getUpdate().get(i).getId() == 0){
                    return super.getFailure(
                        String.format("Id is MANDATORY!, Found Update @ %s with id = 0", i)
                    );
                }
            }

            //delete validation is in super


            return super.getResponse(PRODUCTS_SHIPPING_CLASSES, batch, new TypeReference<BatchResult<ProductShippingClass>>() {});
        }

    }

    public static class ListAll<T extends ListAll<T>> extends CoreSeek.Searcher<ProductShippingClass, T> {

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

            return super.getResponse(
                PRODUCTS_SHIPPING_CLASSES,
                build(),
                new TypeReference<List<ProductShippingClass>>() {}
            );
            /*return new Listed<>(
                new Rest<List<ProductShippingClass>>().listAll(
                    PRODUCTS_SHIPPING_CLASSES,
                    build()
                )
            );*/

        }

    }
}
