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
import org.slf4j.LoggerFactory;
import uk.co.twinn.api.woocommerce.exceptions.ResponseException;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.coupon.Coupon;
import uk.co.twinn.api.woocommerce.pl_wtx_woocommerce.model.product.*;
import uk.co.twinn.api.woocommerce.response.*;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static uk.co.twinn.api.woocommerce.defines.EndPoints.COUPONS;
import static uk.co.twinn.api.woocommerce.defines.EndPoints.PRODUCTS;

public class ProductBuilder extends ApiRequest {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProductBuilder.class);
    protected final Product product = new Product();

    public ProductBuilder(){}

    //<editor-fold defaultstate="collapsed" desc="Private Constructors">
    /*Can not extend Reader as Create should not have an id set, so to enforce the rules we do not extend*/
    private ProductBuilder(Creator<?> creator){

        product.setName(creator.name);
        product.setSlug(creator.slug);
        product.setType(creator.type);
        product.setStatus(creator.status);
        product.setFeatured(creator.featured);
        product.setCatalogVisibility(creator.catalogVisibility);
        product.setDescription(creator.description);
        product.setShortDescription(creator.shortDescription);
        product.setSku(creator.sku);
        product.setRegularPrice(creator.regularPrice);
        product.setSalePrice(creator.salePrice);
        product.setDateOnSaleFrom(creator.dateOnSaleFrom);
        product.setDateOnSaleFromGmt(creator.dateOnSaleFromGmt);
        product.setDateOnSaleTo(creator.dateOnSaleTo);
        product.setDateOnSaleToGmt(creator.dateOnSaleToGmt);
        product.setVirtual(creator.virtual);
        product.setDownloadable(creator.downloadable);
        product.setDownloads(creator.downloads);
        product.setDownloadLimit(creator.downloadLimit);
        product.setDownloadExpiry(creator.downloadExpiry);
        product.setExternalUrl(creator.externalUrl);
        product.setButtonText(creator.buttonText);
        product.setTaxStatus(creator.taxStatus);
        product.setTaxClass(creator.taxClass);
        product.setManageStock(creator.manageStock);
        product.setStockQuantity(creator.stockQuantity);
        product.setStockStatus(creator.stockStatus);
        product.setBackorders(creator.backorders);
        product.setSoldIndividually(creator.soldIndividually);
        product.setWeight(creator.weight);
        product.setDimensions(creator.dimensions);
        product.setShippingClass(creator.shippingClass);
        product.setReviewsAllowed(creator.reviewsAllowed);
        product.setUpsellIds(creator.upsellIds);
        product.setCrossSellIds(creator.crossSellIds);
        product.setParentId(creator.parentId);
        product.setPurchaseNote(creator.purchaseNote);
        product.setCategories(creator.categories);
        product.setTags(creator.tags);
        product.setImages(creator.images);
        product.setAttributes(creator.attributes);
        product.setDefaultAttributes(creator.defaultAttributes);
        product.setGroupedProducts(creator.groupedProducts);
        product.setMenuOrder(creator.menuOrder);
        product.setMetaData(creator.metaData);

        product.setVariations(creator.variationIds);
        product.setRelatedIds(creator.relatedIds);

    }

    private ProductBuilder(Updater<?> updater){

        this((Creator<?>)updater);
        product.setId(updater.id);

    }

    private ProductBuilder(Deleter deleter){

        product.setId(deleter.id);

    }
    //</editor-fold>

    public String toJson(){

        try {

            return getObjectMapper().writeValueAsString(product);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private String endPoint(){

        return PRODUCTS +
            (product.getId() != null && product.getId() != 0
                ? ("/" + product.getId())
                : ""
            );

    }

    //<editor-fold defaultstate="collapsed" desc="Creator Builder">
    public static class Creator<T extends Creator<T>> extends CoreProductsVariations.Creator<Product, T> {

        private String name;        //string	Product name.
        private String slug;        //string	Product slug.
        private String type;    //string	Product type. Options: simple, grouped, external and variable. Default is simple.
        private Boolean featured;    //boolean	Featured product. Default is false.
        private String catalogVisibility;    //string	Catalog visibility. Options: visible, catalog, search and hidden. Default is visible.
        private String shortDescription;    //string	Product short description.
        private String externalUrl;    //string	Product external URL. Only for external products.
        private String buttonText;    //string	Product external button text. Only for external products.
        private Boolean soldIndividually;    //boolean	Allow one item to be bought in a single order. Default is false.
        private Boolean reviewsAllowed;    //boolean	Allow reviews. Default is true.
        private Integer parentId;    //integer	Product parent ID.
        private String purchaseNote;    //string	Optional note to send the customer after purchase.
        private List<Integer> upsellIds;    //array	List of up-sell products IDs.
        private List<Integer> crossSellIds;    //array	List of cross-sell products IDs.
        private List<ProductCategoriesItem> categories;    //array	List of categories. See Product - Categories properties
        private List<ProductTag> tags;    //array	List of tags. See Product - Tags properties
        private List<ProductImage> images;    //array	List of images. See Product - Images properties
        private List<ProductAttribute> defaultAttributes;    //array	Defaults variation attributes. See Product - Default attributes properties
        //private int[] variations;    //array	List of variations IDs.read-only
        private List<Integer> groupedProducts;    //array	List of grouped products ID.

        private List<Integer> variationIds;    //array	List of grouped products ID.
        private List<Integer> relatedIds;    //array	List of grouped products ID.

        public Creator(){}

        public Creator(Product product){

            super(product);

            name = product.getName();
            slug = product.getSlug();
            type = product.getType();

            featured = product.getFeatured();
            catalogVisibility = product.getCatalogVisibility();
            shortDescription = product.getShortDescription();
            externalUrl = product.getExternalUrl();
            buttonText = product.getButtonText();
            soldIndividually = product.getSoldIndividually();
            reviewsAllowed = product.getReviewsAllowed();

            parentId = product.getParentId();
            purchaseNote = product.getPurchaseNote();

            //initialised in product as a list, therefore if new list is empty force ignore with null
            upsellIds = product.getUpsellIds().isEmpty() ? null : product.getUpsellIds();
            crossSellIds = product.getCrossSellIds().isEmpty() ? null : product.getCrossSellIds();
            categories = product.getCategories().isEmpty() ? null : product.getCategories();
            tags = product.getTags().isEmpty() ? null : product.getTags();
            images = product.getImages().isEmpty() ? null : product.getImages();
            defaultAttributes = product.getDefaultAttributes().isEmpty() ? null : product.getDefaultAttributes();
            groupedProducts = product.getGroupedProducts().isEmpty()? null : product.getGroupedProducts();
            variationIds = product.getVariations().isEmpty() ? null : product.getVariations();
            relatedIds = product.getRelatedIds().isEmpty() ? null : product.getRelatedIds();

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
        public T setType(Product.TypeEnum type) {
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
        public T setStatus(Product.StatusEnum status) {
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
         * @param catalogVisibility  Catalog visibility.
         *                            Options: visible, catalog, search and hidden.
         *                            Default is visible.
         * @return T
         */
        public T setCatalogVisibility(String catalogVisibility) {
            this.catalogVisibility = catalogVisibility;
            return self();
        }

        /**
         *
         * @param catalogVisibility  Catalog visibility.
         *                            Options: visible, catalog, search and hidden.
         *                            Default is visible.
         * @return T
         */
        public T setCatalogVisibility(Product.CatalogVisiblityEnum catalogVisibility) {
            this.catalogVisibility = catalogVisibility.getValue();
            return self();
        }

        /**
         *
         * @param shortDescription Product short description.
         * @return T
         */
        public T setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return self();
        }


        /**
         *
         * @param externalUrl Product external URL.
         *                     Only for external products.
         * @return T
         */
        public T setExternalUrl(String externalUrl) {
            this.externalUrl = externalUrl;
            return self();
        }

        /**
         *
         * @param buttonText Product external button text.
         *                    Only for external products.
         * @return T
         */
        public T setButtonText(String buttonText) {
            this.buttonText = buttonText;
            return self();
        }

        /**
         *
         * @param soldIndividually Allow one item to be bought in a single order.
         *                          Default is false
         * @return T
         */
        public T setSoldIndividually(boolean soldIndividually) {
            this.soldIndividually = soldIndividually;
            return self();
        }

        /**
         *
         * @param reviewsAllowed Allow reviews. Default is true.
         * @return T
         */
        public T setReviewsAllowed(boolean reviewsAllowed) {
            this.reviewsAllowed = reviewsAllowed;
            return self();
        }

        /**
         *
         * @param upsellIds List of up-sell products IDs.
         * @return T
         */
        public T setUpsellIds(List<Integer> upsellIds) {
            this.upsellIds = upsellIds;
            return self();
        }

        /**
         *
         * @param crossSellIds List of cross-sell products IDs.
         * @return T
         */
        public T setCrossSellIds(List<Integer> crossSellIds) {
            this.crossSellIds = crossSellIds;
            return self();
        }

        /**
         *
         * @param parentId  	Product parent ID.
         * @return T
         */
        public T setParentId(int parentId) {
            this.parentId = parentId;
            return self();
        }

        /**
         *
         * @param purchaseNote Optional note to send the customer after purchase.
         * @return T
         */
        public T setPurchaseNote(String purchaseNote) {
            this.purchaseNote = purchaseNote;
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
        * @param imageUrl adds this url to the images or creates a new list
        * @return T
        */
        public T setImage(String imageUrl){
            if (images == null){
                images = new ArrayList<>();
            }
            images.add(new ProductImage().src(imageUrl));
            return self();
        }

        /**
         *
         * @param defaultAttributes  Defaults variation attributes. See Product - Default attributes properties
         * @return T
         */
        public T setDefaultAttributes(List<ProductAttribute> defaultAttributes) {
            this.defaultAttributes = defaultAttributes;
            return self();
        }

        /**
         *
         * @param groupedProducts List of grouped products ID.
         * @return T
         */
        public T setGroupedProducts(List<Integer> groupedProducts) {
            this.groupedProducts = groupedProducts;
            return self();
        }

        public T setVariationIds(List<Integer> variationIds) {
            this.variationIds = variationIds;
            return self();
        }

        public T setRelatedIds(List<Integer> relatedIds) {
            this.relatedIds = relatedIds;
            return self();
        }

        private ProductBuilder build(){
            return new ProductBuilder(this);
        }

        public Created<Product> getResponse(){

            //nothing is defined as mandatory, but we may want to build in some pre-validation
            ProductBuilder create = build();

            return super.getCreate(create.endPoint(), create.toJson(), new TypeReference<Product>() {});

            //make the call
            /*return new Created<>(
                new Rest<Product>().create(create.endPoint(), create.toJson())
            );*/

        }

        public Optional<Product> getCreated() {

            ProductBuilder create = build();

            return super.getCreated(create.endPoint(), create.toJson(), new TypeReference<Product>() {});

        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Update Builder">
    public static class Updater<T extends Updater<T>> extends Creator<T>{

        public Updater(int productId){
            this.id = productId;
        }

        public Updater(Product product){
            super(product);
            if (product.getId() != null) {
                this.id = product.getId();
            }else{
                this.id = 0;
            }
        }
        private final int id;

        private ProductBuilder build(){
            return new ProductBuilder(this);
        }

        @Override
        public Updated<Product> getResponse(){

            if (id > 0){

                ProductBuilder create = build();

                Logger.getLogger(ProductBuilder.class.getName()).log(Level.INFO,String.format("Updating: %s", create.toJson()));
                /*return new Updated<>(
                    new Rest<Product>().update(create.endPoint(), create.toJson())
                );*/
                Updated<Product> updated = super.getUpdate(create.endPoint(), create.toJson(), new TypeReference<Product>() {});

                if (updated.isSuccess()) {
                    Logger.getLogger(ProductBuilder.class.getName()).log(Level.INFO, String.format("Updated: %s", updated.toJson()));
                }

                return updated;

            }else{
                return new Updated<>(new ApiResponseResult<>(false, 0, "Invalid Identifier"));
            }
        }

        public Optional<Product> getUpdated() {

            if (id > 0){

                ProductBuilder create = build();

                return super.getUpdated(create.endPoint(), create.toJson(), new TypeReference<Product>() {});

            }else{

                throw new ResponseException("Id is MANDATORY!");

            }

        }
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Reader Builder">
    public static class Reader extends CoreReader.ReaderCore<Product>{

        public Reader(int productId){
            super(productId);
        }

        public Read<Product> getResponse(){
            return super.getResponse(PRODUCTS, new TypeReference<Product>() {});
        }

        public Optional<Product> getRead() {

            if (id > 0) {

                return super.getRead(PRODUCTS, new TypeReference<Product>() {});

            }else{

                throw new ResponseException("Id is MANDATORY!");

            }

        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Deleter Builder">
    public static class Deleter extends CoreDeleter.DeleterCore<Product>{

        public Deleter(int productId, boolean force){
            super(productId, force);
        }

        protected ProductBuilder build(){
            return new ProductBuilder(this);
        }

        public Deleted<Product> getResponse(){
            return super.getResponse(PRODUCTS, new TypeReference<Product>() {});

        }

        public Optional<Product> getDeleted() {

            if (id > 0) {

                return super.getDeleted(PRODUCTS, new TypeReference<Product>() {});

            }else{

                throw new ResponseException("Id is MANDATORY!");

            }

        }

    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Duplicator Builder">
    public static class Duplicator extends CoreDuplicator.DuplicatorCore<Product>{

        public Duplicator(int productId){
            super(productId);
        }

        public Duplicated<Product> getResponse(){

            return super.getResponse(PRODUCTS, new TypeReference<Product>(){});

        }

    }
    //</editor-fold>

    //<editor-fold  defaultstate="collapsed" desc="Batch Builder">
    public static class Batcher<T extends Batcher<T>> extends CoreBatch.BatchCore<Product, T>{

        public Batcher(){
            super();
        }

        public T addCreator(Creator<?> create){
            return addCreator(create.build().product);
        }

        public T addCreator(List<Product> create){
            return super.addCreate(create);
        }

        public T addCreator(Product create){
            return super.addCreate(create);
        }

        public T addUpdater(Updater<?> update){
            return addUpdater(update.build().product);
        }

        public T addUpdater(List<Product> updateList){
            return super.addUpdate(updateList);
        }

        public T addUpdater(Product update){
            return super.addUpdate(update);
        }

        public T addDeleter(Deleter delete){
            return addDeleter(delete.build().product);
        }

        public T addDeleter(List<Product> deleteList){
            for (Product delete : deleteList) {
                addDeleter(delete);
            }
            return self();
        }

        public T addDeleter(Product delete){
            return super.addDelete(delete.getId());
        }

        public Batched<Product> getResponse(){

            //pre-validate
            /* Nothing is MANDATORY!!*/
            for (Product product : batch.getCreate()){
                if (product.getId() != null){
                    product.setId(null);
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

            return super.getResponse(PRODUCTS, batch, new TypeReference<BatchResult<Product>>() {});

        }

        public Optional<BatchResult<Product>> getBatched(){

            //pre-validate
            /* Nothing is MANDATORY!!*/
            for (Product product : batch.getCreate()){
                if (product.getId() != null){
                    product.setId(null);
                }
            }

            for (int i = 0; i < batch.getUpdate().size(); i++){
                if (batch.getUpdate().get(i).getId() == null || batch.getUpdate().get(i).getId() == 0){
                    throw new ResponseException(
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

            return super.getBatched(PRODUCTS, batch, new TypeReference<BatchResult<Product>>() {});
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
    public static class ListAll<T extends ListAll<T>> extends CoreSeek.StockSearcher<Product, T> {

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
        public T setCategory(int category) {
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

        public Listed<Product> getResponse(){

            return super.getResponse(
                PRODUCTS,
                build(),
                new TypeReference<List<Product>>() {}
            );

            /*return new Listed<>(
                new Rest<List<Product>>().listAll(
                    PRODUCTS,
                    build()
                )
            );*/

        }

        public Optional<List<Product>> getListed(){

            return super.getListed(
                PRODUCTS,
                build(),
                new TypeReference<List<Product>>() {}
            );

            /*return new Listed<>(
                new Rest<List<Product>>().listAll(
                    PRODUCTS,
                    build()
                )
            );*/

        }

    }
    //</editor-fold>

    public static class ListProductsInCategory<T extends ListProductsInCategory<T>> extends CoreSeek.SearchBase<Product, T> {

        private int categoryId;
        private static final int COUNT = 50;
        private static final int FAILSAFE = 5;
        private int counter = 1;

        public ListProductsInCategory(int categoryId){
            super();
            this.categoryId = categoryId;
        }

        public Listed<Product> getResponse(){

            //if in constructor, possible 'this' leakage
            addNameValuePair("category", categoryId);
            addNameValuePair("per_page", COUNT);
            addNameValuePair("page", counter);

            List<Product> products = new ArrayList<>();
            Listed<Product> search;

            counter = 1;

            while (counter < FAILSAFE){

                search = doSearch();
                counter++;

                if (search.isSuccess()){
                    if (search.getResult().size() < COUNT){ //less than requested assume we have bottomed out
                        if (!products.isEmpty()){
                            products.addAll(search.getResult());
                            return new Listed<>(products);
                        }else{
                            return search;
                        }
                    }else if (counter < FAILSAFE){
                        addNameValuePair("page", counter);//nudge the page
                        products.addAll(search.getResult());
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

        private Listed<Product> doSearch(){
            return super.getResponse(
                PRODUCTS,
                build(),
                new TypeReference<List<Product>>() {}
            );
        }

        /**
        * Max return is 50 per page, we loop the results to get the full list in what we make appear to be one hit
        **/
        public Optional<List<Product>> getListed(){

            //if in constructor, possible 'this' leakage
            addNameValuePair("category", categoryId);
            addNameValuePair("per_page", COUNT);
            addNameValuePair("page", counter);

            List<Product> products = new ArrayList<>();
            Listed<Product> search;

            counter = 1;

            while (counter < FAILSAFE){

                search = doSearch();
                counter++;

                if (search.isSuccess()){

                    if (search.getResult().size() < COUNT){ //less than requested assume we have bottomed out

                        products.addAll(search.getResult());
                        return Optional.of(search.getResult());

                    }else if (counter < FAILSAFE){

                        addNameValuePair("page", counter);//nudge the page
                        products.addAll(search.getResult());

                        //continue loop
                    }else{
                        throw new ResponseException(
                            "Failsafe triggered. called " + COUNT + ", " + FAILSAFE + " times. Do not want a perpertual loop"
                        );
                    }
                }else{

                    return Optional.of(search.getResult());

                }

            }

            throw new ResponseException(
                "Something has gone wrong we should have returned a result before now, " +
                    "Could be Failsafe triggered. Hunted for " + COUNT + ", " + FAILSAFE + " times. " +
                    "Do not want a perpertual loop"
            );

        }

    }

}
