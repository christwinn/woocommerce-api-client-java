
# A fluent java client interface to the  WooCommerce REST Api

A fully implemented lightweight, deceptively simple, fluent Java client library for the WooCommerce REST API v3.

Built for Java developers who need to quickly integrate their applications with the WooCommerce e-commerce platform.

The samples shown are the same as [https://woocommerce.github.io/woocommerce-rest-api-docs/](https://woocommerce.github.io/woocommerce-rest-api-docs/) but using this WooCommerce API Java Client

##### Installation

```xml
<dependency>
    <groupId>uk.co.twinn.api</groupId>
    <artifactId>woocommerce-api-client</artifactId>
    <version>0.1.1</version>
</dependency>
```

##### Reaction = action

Newton's Third Law of Motion: "For every action, there is an equal and opposite reaction"

```java

/** Generic system setup **/

/** Following the simple methodology: Reaction<SingularType> = PluralType.action.getResponse(); 
 *  More specific examples are provided under the 'PluralType' sections further down.
 *
 * Builder patterns can also be accessed via WooCommerce.PluralType.create()/read()/etc for extra simplicity.
 *   e.g. WooCommerce.Products.read(123).getResponse();
 *
 **/

/**Create**/
Created<SingularType> created = PluralType.create(anyMandatoryValues).setX("...").getResponse();
Created<SingularType> created = PluralType.create(aSingularObjectWeCreatedEarlier).getResponse();

/**Read**/
Read<SingularType> read = PluralType.read(123).getResponse();

/**Update**/
Updated<SingularType> updated = PluralType.update(123).setX("...").getResponse();
Updated<SingularType> updated = PluralType.update(aSingularObjectWeCreatedEarlier).getResponse();

/**Delete**/
Deleted<SingularType> deleted = PluralType.delete(123, true).getResponse();

/**List All**/
Listed<SingularType> listed = PluralType.listing().getResponse();

/** Batch [Create, Update, Delete]**/
Batched<SingularType> batched = PluralType.batch()
        .addCreator(PluralType.create().setX("..."))
        .addCreator(PluralType.create(aSingularObjectWeCreatedEarlier))
        .addUpdater(PluralType.update().setX("..."))
        .addUpdater(PluralType.update(aSingularObjectWeCreatedEarlier))
        .addDeleter(PluralType.delete(123, true))
        .getResponse();

/**
    The left hand contains
    .isSuccess() -> did it work;

    Created, Read, Updated, Deleted will contain a single SingularType in .getResult(),
        i.e SingularType result = [created, read, updated, deleted].getResult();

    Listed will contain a list of SingularType in .getResult(), 
        i.e List<SingularType> result = listed.getResult();
        Really this is "search" with no parameters and only limited results are returned [default 10], 
            you need to increment the offset to retrieve all the result)

    Batched will contain a list of SingularType in .getResult() under the requested action.
        i.e.
            List<SingularType> created = batched.getResult().getCreated();
            List<SingularType> updated = batched.getResult().getUpdated();
            List<SingularType> deleted = batched.getResult().getDeleted();

        Warning! While the action of batch() may be a success,
                 you should loop the lists to check that each record
                 actually succeeded and the API did not reject that request.
        
        Note: WooCommerce limits you to 100 objects per batch, having run experiments this is a very hopeful limit.
            You could experience PHP running out of memory, isSuccess() will be false and an error message 500 will be returned in the .getError().getMessage(), try smaller batch sizes.

    result.getLinks() -> 
        HAL - Hypertext Application Language 
        <a href="https://stateless.group/hal_specification.html">https://stateless.group/hal_specification.html</a>
**/

```

##### Code Samples

## Authentication
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
    Message message = Authentication.https()
        .setWebsite("example.com")
        .setApiPath("/wp-json/wc/v3")
        .setKey("myverysecretkeythatIgotfrommywoocommerceinstallation")
        .setSecret("myverysecretsecretthatIgotfrommywoocommerceinstallation")
        .getResponse();

    /** Please note we have not implemented "Authentication over HTTP". We should not be retrieving customer detail's unencrypted **/
}
```

</details>

## Coupons 
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#coupons](https://woocommerce.github.io/woocommerce-rest-api-docs/#coupons)

<details>
<summary>Example code to Create, Read, Update, Delete, Batch and get listed Coupons using the WooCommerce API</summary>
    
```java
private void coupons() {
    /** Create **/
    Created<Coupon> created = Coupons.create()
                        .setCode("10off")
                        .setDiscountType("percent")
                        .setAmount(new BigDecimal(10))
                        .setIndividualUse(true)
                        .setExcludeSaleItems(true)
                        .setMinimumAmount(new BigDecimal(100.00))
                        .getResponse();
    /**Read**/
    Read<Coupon> read = Coupons.read(719).getResponse();

    /**Update**/
    Updated<Coupon> updated = Coupons.update(719)
                        .setAmount(new BigDecimal(15))
                        .getResponse();
    /**Delete**/
    Deleted<Coupon> deleted = Coupons.delete(719, true).getResponse();

    /**List All**/
    Listed<Coupon> listed = Coupons.listing().getResponse();

    /** Batch [Create, Update, Delete]**/
    Batched<Coupon> batched = Coupons.batch()
                        .addCreator(
                            Coupons.create()
                                .setCode("20off")
                                .setDiscountType("percent")
                                .setAmount(new BigDecimal(20))
                                .setIndividualUse(true)
                                .setExcludeSaleItems(true)
                                .setMinimumAmount(new BigDecimal(100.00))
                        )
                        .addCreator(
                            Coupons.create()
                                .setCode("30off")
                                .setDiscountType("percent")
                                .setAmount(new BigDecimal(30))
                                .setIndividualUse(true)
                                .setExcludeSaleItems(true)
                                .setMinimumAmount(new BigDecimal(400.00))
                        )
                        .addUpdater(
                            Coupons.update(719)
                                .setMinimumAmount(new BigDecimal(50))
                        )
                        .addDeleter(
                            Coupons.delete(720, true)
                        )
                        .getResponse();
}
```
</details>

## Customers
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#customers](https://woocommerce.github.io/woocommerce-rest-api-docs/#customers)

<details>
<summary>Example code to Create, Read, Update, Delete, Batch and get listed Customers using the WooCommerce API</summary>
    
```java
private void customers() {
    /** Create **/
    Created<Customer> created = Customers.create()
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
    /**Read (id of customer)**/
    Read<Customer> read = Customers.read(25).getResponse();

    /**Update**/
    Updated<Customer> updated = Customers.update(25)
                        .setFirstName("James")
                        .setShipping(
                            new Shipping().firstName("James")
                        )
                        .getResponse();
    /**Delete**/
    Deleted<Customer> deleted = Customers.delete(25, true).getResponse();

    /**List All**/
    Listed<Customer> listed = Customers.listing().getResponse();

    /**Search**/
    Listed<Customer> listed = Customers.listing().setEmail("john.doe@example.com").getResponse();

    /** Batch [Create, Update, Delete]**/
    Batched<Customer> batched = Customers.batch()
                        .addCreator(
                            Customers.create()
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
                            Customers.create()
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
                            Customers.update(26)
                                .setBilling(
                                    new Billing()
                                    .phone("(11) 1111-1111")
                                )
                        )
                        .addDeleter(
                            Customers.delete(21, true)
                        )
                        .getResponse();
}
```
</details>

## Orders
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#orders](https://woocommerce.github.io/woocommerce-rest-api-docs/#orders)

<details>
<summary>Example code to for Orders using the WooCommerce API</summary>

```java
private void orders(){  
    
    /** Create **/
    Created<Order> creator = Orders.create()
            .setPaymentMethod("bacs")
            .setPaymentMethodTitle("Direct Bank Transfer")
            .setPaid(true)
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
            .setLineItems(
                    Arrays.asList(
                            new OrderLineItem().productId(93).quantity(2),
                            new OrderLineItem().productId(22).variationId(23).quantity(2)
                    )
            )
            .setShippingLines(
                    Stream.of(
                        new OrderShippingLine().methodId("flat_rate")
                            .methodTitle("Flat Rate")
                            .total(new BigDecimal(10))
                    ).collect(Collectors.toList())
            ).getResponse();

    /** Read **/
    Read<Order> read = Orders.read(123).getResponse();
    
    /** Update **/
    Updated<Order> updater = Orders.update(123).setStatus("completed").getResponse();

    /** Delete**/
    Deleted<Order> deleted = Orders.delete(123, true).getResponse();

    /** List All **/
    Listed<Order> listed = Orders.listing().getResponse();

    /** Batch Update of orders */
    Batched<Order> batched = Orders.batch()
            .addCreator(
                    Orders.create()
                            .setPaymentMethod("bacs")
                            .setPaymentMethodTitle("Direct Bank Transfer")
                            .setPaid(true)
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
                            .setLineItems(
                                    Arrays.asList(
                                            new OrderLineItem().productId(93).quantity(2),
                                            new OrderLineItem().productId(22).variationId(23).quantity(2)
                                    )
                            )
                            .setShippingLines(
                                    Stream.of(
                                            new OrderShippingLine().methodId("flat_rate")
                                                    .methodTitle("Flat Rate")
                                                    .total(new BigDecimal(10))
                                    ).collect(Collectors.toList())
                            )
            )
            .addCreator(
                    Orders.create(anOrderWeMadeEarlier)
            )
            .addUpdater(
                    Orders.update(727)
                            .setStatus("completed")

            )
            .addDeleter(
                    Orders.delete(723, true)
            )
            .getResponse();
    
}
```
</details>

## Order Actions
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#order-actions](https://woocommerce.github.io/woocommerce-rest-api-docs/#order-actions)

<details>
<summary>Example code for Order Actions using the WooCommerce API</summary>

```java
import uk.co.twinn.api.woocommerce.api.OrderActions;

private void orderActions(int customerId) {

    /** Send Email **/
    Created<Message> created = OrderActions.sendEmail(customerId).getResponse();
    
}    
```
</details>

## Order Notes
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#order-notes](https://woocommerce.github.io/woocommerce-rest-api-docs/#order-notes)
<details>
<summary>Example code for Order Notes using the WooCommerce API</summary>
    
```java
private void orderNotes(int orderId, int noteId){

    Created<OrderNote> created = OrderNotes.create(orderId).setNote("Hello World").getResponse();
    
    Read<OrderNote> read = OrderNotes.read(orderId, noteId).getResponse();

    Listed<OrderNote> list = OrderNotes.listing(orderId).getResponse();
    
    Deleted<OrderNote> deleted = OrderNotes.delete(orderId, noteId, true).getResponse();
    
}    
```
</details>

## Order Refunds
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#order-refunds](https://woocommerce.github.io/woocommerce-rest-api-docs/#order-refunds)
<details>
<summary>Example code for Order Refunds using the WooCommerce API</summary>

```java
private void orderRefunds(){

}
```
</details>

## Products
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#products](https://woocommerce.github.io/woocommerce-rest-api-docs/#products)
<details>
    <summary>Example code for Products using the WooCommerce API</summary>

```java
import uk.co.twinn.api.woocommerce.api.Products;
import uk.co.twinn.api.woocommerce.response.core.BatchResult;

import java.math.BigDecimal;

private void products() {

    /*Create*/
    Created<Product> created = Products.create()
            .setName("Premium Quality")
            .setType("simple")
            .setRegularPrice(new BigDecimal(21.99))
            .setDescription("Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. " +
                    "Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. " +
                    "Donec eu libero sit amet quam egestas semper. " +
                    "Aenean ultricies mi vitae est. Mauris placerat eleifend leo.")
            .setShortDescription("Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.")
            .setCategories(
                    Arrays.asList(
                            new ProductCategoriesItem().id(9),
                            new ProductCategoriesItem().id(14)
                    )
            )
            .setImage("http://mysite/woocommerce/wp-content/uploads/sites/56/2013/06/T_2_back.jpg")
            .getResponse();

    Read<Product> read = Products.read(123).getResponse();

    Updated<Product> updated = Products.update(123).setRegularPrice(new BigDecimal(9.99)).getResponse();

    Duplicated<Product> duplicated = Products.duplicate(123).getResponse();

    Deleted<Product> deleted = Products.delete(123, true).getResponse();

    Listed<Product> list = Products.listing().getResponse();

    Batched<Product> batched = Products.batch()
            .addCreator(
                Products.create()
                    .setName("New Premium Quality 2")
                    .setType("simple")
                    .setRegularPrice(new BigDecimal(42.99))
                    .setDescription("Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. " +
                            "Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. " +
                            "Donec eu libero sit amet quam egestas semper. " +
                            "Aenean ultricies mi vitae est. Mauris placerat eleifend leo.")
                    .setShortDescription("Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.")
                    .setCategories(
                            Arrays.asList(
                                    new ProductCategoriesItem().id(9),
                                    new ProductCategoriesItem().id(14)
                            )
                    )
                    .setImage("http://mysite/woocommerce/wp-content/uploads/sites/56/2013/06/T_2_back.jpg")
            )
            .addUpdater(
                Products.update(234).setMenuOrder(2).setName("Modified Title")
            )
            .addDeleter(
                Products.delete(235, false)
            )
            .getResponse();

}    
```
</details>

## Product Variations
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#product-variations](https://woocommerce.github.io/woocommerce-rest-api-docs/#product-variations)

<details>
    <summary>Example code for Product Variations using the WooCommerce API</summary>

```java
private void productVariations(){
}    
```
</details>

## Product Attributes

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#product-attributes](https://woocommerce.github.io/woocommerce-rest-api-docs/#product-attributes)

<details>
    <summary>Example code for Product Attributes using the WooCommerce API</summary>

```java
private void productAttributes(){
}        
```
</details>

## Product Attribute Terms

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#product-attribute-terms](https://woocommerce.github.io/woocommerce-rest-api-docs/#product-attribute-terms)

<details>
    <summary>Example code for Product Attribute Terms using the WooCommerce API</summary>

```java
private void productAttributeTerms(){
}    
```
</details>

## Product Categories

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#product-categories](https://woocommerce.github.io/woocommerce-rest-api-docs/#product-categories)

<details>
    <summary>Example code for Product Categories using the WooCommerce API</summary>

```java
private void productCategories(){
}    
```
</details>

## Product Custom Fields

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#product-custom-fields](https://woocommerce.github.io/woocommerce-rest-api-docs/#product-custom-fields)

<details>
    <summary>Example code for Product Custom Fields using the WooCommerce API</summary>

```java
private void productCustomFields(){
}    
```
</details>

## Product Shipping Classes

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#product-shipping-classes](https://woocommerce.github.io/woocommerce-rest-api-docs/#product-shipping-classes)

<details>
    <summary>Example code for Product Shipping Classes using the WooCommerce API</summary>

```java
private void productShippingClasses(){
}    
```
</details>

## Product Tags

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#product-tags](https://woocommerce.github.io/woocommerce-rest-api-docs/#product-tags)

<details>
    <summary>Example code for Product Tags using the WooCommerce API</summary>

```java
private void productTags(){
}    
```
</details>

## Product Reviews

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#product-reviews](https://woocommerce.github.io/woocommerce-rest-api-docs/#product-reviews)

<details>
    <summary>Example code for Product Reviews using the WooCommerce API</summary>

```java
private void productReviews(){
}    
```
</details>

## Reports

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#reports](https://woocommerce.github.io/woocommerce-rest-api-docs/#reports)

<details>
<summary>Example code to retrieve the Reports using the WooCommerce API</summary>

```java
private void reports() {

    /*List All reports*/
    Listed<ReportListItem> list = Reports.listAll().getResponse();
    System.out.println(list.getResult().toJson());

    /*Retrieve sales report*/
    Listed<ReportSalesSummary> sales = Reports.salesSummary().getResponse();
    
    /*Retrieve top sellers report*/
    Listed<ReportTopSellersItem> topSellers = Reports.topSellers().getResponse();

    /*Retrieve coupons totals*/
    Listed<ReportOrderTotalSummary> coupons = Reports.couponsTotals().getResponse();

    /*Retrieve customers totals*/
    Listed<ReportOrderTotalSummary> customers = Reports.customersTotals().getResponse();

    /*Retrieve orders totals*/
    Listed<ReportOrderTotalSummary> orders = Reports.ordersTotals().getResponse();

    /*Retrieve products totals*/
    Listed<ReportOrderTotalSummary> products = Reports.productsTotals().getResponse();

    /*Retrieve reviews totals*/
    Listed<ReportOrderTotalSummary> reviews = Reports.reviewsTotals().getResponse();

}
```
</details>

## Refunds

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#refunds](https://woocommerce.github.io/woocommerce-rest-api-docs/#refunds)

<details>
    <summary>Example code for Refunds using the WooCommerce API</summary>

```java
private void refunds(){
}    
```
</details>


## Tax Rates

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#tax-rates](https://woocommerce.github.io/woocommerce-rest-api-docs/#tax-rates)

<details>
    <summary>Example code for Tax Rates using the WooCommerce API</summary>

```java
private void taxRates(){
    
    
    
}    
```
</details>

## Tax Classes

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#tax-classes](https://woocommerce.github.io/woocommerce-rest-api-docs/#tax-classes)

<details>
    <summary>Example code for Tax Classes using the WooCommerce API</summary>

```java
private void taxClasses(){
}    
```
</details>

## Webhooks

Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#webhooks](https://woocommerce.github.io/woocommerce-rest-api-docs/#webhooks)

<details>
    <summary>Example code for Webhooks using the WooCommerce API</summary>

```java

private void webhooks() {

    Created<Webhook> created = Webhooks.create("order.updated", "http://requestb.in/1g0sxmo1").setName("Order Updat!").getResponse();

    Read<Webhook> read = Webhooks.read(2).getResponse();

    Listed<Webhook> listed = Webhooks.listing().getResponse();

    Updated<Webhook> updated = Webhooks.update(2).setName("Order Updated").getResponse();

    Deleted<Webhook> deleted = Webhooks.delete(2, true).getResponse();

    BatchResult<Webhook> batched = Webhooks.batch()
            .addCreator(
                    Webhooks.create("coupon.created", "http://requestb.in/1g0sxmo1").setName("Coupon created")
            )
            .addCreator(
                    Webhooks.create("customer.deleted", "http://requestb.in/1g0sxmo1").setName("Customer deleted")
            )
            .addDeleter(
                    Webhooks.delete(143, true)
            )
            .getResponse();

}    
```
</details>

## Settings
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#settings](https://woocommerce.github.io/woocommerce-rest-api-docs/#settings)

<details>
<summary>Example code to list Settings using the WooCommerce API</summary>
    
```java
private void settings() {

    /**List All**/
    Listed<Setting> listing = Settings.listing().getResponse();
    for (Setting s : listing.getResult()){
        System.out.println(s.toJson().replace("},{", "},\n{"));
    }
}
```
</details>

## SettingOptions
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#setting-option-properties](https://woocommerce.github.io/woocommerce-rest-api-docs/#setting-option-properties)

<details>
<summary>Example code to read/ update Settings using the WooCommerce API</summary>
    
```java
private void settingOptions() {

    /** Read a specific option**/
    Read<SettingOption> read = SettingOptions
            .read("general", "woocommerce_allowed_countries")
            .getResponse();
    System.out.println(read.getResult().toJson());

    /**List All**/
    Listed<SettingOption> listingOption = SettingOptions
            .listing("general")
            .getResponse();
    for (SettingOption s : listingOption.getResult()){
        System.out.println(s.toJson().replace("},{", "},\n{"));
    }

    /**Update**/
    Update<SettingOption> updated = SettingOptions
        .update("general", "woocommerce_allowed_countries")
        .setValue("all_except")
        .getResponse();

    /*Batch*/
    Batched<SettingOption> batched = SettingOptions.batch("general")
        .addUpdater(
            WooCommerce.SettingOptions()
                .update("ignoredInBatch", "woocommerce_allowed_countries")
                .setValue("all_except")
        )
        .getResponse();

}
```
</details>


## Payment Gateways
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#payment-gateways](https://woocommerce.github.io/woocommerce-rest-api-docs/#payment-gateways)

<details>
<summary>Example code to list and update Payment Gateways using the WooCommerce API</summary>

```java
private void paymentGateways() {

    /*Read*/
    Read<PaymentGateway> gateway = PaymentGateways.read("woocommerce_payments").getResponse();
    System.out.println(gateway.getResult().toJson()); 

    /**List All**/
    Listed<PaymentGateway> gateways = PaymentGateways.listing().getResponse();
    for (PaymentGateway s : gateways.getResult()){
        System.out.println(s.toJson()); 
    }

    /*Update*/
    Updated<PaymentGateway> update = PaymentGateways.update("woocommerce_payments")
                .setEnable(true).getResponse();
}
```
</details>


## Shipping Zones
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#shipping-zones](https://woocommerce.github.io/woocommerce-rest-api-docs/#shipping-zones)

<details>
    <summary>Example code for Shipping Zones using the WooCommerce API</summary>

```java
private void shippingZones() {

    Created<ShippingZone> created = ShippingZones.create("Brazil").getResponse();
    
    Read<ShippingZone> read = ShippingZones.read(5).getResponse();

    Listed<ShippingZone> zones = ShippingZones.listing().getResponse();

    for (ShippingZone zone : zones.getResult()) {
        System.out.println(zone.toJson());
    }

    Updated<ShippingZone> updated = ShippingZones.update(1).setOrder(2).getResponse();

    Deleted<ShippingZone> deleted = ShippingZones.delete(1, true).getResponse();

}    
```
</details>


## Shipping Zone Locations
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#shipping-zone-locations](https://woocommerce.github.io/woocommerce-rest-api-docs/#shipping-zone-locations)

<details>
    <summary>Example code for Shipping Zone Locations using the WooCommerce API</summary>

```java
private void shippingZoneLocations() {

    Listed<ShippingZoneLocation> listed = ShippingZoneLocations.listing(1).getResponse();

    UpdatedList<ShippingZoneLocation> shipZones = ShippingZoneLocations.update(1)
            .setZoneLocation("BR:SP", "state")
            .setZoneLocation("BR:RJ", "state").getResponse();

    for (ShippingZoneLocation zoned : shipZones.getResult()) {
        System.out.println(zoned.toString());
    }

}    
```
</details>


## Shipping Zone Methods
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#shipping-zone-methods](https://woocommerce.github.io/woocommerce-rest-api-docs/#shipping-zone-methods)

<details>
    <summary>Example code for Shipping Zone Methods using the WooCommerce API</summary>

```java
private void shippingZoneMethods() {

    Created<ShippingZoneMethod> create = ShippingZoneMethods.create(2, "flat_rate").getResponse();

    Read<ShippingZoneMethod> zoneMethod = ShippingZoneMethods.read(2, 4).getResponse();
    System.out.println(zoneMethod.getResult().toJson());

    Listed<ShippingZoneMethod> list = ShippingZoneMethods.listing(1).getResponse();
    for (ShippingZoneMethod zoneMethod : list.getResult()) {
        System.out.println(zoneMethod.toJson());
    }

    Updated<ShippingZoneMethod> zoneUpdated = ShippingZoneMethods
            .update(2, 4)
            .setEnabled(false)
            .setSetting("cost", "10")
            .getResponse();

    Deleted<ShippingZoneMethod> zoneMethodDeleted = ShippingZoneMethods.delete(2, 4).getResponse();

}    
```
</details>


## System Status
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#system-status](https://woocommerce.github.io/woocommerce-rest-api-docs/#system-status)

<details>
    <summary>Example code for System Status using the WooCommerce API</summary>

```java
private void systemStatus(){

    Read<SystemState> status = SystemStatus.read().getResponse();
    
}    
```
</details>

## System Status Tools
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#system-status-tools](https://woocommerce.github.io/woocommerce-rest-api-docs/#system-status-tools)
<details>
<summary>Example code for System Status Tools using the WooCommerce API</summary>

```java
private void systemStatusTools(){

    Listed<SystemStatusTool> systemStatusToolListed = SystemStatusTools.listing().getResponse();
    for (SystemStatusTool systemStatusTool : systemStatusToolListed.getResult()){
        System.out.println(systemStatusTool.toJson());
    }

    Read<SystemStatusTool> readTool = WooCommerce. SystemStatusTools.read("clear_transients").getResponse();

    Ran<SystemStatusTool> ranTool = SystemStatusTools.run("clear_transients").setConfirm(true).getResponse();
    
}
```
</details>

## Data
Ref: [https://woocommerce.github.io/woocommerce-rest-api-docs/#data](https://woocommerce.github.io/woocommerce-rest-api-docs/#data)

<details>
    <summary>Example code for Data using the WooCommerce API</summary>

```java
private void data(){

    Listed<Continent> continents = WooCommerce.Data.listAllContinents().getResponse();
    for (Continent continent : continents.getResult()){
        System.out.println(continent.toJson());
    }

    Read<Continent> continent = WooCommerce.Data.readContinent("eu").getResponse();
    System.out.println(continent.getResult().toJson());

    Listed<Country> countries = WooCommerce.Data.listAllCountries().getResponse();
    for (Country country : countries.getResult()){
        System.out.println(country.toJson());
    }

    Read<Country> country = WooCommerce.Data.readCountry("gb").getResponse();
    System.out.println(country.getResult().toJson());

    Listed<Currency> currencies = WooCommerce.Data.listAllCurrencies().getResponse();
    for (Currency continent : currencies.getResult()){
        System.out.println(continent.toJson());
    }

    Read<Currency> currency = WooCommerce.Data.readCurrency("gbp").getResponse();
    System.out.println(currency.getResult().toJson());
}    
```
</details>

# WooCommerce API Client for Java

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-8%2B-blue)](https://www.java.com)

A lightweight Java client library for WooCommerce REST API integration. Built for Java developers who need to integrate their applications with WooCommerce e-commerce platform.

This API client provides a type-safe Java interface for WooCommerce REST API v3, enabling seamless management of:
- WooCommerce
- Customer data and orders
- E-commerce operations via REST API

## ✨ Why choose this client?

- 💡 **Type-Safe Java API** - fully typed interfaces for WooCommerce REST endpoints
- 🛡️ **Basic authentication** - secure WooCommerce API access
- 📚 **Clear documentation** - comprehensive examples for Java integration
- 🚀 **Wide Java support** - compatible with Java 8 and newer
- ⚡ **OpenAPI Generated** - based on our [OpenAPI specification](https://github.com/wtx-labs/woocommerce-api-openapi-specification) developed from official WooCommerce documentation

## 📦 Version information

- **Current version**: `0.1.0`
- **Supported WooCommerce API version**: `v3`
- **Java compatibility**: Java 8+

## 🔓 License

**MIT License**

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files, to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the software.

The only requirement is to preserve the original author attribution in the source code and documentation.

## 🔗 Get involved

- ✨ Check our [GitHub Issues](https://github.com/christwinn/woocommerce-api-client-java/issues) for latest updates
- 💡 Have suggestions? Open an Issue or contribute to the project
- 🌟 Star this repository if you find it helpful!

## 📊 Project statistics

- 🔄 Regular updates and improvements
- 👥 Open for community contributions

## 🔍 Keywords

woocommerce java client, woocommerce rest api java, java woocommerce integration, woocommerce api v3 java, e-commerce java integration, woocommerce java library, java rest api client woocommerce, woocommerce api client library for java

