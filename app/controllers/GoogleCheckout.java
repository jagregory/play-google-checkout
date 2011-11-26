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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class GoogleCheckout extends Controller {
    public static void callback() {
        try {
            GoogleCheckoutApiFactory.build()
                .handleNotification(new DelegatedNotificationDispatcher(request,  response));
        } catch (GoogleCheckoutInitializationException ex) {
            Logger.error(ex, "Unable to create Google Checkout API");
            error();
        }
    }
    
    static class DelegatedNotificationDispatcher extends PlayNotificationDispatcher {
        public DelegatedNotificationDispatcher(Http.Request request, Http.Response response) {
            super(request, response);
        }

        @Override
        protected boolean hasAlreadyHandled(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            Object result = NotificationHandler.alreadyHandled(serialNumber, orderSummary, notification);

            return result != null && result.equals(true);
        }

        @Override
        protected void rememberSerialNumber(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.rememberSerialNumber(serialNumber, orderSummary, notification);
        }

        @Override
        protected void onNewOrderNotification(OrderSummary orderSummary, NewOrderNotification notification) throws Exception {
            NotificationHandler.newOrder(orderSummary, notification);
        }

        @Override
        protected void onOrderStateChangeNotification(OrderSummary orderSummary, OrderStateChangeNotification notification) throws Exception {
            NotificationHandler.orderStateChange(orderSummary, notification);
        }

        @Override
        protected void onChargeAmountNotification(OrderSummary orderSummary, ChargeAmountNotification notification) throws Exception {
            NotificationHandler.chargeAmount(orderSummary, notification);
        }

        @Override
        protected void startTransaction(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.startTransaction(serialNumber, orderSummary, notification);
        }

        @Override
        protected void commitTransaction(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.commitTransaction(serialNumber, orderSummary, notification);
        }

        @Override
        protected void rollBackTransaction(String serialNumber, OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.rollbackTransaction(serialNumber, orderSummary, notification);
        }

        @Override
        protected void onAllNotifications(OrderSummary orderSummary, Notification notification) throws Exception {
            NotificationHandler.all(orderSummary, notification);
        }

        @Override
        protected void onRefundAmountNotification(OrderSummary orderSummary, RefundAmountNotification notification) throws Exception {
            NotificationHandler.refundAmount(orderSummary, notification);
        }

        @Override
        protected void onRiskInformationNotification(OrderSummary orderSummary, RiskInformationNotification notification) throws Exception {
            NotificationHandler.riskInformation(orderSummary, notification);
        }

        @Override
        protected void onChargebackAmountNotification(OrderSummary orderSummary, ChargebackAmountNotification notification) throws Exception {
            NotificationHandler.chargebackAmount(orderSummary, notification);
        }

        @Override
        protected void onAuthorizationAmountNotification(OrderSummary orderSummary, AuthorizationAmountNotification notification) throws Exception {
            NotificationHandler.authorizationAmount(orderSummary, notification);
        }

        @Override
        protected void onRiskAmountNotification(OrderSummary orderSummary, RefundAmountNotification notification) throws Exception {
            NotificationHandler.riskAmount(orderSummary, notification);
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
            Object result = invokeOverride("alreadyHandled", serialNumber, orderSummary, notification);

            return result != null && result.equals(true);
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
            invokeOverride("rememberSerialNumber", serialNumber, orderSummary, notification);
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled NewOrderNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void newOrder(OrderSummary orderSummary, NewOrderNotification notification) {
            invokeOverride("newOrder", orderSummary, notification);
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled OrderStateChangeNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void orderStateChange(OrderSummary orderSummary, OrderStateChangeNotification notification) {
            invokeOverride("orderStateChange", orderSummary, notification);
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled ChargeAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void chargeAmount(OrderSummary orderSummary, ChargeAmountNotification notification) {
            invokeOverride("chargeAmount", orderSummary, notification);
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
            invokeOverride("startTransaction", serialNumber, orderSummary, notification);
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
            invokeOverride("commitTransaction", serialNumber, orderSummary, notification);
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
            invokeOverride("rollbackTransaction", serialNumber, orderSummary, notification);
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters any new notification from Checkout.  This method will be called
         * before the specific onFooNotification() methods.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void all(OrderSummary orderSummary, Notification notification) {
            invokeOverride("all", orderSummary, notification);
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled RefundAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void refundAmount(OrderSummary orderSummary, RefundAmountNotification notification) {
            invokeOverride("refundAmount", orderSummary, notification);
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled RiskInformationNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void riskInformation(OrderSummary orderSummary, RiskInformationNotification notification) {
            invokeOverride("riskInformation", orderSummary, notification);
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled ChargebackAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void chargebackAmount(OrderSummary orderSummary, ChargebackAmountNotification notification) {
            invokeOverride("chargebackAmount", orderSummary, notification);
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled AuthorizationAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void authorizationAmount(OrderSummary orderSummary, AuthorizationAmountNotification notification) {
            invokeOverride("authorizationAmount", orderSummary, notification);
        }

        /**
         * Should be overridden with the behavior that should happen when your system
         * encounters a not-yet-handled RefundAmountNotification.
         * @param orderSummary The parsed OrderSummary object from the notification.
         * @param notification The parsed JAXB object of the notification itself.
         */
        static void riskAmount(OrderSummary orderSummary, RefundAmountNotification notification) {
            invokeOverride("riskAmount", orderSummary, notification);
        }

        static Method[] methods;

        /**
         * Find a method in the user defined subclass
         * @param name Method name to try to find
         * @return Method if it exists, otherwise null
         */
        static Method findMethod(String name) {
            // try to find a subclass and grab all its methods
            if (methods == null) {
                List<Class> classes = Play.classloader.getAssignableClasses(NotificationHandler.class);
                Class handler = classes.size() == 0 ? null : classes.get(0);

                if (handler != null) {
                    methods = handler.getMethods();
                } else {
                    Logger.warn("No user-defined implementation found for GoogleCheckout.NotificationHandler");
                    methods = new Method[0]; // we've tried to find some overridden methods, but can't
                }
            }

            // iterate the overridden methods and try to find the one we're looking for
            for (Method method : methods) {
                if (method.getName().endsWith(name)) {
                    return method;
                }
            }

            return null;
        }

        /**
         * Invoke a method in the user defined subclass if it exists
         * @param methodName Method to invoke
         * @param args Arguments to pass to the overidden method
         * @return The result of the method invocation, or null if it didn't exist (or is void)
         */
        static Object invokeOverride(String methodName, Object... args) {
            Method method = findMethod(methodName);
            
            if (method == null) {
                Logger.warn("No user-defined implementation found for "+methodName);
                return null;
            }
            
            if (method.getParameterTypes().length != args.length) {
                Logger.error("Overriden method signature is incorrect - "+methodName);
                return null;
            }
            
            method.setAccessible(true);
            try {
                return method.invoke(null, args);
            } catch (IllegalAccessException e) {
                Logger.error(e, "Unable to access method");
            } catch (InvocationTargetException e) {
                Logger.error(e, "Unable to invoke method");
            }

            return null;
        }
    }
}