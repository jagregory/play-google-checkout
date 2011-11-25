package controllers;

import com.google.checkout.sdk.commands.ApiContext;
import com.google.checkout.sdk.domain.ChargeAmountNotification;
import com.google.checkout.sdk.domain.NewOrderNotification;
import com.google.checkout.sdk.domain.OrderStateChangeNotification;
import com.google.checkout.sdk.domain.OrderSummary;
import com.google.checkout.sdk.notifications.Notification;
import models.*;
import play.Logger;
import play.Play;
import play.mvc.Controller;
import play.utils.Java;

import java.util.List;

public class GoogleCheckout extends Controller {
    public static void callback() {
        ApiContext api;

        try {
            api = GoogleCheckoutApiFactory.build();
        } catch (GoogleCheckoutInitializationException ex) {
            Logger.error(ex, "Unable to create Google Checkout API");
            badRequest();
            return;
        }

        api.handleNotification(new PlayNotificationDispatcher(request, response) {
            @Override
            protected boolean hasAlreadyHandled(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
                Object result = NotificationHandler.invoke("alreadyHandled", serialNumber);

                return result != null && result.equals(true);
            }

            @Override
            protected void rememberSerialNumber(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
                NotificationHandler.invoke("rememberSerialNumber", serialNumber);
            }

            @Override
            protected void onNewOrderNotification(OrderSummary orderSummary, NewOrderNotification notification) throws Exception {
                NotificationHandler.invoke("newOrder", notification);
            }

            @Override
            protected void onOrderStateChangeNotification(OrderSummary orderSummary, OrderStateChangeNotification notification) throws Exception {

            }

            @Override
            protected void onChargeAmountNotification(OrderSummary orderSummary, ChargeAmountNotification notification) throws Exception {
                
            }
        });
    }

    public static class NotificationHandler {
        /**
         * This method is called to determine whether a Google Checkout notification
         * has already been handled by the application. This should simply lookup
         * in your order management system and return whether the serial number has
         * already been used.
         *
         * @param serialNumber
         * @return true if the notification has already been handled
         */
        static boolean alreadyHandled(String serialNumber) {
            return false;
        }
        
        static void rememberSerialNumber(String serialNumber) {
        }

        static void newOrder(NewOrderNotification notification) {
        }

        static Object invoke(String methodName, Object... args) throws Exception {
            List<Class> classes = Play.classloader.getAssignableClasses(NotificationHandler.class);
            Class handler = classes.size() == 0 ? NotificationHandler.class : classes.get(0);

            return Java.invokeStaticOrParent(handler, methodName, args);
        }
    }
}