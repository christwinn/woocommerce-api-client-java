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

import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductImage;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductVariation;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS;
import static uk.co.twinn.api.woocommerce.defines.EndPoints.VARIATIONS;

public class ProductVariationBuilder extends ApiRequest {

    //never appears in the variation but is key to retrieving the data.
    private int productId;

    protected final ProductVariation productVariation = new ProductVariation();

    private ProductVariationBuilder(){}

    private ProductVariationBuilder(Creator<?> creator){

        //Set the object parameters from creator;
        productId = creator.productId;

        productVariation.setStatus(creator.status);
        productVariation.setDescription(creator.description);
        productVariation.setSku(creator.sku);
        productVariation.setRegularPrice(creator.regularPrice);
        productVariation.setSalePrice(creator.salePrice);
        productVariation.setDateOnSaleFrom(creator.dateOnSaleFrom);
        productVariation.setDateOnSaleFromGmt(creator.dateOnSaleFromGmt);
        productVariation.setDateOnSaleTo(creator.dateOnSaleTo);
        productVariation.setDateOnSaleToGmt(creator.dateOnSaleToGmt);
        productVariation.setVirtual(creator.virtual);
        productVariation.setDownloadable(creator.downloadable);
        productVariation.setDownloads(creator.downloads);
        productVariation.setDownloadLimit(creator.downloadLimit);
        productVariation.setDownloadExpiry(creator.downloadExpiry);
        productVariation.setTaxStatus(creator.taxStatus);
        productVariation.setTaxClass(creator.taxClass);
        productVariation.setManageStock(creator.manageStock);
        productVariation.setStockQuantity(creator.stockQuantity);
        productVariation.setStockStatus(creator.stockStatus);
        productVariation.setBackorders(creator.backorders);
        productVariation.setWeight(creator.weight);
        productVariation.setDimensions(creator.dimensions);
        productVariation.setShippingClass(creator.shippingClass);
        productVariation.setAttributes(creator.attributes);
        productVariation.setMenuOrder(creator.menuOrder);
        productVariation.setMetaData(creator.metaData);

    }

    private ProductVariationBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        productVariation.setVariationId(updater.variationId);

    }

    private ProductVariationBuilder(Deleter deleter){

        productVariation.setVariationId(deleter.childId);

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

    public static class Creator<T extends Creator<T>> extends CoreProductsVariations.Creator<ProductVariation, T>{

        //set up the private variables
        protected final int productId; //_differentiate

        private ProductImage image;    //array	List of images. See Product - Images properties

        public Creator(int productId){
            this.productId = productId;
        }

        public Creator(int productId, ProductVariation productVariation){
            super(productVariation);
            this.productId = productId;
        }

        protected ProductVariationBuilder build(){
            return new ProductVariationBuilder(this);
        }

        /** Returns single Created ProductVariation**/
        public Created<ProductVariation> getResponse(){
            //premliminary checks
            if (productId <= 0) {
                return new Created<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Product Id is MANDATORY!")
                );
            }else {

                ProductVariationBuilder create = build();
                return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<ProductVariation>() {});
                //make the call
                /*return new Created<>(
                    new Rest<ProductVariation>().create(create.endPoint(), create.toJson())
                );*/
            }
        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T> {

        //set up the private variables
        private final int variationId;

        public Updater(int productId, int variationId){
            super(productId);
            this.variationId = variationId;
        }

        public Updater(int productId, ProductVariation productVariation){
            super(productId, productVariation);
            if (productVariation.getVariationId() != null) {
                this.variationId = productVariation.getVariationId();
            }else {
                this.variationId = 0;
            }
        }

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
                return new Updated<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "ProductId and id are MANDATORY")
                );
            }else {
                ProductVariationBuilder create = build();
                //make the call
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<ProductVariation>() {});
                /*return new Updated<>(
                    new Rest<ProductVariation>().update(create.endPoint(), create.toJson())
                );*/
            }

        }

    }


    //<editor-fold name="Reader">
    public static class Reader extends CoreReader.ChildReaderCore<ProductVariation> {

        public Reader(int productId, int variationId){
            super(productId, variationId);
        }

        public Read<ProductVariation> getResponse(){
            return super.getResponse(PRODUCTS, VARIATIONS, new TypeReference<ProductVariation>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter extends CoreDeleter.ChildDeleterCore<ProductVariation>{

        public Deleter(int productId, int variationId, boolean force){
            super(productId, variationId, force);
        }

        protected ProductVariationBuilder build(){
            return new ProductVariationBuilder(this);
        }

        public Deleted<ProductVariation> getResponse(){
            return super.getResponse(PRODUCTS, VARIATIONS, new TypeReference<ProductVariation>() {});
        }

    }
    //</editor-fold>

    //<editor-fold name="Batcher">
    public static class Batcher<T extends Batcher<T>>  extends CoreBatch.BatchCore<ProductVariation, T>{

        private int id;

        public Batcher(int productId){
            this.id = productId;
        }

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            return addCreator(create.build().productVariation);
        }

        public T addCreator(List<ProductVariation> create){
            return super.addCreate(create);
        }

        public T addCreator(ProductVariation create){
            return super.addCreate(create);
        }

        public T addUpdater(Updater<?> update){
            return addUpdater(update.build().productVariation);
        }

        public T addUpdater(List<ProductVariation> updateList){
            return super.addUpdate(updateList);
        }

        public T addUpdater(ProductVariation update){
            return super.addUpdate(update);
        }

        public T addDeleter(Deleter delete){
            return addDeleter(delete.build().productVariation);
        }

        public T addDeleter(List<ProductVariation> deleteList){
            for (ProductVariation delete : deleteList) {
                addDeleter(delete);
            }
            return self();
        }

        public T addDeleter(ProductVariation delete){
            return super.addDelete(delete.getVariationId());
        }

        /** Returns list of amended **/
        public Batched<ProductVariation> getResponse(){

            if (id <= 0){
                return super.getFailure("Product Id is Mandatory");
            }else {
                //pre-validate
                /* Nothing is MANDATORY!!*/
                for (ProductVariation product : batch.getCreate()) {
                    if (product.getVariationId() != null) {
                        product.setVariationId(null);
                    }
                }
                for (int i = 0; i < batch.getUpdate().size(); i++) {
                    if (batch.getUpdate().get(i).getVariationId() == null || batch.getUpdate().get(i).getVariationId() == 0) {
                        return super.getFailure(
                            String.format("Id is MANDATORY!, Found Update @ %s with id = 0", i)
                        );
                    }
                }

                //delete validation is in super
                return super.getResponse(
                    PRODUCTS,
                    id,
                    VARIATIONS,
                    batch,
                    new TypeReference<BatchResult<ProductVariation>>() {}
                );

            }

        }

    }
    //</editor-fold>

    //or Seek.SearchCore<T>
    public static class ListAll<T extends ListAll<T>> extends CoreSeek.StockSearcher<ProductVariation, T>{

        private int id;

        public T setProductId(int productId){
            this.id = productId;
            return self();
        }

        public Listed<ProductVariation> getResponse(){

            if (id <= 0) {
                return new Listed<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "Product Id is MANDATORY!")
                );
            }else {
                return super.getResponse(
                    endPoint(id, 0), //endPoint, SET endPoint
                    build(),
                    new TypeReference<List<ProductVariation>>() {}
                );

                /*return new Listed<>(
                    new Rest<List<ProductVariation>>().listAll(
                        endPoint(id, 0), //endPoint, SET endPoint
                        build()
                    )
                );*/
            }

        }

    }
}
