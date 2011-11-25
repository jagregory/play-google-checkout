package models;

import com.google.checkout.sdk.notifications.BaseNotificationDispatcher;
import play.mvc.Http;

public abstract class PlayNotificationDispatcher extends BaseNotificationDispatcher {
    protected PlayNotificationDispatcher(Http.Request request, Http.Response response) {
        super(new PlayHttpServletRequest(request), new PlayHttpServletResponse(response));
    }
}