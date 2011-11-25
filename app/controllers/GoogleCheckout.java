package controllers;

import com.google.checkout.sdk.domain.*;
import com.google.checkout.sdk.notifications.Notification;
import models.GoogleCheckoutApiFactory;
import models.GoogleCheckoutInitializationException;
import models.PlayNotificationDispatcher;
import play.Logger;
import play.Play;
import play.mvc.Controller;
import play.mvc.Http;
import play.utils.Java;

import java.util.List;

public class GoogleCheckout extends Controller {
    public static void callback() {
        try {
            GoogleCheckoutApiFactory.build()
                .handleNotification(new DelegatedNotificationDispatcher(request,  response));
        } catch (GoogleCheckoutInitializationException ex) {
            Logger.error(ex, "Unable to create Google Checkout API");
            badRequest();
        }
    }
    
    static class DelegatedNotificationDispatcher extends PlayNotificationDispatcher {
        public DelegatedNotificationDispatcher(Http.Request request, Http.Response response) {
            super(request, response);
        }

        @Override
        protected boolean hasAlreadyHandled(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            Object result = NotificationHandler.invoke("alreadyHandled", serialNumber, orderSummary, notification);

            return result != null && result.equals(true);
        }

        @Override
        protected void rememberSerialNumber(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.invoke("rememberSerialNumber", serialNumber, orderSummary, notification);
        }

        @Override
        protected void onNewOrderNotification(OrderSummary orderSummary, NewOrderNotification notification) throws Exception {
            NotificationHandler.invoke("newOrder", orderSummary, notification);
        }

        @Override
        protected void onOrderStateChangeNotification(OrderSummary orderSummary, OrderStateChangeNotification notification) throws Exception {
            NotificationHandler.invoke("orderStateChange", orderSummary, notification);
        }

        @Override
        protected void onChargeAmountNotification(OrderSummary orderSummary, ChargeAmountNotification notification) throws Exception {
            NotificationHandler.invoke("chargeAmount", orderSummary, notification);
        }

        @Override
        protected void startTransaction(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.invoke("startTransaction", serialNumber, orderSummary, notification);
        }

        @Override
        protected void commitTransaction(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.invoke("commitTransaction", serialNumber, orderSummary, notification);
        }

        @Override
        protected void rollBackTransaction(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.invoke("rollbackTransaction", serialNumber, orderSummary, notification);
        }

        @Override
        protected void onAllNotifications(OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.invoke("all", orderSummary, notification);
        }

        @Override
        protected void onRefundAmountNotification(OrderSummary orderSummary, RefundAmountNotification notification) throws Exception {
            NotificationHandler.invoke("refundAmount", orderSummary, notification);
        }

        @Override
        protected void onRiskInformationNotification(OrderSummary orderSummary, RiskInformationNotification notification) throws Exception {
            NotificationHandler.invoke("riskInformation", orderSummary, notification);
        }

        @Override
        protected void onChargebackAmountNotification(OrderSummary orderSummary, ChargebackAmountNotification notification) throws Exception {
            NotificationHandler.invoke("chargebackAmount", orderSummary, notification);
        }

        @Override
        protected void onAuthorizationAmountNotification(OrderSummary orderSummary, AuthorizationAmountNotification notification) throws Exception {
            NotificationHandler.invoke("authorizationAmount", orderSummary, notification);
        }

        @Override
        protected void onRiskAmountNotification(OrderSummary orderSummary, RefundAmountNotification notification) throws Exception {
            NotificationHandler.invoke("riskAmount", orderSummary, notification);
        }
    }

    public static class NotificationHandler {
        /**
         * With a read lock, determine whether this notification has already been
         * handled.
         * @param serialNumber The serial number of this notification, which is
         *    unique to this order/notification pair, but stable across each attempt
         *    to deliver the notification.
         * @param orderSummary The state of the order whose notification is currently
         *    being handled.
         * @param notification The parsed JAXB object of the notification itself.
         * @return True if this notification has already been successfully handled and
         *    committed; otherwise false.
         */
        static boolean alreadyHandled(String serialNumber, OrderSummary orderSummary, Notification notification) {
            return false;
        }

        /**
         * Save away the serialNumber so that if hasAlreadyHandled is called with this
         * serial number in the future, it will return "true".
         * @param serialNumber The serial number of this notification, which is
         *    unique to this order/notification pair, but stable across each attempt
         *    to deliver the notification.
         * @param orderSummary The state of the order whose notification is currently
         *    being handled.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void rememberSerialNumber(String serialNumber, OrderSummary orderSummary, Notification notification) {
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled NewOrderNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void newOrder(OrderSummary orderSummary, NewOrderNotification notification) {
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled OrderStateChangeNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void orderStateChange(OrderSummary orderSummary, OrderStateChangeNotification notification) {
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled ChargeAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void chargeAmount(OrderSummary orderSummary, ChargeAmountNotification notification) {
        }

        /**
         * Start a new database transaction for handling this notification.
         * @param serialNumber The serial number of this notification, which is
         *    unique to this order/notification pair, but stable across each attempt
         *    to deliver the notification.
         * @param orderSummary The state of the order whose notification is currently
         *    being handled.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void startTransaction(String serialNumber, OrderSummary orderSummary, Notification notification) {
        }

        /**
         * The notification was successfully handled, so commit the current
         * transaction.
         * @param serialNumber The serial number of this notification, which is
         *    unique to this order/notification pair, but stable across each attempt
         *    to deliver the notification.
         * @param orderSummary The state of the order whose notification is currently
         *    being handled.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void commitTransaction(String serialNumber, OrderSummary orderSummary, Notification notification) {
        }

        /**
         * An error occurred while handling the notification, so roll back the current
         * transaction.
         * @param serialNumber The serial number of this notification, which is
         *    unique to this order/notification pair, but stable across each attempt
         *    to deliver the notification.
         * @param orderSummary The state of the order whose notification is currently
         *    being handled.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void rollbackTransaction(String serialNumber, OrderSummary orderSummary, Notification notification) {
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters any new notification from Checkout.  This method will be called
         * before the specific onFooNotification() methods.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void all(OrderSummary orderSummary, Notification notification) {
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled RefundAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void refundAmount(OrderSummary orderSummary, RefundAmountNotification notification) {
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled RiskInformationNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void riskInformation(OrderSummary orderSummary, RiskInformationNotification notification) {
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled ChargebackAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void chargebackAmount(OrderSummary orderSummary, ChargebackAmountNotification notification) {
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled AuthorizationAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void authorizationAmount(OrderSummary orderSummary, AuthorizationAmountNotification notification) {
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled RefundAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void riskAmount(OrderSummary orderSummary, RefundAmountNotification notification) {
        }

        static Object invoke(String methodName, Object... args) throws Exception {
            List<Class> classes = Play.classloader.getAssignableClasses(NotificationHandler.class);
            Class handler = classes.size() == 0 ? NotificationHandler.class : classes.get(0);

            return Java.invokeStaticOrParent(handler, methodName, args);
        }
    }
}