/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 *
 */

package uk.co.twinn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

import uk.co.twinn.api.woocommerce.WooCommerce;
import uk.co.twinn.api.woocommerce.api.*;
import uk.co.twinn.api.woocommerce.response.*;

import uk.co.twinn.api.woocommerce.demonstration.CustomerDemo;
import uk.co.twinn.pl_wtx_woocommerce.model.billing.Billing;
import uk.co.twinn.pl_wtx_woocommerce.model.coupon.Coupon;
import uk.co.twinn.pl_wtx_woocommerce.model.customer.Customer;
import uk.co.twinn.pl_wtx_woocommerce.model.data.Continent;
import uk.co.twinn.pl_wtx_woocommerce.model.data.Country;
import uk.co.twinn.pl_wtx_woocommerce.model.data.Currency;
import uk.co.twinn.pl_wtx_woocommerce.model.order.Order;
import uk.co.twinn.pl_wtx_woocommerce.model.order.OrderLineItem;
import uk.co.twinn.pl_wtx_woocommerce.model.order.OrderShippingLine;
import uk.co.twinn.pl_wtx_woocommerce.model.payment.PaymentGateway;
import uk.co.twinn.pl_wtx_woocommerce.model.product.Product;
import uk.co.twinn.pl_wtx_woocommerce.model.product.ProductCategory;
import uk.co.twinn.pl_wtx_woocommerce.model.report.ReportOrderTotalSummary;
import uk.co.twinn.pl_wtx_woocommerce.model.shipping.Shipping;
import uk.co.twinn.pl_wtx_woocommerce.model.shipping.ShippingZoneLocation;

/**
 * WooCommerce API Client - Usage Demo
 *<br/>
 * A lot of Models from <a href="https://github.com/wtx-labs/woocommerce-api-client-java">https://github.com/wtx-labs/woocommerce-api-client-java</a>
 * license MIT
 */
public class WooCommerceApiClientUsageDemo {

    //

    /*private static final String API_BASE_PATH = "https://woocommerce/wp-json/wc/v3";
    private static final String API_USERNAME = "ck_1234567890ABCDEF01234567890ABCDEF0123456";
    private static final String API_PASSWORD = "cs_1234567890ABCDEF01234567890ABCDEF0123456";*/

    private static void data(){

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
        for (Currency currency : currencies.getResult()){
            System.out.println(currency.toJson());
        }

        Read<Currency> currency = WooCommerce.Data.readCurrency("gbp").getResponse();
        System.out.println(currency.getResult().toJson());
    }

    /** Scratchpad, initial testing zone.*/
    public static void main(String[] args) {

        data();


        /*System.out.println(">>> Start running the WooCommerceApiClientUsageDemo...");
        Listed<ShippingZone> zones = ShippingZones.listing().getResponse();

        for (ShippingZone zone : zones.getResult()){

            System.out.println(zone.toJson());*/

  /*      Listed<ShippingZoneMethod> zoneMethodList = ShippingZoneMethods.listing(2).getResponse();


        for (ShippingZoneMethod zoneMethod : zoneMethodList.getResult()) {
            System.out.println(zoneMethod.toJson());
        }
*/

        /*Listed<SystemStatusTool> systemStatusToolListed = SystemStatusTools.listing().getResponse();
        for (SystemStatusTool systemStatusTool : systemStatusToolListed.getResult()){

            System.out.println(systemStatusTool.toJson());

        }*/

        /*Listed<Continent> continents = WooCommerce.Data.listAllContinents().getResponse();
        for (Continent continent : continents.getResult()){
            System.out.println(continent.toJson());
        }*/


        /*Listed<Country> countries = WooCommerce.Data.listAllCountries().getResponse();
        for (Country continent : countries.getResult()){
            System.out.println(continent.toJson());
        }*/

        /*Listed<Currency> currencies = WooCommerce.Data.listAllCurrencies().getResponse();
        for (Currency continent : currencies.getResult()){
            System.out.println(continent.toJson());
        }

        Read<Continent> read = WooCommerce.Data.readContinent("eu").getResponse();
        System.out.println(read.getResult().toJson());*/

        //WooCommerce.Products.create()

        /*Read<SystemStatusTool> readTool = WooCommerce. SystemStatusTools.read("clear_transients").getResponse();

        System.out.println(readTool.getResult().toJson());

        Ran<SystemStatusTool> ranTool = SystemStatusTools.run("clear_transients").setConfirm(true).getResponse();

        System.out.println(ranTool.toJson());*/

        /*Read<SystemStatusTool> statustool = SystemStatuses.read().getResponse();
        System.out.println(status.getResult().toJson());*/


        /*Read<SystemStatus> status = SystemStatuses.read().getResponse();
        System.out.println(status.getResult().toJson());*/

       /* Listed<ShippingMethod> shippingMethods = ShippingMethods.listing().getResponse();
        for (ShippingMethod shippingMethod : shippingMethods.getResult()){

                System.out.println(shippingMethod.toJson());

        }*/


        /*Read<ShippingZoneMethod> zoneMethod= ShippingZoneMethods.read(2, 4).getResponse();
        System.out.println(zoneMethod.getResult().toJson());

        Updated<ShippingZoneMethod> zoneUpdated = ShippingZoneMethods
            .update(2, 4)
            .setEnabled(false)
            .setSetting("cost", "10")
            .getResponse();
        System.out.println(zoneUpdated.getResult().toJson());
        System.out.println(zoneUpdated.getResult().getEnabled());
        System.out.println(zoneUpdated.getResult().getSettings().get("cost").getValue());
*/
        /*}


        Listed<ShippingZoneMethod> list = ShippingZoneMethods.listing(1).getResponse();
        for (ShippingZoneMethod zoneMethod : list.getResult()){

            System.out.println(zoneMethod.toJson());
        }


        /*Created<ShippingZoneMethod> create = ShippingZoneMethods.create(2, "flat_rate").getResponse();
        System.out.println(create.toJson());*/

        /*ShippingZoneMethods.create(1, "flat_rate").getResponse();
        ShippingZoneMethods.update(1, "flat_rate").getResponse();*/

        System.exit(0);

        Message m = Authentication.Https().getResponse();
        System.out.println(m.getMessage());

        Read<Product> product = Products.read(315).getResponse();

        System.out.println(product.toJson());


        //System.exit(0);


        CustomerDemo customerDemo = new CustomerDemo();

        List<Customer> customers = customerDemo.listAllCustomers();
        for (Customer bc : customers){
            if (!bc.hasError()){
                System.out.println(bc.toString());
            }else{
                System.out.println("CFAIL:" + bc.getError().getMessage());
            }
        }

        Batched<Customer> batched = customerDemo.batchUpdateCustomers();
        if (batched.isSuccess()){
            System.out.println("The request was a success BUT cycle the records to check that they are!");

            for (Customer bc : batched.getResult().getCreated()){
                if (!bc.hasError()){
                    System.out.println(bc.toString());
                }else{
                    System.out.println("CFAIL:" + bc.getError().getMessage());
                }
            }
            for (Customer bc : batched.getResult().getUpdated()){
                if (!bc.hasError()){
                    System.out.println(bc.toString());
                }else{
                    System.out.println("UFAIL:" + bc.getError().getMessage());
                }

            }
            for (Customer bc : batched.getResult().getDeleted()){
                if (!bc.hasError()){
                    System.out.println(bc.toString());
                }else{
                    System.out.println("DFAIL:" + bc.getError().getMessage());
                }
            }
        }else{
            System.out.println(batched.getError().getMessage());
        }


        UpdatedList<ShippingZoneLocation> shipZones = ShippingZoneLocations.update(1)
            .setZoneLocation("BR:SP", "state")
            .setZoneLocation("BR:RJ", "state").getResponse();

        for (ShippingZoneLocation zoned : shipZones.getResult()){
            System.out.println(zoned.toString());
        }

        //Updated<ProductCategory> update = new ProductCategoryRequest.Updater<>().setId(346).setParent(0).getResponse();
/*
        if (update.isSuccess()) {
            System.out.println(update.toString());
        }*/
/*
        Listed<ProductCategory> root = ProductCategories.listing().setParentId(0).setPerPage(50).getResponse();

        if (root.isSuccess()) {
            for (ProductCategory p : root.getResult()) {

                System.out.println(p.toJson());

                Read<ProductCategory> categoryRead = ProductCategories.read(p.getId()).getResponse();

                if (categoryRead.isSuccess()){
                    if (categoryRead.getResult().getId().intValue() != p.getId().intValue()){
                        System.out.println("EXPECTED: " + p.getId() + " GOT: " + categoryRead.getResult().getId());
                        break;
                    }
                }else{
                    System.out.println("FAIL" + categoryRead.getError().getMessage());
                }
                System.out.println(categoryRead.getResult().toJson());
                //Updated<ProductCategory> updte = new ProductCategoryRequest.Updater<>().setId(p.getId()).setDisplay(ProductCategory.DisplayEnum.SUBCATEGORIES).getResponse();

                //if (updte.isSuccess()) {
                 //   System.out.println(updte.toString());
                //}
            }
        }else{
            System.out.println(root.getError().getMessage());
        }*/

        /*Created<Coupon> created = Coupons.create()
            .setCode("10off")
            .setDiscountType("percent")
            .setAmount(new BigDecimal(10))
            .setIndividualUse(true)
            .setExcludeSaleItems(true)
            .setMinimumAmount(new BigDecimal(100.00))
            .getResponse();

        Listed<Coupon> listed = Coupons.listing().getResponse();

        Batched<Coupon> batch = Coupons.batch()
                .addCreator(
                    Coupons.create()
                    .setCode("25off")
                    .setDiscountType("percent")
                    .setAmount(new BigDecimal(20))
                    .setIndividualUse(true)
                    .setExcludeSaleItems(true)
                    .setMinimumAmount(new BigDecimal(100.00)))
                .addUpdater(
                    Coupons.update(719)
                        .setMinimumAmount(new BigDecimal(50))
                )
                .addDeleter(
                    Coupons.delete(720, true)
                )
                .getResponse();
*/
        //Listed<Setting> listing = WooCommerce.Settings().listing().getResponse();

        /*Read<Product> read = WooCommerce.Products().read(1252).getResponse();*/
        /*for (Setting s : listing.getResult()){
            System.out.println(s.toJson().replace("},{", "},\n{"));
        }


        Read<SettingOption> read = WooCommerce.SettingOptions().read("general", "woocommerce_allowed_countries").getResponse();*/

        /*Read<Product> read = WooCommerce.Products().read(1252).getResponse();*/
        /*System.out.println(read.getResult().toJson().replace(",", ",\n"));


        Listed<SettingOption> listingOption = WooCommerce.SettingOptions().listing("general").getResponse();
*/
        /*Read<Product> read = WooCommerce.Products().read(1252).getResponse();*/
        /*for (SettingOption s : listingOption.getResult()){
            System.out.println(s.toJson().replace("},{", "},\n{"));
        }

        //WooCommerce.SettingOptions().update("general", "woocommerce_allowed_countries").setValue("all_except").getResponse();
        WooCommerce.SettingOptions().batch("general")
            .addUpdater(
                WooCommerce.SettingOptions()
                    .update("general","woocommerce_allowed_countries")
                    .setValue("all_except"))
            .getResponse();*/
        /*Created<Message> message = WooCommerce.OrderActions().sendEmail(123).getResponse();

        Read<Product> readP = new ProductApi().read(1).getResponse();
        Deleted<OrderRefund> deleted = WooCommerce.OrderRefunds().delete(1, 2, true).getResponse();

        //ProductApi.create()
        Created<Coupon> created2 = WooCommerce.Coupons().create()
            .setCode("10off")
            .setDiscountType("percent")
            .setAmount(new BigDecimal(10))
            .setIndividualUse(true)
            .setExcludeSaleItems(true)
            .setMinimumAmount(new BigDecimal(100.00))
            .getResponse();
*/
        //Read<Product> product ; //= new ProductRequest.Reader<>().setId(315).getResponse();

        //System.out.println(product.toJson());

        //product = new ProductRequest.Reader<>().setId(668).getResponse();

        //System.out.println(product.toJson());


        /*Read<Product> list = Products.read(1).getResponse();
        Read<Product> read = Products.read(1).getResponse();*/
        //Products.create().setUpsellIds().setTaxClass().setSku().getResponse()

        //Products.listing().setModifiedAfter(LocalDate.now()).setMaxPrice(BigDecimal.TEN).setSlug("").getResponse();
        //ProductVariations.listing().setContext().setProductId().setContext().setDownloadable()
        //System.out.println(list.toJson());
        Listed<ReportOrderTotalSummary> report = Reports.orderTotals().getResponse();

        //Orders.update(2).setBilling().setPaymentMethodTitle().
        System.out.println(report.toJson());

        for (ReportOrderTotalSummary i : report.getResult()){

            System.out.println(i.toString());

        }

        /*System.out.println(new TaxRateApi.ListAll<>().getResponse().toJson());
        System.out.println(new RefundsApi.ListAll<>().getResponse().toJson());*/

        Listed<PaymentGateway> gateways = PaymentGateways.listing().getResponse();

        for (PaymentGateway s : gateways.getResult()){
            System.out.println(s.toJson(true));
        }

        Read<PaymentGateway> gateway = PaymentGateways.read("cod").getResponse();

        Read<Customer> readCustomer = Customers.read(2).getResponse();

        System.out.println(gateway.getResult().toJson(true));

        /*Updated<PaymentGateway> update = PaymentGateways().update("woocommerce_payments")
                .setEnable(true).getResponse();*/

        //System.exit(0);

        /*String date = "2025-07-03T11:12:55Z";
        if (date.endsWith("+0000")) {
            date = date.substring(0, date.length()-5) + "Z";
        }
        if (date.length() > 19){
            date = date.substring(0, 19);
        }
        System.out.println(date);
        */



/*
        Read<Product> read = new ProductRequest.Reader<>().withId(2).getResponse();

        if (read.isSuccess()) {
            Product p = read.getRead();
        }

        System.out.println("---Coupons");
        Listed<Coupon> coupons = new CouponRequest.ListAll<>().getResponse();

        if (coupons.isSuccess()) {
            if (!coupons.getListed().isEmpty()) {

                for (Coupon coupon : coupons.getListed()) {
                    System.out.println(coupon.getCode());
                }

            } else {
                System.out.println("No coupons found");
            }
        }else{
            System.out.println(coupons.getError().getMessage());
        }

        Searched<Coupon> coup = new CouponRequest.Searcher<>().setCode("ABC").getResponse();

        if (coup.isSuccess()) {
            if (!coup.getSearched().isEmpty()) {

                for (Coupon coupon : coup.getSearched()) {
                    System.out.println(coupon.getCode());
                }

            } else {
                System.out.println("No coupons found");
            }
        }else{
            System.out.println(coup.getError().getMessage());
        }


        CustomerDemo customerDemo = new CustomerDemo();
        Customer customer = customerDemo.createACustomer();
        //example. Create a customer, if success then use the id to retrieve said customer.(I know we already have the customer but it is for demomstration!)
        // otherwise we will search based on the email.
        int id;
        if (customer != null) {
            id = customer.getId();
            customer = customerDemo.retrieveACustomer(id);
        }else {
            List<Customer> customers = customerDemo.retrieveCustomers("john.doe@example.com");
            if (!customers.isEmpty()) {
                id = customers.get(0).getId();
                customer = customerDemo.retrieveACustomer(id);
            }
        }

        if (customer != null) {
            System.out.println(customer.toString());
        }
*/

/*
        pleaseExplain();

        Searched<Customer> customerResponse =
            new CustomerRequest.Searcher<>()
                .setEmail("john.doe@example.com")
                .getResponse();

        if (customerResponse.isSuccess()) {
            if (!customerResponse.getSearched().isEmpty()) {
                for (Customer cust : customerResponse.getSearched()) {
                    System.out.println(cust.getFirstName() + " " + cust.getLastName() + " " + cust.getEmail());
                }
            }else{
                System.out.println("Sorry, but no customers were found");
            }
        }else{
            System.out.println(customerResponse.getError().getMessage());
        }
*/

       /* Updated<ProductCategory> category = new ProductCategoryRequest
            .Updater<>()
            .setId(17)
            .setImage("https://img.mackay.co.uk/i.php?width=256&image=category/Angle.2.png")
            .getResponse();

        if (category.isSuccess()){
            System.out.println(category.toJson());
        }else{
            System.out.println(category.getError().getMessage());
        }*/

        //Customer fred = new CustomerTestRequest.Reader<>().setId(1).getResponse().getResult();
        Coupons.read(1).getResponse();
        Coupons.delete(1, true).getResponse();
        Coupons.delete(1, true).getResponse();
        OrderNotes.delete(1,2, true).getResponse();

        Updated<ProductCategory> category2 = ProductCategories.update(17)
            .setImage("https://wordpress.mackay.co.uk/wp-content/uploads/media/256/category/Angle.2.png")
            .getResponse();

        if (category2.isSuccess()){
            System.out.println(category2.toJson());
        }else{
            System.out.println(category2.getError().getMessage());
        }

        //readProductCategory(17);
/*
        createAndReadProductCategories();

        crudPlusProducts();

        readProduct(315);

        // Use WooCommerceApiClient(true) if you need to log API communication messages.
        /*WooCommerceApiClient apiClient = new WooCommerceApiClient();

        apiClient.setBasePath(API_BASE_PATH);
        apiClient.setUsername(API_USERNAME);
        apiClient.setPassword(API_PASSWORD);

        CustomersApi customersApi = new CustomersApi(apiClient);

        try {
            List<Customer> customers = customersApi.listAllCustomers(null, null, null, null, null, null, null, null, null, null, null);


            // Example list of customer's emails:
            customers.forEach(customer -> System.out.println("Customer: " + customer.toString()));

        } catch (ApiException e) {
            System.err.println("Error occurred during API call: " + e);
        }*/


        System.out.println("<<< The WooCommerceApiClientUsageDemo has been finished.");

    }

    private static void createAndReadProductCategories() {

        Created<ProductCategory> createResponse =
            ProductCategories.create("NEW Category Name 123")
                .setDescription("Category Description")
                .setParent(0)
                //.image("TO DO: IMAGE MUST EXIST IN WORDPRESS, MEDIA UPLOAD REQUIRED OR USE EXISTING")
                .getResponse();

        if (createResponse.isSuccess()) {
            //System.out.println("Woo ;-) Hoo" + createResponse.toJson());

            try {
                System.out.println(">>> Reading back the category we just made");
                readProductCategory(createResponse.getResult().getId());
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            System.out.println(">>> Reading all categories...");
            readProductCategory(0);

        } else {

            System.out.println("COULD BE AS EXPECTED -> " + createResponse.getError().getMessage());

            readProductCategory(0);

        }
    }

    private static void readProductCategory(int id){

        System.out.println("+++++++ Read +++++++");
        //WooCategoryReadRequest.Builder<?> builder = new WooCategoryReadRequest.Builder<?>();

        if (id == 0){

            List<ProductCategory> categories =
                ProductCategories.listing()
                    .getResponse().getResult();

            for (ProductCategory productCategory : categories) {
                System.out.println(productCategory.toString());
            }

        }else{

            ProductCategory category =
                ProductCategories.read(id)
                    .getResponse().getResult();

                System.out.println(category.toString());

        }

    }

    private static void crudPlusProducts(){

        String sku = "A1234567";
        Created<Product> create = Products.create()
            .setSku(sku)
            .setName("My New Product")
            .setDescription("<b>This is a bold assertion</b><br/>Buy Five Get one Free!")
            .getResponse();

        int id = 0;

        if (create.isSuccess()){

            System.out.println("+++ Created Product -> check we have a product, maybe there was a parseFailure");

            if (create.hasResult()) {

                try {
                    id = create.getResult().getId();
                    printProduct(create.getResult());
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }

            readProduct(id);

        }else{

            System.out.println("QuiteLikely of we have already this SKU\n" + create.getError().getMessage());
            id = readProduct(0);

        }

        updateProduct(id, "Sold Out - Sorry one second special");

        seekProduct(sku);

        readProduct(id);

    }

    private static int readProduct(int id){

        if (id == 0) {

            Listed<Product> response = Products.listing()
                .getResponse();

            if (response.isSuccess()) {
                if (!response.getResult().isEmpty()) {
                    try {
                        id = response.getResult().get(0).getId();

                        System.out.println("+++ Multiple Products Found - id == 0");
                        for (Product product : response.getResult()) {
                            printProduct(product);
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("+++ Noithing Found");
                }
            }else{
                //Should always go here
                System.out.println(response.getError().getMessage());
            }

        }else{

            Product product = Products.read(id)
                .getResponse()
                .getResult();

            printProduct(product);

            try {
                Updated<Product> response = Products.update(product.getId())
                    .setSku("22221111")//.setMetaData(product.getMetaData())
                    .getResponse();

                if (response.isSuccess()) {
                    printProduct(response.getResult());
                    System.out.println("SUCCESS");
                } else {
                    System.out.println(response.getError().getMessage());
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        }

        return id;

    }

    private static void updateProduct(int id, String description){

        Updated<Product> updater = Products.update(id)
            .setDescription(description)
            .getResponse();

        if (updater.isSuccess()) {

            if (!updater.hasError()) {

                System.out.println("Woo ;-) Hoo\n UPDATE");
                //printProduct(updater.getProduct());

            } else {
                System.out.println(updater.getError().getMessage());
            }

            if (updater.hasResult()) {

                System.out.println("+++ Single Product Found");
                printProduct(updater.getResult());

            } else {

                System.out.println("+++ Successful retrive but no data found");

            }
        }else{
            System.out.println(updater.getError().getMessage());
        }

    }

    private static void seekProduct(String sku){

        Listed<Product> seeker = Products.listing()
            .setSku(sku)
            .getResponse();

        if (seeker.isSuccess()){

            System.out.println("+++ Seeked Product");

            for (Product p : seeker.getResult()) {
                printProduct(p);
            }

        }else{
            System.out.println(seeker.getError().getMessage());
        }

    }
    private static void printProduct(Product product){

        System.out.println(
            product.getId() + " " + product.getSku() + " "+ product.getDescription()
        );

        /*
        Lots of output
            System.out.println(
            product.toString()
        );

        if (!product.getMetaData().isEmpty()){

            System.out.println("FOUND! -> META!");

            for (MetaData meta : product.getMetaData()){

                System.out.println(meta.toString());

            }

        }else{

            System.out.println("NO META FOUND! - SHOULD BE META!");

        }*/

    }


    private static void pleaseExplain() {

        String ex =
            "Use the Configuraton class or not?\n" +
            "The Configuration expects a file to at: ~/.woocommerce-api/config.json\n" +
            "if this file exists you do not need to call Configuration, the program just deals with it\n\n" +

            "So what should this file contain?\n\n" +
            "{\n" +
            "\t\"website\" : \"myexamplewordpresssite.com\",\n" +
            "\t\"api\" : \"/wp-json/wc/v3/\",\n" +
            "\t\"key\" : \"ky_1234567890ABCDEF1234567890ABCDEF123456789\",\n" +
            "\t\"secret\": \"sh_1234567890ABCDEF1234567890ABCDEF123456789\"\n" +
            "}\n\n" +

            "but it does not have to exist there and you can use any of the following:\n" +

            "* -set the values by literal strings\n" +
            "new Configuration(\"myWebsite\", \"apiPath\", \"mykey\", \"mysecret\");\n" +
            "* -as above but use the preset api of /wp-json/wc/v3/ but then this could change in future releases\n" +
            "new Configuration(\"myWebsite\", \"mykey\", \"mysecret\");\n" +

            "* -read a file somewhere else\n" +
            "new Configuration(\"/literal/path/to/a/file/named/config.json\");\n" +

            "* -use a builder to set the values\n" +
            "new Configuration().Builder()\n" +
            "  .website(\"myWebsite\")\n" +
            "  .api(\"apiPath\")\n" +
            "  .key(\"key\")\n" +
            "  .secret(\"secret\")\n" +
            "  .build();\n" +

            "-------------------------------------------------------------------\n" +
            "Thank you for taking the time to read thus far\n" +
            "What follows is the API in action.......\n\n";

        System.out.println(ex);

    }
    public void Coupons() {
        /** Create **/
        Created<Coupon> created = Coupons.create()
            .setCode("10off")
            .setDiscountType("percent")
            .setAmount(new BigDecimal(10))
            .setIndividualUse(true)
            .setExcludeSaleItems(true)
            .setMinimumAmount(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP))
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

        Message message = Authentication.Https()
            .setWebsite("example.com")
            .setApiPath("/wp-json/wc/v3")
            .setKey("myverysecretkeythatIgotfrommywoocommerceinstallation")
            .setSecret("myverysecretsecretthatIgotfrommywoocommerceinstallation")
            .getResponse();


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
    }

}


