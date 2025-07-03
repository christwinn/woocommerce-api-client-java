package pl.wtx.woocommerce;

import java.util.*;

import pl.wtx.woocommerce.api.client.model.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.CouponRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.CustomerRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.ProductCategoryRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.request.ProductRequest;
import pl.wtx.woocommerce.crudPlusActionBuilder.response.*;
import pl.wtx.woocommerce.crudPlusActionBuilder.demonstration.CustomerDemo;

/**
 * WooCommerce API Client - Usage Demo
 * @author WTX Labs
 * @see "https://github.com/wtx-labs/woocommerce-api-client-java"
 * @license MIT
 */
public class WooCommerceApiClientUsageDemo {

    // TODO: Set your WooCommerce API base path!

    /*private static final String API_BASE_PATH = "https://woocommerce/wp-json/wc/v3";
    private static final String API_USERNAME = "ck_1234567890ABCDEF01234567890ABCDEF0123456";
    private static final String API_PASSWORD = "cs_1234567890ABCDEF01234567890ABCDEF0123456";*/

    /** Scratchpad, initial testing zone.*/
    public static void main(String[] args) {

        System.out.println(">>> Start running the WooCommerceApiClientUsageDemo...");

        String date = "2025-07-03T11:12:55Z";
        if (date.endsWith("+0000")) {
            date = date.substring(0, date.length()-5) + "Z";
        }
        if (date.length() > 19){
            date = date.substring(0, 19);
        }

        System.out.println(date);

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

        Batched<Customer> batched = customerDemo.batchUpdateCustomers();
        if (batched.isSuccess()){
            System.out.println("The request was a success BUT cycle the records to check that they are!");

            for (Customer bc : batched.getBatch().getCreated()){
                if (!bc.hasError()){
                    System.out.println(bc.toString());
                }else{
                    System.out.println("CFAIL:" + bc.getError().getMessage());
                }
            }
            for (Customer bc : batched.getBatch().getUpdated()){
                if (!bc.hasError()){
                    System.out.println(bc.toString());
                }else{
                    System.out.println("UFAIL:" + bc.getError().getMessage());
                }

            }
            for (Customer bc : batched.getBatch().getDeleted()){
                if (!bc.hasError()){
                    System.out.println(bc.toString());
                }else{
                    System.out.println("DFAIL:" + bc.getError().getMessage());
                }
            }
        }else{
            System.out.println(batched.getError().getMessage());
        }

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

        readProductCategory(0);
*/
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
            new ProductCategoryRequest.Creator<>()
                .setName("NEW Category Name 123")
                .setDescription("Category Description")
                .setParent(0)
                //.image("TO DO: IMAGE MUST EXIST IN WORDPRESS, MEDIA UPLOAD REQUIRED OR USE EXISTING")
                .getResponse();

        if (createResponse.isSuccess()) {
            //System.out.println("Woo ;-) Hoo" + createResponse.toJson());

            System.out.println(">>> Reading back the category we just made");
            readProductCategory(createResponse.getCreated().getId());

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
                new ProductCategoryRequest.ListAll<>()
                    .getResponse().getListed();

            for (ProductCategory productCategory : categories) {
                System.out.println(productCategory.toString());
            }

        }else{

            ProductCategory category =
                new ProductCategoryRequest.Reader<>()
                    .setId(id)
                    .getResponse().getRead();

                System.out.println(category.toString());

        }

    }

    private static void crudPlusProducts(){

        String sku = "A1234567";
        Created<Product> create = new ProductRequest.Creator<>()
            .setSku(sku)
            .setName("My New Product")
            .setDescription("<b>This is a bold assertion</b><br/>Buy Five Get one Free!")
            .getResponse();

        int id = 0;

        if (create.isSuccess()){

            System.out.println("+++ Created Product -> check we have a product, maybe there was a parseFailure");

            if (create.hasCreated()) {

                id = create.getCreated().getId();
                printProduct(create.getCreated());

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

            Searched<Product> response = new ProductRequest.Searcher<>()
                .getResponse();

            if (response.isSuccess()) {
                if (!response.getSearched().isEmpty()) {
                    id = response.getSearched().get(0).getId();
                    System.out.println("+++ Multiple Products Found - id == 0");
                    for (Product product : response.getSearched()) {
                        printProduct(product);
                    }
                } else {
                    System.out.println("+++ Noithing Found");
                }
            }else{
                //Should always go here
                System.out.println(response.getError().getMessage());
            }

        }else{

            Product product = new ProductRequest.Reader<>()
                .withId(id)
                .getResponse()
                .getRead();

            printProduct(product);

            Updated<Product> response = new ProductRequest.Updater<>()
                .setId(product.getId())
                .setSku("22221111")//.setMetaData(product.getMetaData())
                .getResponse();

            if (response.isSuccess()) {
                printProduct(response.getUpdated());
                System.out.println("SUCCESS");
            }else{
                System.out.println(response.getError().getMessage());
            }

        }

        return id;

    }

    private static void updateProduct(int id, String description){

        Updated<Product> updater = new ProductRequest.Updater<>()
            .setId(id)
            .setDescription(description)
            .getResponse();

        if (updater.isSuccess()) {

            if (!updater.hasError()) {

                System.out.println("Woo ;-) Hoo\n UPDATE");
                //printProduct(updater.getProduct());

            } else {
                System.out.println(updater.getError().getMessage());
            }

            if (updater.hasUpdated()) {

                System.out.println("+++ Single Product Found");
                printProduct(updater.getUpdated());

            } else {

                System.out.println("+++ Successful retrive but no data found");

            }
        }else{
            System.out.println(updater.getError().getMessage());
        }

    }

    private static void seekProduct(String sku){

        Searched<Product> seeker = new ProductRequest.Searcher<>()
            .setSku(sku)
            .getResponse();

        if (seeker.isSuccess()){

            System.out.println("+++ Seeked Product");

            for (Product p : seeker.getSearched()) {
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

        String ex = "The notion of CRUD Create, Read, Update, Delete is well establised.\n" +
            "Many methods for Creating API's there are, but these all focus on the generation.\n" +
            "There appears to be a lack of coherent thinking on the API Consumption side.\n" +
            "\n" +
            "Here we introduce using the simple concept of CRUD+\n" +
            "Whats's that?\n\n" +

            "OK so each server API has it's objects and operations, distilling down to OBJECT+CRUD+OTHERS, whichever way you chuck a ball at it.\n" +
            "So building upon Builder classes and using fluent programming ideas, and sticking to the set and get principles we have \n" +
            " [Object]Request\n" +
            "   [Basically Builders, we use inheritance therefore we need the <>, even those that are base are forced to <> for consistency]\n" +
            "   .Creator<>()\n" +
            "   .Reader<>()\n" +
            "   .Updater<>()\n" +
            "   .Deleter<>()\n" +
            "   [and now the plus]\n" +
            "   .Seeker<>()\n" +
            "   .Batcher<>()\n" +
            "   .WhateverIAmTryingToAcheive<>()\n\n" +

            "Each Builder only conveys what you can do with it, \n" +
            " e.g. in Creator<>() you can not set the Id, therefore the option to setId is not available.\n" +
            "   but in Updater<>() we need to set the Id, therefore the option to setId is available. (Updater is an extension of Creator)\n\n" +

            "For the final piece of the jigsaw we have a partner [Object]Response\n" +

            "Core construction is \n" +
            "getSuccess() -> simply have we succeeded\n" +
            "getStatus() -> integer response code from the remote server\n" +

            "Rather than force a [Object] and [Object]s hierarchy we supply a\n" +
            "has[Object] and has[Object]s inside the Response\n" +
            "and of course: \n" +
            "get[Object] and get[Object]s, " +
            "usually whether you get one or multiples will be defined by the API but you could get either!\n\n" +

            "Example: create a product: \n" +
            "ProductResponse result = new ProductRequest.Creator<>()\n" +
            "\t.setSku(\"12345678\")\n" +
            "\t.setName(\"My New Product Name\")\n" +
            "\t.setDescription(\"<b>This is a bold assertion</b><br/>Buy Five Get one Free!\")\n" +
            "\t.getResponse();\n" +

            "We can then check our result with: \n" +
            "if (result.getSuccess()){\n" +
            "\tif (result.hasProduct()){\n" +
            "\t\tSystem.out.println(return result.getProduct().getId());\n" +
            "\t}else\t" +
            "\t\tSystem.out.println(\"Send to server succeeded but we have failed to parse the result \" + result.getError().getMessage());\n" +
            "\t}\n" +
            "}else{" +
            "\tSystem.out.println(result.getError().getMessage());\n"+
            "}\n" +

            "-------------------------------------------------------------------\n" +

            "OK Great, but how do I set up my user details. Glad you asked: \n" +
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
            "What follows is the API in action.......\n\n" +

            "";

        System.out.println(ex);

    }

}


