/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.builders;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.response.Created;
import uk.co.twinn.api.woocommerce.builders.core.ApiRequest;
import uk.co.twinn.api.woocommerce.response.Message;
import uk.co.twinn.api.woocommerce.response.core.ApiResponseResult;
import uk.co.twinn.api.woocommerce.rest.Rest;


import static uk.co.twinn.api.woocommerce.defines.EndPoints.ORDERS;

/*
* Experimental class. Referencing the API
* I think! I send a request with empty content
* we do not receive an object back, just a message
* refinement most likely
*
* The order actions API allows you to perform specific actions with existing orders
* like you can from the Edit Order screen in the web app.
*
* Note: currently only one action is available, other actions will be introduced at a later time.
*
* Supposedly!
*
* order_actions is V2 I am getting 404 on SendEmail and SendOrderDetails
*
* email_templates DOES yield a result. to do : locate in PHP
*
* All other methods appear to show in the api folder. this is a strange one.
*
*
*
*
* */
public class OrderActionBuilder extends ApiRequest {

    /** Send order details to customer
    * This endpoint allows you to trigger an email to the customer with the details of their order, if the order contains a customer email address.
     *
     **/

    private OrderActionBuilder(){

    }

    public static class SendEmail{

        private final int orderId;

        public SendEmail(int orderId){
            this.orderId = orderId;
        }

        public Created<Message> getResponse(){

            return new Created<>(
                new ApiResponseResult<>(
                    false,
                    0,
                    "API MIA!")
            );

        }

        /*private final int orderId;

        public SendEmail(int orderId){
            this.orderId = orderId;
        }

        private String getEndPoint(){
            return ORDERS + "/" + orderId + "/actions/send_email";
        }

        public Created<Message> getResponse(){
            if (orderId == 0) {
                return new Created<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "An OrderId is required.")
                );
            }else {
                return new Created<>(
                    new Rest<Message>().create(getEndPoint(), new TypeReference<Message>() {})
                );
            }
        }*/

    }


    public static class SendOrderDetails{

        public Created<Message> getResponse(){

            return new Created<>(
                new ApiResponseResult<>(
                    false,
                    0,
                    "API MIA!")
            );

        }

        /*private final int orderId;

        public SendEmail(int orderId){
            this.orderId = orderId;
        }

        private String getEndPoint(){
            return ORDERS + "/" + orderId + "/actions/send_order_details";
        }

        public Created<Message> getResponse(){
            if (orderId == 0) {
                return new Created<>(
                    new ApiResponseResult<>(
                        false,
                        0,
                        "An OrderId is required.")
                );
            }else {
                return new Created<>(
                    new Rest<Message>().create(getEndPoint(), new TypeReference<Message>() {})
                );
            }
        }*/

    }
}
