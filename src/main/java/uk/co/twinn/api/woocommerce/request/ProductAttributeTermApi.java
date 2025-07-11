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
import uk.co.twinn.pl_wtx_woocommerce.model.ProductAttributeTerm;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.*;

public class ProductAttributeTermApi extends ApiRequest {

    protected final ProductAttributeTerm productAttribute = new ProductAttributeTerm();

    private int attributeId;
    private boolean force;

    public ProductAttributeTermApi(){}

    private ProductAttributeTermApi(Creator<?> creator){

        attributeId = creator.attributeId;
        productAttribute.setName(creator.name);
        productAttribute.setSlug(creator.slug);
        productAttribute.setDescription(creator.description);
        productAttribute.setMenuOrder(creator.menuOrder);

    }

    private ProductAttributeTermApi(Updater<?> updater){

        this((Creator<?>)updater);
        productAttribute.setId(updater.termsId);
        //set the specific ids required to update an object


    }

    private ProductAttributeTermApi(Deleter<?> deleter){

        attributeId = deleter.id;
        productAttribute.setId(deleter.childId);
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
            ) + "/" + TERMS;

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

        protected int attributeId;

        //set up the private variables
        protected String name; //	string	Attribute name.mandatory
        protected String slug; //	string	An alphanumeric identifier for the resource unique to its type.
        protected String description; //	string	Type of attribute. By default only select is supported.
        protected Integer menuOrder; //	string	Default sort order. Options: menu_order, name, name_num and id. Default is menu_order


        T self() {
            return (T) this;
        }

        /**
         *
         * @param attributeId Create a new product attribute term for Attribute.
         * @return T
         */
        public T setAttributeId(int attributeId){
            this.attributeId = attributeId;
            return self();
        }

        /**
         *
         * @param name Term name mandatory
         * @return T
         */
        public T setName(String name) {
            this.name = name;
            return self();
        }

        /**
         *
         * @param slug An alphanumeric identifier for the resource unique to its type.
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

        /**
         *
         * @param menuOrder Menu order, used to custom sort the resource.
         * @return T
         */
        public T setMenuOrder(int menuOrder) {
            this.menuOrder = menuOrder;
            return self();
        }

        protected ProductAttributeTermApi build(){
            return new ProductAttributeTermApi(this);
        }

        /** Returns single Created ProductAttribute**/
        public Created<ProductAttributeTerm> getResponse(){
            //premliminary checks
            if (attributeId <= 0 || (name == null && name.isEmpty())) {
                return new Created<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "AttributeId and Name is MANDATORY!")
                );
            }else {

                ProductAttributeTermApi create = build();
                //make the call
                return new Created<>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductAttributeTerm>(){})
                );
            }
        }


    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        //set up the private variables

        private int termsId;

        T self() {
            return (T) this;
        }

        public T setTermsId(int termsId){
            this.termsId = termsId;
            return self();
        }

        protected ProductAttributeTermApi build(){
            return new ProductAttributeTermApi(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Updated<ProductAttributeTerm> getResponse(){

            //preliminary checks
            if (attributeId <= 0 || termsId <= 0) {
                return new Updated<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Attribute Id and Term Id are MANDATORY")
                );
            }else {
                ProductAttributeTermApi create = build();
                //make the call
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ProductAttributeTerm>(){})
                );
            }

        }

    }


    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ChildReaderCore<T>{

        @Override
        T self() {return (T) this;}

        public T setAttributeId(int attributeId){
            super.setId(attributeId);
            return self();
        }

        public T setTermsId(int termsId){
            super.setChildId(termsId);
            return self();
        }

        public Read<ProductAttributeTerm> getResponse(){
            return (Read<ProductAttributeTerm>)super.getResponse(PRODUCTS_ATTRIBUTES, TERMS, new TypeReference<ProductAttributeTerm>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.ChildDeleterCore<T>{

        @Override
        T self() {return (T) this;}

        public T setAttributeId(int attributeId){
            super.setId(attributeId);
            return self();
        }

        public T setTermsId(int termsId){
            super.setChildId(termsId);
            return self();
        }

        protected ProductAttributeTermApi build(){
            return new ProductAttributeTermApi(this);
        }

        public Deleted<ProductAttributeTerm> getResponse(){
            return (Deleted<ProductAttributeTerm>)super.getResponse(PRODUCTS_ATTRIBUTES, TERMS, new TypeReference<ProductAttributeTerm>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Batcher">
    public static class Batcher<T extends Batcher<T>> extends CoreBatchRequest.BatchCore<T>{

        private int attributeId;

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T setAttributeId(int attributeId){
            this.attributeId = attributeId;
            return self();
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
        public Batched<ProductAttributeTerm> getResponse(){

            if (attributeId <= 0){
                return new Batched<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Attribute Id is MANDATORY")
                );
            }else {
                return (Batched<ProductAttributeTerm>) super.getResponse(
                    endPoint(attributeId),
                    batch,
                    new TypeReference<Batch<ProductAttributeTerm>>() {}
                );
            }

        }

    }
    //</editor-fold>

    //or Seek.SearchCore<T>
    public static class ListAll<T extends ListAll<T>> extends Seek.Searcher<T>{
        private int attributeId;

        T self() {
            return (T) this;
        }

        public T setAttributeId(int attributeId){
            this.attributeId = attributeId;
            return self();
        }

        /**
         *
         *
         * @param hideEmpty Whether to hide resources not assigned to any products.
         *                  Default is false.
         * @return T
         */
        public T setHideEmpty(boolean hideEmpty) {
            addNameValuePair("hide_empty", hideEmpty);
            return self();
        }

        /**
         *
         *
         * @param parent Limit result set to resources assigned to a specific parent.
         * @return T
         */
        public T setParent(int parent) {
            addNameValuePair("parent", parent);
            return self();
        }

        /**
         *
         *
         * @param product Limit result set to resources assigned to a specific product.
         * @return T
         */
        public T setProduct(int product) {
            addNameValuePair("product", product);
            return self();
        }

        /**
         *
         *
         * @param slug Limit result set to resources with a specific slug.
         * @return T
         */
        public T setSlug(String slug) {
            addNameValuePair("slug", slug);
            return self();
        }

        public Listed<ProductAttributeTerm> getResponse(){

            if (attributeId <= 0){
                return new Listed<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Attribute Id is MANDATORY")
                );
            }else {
                return new Listed<>(
                    new Rest().listAll(
                        endPoint(attributeId), //endPoint, SET endPoint
                        build(),
                        new TypeReference<List<ProductAttributeTerm>>() {
                        }
                    )
                );
            }

        }

    }
}
