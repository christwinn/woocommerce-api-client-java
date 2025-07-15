/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce;

import uk.co.twinn.api.woocommerce.request.*;

/**
 *
 * Purely a convenient utility class to allow nice easy fluent access to the API
 *  Allows us to write:
 *  <p>Read&lt;Product&gt; read = WooCommerce.Products().read(productId).getResponse();</p>
 *  <p><i>Capitalised methods as they are class interfaces and so deserve to be capitalised</i>
 *  as opposed to:</p>
 *  <p>Read&lt;Product&gt; read = new ProductApi.Reader&lt;&gt;(productId).getResponse();</p>
 *  Pick your poison....
 *
 *  <p>Aim is to provide easy simple access to the specialist builders in each [Object]Api
 *  Calling create, update, read .... on each [Object]Api which returns us a new [Builder].</p>
 *
 *  <p>Each Specialist Builder (Creator, Reader, Updater, Deleter, ListAll, Batcher) is
 *  rigid in what is allowed and parameters that can be set.</p>
 *  Mandatory parameters must be provided in the constructors.<br>
 *  Optionals are set using standard setX(value).
 *
 */
public final class WooCommerce {

    private WooCommerce(){}

    //lazily load these as required
    private static AuthenticationApi authenticationApi = null;
    private static CouponApi couponApi = null;
    private static CustomerApi customerApi = null;
    private static CustomerDownloadApi customerDownloadApi = null;
    private static OrderActionApi orderActionApi = null;
    private static OrderApi orderApi = null;
    private static OrderNoteApi orderNoteApi = null;
    private static OrderRefundApi orderRefundApi = null;
    private static ProductApi productApi = null;
    private static ProductAttributeApi productAttributeApi = null;
    private static ProductAttributeTermApi productAttributeTermApi = null;
    private static ProductCategoryApi productCategoryApi = null;
    private static ProductCustomFieldApi productCustomFieldApi = null;
    private static ProductReviewApi productReviewApi = null;
    private static ProductShippingClassApi productShippingClassApi = null;
    private static ProductTagApi productTagApi = null;
    private static ProductVariationApi productVariationApi = null;
    private static RefundsApi refundsApi = null;
    private static ReportApi reportsApi = null;
    private static SettingsApi settingsApi = null;
    private static SettingOptionsApi settingOptionsApi = null;
    private static TaxRateApi taxRateApi = null;
    private static TaxClassApi taxClassApi = null;
    private static WebHooksApi webHooksApi = null;



    public static AuthenticationApi Authentication(){
        if (authenticationApi == null){
            authenticationApi = new AuthenticationApi();
        }
        return authenticationApi;
    }

    public static CouponApi Coupons(){
        if (couponApi == null){
            couponApi = new CouponApi();
        }
        return couponApi;
    }

    public static CustomerApi Customers(){
        if (customerApi == null){
            customerApi = new CustomerApi();
        }
        return customerApi;
    }

    public static CustomerDownloadApi CustomerDownloads(){
        if (customerDownloadApi == null){
            customerDownloadApi = new CustomerDownloadApi();
        }
        return customerDownloadApi;
    }

    public static OrderApi Orders(){
        if (orderApi == null){
            orderApi = new OrderApi();
        }
        return orderApi;
    }

    public static OrderActionApi OrderActions(){
        if (orderActionApi == null){
            orderActionApi = new OrderActionApi();
        }
        return orderActionApi;
    }

    public static OrderNoteApi OrderNotes(){
        if (orderNoteApi == null){
            orderNoteApi = new OrderNoteApi();
        }
        return orderNoteApi;
    }

    public static OrderRefundApi OrderRefunds(){
        if (orderRefundApi == null){
            orderRefundApi = new OrderRefundApi();
        }
        return orderRefundApi;
    }

    public static ProductApi Products(){
        if (productApi == null){
            productApi = new ProductApi();
        }
        return productApi;
    }

    public static ProductAttributeApi ProductAttributes(){
        if (productAttributeApi == null){
            productAttributeApi = new ProductAttributeApi();
        }
        return productAttributeApi;
    }

    public static ProductAttributeTermApi ProductAttributeTerms(){
        if (productAttributeTermApi == null){
            productAttributeTermApi = new ProductAttributeTermApi();
        }
        return productAttributeTermApi;
    }

    public static ProductCategoryApi ProductCategory(){
        if (productCategoryApi == null){
            productCategoryApi = new ProductCategoryApi();
        }
        return productCategoryApi;
    }

    public static ProductCustomFieldApi ProductCustomFields(){
        if (productCustomFieldApi == null){
            productCustomFieldApi = new ProductCustomFieldApi();
        }
        return productCustomFieldApi;
    }

    public static ProductReviewApi ProductReviews(){
        if (productReviewApi == null){
            productReviewApi = new ProductReviewApi();
        }
        return productReviewApi;
    }

    public static ProductShippingClassApi ProductShipping(){
        if (productShippingClassApi == null){
            productShippingClassApi = new ProductShippingClassApi();
        }
        return productShippingClassApi;
    }

    public static ProductTagApi ProductTags(){
        if (productTagApi == null){
            productTagApi = new ProductTagApi();
        }
        return productTagApi;
    }

    public static ProductVariationApi ProductVariations(){
        if (productVariationApi == null){
            productVariationApi = new ProductVariationApi();
        }
        return productVariationApi;
    }

    public static RefundsApi Refunds(){
        if (refundsApi == null){
            refundsApi = new RefundsApi();
        }
        return refundsApi;
    }

    public static ReportApi Reports(){
        if (reportsApi == null){
            reportsApi = new ReportApi();
        }
        return reportsApi;
    }

    public static SettingsApi Settings(){
        if (settingsApi == null){
            settingsApi = new SettingsApi();
        }
        return settingsApi;
    }

    public static SettingOptionsApi SettingOptions(){
        if (settingOptionsApi == null){
            settingOptionsApi = new SettingOptionsApi();
        }
        return settingOptionsApi;
    }

    public static TaxRateApi TaxRates(){
        if (taxRateApi == null){
            taxRateApi = new TaxRateApi();
        }
        return taxRateApi;
    }

    public static TaxClassApi TaxClass(){
        if (taxClassApi == null){
            taxClassApi = new TaxClassApi();
        }
        return taxClassApi;
    }

    public static WebHooksApi Webhooks(){
        if (webHooksApi == null){
            webHooksApi = new WebHooksApi();
        }
        return webHooksApi;
    }

}
