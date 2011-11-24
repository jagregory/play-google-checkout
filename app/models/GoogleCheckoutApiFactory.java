package models;

import com.google.checkout.sdk.commands.ApiContext;
import com.google.checkout.sdk.commands.Environment;
import play.Play;

public final class GoogleCheckoutApiFactory {
    public static ApiContext build() throws GoogleCheckoutInitializationException {
        Environment environment = isSandbox() ? Environment.SANDBOX : Environment.PRODUCTION;
        String merchantId = getMerchantId();
        String merchantKey = getMerchantKey();
        String currency = getCurrency();

        return new ApiContext(environment, merchantId, merchantKey, currency);
    }

    static String getMerchantId() throws GoogleCheckoutInitializationException {
        Object merchantId = Play.configuration.get("googlecheckout.merchantid");

        if (merchantId == null) {
            throw new GoogleCheckoutInitializationException("Unable to read MerchantId from application.conf");
        }

        return merchantId.toString();
    }

    static String getMerchantKey() throws GoogleCheckoutInitializationException {
        Object merchantKey = Play.configuration.get("googlecheckout.merchantkey");

        if (merchantKey == null) {
            throw new GoogleCheckoutInitializationException("Unable to read MerchantKey from application.conf");
        }

        return merchantKey.toString();
    }

    static String getCurrency() {
        Object currency = Play.configuration.get("googlecheckout.currency");

        if (currency == null) {
            return "USD";
        }

        return currency.toString();
    }

    static boolean isSandbox() {
        Object isSandbox = Play.configuration.get("googlecheckout.sandbox");

        return isSandbox == null || isSandbox.equals(true);
    }
}
