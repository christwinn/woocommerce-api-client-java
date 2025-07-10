/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.request;

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
class CoreProductRequest {

    /**
     * Implements core elements to ProductRequest and ProductVariationRequest
     * in an ideal world we would have a "friend" option, similar to package-private
     * but only the next inheritor can see or say package+1-private
     *
     * If I move to core package, where it really should be, then the protected can not be seen.
     * all Core* are package-private
     *
     * Sets and Gets only do NOT use.
    **/
    public static class Creator<T extends Creator> {

        protected String description;    //string	Product description.
        protected String sku;    //string	Unique identifier.
        protected BigDecimal regularPrice;    //string	Product regular price.
        protected BigDecimal salePrice;    //string	Product sale price.
        protected LocalDateTime date_on_sale_from;    //date-time	Start date of sale price, in the site's timezone.
        protected LocalDateTime date_on_sale_from_gmt;    //date-time	Start date of sale price, as GMT.
        protected LocalDateTime date_on_sale_to;    //date-time	End date of sale price, in the site's timezone.
        protected LocalDateTime date_on_sale_to_gmt;    //date-time	End date of sale price, as GMT.
        protected String status;    //string	Product status (post status). Options: draft, pending, protected and publish. Default is publish.
        protected Boolean virtual;    //boolean	If the product is virtual. Default is false.
        protected Boolean downloadable;    //boolean	If the product is downloadable. Default is false.
        protected List<ProductDownload> downloads;    //array	List of downloadable files. See Product - Downloads properties
        protected Integer download_limit;    //integer	Number of times downloadable files can be downloaded after purchase. Default is -1.
        protected Integer download_expiry;    //integer	Number of days until access to downloadable files expires. Default is -1.
        protected String tax_status;    //string	Tax status. Options: taxable, shipping and none. Default is taxable.
        protected String tax_class;    //string	Tax class.
        protected Boolean manage_stock;    //boolean	Stock management at product level. Default is false.
        protected Integer stock_quantity;    //integer	Stock quantity.
        protected String stock_status;    //string	Controls the stock status of the product. Options: instock, outofstock, onbackorder. Default is instock.
        protected String backorders;    //string	If managing stock, this controls if backorders are allowed. Options: no, notify and yes. Default is no.
        protected String weight;    //string	Product weight.
        protected ProductDimension dimensions;    //object	Product dimensions. See Product - Dimensions properties
        protected String shipping_class;    //string	Shipping class slug.
        protected List<ProductAttribute>attributes;    //array	List of attributes. See Product - Attributes properties
        protected Integer menu_order;    //integer	Menu order, used to custom sort products.

        protected List<MetaData> meta_data;    //array	Meta data. See Product - Meta data properties

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
         * @param regular_price Product regular price.
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
         * @param sale_price  Product sale price.
         * @return T
         */
        public T setSalePrice(BigDecimal salePrice) {
            this.salePrice = salePrice;
            return self();
        }

        /**
         *
         * @param date_on_sale_from Start date of sale price, in the site's timezone.
         * @return T
         */
        public T setDateOnSaleFrom(LocalDateTime date_on_sale_from) {
            this.date_on_sale_from = date_on_sale_from;
            return self();
        }

        /**
         *
         * @param date_on_sale_from_gmt Start date of sale price, as GMT.
         * @return T
         */
        public T setDateOnSaleFromGMT(LocalDateTime date_on_sale_from_gmt) {
            this.date_on_sale_from_gmt = date_on_sale_from_gmt;
            return self();
        }

        /**
         *
         * @param date_on_sale_to End date of sale price, in the site's timezone.
         * @return T
         */
        public T setDateOnSaleTo(LocalDateTime date_on_sale_to) {
            this.date_on_sale_to = date_on_sale_to;
            return self();
        }

        /**
         *
         * @param date_on_sale_to_gmt  End date of sale price, as GMT.
         * @return T
         */
        public T setDateOnSaleToGMT(LocalDateTime date_on_sale_to_gmt) {
            this.date_on_sale_to_gmt = date_on_sale_to_gmt;
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
         * @param download_limit  Number of times downloadable files can be downloaded after purchase.
         *                        Default is -1.
         * @return T
         */
        public T setDownloadLimit(int download_limit) {
            this.download_limit = download_limit;
            return self();
        }

        /**
         *
         * @param download_expiry Number of days until access to downloadable files expires.
         *                        Default is -1.
         * @return T
         */
        public T setDownloadExpiry(int download_expiry) {
            this.download_expiry = download_expiry;
            return self();
        }
        /**
         *
         * @param tax_status Tax status.
         *                   Options: taxable, shipping and none.
         *                   Default is taxable.
         * @return T
         */
        public T setTaxStatus(String tax_status) {
            this.tax_status = tax_status;
            return self();
        }

        /**
         *
         * @param tax_status Tax status.
         *                   Options: taxable, shipping and none.
         *                   Default is taxable.
         * @return T
         */
        public T setTaxStatus(uk.co.twinn.pl_wtx_woocommerce.model.Product.TaxStatusEnum tax_status) {
            this.tax_status = tax_status.getValue();
            return self();
        }

        /**
         *
         * @param tax_class Tax class.
         * @return T
         */
        public T setTaxClass(String tax_class) {
            this.tax_class = tax_class;
            return self();
        }

        /**
         *
         * @param manage_stock Stock management at product level. Default is false.
         * @return T
         */
        public T setManageStock(boolean manage_stock) {
            this.manage_stock = manage_stock;
            return self();
        }

        /**
         *
         * @param stock_quantity Stock quantity.
         * @return T
         */
        public T setStockQuantity(int stock_quantity) {
            this.stock_quantity = stock_quantity;
            return self();
        }

        /**
         *
         * @param stock_status Controls the stock status of the product.
         *                     Options: instock, outofstock, onbackorder.
         *                     Default is instock.
         * @return T
         */
        public T setStockStatus(String stock_status) {
            this.stock_status = stock_status;
            return self();
        }
        /**
         *
         * @param stock_status Controls the stock status of the product.
         *                     Options: instock, outofstock, onbackorder.
         *                     Default is instock.
         * @return T
         */
        public T setStockStatus(uk.co.twinn.pl_wtx_woocommerce.model.Product.StockStatusEnum stock_status) {
            this.stock_status = stock_status.getValue();
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
         * @param shipping_class Shipping class slug.
         * @return T
         */
        public T setShippingClass(String shipping_class) {
            this.shipping_class = shipping_class;
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
         * @param menu_order Menu order, used to custom sort products.
         * @return T
         */
        public T setMenuOrder(int menu_order) {
            this.menu_order = menu_order;
            return self();
        }

        /**
         *
         * @param meta_data  Meta data. See Product - Meta data properties
         * @return T
         */
        public T setMetaData(List<MetaData> meta_data) {
            this.meta_data = meta_data;
            return self();
        }

    }

}
