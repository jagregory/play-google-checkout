package models;

import com.google.checkout.sdk.commands.ApiContext;
import com.google.checkout.sdk.commands.CartPoster;

public final class GoogleCheckoutCartFactory {
    public static CartPoster.CheckoutShoppingCartBuilder build(ApiContext api) {
        return api.cartPoster().makeCart();
    }
}