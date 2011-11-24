package models;

import com.google.checkout.sdk.commands.ApiContext;

public final class GoogleCheckoutCartFactory {
    public static GoogleCheckoutCart build(ApiContext api) {
        return new GoogleCheckoutCart(api.cartPoster().makeCart());
    }
}