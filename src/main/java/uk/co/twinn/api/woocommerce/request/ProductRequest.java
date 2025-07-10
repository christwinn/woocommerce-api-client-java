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
import uk.co.twinn.api.woocommerce.request.core.Seek;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.request.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;
import uk.co.twinn.pl_wtx_woocommerce.model.*;

import java.time.LocalDate;
import java.util.List;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS;

public class ProductRequest extends ApiRequest {

    protected final uk.co.twinn.pl_wtx_woocommerce.model.Product product = new uk.co.twinn.pl_wtx_woocommerce.model.Product();

    private Batch batch;

    private boolean isBatch;
    private boolean force;
    private boolean duplicate;

    public ProductRequest(){}

    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ProductRequest(Creator<?> creator){

        product.setName(creator.name);
        product.setSlug(creator.slug);
        product.setType(creator.type);
        product.setStatus(creator.status);
        product.setFeatured(creator.featured);
        product.setCatalogVisibility(creator.catalog_visibility);
        product.setDescription(creator.description);
        product.setShortDescription(creator.short_description);
        product.setSku(creator.sku);
        product.setRegularPrice(creator.regularPrice.toString());
        product.setSalePrice(creator.salePrice.toString());
        product.setDateOnSaleFrom(creator.date_on_sale_from);
        product.setDateOnSaleFromGmt(creator.date_on_sale_from_gmt);
        product.setDateOnSaleTo(creator.date_on_sale_to);
        product.setDateOnSaleToGmt(creator.date_on_sale_to_gmt);
        product.setVirtual(creator.virtual);
        product.setDownloadable(creator.downloadable);
        product.setDownloads(creator.downloads);
        product.setDownloadLimit(creator.download_limit);
        product.setDownloadExpiry(creator.download_expiry);
        product.setExternalUrl(creator.external_url);
        product.setButtonText(creator.button_text);
        product.setTaxStatus(creator.tax_status);
        product.setTaxClass(creator.tax_class);
        product.setManageStock(creator.manage_stock);
        product.setStockQuantity(creator.stock_quantity);
        product.setStockStatus(creator.stock_status);
        product.setBackorders(creator.backorders);
        product.setSoldIndividually(creator.sold_individually);
        product.setWeight(creator.weight);
        product.setDimensions(creator.dimensions);
        product.setShippingClass(creator.shipping_class);
        product.setReviewsAllowed(creator.reviews_allowed);
        product.setUpsellIds(creator.upsell_ids);
        product.setCrossSellIds(creator.cross_sell_ids);
        product.setParentId(creator.parent_id);
        product.setPurchaseNote(creator.purchase_note);
        product.setCategories(creator.categories);
        product.setTags(creator.tags);
        product.setImages(creator.images);
        product.setAttributes(creator.attributes);
        product.setDefaultAttributes(creator.default_attributes);
        product.setGroupedProducts(creator.grouped_products);
        product.setMenuOrder(creator.menu_order);
        product.setMetaData(creator.meta_data);

    }

    private ProductRequest(Updater<?> updater){

        this((Creator<?>)updater);
        product.setId(updater.id);

    }

    private ProductRequest(Deleter<?> deleter){

        product.setId(deleter.id);
        isBatch = false;
        duplicate = false;
        force = deleter.force;

    }

    private ProductRequest(Duplicator<?> duplicator){

        product.setId(duplicator.id);
        isBatch = false;
        force = false;
        duplicate = true;

    }

    public uk.co.twinn.pl_wtx_woocommerce.model.Product getProduct(){
        return product;
    }

    public String toJson(){

        try {

            if (isBatch){
                return getObjectMapper().writeValueAsString(batch);
            }else{
                // covert Java object to JSON strings
                return getObjectMapper().writeValueAsString(product);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public String endPoint(){

        return PRODUCTS +
            (product.getId() != null && product.getId() != 0
                ? ("/" + product.getId())
                : ""
            ) +
            (duplicate ? "/duplicate"  : "") +
            (isBatch ? "/batch" : "") +
            (force ? "?force=true" : "");

    }

    public static class Creator<T extends Creator<?>> extends CoreProductRequest.Creator<T> {

        private String name;        //string	Product name.
        private String slug;        //string	Product slug.
        private String type;    //string	Product type. Options: simple, grouped, external and variable. Default is simple.
        private Boolean featured;    //boolean	Featured product. Default is false.
        private String catalog_visibility;    //string	Catalog visibility. Options: visible, catalog, search and hidden. Default is visible.
        private String short_description;    //string	Product short description.
        private String external_url;    //string	Product external URL. Only for external products.
        private String button_text;    //string	Product external button text. Only for external products.
        private Boolean sold_individually;    //boolean	Allow one item to be bought in a single order. Default is false.
        private Boolean reviews_allowed;    //boolean	Allow reviews. Default is true.
        private List<Integer> upsell_ids;    //array	List of up-sell products IDs.
        private List<Integer> cross_sell_ids;    //array	List of cross-sell products IDs.
        private Integer parent_id;    //integer	Product parent ID.
        private String purchase_note;    //string	Optional note to send the customer after purchase.
        private List<ProductCategoriesItem> categories;    //array	List of categories. See Product - Categories properties
        private List<ProductTag> tags;    //array	List of tags. See Product - Tags properties
        private List<ProductImage> images;    //array	List of images. See Product - Images properties
        private List<ProductAttribute> default_attributes;    //array	Defaults variation attributes. See Product - Default attributes properties
        //private int[] variations;    //array	List of variations IDs.read-only
        private List<Integer> grouped_products;    //array	List of grouped products ID.

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
         * @param type Product type.
         *             Options: simple, grouped, external and variable.
         *             Default is simple.
         * @return T
         */
        public T setType(String type) {
            this.type = type;
            return self();
        }
        /**
         *
         * @param type Product type.
         *             Options: simple, grouped, external and variable.
         *             Default is simple.
         * @return T
         */
        public T setType(uk.co.twinn.pl_wtx_woocommerce.model.Product.TypeEnum type) {
            this.type = type.getValue();
            return self();
        }



        /**
         *
         * @param status Product status (post status).
         *               Options: draft, pending, private and publish.
         *               Default is publish.
         * @return T
         */
        public T setStatus(uk.co.twinn.pl_wtx_woocommerce.model.Product.StatusEnum status) {
            this.status = status.getValue();
            return self();
        }

        /**
         *
         * @param featured Featured product. Default is false.
         * @return T
         */
        public T setFeatured(boolean featured) {
            this.featured = featured;
            return self();
        }

        /**
         *
         * @param catalog_visibility  Catalog visibility.
         *                            Options: visible, catalog, search and hidden.
         *                            Default is visible.
         * @return T
         */
        public T setCatalogVisibility(String catalog_visibility) {
            this.catalog_visibility = catalog_visibility;
            return self();
        }

        /**
         *
         * @param catalog_visibility  Catalog visibility.
         *                            Options: visible, catalog, search and hidden.
         *                            Default is visible.
         * @return T
         */
        public T setCatalogVisibility(uk.co.twinn.pl_wtx_woocommerce.model.Product.CatalogVisiblityEnum catalog_visibility) {
            this.catalog_visibility = catalog_visibility.getValue();
            return self();
        }

        /**
         *
         * @param short_description Product short description.
         * @return T
         */
        public T setShort_description(String short_description) {
            this.short_description = short_description;
            return self();
        }


        /**
         *
         * @param external_url Product external URL.
         *                     Only for external products.
         * @return T
         */
        public T setExternal_url(String external_url) {
            this.external_url = external_url;
            return self();
        }

        /**
         *
         * @param button_text Product external button text.
         *                    Only for external products.
         * @return T
         */
        public T setButtonText(String button_text) {
            this.button_text = button_text;
            return self();
        }




        /**
         *
         * @param sold_individually Allow one item to be bought in a single order.
         *                          Default is false
         * @return T
         */
        public T setSoldIndividually(boolean sold_individually) {
            this.sold_individually = sold_individually;
            return self();
        }



        /**
         *
         * @param reviews_allowed Allow reviews. Default is true.
         * @return T
         */
        public T setReviewsAllowed(boolean reviews_allowed) {
            this.reviews_allowed = reviews_allowed;
            return self();
        }

        /**
         *
         * @param upsell_ids List of up-sell products IDs.
         * @return T
         */
        public T setUpsellIds(List<Integer> upsell_ids) {
            this.upsell_ids = upsell_ids;
            return self();
        }

        /**
         *
         * @param cross_sell_ids List of cross-sell products IDs.
         * @return T
         */
        public T setCrossSellIds(List<Integer> cross_sell_ids) {
            this.cross_sell_ids = cross_sell_ids;
            return self();
        }

        /**
         *
         * @param parent_id  	Product parent ID.
         * @return T
         */
        public T setParentId(int parent_id) {
            this.parent_id = parent_id;
            return self();
        }

        /**
         *
         * @param purchase_note Optional note to send the customer after purchase.
         * @return T
         */
        public T setPurchaseNote(String purchase_note) {
            this.purchase_note = purchase_note;
            return self();
        }

        /**
         *
         * @param categories List of categories. See Product - Categories properties
         * @return T
         */
        public T setCategories(List<ProductCategoriesItem> categories) {
            this.categories = categories;
            return self();
        }

        /**
         *
         * @param tags List of tags. See Product - Tags properties
         * @return T
         */
        public T setTags(List<ProductTag> tags) {
            this.tags = tags;
            return self();
        }

        /**
         *
         * @param images List of images. See Product - Images properties
         * @return T
         */
        public T setImages(List<ProductImage> images) {
            this.images = images;
            return self();
        }



        /**
         *
         * @param default_attributes  Defaults variation attributes. See Product - Default attributes properties
         * @return T
         */
        public T setDefaultAttributes(List<ProductAttribute> default_attributes) {
            this.default_attributes = default_attributes;
            return self();
        }

        /**
         *
         * @param grouped_products List of grouped products ID.
         * @return T
         */
        public T setGroupedProducts(List<Integer> grouped_products) {
            this.grouped_products = grouped_products;
            return self();
        }



        T self() {
            return (T) this;
        }

        private ProductRequest build(){
            return new ProductRequest(this);
        }

        public Created<Product> getResponse(){

            //nothing is defined as mandatory, but we may want to build in some pre-validation
            ProductRequest create = build();

            //make the call
            return new Created<Product>(
                new Rest().create(create.endPoint(), create.toJson(), new TypeReference<ProductVariation>(){})
            );

        }

    }

    public static class Updater<T extends Updater<T>> extends Creator<T>{

        private int id;

        public T setId(int id) {
            this.id = id;
            return self();
        }

        private ProductRequest build(){
            return new ProductRequest(this);
        }

        @Override
        public Updated<uk.co.twinn.pl_wtx_woocommerce.model.Product> getResponse(){
            if (id > 0){

                ProductRequest create = build();
                return new Updated<>(
                    new Rest().update(create.endPoint(), create.toJson(), new TypeReference<Product>(){})
                );

            }else{
                return new Updated<>(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    //<editor-fold name="Reader">
    public static class Reader<T extends Reader<T>> extends CoreReaderRequest.ReaderCore<T>{

        @Override
        T self() {return (T) this;}

        public Read<uk.co.twinn.pl_wtx_woocommerce.model.Product> getResponse(){
            return (Read<uk.co.twinn.pl_wtx_woocommerce.model.Product>)super.getResponse(PRODUCTS, new TypeReference<uk.co.twinn.pl_wtx_woocommerce.model.Product>() {});

        }

    }
    //</editor-fold>

    //<editor-fold name="Deleter">
    public static class Deleter<T extends Deleter<T>> extends CoreDeleterRequest.DeleterCore<T>{

        @Override
        T self() {return (T) this;}

        protected ProductRequest build(){
            return new ProductRequest(this);
        }

        public Deleted<uk.co.twinn.pl_wtx_woocommerce.model.Product> getResponse(){
            return (Deleted<uk.co.twinn.pl_wtx_woocommerce.model.Product>)super.getResponse(PRODUCTS, new TypeReference<uk.co.twinn.pl_wtx_woocommerce.model.Product>() {});

        }

    }
    //</editor-fold>
    //<editor-fold name="Reader">
    public static class Duplicator<T extends Duplicator<T>> extends CoreDuplicatorRequest.DuplicatorCore<T>{

        @Override
        T self() {return (T) this;}

        public Duplicated<uk.co.twinn.pl_wtx_woocommerce.model.Product> getResponse(){
            return (Duplicated<uk.co.twinn.pl_wtx_woocommerce.model.Product>)super.getResponse(PRODUCTS, new TypeReference<uk.co.twinn.pl_wtx_woocommerce.model.Product>() {});

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

        public T addCreator(Creator<?> create){
            batch.addCreate(create.build().product);
            return self();
        }

        public T addUpdater(Updater<?> update){
            batch.addUpdate(update.build().product);
            return self();
        }

        public T addDeleter(Deleter<?> delete){
            batch.addDelete(delete.build().product);
            return self();
        }

        public Batched<uk.co.twinn.pl_wtx_woocommerce.model.Product> getResponse(){

            return (Batched<uk.co.twinn.pl_wtx_woocommerce.model.Product>) super.getResponse(PRODUCTS, batch, new TypeReference<Batch<uk.co.twinn.pl_wtx_woocommerce.model.Product>>(){});

        }

    }

    /**
     *
     * Searches the Products
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products">...</a>
     *
     * @param <T>
     */
    public static class ListAll<T extends ListAll<T>> extends Seek.StockSearcher<T> {

        T self() {
            return (T) this;
        }

        /**
         *
         * @param modified_after Limit response to resources modified after a given ISO8601 compliant date.
         * @return T
         */
        public T setModifiedAfter(LocalDate modified_after) {
            addNameValuePair("modified_after", modified_after);
            return self();
        }
        /**
         *
         * @param modified_before Limit response to resources modified after a given ISO8601 compliant date.
         * @return T
         */
        public T setModifiedBefore(LocalDate modified_before) {
            addNameValuePair("modified_before", modified_before);
            return self();
        }
        /**
         *
         * @param dates_are_gmt Whether to consider GMT post dates when limiting response by published or modified date.
         * @return T
         */
        public T setDatesAreGmt(boolean dates_are_gmt) {
            addNameValuePair("dates_are_gmt", dates_are_gmt);
            return self();
        }

        /**
         *
         * @param type Limit result set to products assigned a specific type.
         *           Options: simple, grouped, external and variable.
         * @return T
         */
        public T setType(String type) {
            addNameValuePair("type", type);
            return self();
        }
        /**
         *
         * @param include_types Limit result set to products with any of the types.
         *          Multiple statuses can be provided as a comma-separated list.
         *          Takes precedence over the type parameter.
         *          Options: simple, grouped, external and variable.
         * @return T
         */
        public T setIncludeTypes(String include_types) {
            addNameValuePair("include_types", include_types);
            return self();
        }

        /**
         *
         * @param exclude_types Exclude products from result set with any of the specified types.
         *          Multiple statuses can be provided as a comma-separated list.
         *          Takes precedence over the include_types parameter.
         *          Options: simple, grouped, external and variable.
         * @return T
         */
        public T setExcludeTypes(String exclude_types) {
            addNameValuePair("exclude_types", exclude_types);
            return self();
        }

        /**
         *
         * @param featured Limit result set to featured products.
         * @return T
         */
        public T setFeatured(boolean featured) {
            addNameValuePair("featured", featured);
            return self();
        }

        /**
         *
         * @param category Limit result set to products assigned a specific category ID.
         * @return T
         */
        public T setCategory(String category) {
            addNameValuePair("category", category);
            return self();
        }
        /**
         *
         * @param tag Limit result set to products assigned a specific tag ID.
         * @return T
         */
        public T setTag(String tag) {
            addNameValuePair("tag", tag);
            return self();
        }
        /**
         *
         * @param shipping_class Limit result set to products assigned a specific shipping class ID.
         * @return T
         */
        public T setShippingClass(String shipping_class) {
            addNameValuePair("shipping_class", shipping_class);
            return self();
        }
        /**
         *
         * @param attribute Limit result set to products with a specific attribute.
         * @return T
         */
        public T setAttribute(String attribute) {
            addNameValuePair("attribute", attribute);
            return self();
        }
        /**
         *
         * @param attribute_term Limit result set to products with a specific attribute term ID
         *           (required an assigned attribute).
         * @return T
         */
        public T setAttributeTerm(String attribute_term) {
            addNameValuePair("attribute_term", attribute_term);
            return self();
        }


        public Listed<uk.co.twinn.pl_wtx_woocommerce.model.Product> getResponse(){

            return new Listed<uk.co.twinn.pl_wtx_woocommerce.model.Product>(
                new Rest().listAll(
                    PRODUCTS,
                    build(),
                    new TypeReference<List<uk.co.twinn.pl_wtx_woocommerce.model.Product>>(){}
                )
            );

        }

    }
}
