/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.demonstration;

import uk.co.twinn.api.woocommerce.request.ProductRequest;
import uk.co.twinn.api.woocommerce.response.Created;
import uk.co.twinn.api.woocommerce.response.Listed;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.Updated;
import uk.co.twinn.pl_wtx_woocommerce.model.Product;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductCategoriesItem;

import java.math.BigDecimal;
import java.util.Collections;

public class ProductDemo {
    public void createProduct(
        int productCategoryId,
        String sku,
        String name, String description,
        int sortOrder
    ){

        Created<Product> created =
            new ProductRequest.Creator<>()
                .setSku(sku)
                .setName(name)
                .setDescription(description)
                .setCategories(Collections.singletonList(new ProductCategoriesItem().id(productCategoryId)))
                .setCatalogVisibility(Product.CatalogVisiblityEnum.VISIBLE)
                .setMenuOrder(sortOrder)
                .getResponse();

        if (created.isSuccess()){
            System.out.println("Created a record with the id: "+ created.getResult().getId());
        }else{
            System.out.println(created.getError().getMessage());
        }

    }

    public Product read(
        int id
    ){
        Read<Product> read =
            new ProductRequest.Reader<>()
                .setId(id)
                .getResponse();

        if (read.isSuccess()){
            return read.getResult();
        }else{
            System.out.println(read.getError().getMessage());
            return null;
        }

    }

    private boolean checkAndUpdateProduct(
        int id,
        String sku, String name, String description,
        int menuOrder
    ){

        Read<Product> read = new ProductRequest.Reader<>().setId(id).getResponse();

        if (read.isSuccess()){

            Product existing = read.getResult();

            ProductRequest.Updater<?> update = new ProductRequest.Updater<>();

            update.setId(id);

            update.setRegularPrice(new BigDecimal(9.99));

            if ((existing.getSku() == null) || !existing.getSku().equals(sku)){
                update.setSku(sku);
            }
            if ((existing.getName() == null) || !existing.getName().equals(name)){
                update.setSku(sku);
            }
            if (existing.getDescription() == null || !existing.getDescription().equals(description)){
                update.setSku(sku);
            }
            if (existing.getMenuOrder() == null  || existing.getMenuOrder() != menuOrder){
                update.setMenuOrder(menuOrder);
            }

            Updated<Product> updated = update.getResponse();

            if (updated.isSuccess()){
                return true;
            }else{
                return false;
            }

        }else{
            return false;
        }

    }

    private void searchProduct(String sku){

        Listed<Product> isListed = new ProductRequest.ListAll<>().setSku(sku).getResponse();
        if (isListed.isSuccess()){
            if (isListed.getResult().isEmpty()){

                System.out.println("Failure to find " + sku);

            }else{

                for (Product product : isListed.getResult()){
                    System.out.println(product.toString());
                }

            }

        }else{
            System.out.println(isListed.getError().getMessage());
        }

    }

}
