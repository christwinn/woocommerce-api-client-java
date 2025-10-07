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
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductImage;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.ProductBrand;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;

import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS_BRANDS;

public class ProductBrandBuilder extends ApiRequest {

    protected final ProductBrand productBrand = new ProductBrand();

    public ProductBrandBuilder(){}

    //<editor-fold defaultstate="collapsed" desc="Private Constructors">
    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ProductBrandBuilder(Creator<?> creator){

        productBrand.setName(creator.name);
        productBrand.setSlug(creator.slug);
        productBrand.setParent(creator.parentId);
        productBrand.setDescription(creator.description);
        productBrand.setDisplay(creator.display);
        productBrand.setImage(creator.image);
        productBrand.setMenuOrder(creator.menuOrder);

    }

    private ProductBrandBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        productBrand.setId(updater.id);

    }

    private ProductBrandBuilder(Deleter deleter){

        productBrand.setId(deleter.id);

    }
    //</editor-fold>

    public String toJson(){

        try {

            return getObjectMapper().writeValueAsString(productBrand);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private String endPoint(){

        return PRODUCTS_BRANDS +
            (productBrand.getId() != null && productBrand.getId() != 0
                ? ("/" + productBrand.getId())
                : ""
            );

    }

    //<editor-fold defaultstate="collapsed" desc="Creator Builder">
    public static class Creator<T extends Creator<T>> extends CoreCreator<ProductBrand, T> {

        protected String name;        //string	Product name.
        private String slug;        //string	Product slug.
        private Integer parentId;
        private String description;
        private String display;
        private ProductImage image;
        private Integer menuOrder;

        public Creator(){}

        public Creator(ProductBrand productBrand){

            name = productBrand.getName();
            slug = productBrand.getSlug();
            parentId = productBrand.getParent();

            description = productBrand.getDescription();
            display = productBrand.getDisplay();
            image = productBrand.getImage();
            menuOrder = productBrand.getMenuOrder();

        }

        public Creator(String name){
            this.name = name;
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

        public T setParent(Integer parentId) {
            this.parentId = parentId;
            return self();
        }

        public T setDescription(String description) {
            this.description = description;
            return self();
        }

        public T setDisplay(String display) {
            this.display = display;
            return self();
        }

        public T setMenuOrder(Integer menuOrder) {
            this.menuOrder = menuOrder;
            return self();
        }

        public T setImage(ProductImage image){
            this.image = image;
            return self();
        }

        public T setImage(String imageUrl){
            this.image = new ProductImage().src(imageUrl);
            return self();
        }

        private ProductBrandBuilder build(){
            return new ProductBrandBuilder(this);
        }

        public Created<ProductBrand> getResponse(){

            //nothing is defined as mandatory, but we may want to build in some pre-validation
            ProductBrandBuilder create = build();

            return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<ProductBrand>() {});

            //make the call
            /*return new Created<>(
                new Rest<Product>().create(create.endPoint(), create.toJson())
            );*/

        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Update Builder">
    public static class Updater<T extends Updater<T>> extends Creator<T> {

        public Updater(int productBrandId){
            this.id = productBrandId;
        }

        public Updater(ProductBrand productBrand){
            super(productBrand);
            if (productBrand.getId() != null) {
                this.id = productBrand.getId();
            }else{
                this.id = 0;
            }
        }

        /**
         *
         * @param name Product Name
         * @return T
         */
        public T setName(String name) {
            this.name = name;
            return self();
        }

        private final int id;

        private ProductBrandBuilder build(){
            return new ProductBrandBuilder(this);
        }

        @Override
        public Updated<ProductBrand> getResponse(){
            if (id > 0){

                ProductBrandBuilder create = build();
                /*return new Updated<>(
                    new Rest<Product>().update(create.endPoint(), create.toJson())
                );*/
                return super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<ProductBrand>() {});

            }else{
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }

        }
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Reader Builder">
    public static class Reader extends CoreReader.ReaderCore<ProductBrand>{

        public Reader(int productBrandId){
            super(productBrandId);
        }

        public Read<ProductBrand> getResponse(){
            return super.getResponse(PRODUCTS_BRANDS, new TypeReference<ProductBrand>() {});
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Deleter Builder">
    public static class Deleter extends CoreDeleter.DeleterCore<ProductBrand>{

        public Deleter(int productBrandId, boolean force){
            super(productBrandId, force);
        }

        protected ProductBrandBuilder build(){
            return new ProductBrandBuilder(this);
        }

        public Deleted<ProductBrand> getResponse(){
            return super.getResponse(PRODUCTS_BRANDS, new TypeReference<ProductBrand>() {});

        }

    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Batch Builder">
    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<ProductBrand, T>{

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            return addCreator(create.build().productBrand);
        }

        public T addCreator(List<ProductBrand> create){
            return super.addCreate(create);
        }

        public T addCreator(ProductBrand create){
            return super.addCreate(create);
        }

        public T addUpdater(Updater<?> update){
            return addUpdater(update.build().productBrand);
        }

        public T addUpdater(List<ProductBrand> updateList){
            return super.addUpdate(updateList);
        }

        public T addUpdater(ProductBrand update){
            return super.addUpdate(update);
        }

        public T addDeleter(Deleter delete){
            return addDeleter(delete.build().productBrand);
        }

        public T addDeleter(List<ProductBrand> deleteList){
            for (ProductBrand delete : deleteList) {
                addDeleter(delete);
            }
            return self();
        }

        public T addDeleter(ProductBrand delete){
            return super.addDelete(delete.getId());
        }

        public Batched<ProductBrand> getResponse(){

            for (int i = 0; i < batch.getUpdate().size(); i++){

                if (batch.getCreate().get(i).getId() != null){
                    batch.getCreate().get(i).setId(null);
                }
                if (batch.getCreate().get(i).getName() == null || batch.getCreate().get(i).getName().isEmpty()){
                    return super.getFailure(
                        String.format("Name is MANDATORY!, Found Create @ %s with name = \"\"", i)
                    );
                }
            }

            for (int i = 0; i < batch.getUpdate().size(); i++){
                if (batch.getUpdate().get(i).getId() == null || batch.getUpdate().get(i).getId() == 0){
                    return super.getFailure(
                        String.format("Id is MANDATORY!, Found Update @ %s with id = 0", i)
                    );
                }
            }

            //delete validation is in super
            /**
             * Mileage may vary
             * Supposedly we can batch 100 at a time.
             * I have been finding this leads to an internal server error (500)
             * Shrinking the batch to a smaller number works.
             *
             * @return Batched<Product>
             */

            return super.getResponse(PRODUCTS_BRANDS, batch, new TypeReference<BatchResult<ProductBrand>>() {});

        }

    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Listing Builder">
    /**
     *
     * Searches the Products
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products">...</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ListAll<T>> extends CoreSeek.Searcher<ProductBrand, T> {

        public T setParent(Integer parentId) {
            addNameValuePair("parent", parentId);
            return self();
        }

        public T setProduct(Integer productId) {
            addNameValuePair("product", productId);
            return self();
        }

        public T setSlug(String slug) {
            addNameValuePair("slug", slug);
            return self();
        }

        public Listed<ProductBrand> getResponse(){

            return super.getResponse(
                PRODUCTS_BRANDS,
                build(),
                new TypeReference<List<ProductBrand>>() {}
            );

        }

    }
    //</editor-fold>
}
