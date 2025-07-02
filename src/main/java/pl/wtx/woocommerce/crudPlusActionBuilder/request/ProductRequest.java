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
import pl.wtx.woocommerce.api.client.model.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.ApiRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.core.Seek;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.ProductResponse;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.core.ApiResponseResult;
import pl.wtx.woocommerce.crudPlusActionBuilder.woocommerce.WooCommerce;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.wtx.woocommerce.crudPlusActionBuilder.defines.EndPoints.PRODUCTS;

public class ProductRequest extends ApiRequest {

    protected final Product product = new Product();

    private Batch batch;

    private boolean isBatch;
    private boolean force;
    private boolean duplicate;

    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    public ProductRequest(Creator creator){

        product.setName(creator.name);
        product.setSlug(creator.slug);
        product.setType(creator.type);
        product.setStatus(creator.status);
        product.setFeatured(creator.featured);
        product.setCatalogVisibility(creator.catalog_visibility);
        product.setDescription(creator.description);
        product.setShortDescription(creator.short_description);
        product.setSku(creator.sku);
        product.setRegularPrice(creator.regular_price);
        product.setSalePrice(creator.sale_price);
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

    public ProductRequest(Reader reader){

        product.setId(reader.id);

    }

    public ProductRequest(Updater updater){

        this((Creator)updater);
        product.setId(updater.id);

    }

    public ProductRequest(Deleter deleter){

        this((Reader)deleter);
        isBatch = false;
        duplicate = false;
        force = deleter.force;

    }

    public ProductRequest(Duplicator duplicator){

        this((Reader)duplicator);
        isBatch = false;
        force = false;
        duplicate = true;

    }

    public ProductRequest(Batcher batcher){

        batch = batcher.getBatch();
        force = false;
        duplicate = false;
        isBatch = true;

    }

    public Product getProduct(){
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

    public static class Creator<T extends Creator>{

        private String name;        //string	Product name.
        private String slug;        //string	Product slug.

        private String type;    //string	Product type. Options: simple, grouped, external and variable. Default is simple.
        private String status;    //string	Product status (post status). Options: draft, pending, private and publish. Default is publish.
        private Boolean featured;    //boolean	Featured product. Default is false.
        private String catalog_visibility;    //string	Catalog visibility. Options: visible, catalog, search and hidden. Default is visible.
        private String description;    //string	Product description.
        private String short_description;    //string	Product short description.
        private String sku;    //string	Unique identifier.
        private String regular_price;    //string	Product regular price.
        private String sale_price;    //string	Product sale price.
        private LocalDateTime date_on_sale_from;    //date-time	Start date of sale price, in the site's timezone.
        private LocalDateTime date_on_sale_from_gmt;    //date-time	Start date of sale price, as GMT.
        private LocalDateTime date_on_sale_to;    //date-time	End date of sale price, in the site's timezone.
        private LocalDateTime date_on_sale_to_gmt;    //date-time	End date of sale price, as GMT.
        private Boolean virtual;    //boolean	If the product is virtual. Default is false.
        private Boolean downloadable;    //boolean	If the product is downloadable. Default is false.
        private List<ProductDownload> downloads;    //array	List of downloadable files. See Product - Downloads properties
        private Integer download_limit;    //integer	Number of times downloadable files can be downloaded after purchase. Default is -1.
        private Integer download_expiry;    //integer	Number of days until access to downloadable files expires. Default is -1.
        private String external_url;    //string	Product external URL. Only for external products.
        private String button_text;    //string	Product external button text. Only for external products.
        private String tax_status;    //string	Tax status. Options: taxable, shipping and none. Default is taxable.
        private String tax_class;    //string	Tax class.
        private Boolean manage_stock;    //boolean	Stock management at product level. Default is false.
        private Integer stock_quantity;    //integer	Stock quantity.
        private String stock_status;    //string	Controls the stock status of the product. Options: instock, outofstock, onbackorder. Default is instock.
        private String backorders;    //string	If managing stock, this controls if backorders are allowed. Options: no, notify and yes. Default is no.
        private Boolean sold_individually;    //boolean	Allow one item to be bought in a single order. Default is false.
        private String weight;    //string	Product weight.
        private ProductDimension dimensions;    //object	Product dimensions. See Product - Dimensions properties
        private String shipping_class;    //string	Shipping class slug.
        private Boolean reviews_allowed;    //boolean	Allow reviews. Default is true.
        private List<Integer> upsell_ids;    //array	List of up-sell products IDs.
        private List<Integer> cross_sell_ids;    //array	List of cross-sell products IDs.
        private Integer parent_id;    //integer	Product parent ID.
        private String purchase_note;    //string	Optional note to send the customer after purchase.
        private List<ProductCategoriesItem> categories;    //array	List of categories. See Product - Categories properties
        private List<ProductTag> tags;    //array	List of tags. See Product - Tags properties
        private List<ProductImage> images;    //array	List of images. See Product - Images properties
        private List<ProductAttribute>attributes;    //array	List of attributes. See Product - Attributes properties
        private List<ProductAttribute> default_attributes;    //array	Defaults variation attributes. See Product - Default attributes properties
        //private int[] variations;    //array	List of variations IDs.read-only
        private List<Integer> grouped_products;    //array	List of grouped products ID.
        private Integer menu_order;    //integer	Menu order, used to custom sort products.
        private List<MetaData> meta_data;    //array	Meta data. See Product - Meta data properties

        public T setName(String name) {
            this.name = name;
            return self();
        }

        public T setSlug(String slug) {
            this.slug = slug;
            return self();
        }

        public T setType(String type) {
            this.type = type;
            return self();
        }

        public T setStatus(String status) {
            this.status = status;
            return self();
        }

        public T setFeatured(boolean featured) {
            this.featured = featured;
            return self();
        }

        public T setCatalogVisibility(String catalog_visibility) {
            this.catalog_visibility = catalog_visibility;
            return self();
        }

        public T setDescription(String description) {
            this.description = description;
            return self();
        }

        public T setShort_description(String short_description) {
            this.short_description = short_description;
            return self();
        }

        public T setSku(String sku) {
            this.sku = sku;
            return self();
        }

        public T setRegular_price(String regular_price) {
            this.regular_price = regular_price;
            return self();
        }

        public T setSale_price(String sale_price) {
            this.sale_price = sale_price;
            return self();
        }

        public T setDateOnSaleFrom(LocalDateTime date_on_sale_from) {
            this.date_on_sale_from = date_on_sale_from;
            return self();
        }

        public T setDateOnSaleFromGMT(LocalDateTime date_on_sale_from_gmt) {
            this.date_on_sale_from_gmt = date_on_sale_from_gmt;
            return self();
        }

        public T setDateOnSaleTo(LocalDateTime date_on_sale_to) {
            this.date_on_sale_to = date_on_sale_to;
            return self();
        }

        public T setDateOnSaleToGMT(LocalDateTime date_on_sale_to_gmt) {
            this.date_on_sale_to_gmt = date_on_sale_to_gmt;
            return self();
        }

        public T setVirtual(boolean virtual) {
            this.virtual = virtual;
            return self();
        }

        public T setDownloadable(boolean downloadable) {
            this.downloadable = downloadable;
            return self();
        }

        public T setDownloads(List<ProductDownload> downloads) {
            this.downloads = downloads;
            return self();
        }

        public T setDownloadLimit(int download_limit) {
            this.download_limit = download_limit;
            return self();
        }

        public T setDownloadExpiry(int download_expiry) {
            this.download_expiry = download_expiry;
            return self();
        }

        public T setExternal_url(String external_url) {
            this.external_url = external_url;
            return self();
        }

        public T setButtonText(String button_text) {
            this.button_text = button_text;
            return self();
        }

        public T setTaxStatus(String tax_status) {
            this.tax_status = tax_status;
            return self();
        }

        public T setTaxClass(String tax_class) {
            this.tax_class = tax_class;
            return self();
        }

        public T setManageStock(boolean manage_stock) {
            this.manage_stock = manage_stock;
            return self();
        }

        public T setStockQuantity(int stock_quantity) {
            this.stock_quantity = stock_quantity;
            return self();
        }

        public T setStock_status(String stock_status) {
            this.stock_status = stock_status;
            return self();
        }

        public T setBackorders(String backorders) {
            this.backorders = backorders;
            return self();
        }

        public T setSoldIndividually(boolean sold_individually) {
            this.sold_individually = sold_individually;
            return self();
        }

        public T setWeight(String weight) {
            this.weight = weight;
            return self();
        }

        public T setDimensions(ProductDimension dimensions) {
            this.dimensions = dimensions;
            return self();
        }

        public T setShippingClass(String shipping_class) {
            this.shipping_class = shipping_class;
            return self();
        }

        public T setReviewsAllowed(boolean reviews_allowed) {
            this.reviews_allowed = reviews_allowed;
            return self();
        }

        public T setUpsellIds(List<Integer> upsell_ids) {
            this.upsell_ids = upsell_ids;
            return self();
        }

        public T setCrossSellIds(List<Integer> cross_sell_ids) {
            this.cross_sell_ids = cross_sell_ids;
            return self();
        }

        public T setParentId(int parent_id) {
            this.parent_id = parent_id;
            return self();
        }

        public T setPurchaseNote(String purchase_note) {
            this.purchase_note = purchase_note;
            return self();
        }

        public T setCategories(List<ProductCategoriesItem> categories) {
            this.categories = categories;
            return self();
        }

        public T setTags(List<ProductTag> tags) {
            this.tags = tags;
            return self();
        }

        public T setImages(List<ProductImage> images) {
            this.images = images;
            return self();
        }

        public T setAttributes(List<ProductAttribute> attributes) {
            this.attributes = attributes;
            return self();
        }

        public T setDefaultAttributes(List<ProductAttribute> default_attributes) {
            this.default_attributes = default_attributes;
            return self();
        }

        public T setGroupedProducts(List<Integer> grouped_products) {
            this.grouped_products = grouped_products;
            return self();
        }

        public T setMenuOrder(int menu_order) {
            this.menu_order = menu_order;
            return self();
        }

        public T setMetaData(List<MetaData> meta_data) {
            this.meta_data = meta_data;
            return self();
        }

        T self() {
            return (T) this;
        }

        private ProductRequest build(){
            return new ProductRequest(this);
        }

        public ProductResponse getResponse(){
            return new WooCommerce().create(build());
        }

    }

    /*Extend self to enforce Diamond RequestType standard call, If some have diamond and some do not then the consumer could be easily confused*/
    public static class Reader<T extends Reader>{

        /*
            https://www.baeldung.com/java-builder-pattern-inheritance
            https://woocommerce.github.io/woocommerce-rest-api-docs/?shell#product-properties
        */

        protected int id;             //integer	Unique identifier for the resource.read-only

        T self() {
            return (T) this;
        }

        /**
         *
         *
         * @param id This API lets you retrieve and view a specific product by ID.
         *           Set id to 0 to view all the products.
         * @return T
         */
        public T withId(int id) {
            this.id = id;
            return self();
        }

        private ProductRequest build(){
            return new ProductRequest(this);
        }

        public ProductResponse getResponse(){
            if (id > 0) {
                return new WooCommerce().read(build());
            }else {
                return new ProductResponse(
                    new ApiResponseResult(
                        false,
                        0,
                        "CRUD is limited to a single object result\n" +
                            "Please set requested id\n" +
                            "Use the Searcher with no parameters to get a full list")
                );
            }
        }

    }

    public static class Updater<T extends Updater<T>> extends ProductRequest.Creator<T>{

        private int id;

        public T setId(int id) {
            this.id = id;
            return self();
        }

        private ProductRequest build(){
            return new ProductRequest(this);
        }

        @Override
        public ProductResponse getResponse(){
            if (id > 0){
                return new WooCommerce().update(build());
            }else{
                return new ProductResponse(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }

        }
    }

    public static class Deleter<T extends Deleter<T>> extends Reader<T>{

        private boolean force;

        public T setForce(boolean force) {
            this.force = force;
            return self();
        }

        private ProductRequest build(){
            return new ProductRequest(this);
        }

        @Override
        public ProductResponse getResponse(){
            return new WooCommerce().delete(build());
        }

    }

    public static class Duplicator<T extends Duplicator<T>> extends Reader<T>{

        private ProductRequest build(){
            return new ProductRequest(this);
        }

        @Override
        public ProductResponse getResponse(){

            if (id > 0){
                return new WooCommerce().create(build());
            }else{
                return new ProductResponse(new ApiResponseResult(false, 0, "Invalid Identifier"));
            }
        }

    }

    private static class Batch{

        private final List<Product> create;
        private final List<Product> update;
        private final List<Product> delete;

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
            this.create.add(create.build().product);
        }

        public void addUpdater(Updater update){
            this.update.add(update.build().product);
        }

        public void addDeleter(Deleter delete){
            this.delete.add(delete.build().product);
        }

        public List<Product> getCreate(){
            return create;
        }
        public List<Product> getUpdate(){
            return update;
        }
        public List<Product> getDelete(){
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

        private ProductRequest build(){
            return new ProductRequest(this);
        }

        public ProductResponse getResponse(){

            if (batch.isEmpty()){

                return new ProductResponse(new ApiResponseResult(false, 0, "Nothing to do"));

            }else if (batch.getRecordCount() > 100){

                return new ProductResponse(new ApiResponseResult(false, 0,
                    "https://woocommerce.github.io/woocommerce-rest-api-docs/?shell#delete-a-product\n" +
                        "This API helps you to batch create, update and delete multiple products.\n\n" +
                        "Note: By default it's limited to up to 100 objects to be created, updated or deleted.")
                );

            }else{

                return new WooCommerce().batch(build());

            }

        }

    }

    /**
     *
     * Searches the Products
     * <a href="https://woocommerce.github.io/woocommerce-rest-api-docs/?php#list-all-products">...</a>
     *
     * @param <T>
     */
    public static class Searcher<T extends Searcher> extends Seek.Searcher<T> {

        T self() {
            return (T) this;
        }

        /**
         *
         * @param after Limit response to resources published after a given ISO8601 compliant date.
         * @return T
         */
        public T setAfter(LocalDate after) {
            addNameValuePair("after", after);
            return self();
        }

        /**
         *
         * @param before Limit response to resources published before a given ISO8601 compliant date.
         * @return T
         */
        public T setBefore(LocalDate before) {
            addNameValuePair("before", before);
            return self();
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
         * @param offset Offset the result set by a specific number of items.
         * @return T
         */
        public T setOffset(int offset) {
            addNameValuePair("offset", offset);
            return self();
        }


        /**
         *
         * @param parent Limit result set to those of particular parent IDs.
         * @return T
         */
        public T setParent(List<Integer> parent) {
            addNameValueIntegers("parent", parent);
            return self();
        }

        /**
         *
         * @param parent_exclude Limit result set to all items except those of a particular parent ID.
         * @return T
         */
        public T setParentExclude(List<Integer> parent_exclude) {
            addNameValueIntegers("parent_exclude", parent_exclude);
            return self();
        }
        /**
         *
         * @param slug Limit result set to products with a specific slug.
         * @return T
         */
        public T setSlug(String slug) {
            addNameValuePair("slug", slug);
            return self();
        }
        /**
         *
         * @param status Limit result set to products assigned a specific status.
         *           Options: any, draft, pending, private and publish. Default is any.
         * @return T
         */
        public T setStatus(String status) {
            addNameValuePair("status", status);
            return self();
        }

        /**
         *
         * @param include_status Limit result set to products with any of the specified statuses.
         *           Multiple statuses can be provided as a comma-separated list.
         *           Takes precedence over the status parameter.
         *           Options: any, future, trash, draft, pending, private, and publish.
         * @return T
         */
        public T setIncludeStatus(String include_status) {
            addNameValuePair("include_status", include_status);
            return self();
        }
        /**
         *
         * @param exclude_status Exclude products from result set with any of the specified statuses.
         *           Multiple statuses can be provided as a comma-separated list.
         *           Takes precedence over the include_status parameter.
         *           Options: future, trash, draft, pending, private, and publish.
         * @return T
         */
        public T setExcludeStatus(String exclude_status) {
            addNameValuePair("exclude_status", exclude_status);
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
         * @param sku Limit result set to products with a specific SKU.
         * @return T
         */
        public T setSku(String sku) {
            addNameValuePair("sku", sku);
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
        /**
         *
         * @param tax_class Limit result set to products with a specific tax class.
         *          Default options: standard, reduced-rate and zero-rate.
         * @return T
         */
        public T setTaxClass(String tax_class) {
            addNameValuePair("tax_class", tax_class);
            return self();
        }
        /**
         *
         * @param on_sale Limit result set to products on sale.
         * @return T
         */
        public T setOnSale(boolean on_sale) {
            addNameValuePair("on_sale", on_sale);
            return self();
        }
        /**
         *
         * @param min_price Limit result set to products based on a minimum price.
         * @return T
         */
        public T setMinPrice(BigDecimal min_price) {
            addNameValuePair("min_price", min_price.toString());
            return self();
        }
        /**
         *
         * @param max_price Limit result set to products based on a maximum price.
         * @return T
         */
        public T setMaxPrice(BigDecimal max_price) {
            addNameValuePair("max_price", max_price.toString());
            return self();
        }
        /**
         *
         * @param stock_status Limit result set to products with specified stock status.
         *           Options: instock, outofstock and onbackorder.
         * @return T
         */
        public T setStockStatus(String stock_status) {
            addNameValuePair("stock_status", stock_status);
            return self();
        }
        /**
         *
         * @param virtual Limit result set to virtual products.
         * @return T
         */
        public T setVirtual(boolean virtual) {
            addNameValuePair("virtual", virtual);
            return self();
        }
        /**
         *
         * @param downloadable Limit result set to downloadable products.
         * @return T
         */
        public T setDownloadable(boolean downloadable) {
            addNameValuePair("downloadable", downloadable);
            return self();
        }

        public ProductResponse getResponse(){

            return new ProductResponse(
                new WooCommerce().search(
                    PRODUCTS,
                    build(),
                    new TypeReference<List<Product>>(){}
                )
            );

        }

    }
}
