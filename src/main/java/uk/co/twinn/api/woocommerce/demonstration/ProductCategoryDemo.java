/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */

package uk.co.twinn.api.woocommerce.demonstration;

import uk.co.twinn.api.woocommerce.request.ProductCategoryRequest;
import uk.co.twinn.api.woocommerce.response.Created;
import uk.co.twinn.api.woocommerce.response.Read;
import uk.co.twinn.api.woocommerce.response.Updated;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.pl_wtx_woocommerce.model.ProductCategory;

import static uk.co.twinn.pl_wtx_woocommerce.model.ProductCategory.DisplayEnum.PRODUCTS;
import static uk.co.twinn.pl_wtx_woocommerce.model.ProductCategory.DisplayEnum.SUBCATEGORIES;

public class ProductCategoryDemo {

    //see createProductCategory for usage reasons
    private static final String SOURCE = "https://imgserver.url/image/";
    private static final String DESTINATION = "https://mysite/wp-content/uploads/mymedia/";

    //wordpress moves the images to https://mysite/wp-content/uploads/YYYY/MM/name.webp from DESTINATION
    private static final String MODIFY_DESTINATION_START = "https://mysite/wp-content/uploads/";
    private static final String MODIFY_DESTINATION_TYPE = ".webp";

    public ProductCategoryDemo(){

    }

    public Created<ProductCategory> createProductCategory(
        String name, String description,
        String wooCommerceImageUrl, int itemCount
    ){

        return
            new ProductCategoryRequest.Creator<>()
                .setName(name)
                .setDescription(description)
                .setImage(wooCommerceImageUrl)
                .setDisplay(itemCount > 0 ? PRODUCTS : SUBCATEGORIES)
                .getResponse();

    }

    public Read<ProductCategory> readProductCategory(int productCategoryId){

        return new ProductCategoryRequest.Reader<>()
            .setId(productCategoryId)
            .getResponse();

    }

    public Updated<ProductCategory> updateProductCategory(
        int productCategoryId,
        String name, String description,
        String wooCommerceImageUrl, int itemCount
    ){

        return
            new ProductCategoryRequest.Updater<>()
                .setId(productCategoryId)
                .setName(name)
                .setDescription(description)
                .setImage(wooCommerceImageUrl)
                .setDisplay(itemCount > 0 ? PRODUCTS : SUBCATEGORIES)
                .getResponse();

    }


    public Created<ProductCategory> createImageAndProductCategory(
        int parentId,
        String name, String description,
        String image, int productCount
    ){

        return createImageAndProductCategory(
            parentId,
            name, description,
            image, productCount,
            false);

    }

    private Created<ProductCategory> createImageAndProductCategory(
        int parentId,
        String name, String description,
        String image, int productCount,
        boolean isRetry
    ){

        /*
            We create initially with SOURCE, this will fail as WooCommerce can not or refuses to see the remote image
            We use this failure, via a plugin, to get Woo to retrieve the image to DESTINATION
            Therefore retry *SHOULD* then succeed BUT wordpress moves the image to YYYY/MM
        */
        String imgUrl = (isRetry ? DESTINATION : SOURCE) + image;

        Created<ProductCategory> created =
            new ProductCategoryRequest.Creator<>()
                .setParent(parentId)
                .setName(name)
                .setDescription(description)
                .setImage(imgUrl)
                .setDisplay(productCount > 0 ? PRODUCTS : SUBCATEGORIES)
                .getResponse();

        if (created.isSuccess()){

            return created;

        }else if (isRetry){

            return created;

        }else{

            return createImageAndProductCategory(parentId, name, description, image, productCount, true);

        }

    }


    /* *
     *
     * @param wooId
     * @param name
     * @param description
     * @param image
     * @return
    **/
    private Updated<ProductCategory> checkAndUpdateProductCategory(
        int productCategoryId,
        String name, String description,
        String image, int itemCount
    ){

        ProductCategory existingDetails = readProductCategory(productCategoryId).getResult();

        if (existingDetails != null) {
            return checkAndUpdateProductCategory(
                productCategoryId,
                name, description,
                image, itemCount,
                existingDetails, false);
        }else{
            return new Updated<ProductCategory>(
                new ApiResponseResult(
                    false,
                    0,
                    "Failure to find requested ProductCategory")
            );
        }

    }

    /*
        Check and Update ProductCategory, special consideration given to the woo image path
        We create initially with SOURCE, this will fail as WooCommerce can not or refuses to see the remote image
        We use this failure, via a plugin, to get Woo to retrieve the image to DESTINATION
        Therefore retry *SHOULD* then succeed BUT wordpress moves the image to YYYY/MM
    */
    private Updated<ProductCategory> checkAndUpdateProductCategory(
        int wooId,
        String name, String description,
        String image, int itemCount,
        ProductCategory existingCategory, boolean retry
    ){

        ProductCategoryRequest.Updater<?> update =
            new ProductCategoryRequest.Updater<>()
                .setId(wooId);

        boolean updatingImage = false;

        if (existingCategory.getName() == null ||
            !name.equals(existingCategory.getName())
        ){
            update.setName(name);
        }

        if (existingCategory.getDescription() == null ||
            !description.equals(existingCategory.getDescription())
        ){
            update.setDescription(description);
        }

        if (retry){ //update image to DESTINATION have to let woo move it

            update.setImage(DESTINATION + image);

        }else if (image != null && !image.isEmpty()){

            //we appear as a .png, .jpg but we need to rename as a .webp
            //woo is moving into YYYY/DD folder
            String tImage = image
                .substring(0, image.lastIndexOf(".")) +
                MODIFY_DESTINATION_TYPE;

            if (existingCategory.getImage() != null){

                if (existingCategory.getImage().getSrc() != null) {
                    String wooImage = existingCategory.getImage().getSrc();

                    if (!wooImage.startsWith(MODIFY_DESTINATION_START) && !wooImage.endsWith(tImage)) {
                        updatingImage = true;
                        update.setImage(SOURCE + image);
                    }
                }

            }

        }

        if (itemCount > 0){
            if (existingCategory.getDisplay() != PRODUCTS){
                update.setDisplay(PRODUCTS);
            }
        }else if (existingCategory.getDisplay() != SUBCATEGORIES){
            update.setDisplay(SUBCATEGORIES);
        }

        Updated<ProductCategory> updated = update.getResponse();

        if (updatingImage) {
            return checkAndUpdateProductCategory(wooId, name, description, image, itemCount, existingCategory, true);
        }else{
            return updated;
        }

    }

}
