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
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.builders.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.*;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS;
import static uk.co.twinn.api.woocommerce.defines.EndPoints.VARIATIONS;

public class ProductVariationBuilder extends ApiRequest {

    //never appears in the variation but is key to retrieving the data.
    private int productId;

    protected final ProductVariation productVariation = new ProductVariation();

    private boolean force;

    public ProductVariationBuilder(){}

    private ProductVariationBuilder(Creator<?> creator){

        //Set the object parameters from creator;
        productId = creator.productId;

        productVariation.setStatus(creator.status);
        productVariation.setDescription(creator.description);
        productVariation.setSku(creator.sku);
        productVariation.setRegularPrice(creator.regularPrice);
        productVariation.setSalePrice(creator.salePrice);
        productVariation.setDateOnSaleFrom(creator.date_on_sale_from);
        productVariation.setDateOnSaleFromGmt(creator.date_on_sale_from_gmt);
        productVariation.setDateOnSaleTo(creator.date_on_sale_to);
        productVariation.setDateOnSaleToGmt(creator.date_on_sale_to_gmt);
        productVariation.setVirtual(creator.virtual);
        productVariation.setDownloadable(creator.downloadable);
        productVariation.setDownloads(creator.downloads);
        productVariation.setDownloadLimit(creator.download_limit);
        productVariation.setDownloadExpiry(creator.download_expiry);
        productVariation.setTaxStatus(creator.tax_status);
        productVariation.setTaxClass(creator.tax_class);
        productVariation.setManageStock(creator.manage_stock);
        productVariation.setStockQuantity(creator.stock_quantity);
        productVariation.setStockStatus(creator.stock_status);
        productVariation.setBackorders(creator.backorders);
        productVariation.setWeight(creator.weight);
        productVariation.setDimensions(creator.dimensions);
        productVariation.setShippingClass(creator.shipping_class);
        productVariation.setAttributes(creator.attributes);
        productVariation.setMenuOrder(creator.menu_order);
        productVariation.setMetaData(creator.meta_data);

    }

    private ProductVariationBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        productVariation.setVariationId(updater.variationId);

    }

    private ProductVariationBuilder(Deleter<?> deleter){

        productVariation.setVariationId(deleter.childId);
        force = deleter.force;

    }

    public String endPoint(){

        return endPoint(productId, productVariation.getVariationId() != null ? productVariation.getVariationId() : 0);

    }

    private static String endPoint(int pId, int vId){

        return PRODUCTS +
            (pId > 0
                ? ("/" + pId)
                : ""
            ) +
            "/" + VARIATIONS +
            (vId > 0
                ? "/" + vId
                : ""
            );

    }

    public String toJson(){

        try {

            // covert Java object to JSON strings
            return getObjectMapper().writeValueAsString(productVariation);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static class Creator<T extends Creator<T>> extends CoreProductsVariations.Creator<T>{

        //set up the private variables
        protected int productId; //_differentiate

        private ProductImage image;    //array	List of images. See Product - Images properties

        T self() {
            return (T) this;
        }

        public T setProductId(int productId) {
            this.productId = productId;
            return self();
        }

        protected ProductVariationBuilder build(){
            return new ProductVariationBuilder(this);
        }

        /** Returns single Created ProductVariation**/
        public Created<ProductVariation> getResponse(){
            //premliminary checks
            if (productId <= 0) {
                return new Created<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Product Id is MANDATORY!")
                );
            }else {

                ProductVariationBuilder create = build();
                //make the call
                return new Created<ProductVariation>(
                    new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductVariation>(){})
                );
            }
        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        //set up the private variables
        private final int variationId;

        public Updater(int productId, int variationId){
            this.productId = productId;
            this.variationId = variationId;
        }

        T self() {
            return (T) this;
        }

        /*public T setVariationId(int variationId) {
            this.variationId = variationId;
            return self();
        }*/

        protected ProductVariationBuilder build(){
            return new ProductVariationBuilder(this);
        }

        /**
         *  If the id is set returns a single productCategory
         *  otherwise returns list of productCategory
         */
        public Updated<ProductVariation> getResponse(){

            //preliminary checks
            if (productId <= 0 || variationId <= 0) {
                return new Updated<ProductVariation>(
                    new ApiResponseResult(
                        false,
                        0,
                        "ProductId and id are MANDATORY")
                );
            }else {
                ProductVariationBuilder create = build();
                //make the call
                return new Updated<ProductVariation>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<ProductVariation>(){})
                );
            }

        }

    }


    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReader.ChildReaderCore<T>{

        public Reader(int productId, int variationId){
            super(productId, variationId);
        }

        /*@Override
        T self() {return (T) this;}

        public T setProductId(int productId){
            super.setId(productId);
            return self();
        }

        public T setVariationId(int variationId){
            super.setChildId(variationId);
            return self();
        }*/

        public Read<ProductVariation> getResponse(){
            return (Read<ProductVariation>)super.getResponse(PRODUCTS, VARIATIONS, new TypeReference<ProductVariation>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleter.ChildDeleterCore<T>{

        public Deleter(int productId, int variationId, boolean force){
            super(productId, variationId, force);
        }

        /*@Override
        T self() {return (T) this;}

        public T setProductId(int productId){
            super.setId(productId);
            return self();
        }

        public T setVariationId(int variationId){
            super.setChildId(variationId);
            return self();
        }*/

        protected ProductVariationBuilder build(){
            return new ProductVariationBuilder(this);
        }

        public Deleted<ProductVariation> getResponse(){
            return (Deleted<ProductVariation>)super.getResponse(PRODUCTS, VARIATIONS, new TypeReference<ProductVariation>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Batcher">
    public static class Batcher<T extends Batcher<T>>  extends CoreBatch.BatchCore<T>{

        private int id;

        public Batcher(int productId){
            this.id = productId;
        }

        /*public T setProductId(int productId){
            this.id = productId;
            return self();
        }*/

        public Batcher(){
            super();
        }

        T self() {
            return (T) this;
        }

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().productVariation);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().productVariation);
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().productVariation);
            return self();
        }

        /** Returns list of amended Orders **/
        public Batched<ProductVariation> getResponse(){

            return (Batched<ProductVariation>) super.getResponse(PRODUCTS, id, VARIATIONS, batch, new TypeReference<Batch<ProductVariation>>(){});

        }

    }
    //</editor-fold>

    //or Seek.SearchCore<T>
    public static class ListAll<T extends ListAll<T>> extends Seek.StockSearcher<T>{

        private int id;

        public T setProductId(int productId){
            this.id = productId;
            return self();
        }

        T self() {
            return (T) this;
        }

        public Listed<ProductVariation> getResponse(){

            if (id <= 0) {
                return new Listed<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "Product Id is MANDATORY!")
                );
            }else {
                return new Listed<>(
                    new Rest().listAll(
                        endPoint(id, 0), //endPoint, SET endPoint
                        build(),
                        new TypeReference<List<ProductVariation>>(){}
                    )
                );
            }

        }

    }
}
