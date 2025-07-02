# Why the Fork?

Simpicity! 

Server API's mainly seem appear to the Object->CRUD+ philosophy once you boil them all down. 
   
Here I have concisely tied the objects to the CRUD+ actions.

In it's simplicity: 

<ul>
  <li>we can go to https://woocommerce.github.io/woocommerce-rest-api-docs</li>
  <li>We are presented with:
      <ul>
        <li>Create an Order</li>  
        <li>Retrieve an Order</li>  
        <li>List all orders</li>  
        <li>Update an Order</li>  
        <li>Delete an Order</li>  
        <li>Batch update order</li>  
      </ul>
  </li>
  <li>
    These directly correlate with: 
     <ul>
        <li>OrderRequest.Creator<>()</li>  
        <li>OrderRequest.Reader<>()</li>  
        <li>OrderRequest.ListAll<>()</li>  
        <li>OrderRequest.Updater<>()</li>  
        <li>OrderRequest.Deleter<>()</li>  
        <li>OrderRequest.Batcher<>()</li>  
      </ul>
  </li>
  <li>Only the options that are available for the action are available, i.e. you can not set the Id in Creator, but you MUST in updater</li>
  <li>An OrderRequest has a correlating OrderResponse, the idea is to contain properly the server reponse and action result</li>
</ul>

[Object]Response response = [Object]Request.Creator<>();

The response contains whether it was a success trying not to throw errors but cleanly returning them to the user.

The response will then contain: 
  if a Creator,Reader,Updater or a Deleter a single object. 
  otherwise a List<Object>

So far we have 
  Authentication
  Coupon
  Order
  OrderAction
  OrderNote
  OrderRefund
  Product
  ProductCategory
  
# WooCommerce API Client for Java

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-8%2B-blue)](https://www.java.com)
[![Maven Central](https://img.shields.io/maven-central/v/pl.wtx.woocommerce/woocommerce-api-client?label=Maven%20Central&logo=apache-maven&color=blue)](https://mvnrepository.com/artifact/pl.wtx.woocommerce/woocommerce-api-client)

A lightweight Java client library for WooCommerce REST API integration. Built for Java developers who need to integrate their applications with WooCommerce e-commerce platform. 🚀

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

## 🎯 Currently implemented features

- ✅ Customers API
  - List all customers
  - Create a new customer
  - Get a specific customer
  - Update a customer
  - Delete a customer
  - Batch create, update and delete multiple customers

- ✅ Products API
  - List all products
  - Create a new product
  - Get a specific product
  - Update a product
  - Delete a product
  - Batch create, update and delete multiple products

- ✅ Product Variations API
  - List all variations of a product
  - Create a new product variation
  - Get a specific product variation
  - Update a product variation
  - Delete a product variation
  - Batch create, update and delete multiple product variations

- ✅ Product Categories API
  - List all product categories
  - Create a new product category
  - Get a specific product category
  - Update a product category
  - Delete a product category
  - Batch create, update and delete multiple product categories

- ✅ Orders API
  - List all orders
  - Create a new order
  - Get a specific order
  - Update an order
  - Delete an order
  - Batch create, update and delete multiple orders

- ✅ Product Attributes API
  - List all product attributes
  - Create a new product attribute
  - Get a specific product attribute
  - Update a product attribute
  - Delete a product attribute
  - Batch create, update and delete multiple product attributes

- ✅ Product Attribute Terms API
  - List all terms of a product attribute
  - Create a new product attribute term
  - Get a specific product attribute term
  - Update a product attribute term
  - Delete a product attribute term
  - Batch create, update and delete attribute terms

- ✅ Product Shipping Classes API
  - List all product shipping classes
  - Create a new product shipping class
  - Get a specific product shipping class
  - Update a product shipping class
  - Delete a product shipping class
  - Batch create, update and delete multiple shipping classes

- ✅ Product Tags API
  - List all product tags
  - Create a new product tag
  - Get a specific product tag
  - Update a product tag
  - Delete a product tag
  - Batch create, update and delete multiple product tags

- ✅ Product Reviews API
  - List all product reviews
  - Create a new product review
  - Get a specific product review
  - Update a product review
  - Delete a product review
  - Batch create, update and delete multiple product reviews

- ✅ Reports
  - List all reports
  - Retrieve and view sales report
  - Retrieve and view top sellers report
  - Retrieve and view coupons totals report
  - Retrieve and view customers totals report
  - Retrieve and view orders totals report
  - Retrieve and view products totals report
  - Retrieve and view reviews totals report

## 🚨 Project status

> ⚠️ **Note: This is an early development version!**
> 
> We are actively implementing more WooCommerce API features.
> Contributions and feedback are welcome on GitHub!

## 📦 Version information

- **Current version**: `0.9.7`
- **Supported WooCommerce API version**: `v3`
- **Java compatibility**: Java 8+

## 🔓 License

**MIT License**

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files, to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the software.

The only requirement is to preserve the original author attribution in the source code and documentation.

## 🚀 Quick start guide

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

### 2️⃣ Java integration example

Here's how to fetch WooCommerce customer data using the client:

```java
package pl.wtx.woocommerce;

import java.util.List;

import pl.wtx.woocommerce.api.client.CustomersApi;
import pl.wtx.woocommerce.api.client.invoker.ApiException;
import pl.wtx.woocommerce.api.client.model.Customer;

public class WooCommerceApiClientUsageDemo {

    // TODO: Set your WooCommerce API base path!
    private static final String API_BASE_PATH = "https://your-woocommerce-shop.com/wp-json/wc/v3";
    private static final String API_USERNAME = "TODO_SET_API_USERNAME";
    private static final String API_PASSWORD = "TODO_SET_API_PASSWORD";

    public static void main(String[] args) {

        System.out.println(">>> Start running the WooCommerceApiClientUsageDemo...");

        /**
        *  Using CrudPlusActionBuilders
        *  Preferably place a file at ~/.woocommerce-api/config.json containing your secrets
        *  {
        *    "website" : "myexamplewordpresssite.com",
        *    "api" : "/wp-json/wc/v3/" +
        *    "key" : "ky_1234567890ABCDEF1234567890ABCDEF123456789",
        *    "secret": "sh_1234567890ABCDEF1234567890ABCDEF123456789"
        *  }
        *  or quickstart (below)
        **/

        //quickstart
        new Configuration(API_BASE_PATH, API_USERNAME, API_PASSWORD);

        CustomerResponse customerResponse = 
            new CustomerRequest.Searcher<>()
                .setEmail("johndoe@nowhere.com")
                .getResponse();

        if (customerResponse.isSuccess()) {
            if (!customerResponse.getCustomers().isEmpty()) {
                for (Customer customer : customerResponse.getCustomers()) {
                    System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());
                }
            }else{
                System.out.println("Sorry, but no customers were found");
            }
        }else{
            System.out.println(customerResponse.getError().getMessage());
        }

        // Use WooCommerceApiClient(true) if you need to log API communication messages.
        WooCommerceApiClient apiClient = new WooCommerceApiClient();

        apiClient.setBasePath(API_BASE_PATH);
        apiClient.setUsername(API_USERNAME);
        apiClient.setPassword(API_PASSWORD);

        CustomersApi customersApi = new CustomersApi(apiClient);

        try {

            List<Customer> customers = customersApi.listAllCustomers(
                null, null, null, null, null, null, null, null, null, null, null
            );

            // Example list of customer's emails:
            customers.forEach(customer -> System.out.println("Customer: " + customer.getEmail()));

        } catch (ApiException e) {
            System.err.println("Error occurred during API call: " + e);
        }

        System.out.println("<<< The WooCommerceApiClientUsageDemo has been finished.");

    }

}
```

## 🔗 Get involved

- ✨ Check our [GitHub Issues](https://github.com/wtx-labs/woocommerce-api-client-java/issues) for latest updates
- 💡 Have suggestions? Open an Issue or contribute to the project
- 🌟 Star this repository if you find it helpful!

## 📊 Project statistics

- ⭐ 5 GitHub stars
- 🔄 Regular updates and improvements
- 👥 Open for community contributions

## 🔍 Keywords

woocommerce java client, woocommerce rest api java, java woocommerce integration, woocommerce api v3 java, e-commerce java integration, woocommerce java library, java rest api client woocommerce, woocommerce api client library for java

🚀 Happy coding! 😊

**Your WTX Labs Team** 🚀
