/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.builders;

import uk.co.twinn.pl_wtx_woocommerce.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * This is an Internal class, relying on package-private values to create the rest of the ProductRequests
 * can not take down to core with exposing inner functions to end user
 *
 */
class CoreProductsVariations {

    /**
     * Implements core elements to ProductRequest and ProductVariationRequest
     * in an ideal world we would have a "friend" option, similar to package-private
     * but only the next inheritor can see or say package+1-private
     *<br/>
     * If I move to core package, where it really should be, then the protected can not be seen.
     * all Core* are package-private
     *<br/>
     * Sets and Gets only do NOT use.
    **/
    public static class Creator<T extends Creator<?>> {

        protected String description;    //string	Product description.
        protected String sku;    //string	Unique identifier.
        protected BigDecimal regularPrice;    //string	Product regular price.
        protected BigDecimal salePrice;    //string	Product sale price.
        protected LocalDateTime dateOnSaleFrom;    //date-time	Start date of sale price, in the site's timezone.
        protected LocalDateTime dateOnSaleFromGmt;    //date-time	Start date of sale price, as GMT.
        protected LocalDateTime dateOnSaleTo;    //date-time	End date of sale price, in the site's timezone.
        protected LocalDateTime dateOnSaleToGmt;    //date-time	End date of sale price, as GMT.

        protected String status;    //string	Product status (post status). Options: draft, pending, protected and publish. Default is publish.
        protected Boolean virtual;    //boolean	If the product is virtual. Default is false.
        protected Boolean downloadable;    //boolean	If the product is downloadable. Default is false.
        protected List<ProductDownload> downloads;    //array	List of downloadable files. See Product - Downloads properties
        protected Integer downloadLimit;    //integer	Number of times downloadable files can be downloaded after purchase. Default is -1.
        protected Integer downloadExpiry;    //integer	Number of days until access to downloadable files expires. Default is -1.
        protected String taxStatus;    //string	Tax status. Options: taxable, shipping and none. Default is taxable.
        protected String taxClass;    //string	Tax class.
        protected Boolean manageStock;    //boolean	Stock management at product level. Default is false.
        protected Integer stockQuantity;    //integer	Stock quantity.
        protected String stockStatus;    //string	Controls the stock status of the product. Options: instock, outofstock, onbackorder. Default is instock.
        protected String backorders;    //string	If managing stock, this controls if backorders are allowed. Options: no, notify and yes. Default is no.
        protected String weight;    //string	Product weight.
        protected ProductDimension dimensions;    //object	Product dimensions. See Product - Dimensions properties
        protected String shippingClass;    //string	Shipping class slug.
        protected List<ProductAttribute>attributes;    //array	List of attributes. See Product - Attributes properties
        protected Integer menuOrder;    //integer	Menu order, used to custom sort products.

        protected List<MetaData> metaData;    //array	Meta data. See Product - Meta data properties

        public Creator(){}

        public Creator(Product product){

            description = product.getDescription();
            sku = product.getSku();
            regularPrice = product.getRegularPrice();
            salePrice = product.getSalePrice();

            dateOnSaleFrom = product.getDateOnSaleFrom();
            dateOnSaleFromGmt = product.getDateOnSaleFromGmt();
            dateOnSaleTo = product.getDateOnSaleTo();
            dateOnSaleToGmt = product.getDateOnSaleToGmt();

            virtual = product.getVirtual();
            status = product.getStatus();
            downloadable = product.getDownloadable();
            downloads = product.getDownloads();
            downloadLimit = product.getDownloadLimit();
            downloadExpiry = product.getDownloadExpiry();

            taxStatus = product.getTaxStatus();
            taxClass = product.getTaxClass();
            manageStock = product.getManageStock();

            stockQuantity = product.getStockQuantity();
            stockStatus = product.getStockStatus();
            backorders = product.getBackorders();
            weight = product.getWeight();
            dimensions = product.getDimensions();
            shippingClass = product.getShippingClass();
            attributes = product.getAttributes();
            menuOrder = product.getMenuOrder();
            metaData = product.getMetaData();

        }

        /** THESE NEED EXTENDING **/
        public Creator(ProductVariation product){

            description = product.getDescription();
            sku = product.getSku();

            dateOnSaleFrom = product.getDateOnSaleFrom();
            dateOnSaleFromGmt = product.getDateOnSaleFromGmt();
            dateOnSaleTo = product.getDateOnSaleTo();
            dateOnSaleToGmt = product.getDateOnSaleToGmt();

            virtual = product.getVirtual();
            status = product.getStatus();
            downloadable = product.getDownloadable();
            downloads = product.getDownloads();
            downloadLimit = product.getDownloadLimit();
            downloadExpiry = product.getDownloadExpiry();

            taxStatus = product.getTaxStatus();
            taxClass = product.getTaxClass();
            manageStock = product.getManageStock();

            stockQuantity = product.getStockQuantity();
            stockStatus = product.getStockStatus();
            backorders = product.getBackorders();
            weight = product.getWeight();
            dimensions = product.getDimensions();
            shippingClass = product.getShippingClass();
            attributes = product.getAttributes();
            menuOrder = product.getMenuOrder();
            metaData = product.getMetaData();

        }

        @SuppressWarnings ("unchecked")
        T self() {
            return (T) this;
        }

        /**
         *
         * @param description Product description.
         * @return T
         */
        public T setDescription(String description) {
            this.description = description;
            return self();
        }
        /**
         *
         * @param sku  Unique identifier.
         * @return T
         */
        public T setSku(String sku) {
            this.sku = sku;
            return self();
        }

        /**
         * Pseudonym for RegularPrice
         * @param regularPrice Product regular price.
         * @return T
         */
        public T setPrice(BigDecimal regularPrice) {
            return setRegularPrice(regularPrice);
        }
        /**
         *
         * @param regularPrice Product regular price.
         * @return T
         */
        public T setRegularPrice(BigDecimal regularPrice) {
            this.regularPrice = regularPrice;
            return self();
        }

        /**
         *
         * @param salePrice  Product sale price.
         * @return T
         */
        public T setSalePrice(BigDecimal salePrice) {
            this.salePrice = salePrice;
            return self();
        }

        /**
         *
         * @param dateOnSaleFrom Start date of sale price, in the site's timezone.
         * @return T
         */
        public T setDateOnSaleFrom(LocalDateTime dateOnSaleFrom) {
            this.dateOnSaleFrom = dateOnSaleFrom;
            return self();
        }

        /**
         *
         * @param dateOnSaleFromGmt Start date of sale price, as GMT.
         * @return T
         */
        public T setDateOnSaleFromGMT(LocalDateTime dateOnSaleFromGmt) {
            this.dateOnSaleFromGmt = dateOnSaleFromGmt;
            return self();
        }

        /**
         *
         * @param dateOnSaleTo End date of sale price, in the site's timezone.
         * @return T
         */
        public T setDateOnSaleTo(LocalDateTime dateOnSaleTo) {
            this.dateOnSaleTo = dateOnSaleTo;
            return self();
        }

        /**
         *
         * @param dateOnSaleToGmt  End date of sale price, as GMT.
         * @return T
         */
        public T setDateOnSaleToGMT(LocalDateTime dateOnSaleToGmt) {
            this.dateOnSaleToGmt = dateOnSaleToGmt;
            return self();
        }

        /**
         *
         * @param status Product status (post status).
         *               Options: draft, pending, private and publish.
         *               Default is publish.
         * @return T
         */
        public T setStatus(String status) {
            this.status = status;
            return self();
        }

        /**
         *
         * @param virtual  	If the product is virtual. Default is false.
         * @return T
         */
        public T setVirtual(boolean virtual) {
            this.virtual = virtual;
            return self();
        }

        /**
         *
         * @param downloadable If the product is downloadable. Default is false.
         * @return  T
         */
        public T setDownloadable(boolean downloadable) {
            this.downloadable = downloadable;
            return self();
        }

        /**
         *
         * @param downloads List of downloadable files.
         *                  See Product - Downloads properties
         * @return T
         */
        public T setDownloads(List<ProductDownload> downloads) {
            this.downloads = downloads;
            return self();
        }

        /**
         *
         * @param downloadLimit  Number of times downloadable files can be downloaded after purchase.
         *                        Default is -1.
         * @return T
         */
        public T setDownloadLimit(int downloadLimit) {
            this.downloadLimit = downloadLimit;
            return self();
        }

        /**
         *
         * @param downloadExpiry Number of days until access to downloadable files expires.
         *                        Default is -1.
         * @return T
         */
        public T setDownloadExpiry(int downloadExpiry) {
            this.downloadExpiry = downloadExpiry;
            return self();
        }
        /**
         *
         * @param taxStatus Tax status.
         *                   Options: taxable, shipping and none.
         *                   Default is taxable.
         * @return T
         */
        public T setTaxStatus(String taxStatus) {
            this.taxStatus = taxStatus;
            return self();
        }

        /**
         *
         * @param taxStatus Tax status.
         *                   Options: taxable, shipping and none.
         *                   Default is taxable.
         * @return T
         */
        public T setTaxStatus(uk.co.twinn.pl_wtx_woocommerce.model.Product.TaxStatusEnum taxStatus) {
            this.taxStatus = taxStatus.getValue();
            return self();
        }

        /**
         *
         * @param taxClass Tax class.
         * @return T
         */
        public T setTaxClass(String taxClass) {
            this.taxClass = taxClass;
            return self();
        }

        /**
         *
         * @param manageStock Stock management at product level. Default is false.
         * @return T
         */
        public T setManageStock(boolean manageStock) {
            this.manageStock = manageStock;
            return self();
        }

        /**
         *
         * @param stockQuantity Stock quantity.
         * @return T
         */
        public T setStockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
            return self();
        }

        /**
         *
         * @param stockStatus Controls the stock status of the product.
         *                     Options: instock, outofstock, onbackorder.
         *                     Default is instock.
         * @return T
         */
        public T setStockStatus(String stockStatus) {
            this.stockStatus = stockStatus;
            return self();
        }
        /**
         *
         * @param stockStatus Controls the stock status of the product.
         *                     Options: instock, outofstock, onbackorder.
         *                     Default is instock.
         * @return T
         */
        public T setStockStatus(uk.co.twinn.pl_wtx_woocommerce.model.Product.StockStatusEnum stockStatus) {
            this.stockStatus = stockStatus.getValue();
            return self();
        }

        /**
         *
         * @param backorders If managing stock, this controls if backorders are allowed.
         *                   Options: no, notify and yes.
         *                   Default is no.
         * @return T
         */
        public T setBackorders(String backorders) {
            this.backorders = backorders;
            return self();
        }
        /**
         *
         * @param weight Product weight.
         * @return T
         */
        public T setWeight(String weight) {
            this.weight = weight;
            return self();
        }

        /**
         *
         * @param dimensions Product dimensions. See Product - Dimensions properties
         * @return T
         */
        public T setDimensions(ProductDimension dimensions) {
            this.dimensions = dimensions;
            return self();
        }

        /**
         *
         * @param shippingClass Shipping class slug.
         * @return T
         */
        public T setShippingClass(String shippingClass) {
            this.shippingClass = shippingClass;
            return self();
        }

        /**
         *
         * @param attributes  List of attributes. See Product - Attributes properties
         * @return T
         */
        public T setAttributes(List<ProductAttribute> attributes) {
            this.attributes = attributes;
            return self();
        }

        /**
         *
         * @param menuOrder Menu order, used to custom sort products.
         * @return T
         */
        public T setMenuOrder(int menuOrder) {
            this.menuOrder = menuOrder;
            return self();
        }

        /**
         *
         * @param metaData  Meta data. See Product - Meta data properties
         * @return T
         */
        public T setMetaData(List<MetaData> metaData) {
            this.metaData = metaData;
            return self();
        }

    }

}
