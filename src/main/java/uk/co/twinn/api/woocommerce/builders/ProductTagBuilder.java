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
import uk.co.twinn.api.woocommerce.response.core.BatchResult;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductTag;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS_TAGS;

public class ProductTagBuilder extends ApiRequest {

    protected final ProductTag productTag = new ProductTag();

    public ProductTagBuilder(){}

    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ProductTagBuilder(Creator<?> creator){

        productTag.setName(creator.name);
        productTag.setSlug(creator.slug);
        productTag.setDescription(creator.description);

    }

    private ProductTagBuilder(Updater<?> updater){

        this((ProductTagBuilder.Creator<?>)updater);
        productTag.setId(updater.id);

    }

    private ProductTagBuilder(Deleter deleter){

        productTag.setId(deleter.id);

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

    public static class Creator<T extends Creator<T>> extends CoreCreator<ProductTag, T>{

        private String name;        //string	Product name.
        private String slug;        //string	Product slug.
        private String description;

        private Creator(){}

        public Creator(String name){
            this.name = name;
        }

        public Creator(ProductTag productTag){
            this(productTag.getName());
            this.slug = productTag.getSlug();
            this.description = productTag.getDescription();
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

        private ProductTagBuilder build(){
            return new ProductTagBuilder(this);
        }

        public Created<ProductTag> getResponse(){

            if (name == null || name.isEmpty()){
                return new Created<>(new ApiResponseResult<>(false, 0, "Name is Mandatory"));
            }else {
                ProductTagBuilder create = build();
                return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<ProductTag>() {});
                //make the call
                /*return new Created<>(
                    new Rest<ProductTag>().create(create.endPoint(), create.toJson())
                );*/
            }

        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        private final int id;

        public Updater(int productTagId){
            this.id = productTagId;
        }

        public Updater(ProductTag productTag){
            super(productTag);
            if (productTag.getId() != null) {
                this.id = productTag.getId();
            }else{
                this.id = 0;
            }
        }

        private ProductTagBuilder build(){
            return new ProductTagBuilder(this);
        }

        @Override
        public Updated<ProductTag> getResponse(){
            if (id > 0){

                ProductTagBuilder create = build();
                /*return new Updated<>(
                    new Rest<ProductTag>().update(create.endPoint(), create.toJson())
                );*/
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<ProductTag>() {});

            }else{
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ReaderCore<ProductTag>{

        public Reader(int productTagId){
            super(productTagId);
        }

        public Read<ProductTag> getResponse(){
            return super.getResponse(PRODUCTS_TAGS, new TypeReference<ProductTag>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.DeleterCore<ProductTag>{

        public Deleter(int productTagId, boolean force){
            super(productTagId, force);
        }

        protected ProductTagBuilder build(){
            return new ProductTagBuilder(this);
        }

        public Deleted<ProductTag> getResponse(){
            return super.getResponse(PRODUCTS_TAGS, new TypeReference<ProductTag>() {});
        }

    }
    //</editor-fold>

    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<ProductTag, T>{

        public Batcher(){
            super();
        }

        public T addCreator(ProductTagBuilder.Creator<?> create){
            batch.addCreate(create.build().productTag);
            return self();
        }

        public T addUpdater(ProductTagBuilder.Updater<?> update){
            batch.addUpdate(update.build().productTag);
            return self();
        }

        public T addDeleter(ProductTagBuilder.Deleter delete){
            batch.addDelete(delete.build().productTag.getId());
            return self();
        }

        public Batched<ProductTag> getResponse(){

            return super.getResponse(PRODUCTS_TAGS, batch, new TypeReference<BatchResult<ProductTag>>() {});

        }

    }

    public static class ListAll<T extends ListAll<T>> extends CoreSeek.Searcher<ProductTag, T> {

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

            return super.getResponse(
                PRODUCTS_TAGS,
                build(),
                new TypeReference<List<ProductTag>>() {}
            );
            /*return new Listed<>(
                new Rest<List<ProductTag>>().listAll(

                )
            );*/

        }

    }
}
