# The WooCommerce REST API Client for Java  

Effortlessly integrate your Java applications with WooCommerce using our REST API client! 🚀  
This library provides an intuitive and developer-friendly way to interact with the latest **WooCommerce REST API (v3)**, allowing seamless access to store data and operations.  

---

> ⚠️ **Note: This is an early development version!**  
> We are actively expanding the API coverage to support more WooCommerce features.  
> Contributions and feedback are welcome!

---

## 🚀 Quick Start  

### 1️⃣ Install  
Clone this repository and build the library using Maven:  

```sh
mvn clean install
```  

After a successful build, you can add the generated artifact as a dependency in your Java project:  

```xml
<dependency>
    <groupId>wtx.woocommerce</groupId>
    <artifactId>woocommerce-api-client</artifactId>
    <version>0.1.0</version>
</dependency>
```

### 2️⃣ Usage Example  

Easily fetch WooCommerce customer data:  

```java
import java.util.List;
import wtx.woocommerce.api.client.*;
import wtx.woocommerce.api.client.invoker.ApiException;
import wtx.woocommerce.api.client.model.Customer;

public class WooCommerceClientDemo {

    public static void main(String[] args) {

        System.out.println(">>> Starting WooCommerceClientDemo...");

        WooCommerceApiClient apiClient = new WooCommerceApiClient();
        apiClient.setBasePath("https://your-woocommerce-shop.com/wp-json/wc/v3");
        apiClient.setUsername("YOUR_API_USERNAME");
        apiClient.setPassword("YOUR_API_PASSWORD");

        CustomersApi customersApi = new CustomersApi(apiClient);

        try {
            List<Customer> customers = customersApi.listAllCustomers(null, null, null, null, null, null, null, null, null, null, null);
            customers.forEach(customer -> System.out.println("Customer: " + customer.getEmail()));
        } catch (ApiException e) {
            System.err.println("API Error: " + e.getMessage());
        }

        System.out.println("<<< WooCommerceClientDemo finished.");
    }
}
```

---

## 🔗 Stay Connected  

✨ We’re constantly improving this client with new features!  
💡 Have suggestions or need help? Open an [issue](https://github.com/YOUR_REPO/issues) or contribute!  

🚀 Happy coding! 😊  

**Your WTX Labs Team 🚀**
