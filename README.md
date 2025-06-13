# WooCommerce API Client for Java

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-8%2B-blue)](https://www.java.com)

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
- ✅ Products API
  - List all products
  - Create a new product
  - Get a specific product
  - Update a product
  - Delete a product
- ✅ Product Variations API
  - List all variations of a product
  - Create a new product variation
  - Get a specific product variation
  - Update a product variation
  - Delete a product variation
- ✅ Product Categories API
  - List all product categories
  - Create a new product category
  - Get a specific product category
  - Update a product category
  - Delete a product category
- ✅ Orders API
  - List all orders
  - Create a new order
  - Get a specific order
  - Update an order
  - Delete an order
- ✅ Reports
  - Retrieve and view orders totals report
  - Retrieve and view sales report

## 🚨 Project status

> ⚠️ **Note: This is an early development version!**
> 
> We are actively implementing more WooCommerce API features.
> Contributions and feedback are welcome on GitHub!

## 📦 Version information

- **Current version**: `0.9.4`
- **Supported WooCommerce API version**: `v3`
- **Java compatibility**: Java 8+

## 🔓 License

**MIT License**

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files, to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the software.

The only requirement is to preserve the original author attribution in the source code and documentation.

## 🚀 Quick start guide

### 1️⃣ Installation

Clone and build the library from source:

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
    <version>0.9.4</version>
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

woocommerce java client, woocommerce rest api java, java woocommerce integration, woocommerce api v3 java, e-commerce java integration, woocommerce java library, java rest api client woocommerce

🚀 Happy coding! 😊

**Your WTX Labs Team** 🚀
