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

import java.util.*;

import uk.co.twinn.api.woocommerce.request.*;
import uk.co.twinn.api.woocommerce.response.*;

import uk.co.twinn.pl_wtx_woocommerce.model.Customer;
import uk.co.twinn.pl_wtx_woocommerce.model.Product;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductCategory;

import uk.co.twinn.api.woocommerce.demonstration.CustomerDemo;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductVariation;

/**
 * WooCommerce API Client - Usage Demo
 * @author WTX Labs
 * @see "https://github.com/wtx-labs/woocommerce-api-client-java"
 * license MIT
 */
public class WooCommerceApiClientUsageDemo {

    //

    /*private static final String API_BASE_PATH = "https://woocommerce/wp-json/wc/v3";
    private static final String API_USERNAME = "ck_1234567890ABCDEF01234567890ABCDEF0123456";
    private static final String API_PASSWORD = "cs_1234567890ABCDEF01234567890ABCDEF0123456";*/

    /** Scratchpad, initial testing zone.*/
    public static void main(String[] args) {

        System.out.println(">>> Start running the WooCommerceApiClientUsageDemo...");
        /*Updated<ProductCategory> update = new ProductCategoryRequest.Updater<>().setId(346).setParent(0).getResponse();

        if (update.isSuccess()) {
            System.out.println(update.toString());
        }*/

        /*Listed<ProductCategory> root = new ProductCategoryRequest.ListAll<>().setParentId(0).setPerPage(50).getResponse();

        for (ProductCategory p : root.getResult()) {

            System.out.println(p.getDisplayAsString());

            Updated<ProductCategory> updte = new ProductCategoryRequest.Updater<>().setId(p.getId()).setDisplay(ProductCategory.DisplayEnum.SUBCATEGORIES).getResponse();

            if (updte.isSuccess()) {
                System.out.println(updte.toString());
            }
        }*/
        Read<Product> product = new ProductRequest.Reader<>().setId(315).getResponse();

        //System.out.println(product.toJson());

        product = new ProductRequest.Reader<>().setId(668).getResponse();

        System.out.println(product.toJson());

       System.exit(0);


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
        CustomerDemo customerDemo = new CustomerDemo();

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
        new CouponRequest.Reader<>().setId(1).getResponse();
        new CouponRequest.Deleter<>().setForce(true).setId(1).setForce(false).getResponse();

        new CouponRequest.Deleter<>().setId(1).getResponse();

        new OrderNoteRequest.Deleter<>().setNoteId(1).setId(2).setNoteId(1).getResponse();

        Updated<ProductCategory> category2 = new ProductCategoryRequest
            .Updater<>()
            .setId(17)
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
            new ProductCategoryRequest.Creator<>()
                .setName("NEW Category Name 123")
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
                new ProductCategoryRequest.ListAll<>()
                    .getResponse().getResult();

            for (ProductCategory productCategory : categories) {
                System.out.println(productCategory.toString());
            }

        }else{

            ProductCategory category =
                new ProductCategoryRequest.Reader<>()
                    .setId(id)
                    .getResponse().getResult();

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

            Listed<Product> response = new ProductRequest.ListAll<>()
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

            Product product = new ProductRequest.Reader<>()
                .setId(id)
                .getResponse()
                .getResult();

            printProduct(product);

            try {
                Updated<Product> response = new ProductRequest.Updater<>()
                    .setId(product.getId())
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

        Listed<Product> seeker = new ProductRequest.ListAll<>()
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

        String ex = "The notion of CRUD Create, Read, Update, Delete is well establised.\n" +
            "Many methods for Creating API's there are, but these all focus on the generation.\n" +
            "There appears to be a lack of coherent thinking on the API Consumption side.\n" +
            "\n" +
            "Here we introduce using the simple concept of CRUD+\n" +
            "Whats's that?\n\n" +

            "OK so each server API has it's objects and operations, distilling down to OBJECT+CRUDD+OTHERS, whichever way you chuck a ball at it.\n" +
            "So building upon Builder classes and using fluent programming ideas, and sticking to the set and get principles we have \n" +
            " [Object]Request\n" +
            "   [Basically Builders, we use inheritance therefore we need the <>, even those that are base are forced to <> for consistency]\n" +
            "   .Creator<>()\n" +
            "   .Reader<>()\n" +
            "   .Updater<>()\n" +
            "   .Duplicator<>()\n" +
            "   .Deleter<>()\n" +
            "   [and now the plus]\n" +
            "   .ListAll<>()\n" +
            "   .Batcher<>()\n" +
            "   .WhateverIAmTryingToAcheive<>()\n\n" +

            "Each Builder only conveys what you can do with it, \n" +
            " e.g. in Creator<>() you can not set the Id, therefore the option to setId is not available.\n" +
            "   but in Updater<>() we need to set the Id, therefore the option to setId is available. (Updater is an extension of Creator)\n\n" +

            "For simplicity, every response receives a correlating response object, \n" +
            "\ta Creator receives a Created<Type>" +
            "\ta Reader receives a Read<Type>" +
            "\ta Updater receives a Updated<Type>" +
            "\ta Deleter receives a Deleted<Type>" +
            "\ta Duplicator receives a Duplicated<Type>" +

            "Core construction is \n" +
            "isSuccess() -> simply have we succeeded\n" +
            "getStatus() -> integer response code from the remote server\n" +

            "For CRUD actions we contain\n" +
            "hasResult() and getResult() inside the Response\n\n" +
            "the Plus actions return:\n" +
            "\tListAll (Search) receives a Listed<Type> " +
            "\tBatcher receives a Batched<Type>" +
            "these also contain hasResult() and getResult() but the result is a List\n\n" +
            "\n\n" +

            "Example: create a product: \n" +
            "Created<Product> result = new ProductRequest.Creator<>()\n" +
            "\t.setSku(\"12345678\")\n" +
            "\t.setName(\"My New Product Name\")\n" +
            "\t.setDescription(\"<b>This is a bold assertion</b><br/>Buy Five Get one Free!\")\n" +
            "\t.getResponse();\n" +

            "We can then check our result with: \n" +
            "if (result.isSuccess()){\n" +
            "\tif (result.hasResult()){\n" +
            "\t\tSystem.out.println(return result.getResult().getId());\n" +
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
            "What follows is the API in action.......\n\n";

        System.out.println(ex);

    }

}


