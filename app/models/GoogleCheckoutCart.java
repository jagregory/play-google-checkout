package models;

import com.google.checkout.sdk.commands.CartPoster;
import com.google.checkout.sdk.domain.CheckoutRedirect;

public class GoogleCheckoutCart {
    CartPoster.CheckoutShoppingCartBuilder builder;

    public GoogleCheckoutCart(CartPoster.CheckoutShoppingCartBuilder builder) {
        this.builder = builder;
    }

    public void addItem(String name, String description, double unitPrice, int quantity) {
        builder.addItem(name, description, unitPrice, quantity);
    }

    public Response send() {
        return new Response(builder.buildAndPost());
    }
    
    public class Response {
        String redirectUrl;
        String serialNumber;

        public Response(CheckoutRedirect checkoutRedirect) {
            redirectUrl = checkoutRedirect.getRedirectUrl();
            serialNumber = checkoutRedirect.getSerialNumber();
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public String getSerialNumber() {
            return serialNumber;
        }
    }
}
