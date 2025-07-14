/*
 * Copyright (c) 2025.
 *
 * Crud+ ActionBuilder
 * Author: Chris Twinn
 * Licence: MIT Licence see LICENCE file
 * All Rights Reserved
 */
package uk.co.twinn.api.woocommerce.request;

import com.fasterxml.jackson.core.type.TypeReference;
import uk.co.twinn.api.woocommerce.response.Created;
import uk.co.twinn.api.woocommerce.response.core.Msg;
import uk.co.twinn.api.woocommerce.request.core.ApiRequest;
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
* */
public class OrderActionApi extends ApiRequest {

    /** Send order details to customer
    * This endpoint allows you to trigger an email to the customer with the details of their order, if the order contains a customer email address.
     *
     **/

    public OrderActionApi(){

    }

    public SendEmail<?> sendEmail(int customerId){

        return new SendEmail<>(customerId);

    }

    public static class SendEmail<T extends SendEmail<T>>{

        private final int orderId;

        public SendEmail(int orderId){
            this.orderId = orderId;
        }

        /*T self() {
            return (T) this;
        }

        public T setOrderId(int orderId){
            this.orderId = orderId;
            return self();
        }*/

        private String getEndPoint(){
            return ORDERS + "/" + orderId + "/actions/send_order_details";
        }
        /**
        *
         */
        public Created<Message> getResponse(){
            if (orderId == 0) {
                return new Created<>(
                    new ApiResponseResult(
                        false,
                        0,
                        "An OrderId is required.")
                );
            }else {

                Rest woo = new Rest();

                return new Created<>(
                    woo.create(getEndPoint(), new TypeReference<Msg>(){})

                );

            }
        }

    }
}
