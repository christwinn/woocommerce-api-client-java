
# A fluent client WooCommerce Java Api

A deceptively simple java interface to the WooCommerce API

The samples shown are the same as [https://woocommerce.github.io/woocommerce-rest-api-docs/](https://woocommerce.github.io/woocommerce-rest-api-docs/) but using this WooCommerce API Java Client

##### Code Sample Index  
[Authentication](README.md#authentication)

[Coupons](README.md#coupons)

[Customers](README.md#customers)

[Payment Gateways](README.md#payment-gateways)

[Reports](README.md#reports)

[Settings](README.md#settings)

[SettingOptions](README.md#setting-options)

Implemented, awaiting manual
<ul>
    <li>CustomerDownloads</li>
<li>OrderActions</li>
<li>Orders</li>
<li>OrderNotes</li>
<li>OrderRefunds</li>
<li>Products</li>
<li>ProductAttributes</li>
<li>ProductAttributeTerms</li>
<li>ProductCategory</li>
<li>ProductCustomFields</li>
<li>ProductReviews</li>
<li>ProductShippingClasses</li>
<li>ProductTags</li>
<li>ProductVariations</li>
<li>Refunds</li>
<li>TaxClasses</li>
<li>TaxRates</li>
<li>Webhooks</li>
</ul>


## [Authentication](#authentication) 
It is assumed you have obtained the relevant credentials as per [https://woocommerce.github.io/woocommerce-rest-api-docs/#rest-api-keys](https://woocommerce.github.io/woocommerce-rest-api-docs/#rest-api-keys)
<details>
<summary>Example methods to Authenticate when using the WooCommerce API</summary>
    
```java
private void authentication(){

    /** The simplest method is to place a file under ~/woocommerce-api/config.json 
    {
       "website": "example.com",
       "api": "/wp-json/wc/v3",
       "key": "myverysecretkeythatIgotfrommywoocommerceinstallation",
       "secret": "myverysecretsecretthatIgotfrommywoocommerceinstallation"
    }
    **/

    /**Alternatively we can...*/
    Message message = WooCommerce.Authentication()
        .https().
        .setWebsite("example.com")
        .setApiPath("/wp-json/wc/v3")
        .setKey("myverysecretkeythatIgotfrommywoocommerceinstallation")
        .setSecret("myverysecretsecretthatIgotfrommywoocommerceinstallation")
        .getResponse();

    /** Please note we have not implemented "Authentication over HTTP". We should not be retrieving customer detail's unencrypted **/
}
```

</details>

## [Coupons](#coupons) 
Example as per [https://woocommerce.github.io/woocommerce-rest-api-docs/#coupons](https://woocommerce.github.io/woocommerce-rest-api-docs/#coupons)

<details>
<summary>Example code to Create, Read, Update, Delete, Batch and get listed Coupons using the WooCommerce API</summary>
    
```java
private void coupons() {
    /** Create **/
    Created<Coupon> created = WooCommerce.Coupons().create()
                        .setCode("10off")
                        .setDiscountType("percent")
                        .setAmount(new BigDecimal(10))
                        .setIndividualUse(true)
                        .setExcludeSaleItems(true)
                        .setMinimumAmount(new BigDecimal(100.00))
                        .getResponse();
    /**Read**/
    Read<Coupon> read = WooCommerce.Coupons().read(719).getResponse();

    /**Update**/
    Updated<Coupon> updated = WooCommerce.Coupons().update(719)
                        .setAmount(new BigDecimal(15))
                        .getResponse();
    /**Delete**/
    Deleted<Coupon> deleted = WooCommerce.Coupons().delete(719, true).getResponse();

    /**List All**/
    Listed<Coupon> listed = WooCommerce.Coupons().listing().getResponse();

    /** Batch [Create, Update, Delete]**/
    Batched<Coupon> batched = WooCommerce.Coupons().batch()
                        .addCreator(
                            WooCommerce.Coupons().create()
                                .setCode("20off")
                                .setDiscountType("percent")
                                .setAmount(new BigDecimal(20))
                                .setIndividualUse(true)
                                .setExcludeSaleItems(true)
                                .setMinimumAmount(new BigDecimal(100.00))
                        )
                        .addCreator(
                            WooCommerce.Coupons().create()
                                .setCode("30off")
                                .setDiscountType("percent")
                                .setAmount(new BigDecimal(30))
                                .setIndividualUse(true)
                                .setExcludeSaleItems(true)
                                .setMinimumAmount(new BigDecimal(400.00))
                        )
                        .addUpdater(
                            WooCommerce.Coupons().update(719)
                                .setMinimumAmount(new BigDecimal(50))
                        )
                        .addDeleter(
                            WooCommerce.Coupons().delete(720, true)
                        )
                        .getResponse();
}
```
</details>

## [Customers](#customers)
Example as per [https://woocommerce.github.io/woocommerce-rest-api-docs/#customers](https://woocommerce.github.io/woocommerce-rest-api-docs/#customers)

<details>
<summary>Example code to Create, Read, Update, Delete, Batch and get listed Customers using the WooCommerce API</summary>
    
```java
private void customers() {
    /** Create **/
    Created<Customer> created = WooCommerce.Customers().create()
                        .setEmail("john.doe@example.co")
                        .setFirstName("John")
                        .setLastName("Doe")
                        .setUsername("john.doe")
                        .setPassword("shh")
                        .setBilling(new Billing()
                            .firstName("John")
                            .lastName("Doe")
                            .company("")
                            .address1("969 Market Street")
                            .address2("")
                            .city("San Fancisco")
                            .state("CA")
                            .postcode("94103")
                            .email("john.doe@example.com")
                            .phone("(555) 555-5555")
                        ).setShipping(new Shipping()
                            .firstName("John")
                            .lastName("Doe")
                            .company("")
                            .address1("969 Market")
                            .address2("")
                            .city("San Francisco")
                            .state("CA")
                            .postcode("94103")
                            .country("US")
                        )
                        .getResponse();
    /**Read**/
    Read<Customer> read = WooCommerce.Customers().read(25).getResponse();

    /**Update**/
    Updated<Customer> updated = WooCommerce.Customers().update(25)
                        .setFirstName("James")
                        .setShipping(
                            new Shipping().firstName("James")
                        )
                        .getResponse();
    /**Delete**/
    Deleted<Customer> deleted = WooCommerce.Customers().delete(25, true).getResponse();

    /**List All**/
    Listed<Customer> listed = WooCommerce.Customers().listing().getResponse();

    /**Search**/
    Listed<Customer> listed = WooCommerce.Customers().listing().setEmail("john.doe@example.com").getResponse();

    /** Batch [Create, Update, Delete]**/
    Batched<Customer> batched = WooCommerce.Customers().batch()
                        .addCreator(
                            WooCommerce.Customers().create()
                                .setEmail("jane.doe@example.com")
                                .setFirstName("Jane")
                                .setLastName("Doe")
                                .setUsername("jane.doe")
                                .setBilling(new Billing()
                                    .firstName("Jane")
                                    .lastName("Doe")
                                    .company("")
                                    .address1("969 Market Street")
                                    .address2("")
                                    .city("San Fancisco")
                                    .state("CA")
                                    .postcode("94103")
                                    .email("jane.doe@example.com")
                                    .phone("(555) 555-5555")
                                )
                                .setShipping(new Shipping()
                                    .firstName("John")
                                    .lastName("Doe")
                                    .company("")
                                    .address1("969 Market")
                                    .address2("")
                                    .city("San Francisco")
                                    .state("CA")
                                    .postcode("94103")
                                    .country("US")
                                )
                        )
                        .addCreator(
                            WooCommerce.Customers().create()
                                .setEmail("joao.silva2@example.com")
                                .setFirstName("Joao")
                                .setLastName("Silva")
                                .setUsername("joao.silva")
                                .setBilling(new Billing()
                                    .firstName("Joao")
                                    .lastName("Silva")
                                    .company("")
                                    .address1("Av. Brasil, 43")
                                    .address2("")
                                    .city("Rio de Janeiro")
                                    .state("RJ")
                                    .postcode("12345-000")
                                    .country("BR")
                                    .email("joao.silva2@example.com")
                                    .phone("(555) 555-5555")
                                )
                                .setShipping(new Shipping()
                                    .firstName("Joao")
                                    .lastName("Silva")
                                    .company("")
                                    .address1("Av. Brasil, 43")
                                    .address2("")
                                    .city("Rio de Janeiro")
                                    .state("RJ")
                                    .postcode("12345-000")
                                    .country("BR")
                                )
                        )
                        .addUpdater(
                            WooCommerce.Customers().update(26)
                                .setBilling(new Billing()
                                    .phone("(11) 1111-1111")
                        )
                        .addDeleter(
                            WooCommerce.Customers().delete(21, true)
                        )
                        .getResponse();
}
```

</details>

## [Payment Gateways](#payment-gateways)

Example as per [https://woocommerce.github.io/woocommerce-rest-api-docs/#payment-gateways](https://woocommerce.github.io/woocommerce-rest-api-docs/#payment-gateways)

<details>
<summary>Example code to list and update Payment Gateways using the WooCommerce API</summary>
    
```java
private void paymentGateways() {

    /*Read*/
    Read<PaymentGateway> gateway = WooCommerce.PaymentGateways().read("woocommerce_payments").getResponse();
    System.out.println(gateway.getResult().toJson(true)); //pretty print

    /**List All**/
    Listed<PaymentGateway> gateways = WooCommerce.PaymentGateways().listing().getResponse();
    for (PaymentGateway s : gateways.getResult()){
        System.out.println(s.toJson(true)); //pretty print
    }

    /*Update*/
    Updated<PaymentGateway> update = WooCommerce.PaymentGateways().update("woocommerce_payments")
                .setEnable(true).getResponse();
}
```
</details>

## [Reports](#reports)

Example as per [https://woocommerce.github.io/woocommerce-rest-api-docs/#reports](https://woocommerce.github.io/woocommerce-rest-api-docs/#reports)

<details>
<summary>Example code to retrieve the Reports using the WooCommerce API</summary>
    
```java
private void reports() {

    /*List All reports*/
    Listed<ReportListItem> list = WooCommerce.Reports().getList();
    System.out.println(list.toJson());

    /*Retrieve sales report*/
    Listed<ReportSalesSummary> sales = WooCommerce.Reports().getSalesSummary()
    
    /*Retrieve top sellers report*/
    Listed<ReportTopSellersItem> topSellers = WooCommerce.Reports().getTopSellers();

    /*Retrieve coupons totals*/
    Listed<ReportOrderTotalSummary> coupons = WooCommerce.Reports().getCouponsTotals();

    /*Retrieve customers totals*/
    Listed<ReportOrderTotalSummary> customers = WooCommerce.Reports().getCustomersTotals();

    /*Retrieve orders totals*/
    Listed<ReportOrderTotalSummary> orders = WooCommerce.Reports().getOrdersTotals();

    /*Retrieve products totals*/
    Listed<ReportOrderTotalSummary> products = WooCommerce.Reports().getProductsTotals();

    /*Retrieve reviews totals*/
    Listed<ReportOrderTotalSummary> reviews = WooCommerce.Reports().getReviewsTotals();

}
```
</details>

## [Settings](#settings)
Example as per [https://woocommerce.github.io/woocommerce-rest-api-docs/#settings](https://woocommerce.github.io/woocommerce-rest-api-docs/#settings)

<details>
<summary>Example code to list Settings using the WooCommerce API</summary>
    
```java
private void settings() {

    /**List All**/
    Listed<Setting> listing = WooCommerce.Settings().listing().getResponse();
    for (Setting s : listing.getResult()){
        System.out.println(s.toJson().replace("},{", "},\n{"));
    }
}
```
</details>

## [SettingOptions](#setting-options)
Example as per [https://woocommerce.github.io/woocommerce-rest-api-docs/#setting-option-properties](https://woocommerce.github.io/woocommerce-rest-api-docs/#setting-option-properties)

<details>
<summary>Example code to read/ update Settings using the WooCommerce API</summary>
    
```java
private void settingOptions() {

    /** Read a specific option**/
    Read<SettingOption> read = WooCommerce.SettingOptions()
            .read("general", "woocommerce_allowed_countries")
            .getResponse();
    System.out.println(read.getResult().toJson());

    /**List All**/
    Listed<SettingOption> listingOption = WooCommerce.SettingOptions()
            .listing("general")
            .getResponse();
    for (SettingOption s : listingOption.getResult()){
        System.out.println(s.toJson().replace("},{", "},\n{"));
    }

    /**Update**/
    WooCommerce.SettingOptions()
        .update("general", "woocommerce_allowed_countries")
        .setValue("all_except")
        .getResponse();

    /*Batch*/
    WooCommerce.SettingOptions().batch("general")
        .addUpdater(
            WooCommerce.SettingOptions()
                .update("ignoredInBatch", "woocommerce_allowed_countries")
                .setValue("all_except")
        )
        .getResponse();

}
```
</details>

# Why the Fork?

We are no longer a fork of [https://github.com/wtx-labs/woocommerce-api-client-java](https://github.com/wtx-labs/woocommerce-api-client-java)
We drifted too far apart. 

# WooCommerce API Client for Java

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-8%2B-blue)](https://www.java.com)

A lightweight Java client library for WooCommerce REST API integration. Built for Java developers who need to integrate their applications with WooCommerce e-commerce platform.

This API client provides a type-safe Java interface for WooCommerce REST API v3, enabling seamless management of:
- WooCommerce products and inventory
- Customer data and orders
- E-commerce operations via REST API

## ✨ Why choose this client?

- 💡 **Type-Safe Java API** - fully typed interfaces for WooCommerce REST endpoints
- 🛡️ **Basic authentication** - secure WooCommerce API access
- 📚 **Clear documentation** - comprehensive examples for Java integration
- 🚀 **Wide Java support** - compatible with Java 8 and newer
- ⚡ **OpenAPI Generated** - based on our [OpenAPI specification](https://github.com/wtx-labs/woocommerce-api-openapi-specification) developed from official WooCommerce documentation

## 🚨 Project status

> ⚠️ **Note: This is a development version!**
> 
> We are actively implementing more WooCommerce API features.
> Contributions and feedback are welcome on GitHub!

## 📦 Version information

- **Current version**: `0.1.0`
- **Supported WooCommerce API version**: `v3`
- **Java compatibility**: Java 8+

## 🔓 License

**MIT License**

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files, to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the software.

The only requirement is to preserve the original author attribution in the source code and documentation.

### 1️⃣ Installation

You can add the library to your project by including the dependency from Maven Central:
```xml
<!-- https://mvnrepository.com/artifact/pl.wtx.woocommerce/woocommerce-api-client -->
<dependency>
    <groupId>pl.wtx.woocommerce</groupId>
    <artifactId>woocommerce-api-client</artifactId>
    <version>0.9.7</version>
</dependency>
```

Alternatively, clone and build the library from source:

```sh
git clone https://github.com/wtx-labs/woocommerce-api-client-java.git
cd woocommerce-api-client-java
mvn clean install
```

Then add the locally built artifact to your project:

```xml
<dependency>
    <groupId>pl.wtx.woocommerce</groupId>
    <artifactId>woocommerce-api-client</artifactId>
    <version>0.9.7</version>
</dependency>
```

## 🔗 Get involved

- ✨ Check our [GitHub Issues](https://github.com/christwinn/woocommerce-api-client-java/issues) for latest updates
- 💡 Have suggestions? Open an Issue or contribute to the project
- 🌟 Star this repository if you find it helpful!

## 📊 Project statistics

- 🔄 Regular updates and improvements
- 👥 Open for community contributions

## 🔍 Keywords

woocommerce java client, woocommerce rest api java, java woocommerce integration, woocommerce api v3 java, e-commerce java integration, woocommerce java library, java rest api client woocommerce, woocommerce api client library for java

