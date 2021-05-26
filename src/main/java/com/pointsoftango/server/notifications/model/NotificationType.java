package com.pointsoftango.server.notifications.model;

import com.pointsoftango.server.notifications.utils.Constants;

public enum NotificationType {

    EVENT_ACTIVITY_ADDED(1, true, true, false, Constants.EVENT_ACTIVITY_ADDED),
    EVENT_ACTIVITY_UPDATED(2, true, true, false, Constants.EVENT_ACTIVITY_UPDATED),
    EVENT_ACTIVITY_REMOVED(3, true, true, false, Constants.EVENT_ACTIVITY_REMOVED),
    EVENT_ACTIVITY_ADDED_ARTISTS(15, false, true, false, Constants.EVENT_ACTIVITY_ADDED_ARTISTS),
    EVENT_ACTIVITY_REMOVED_ARTISTS(14, false, true, false, Constants.EVENT_ACTIVITY_REMOVED_ARTISTS),
    EVENT_CHANGE_STATUS(11, false, true, false, Constants.EVENT_CHANGE_STATUS);

//    EVENT_ADDED(3, R.string.notification_event_added),
//    EVENT_CANCELLED(4, R.string.notification_event_cancelled),
//    EVENT_FULL(5, R.string.notification_event_full),
//    EVENT_OPEN(6, R.string.notification_event_open),
//    EVENT_ADMIN_MSG(7, R.string.notification_event_admin_msg),
//    USER_PAYMENT(8, R.string.notification_event_payment),
//    USER_ACCEPTED(9, R.string.notification_event_accepted),
//    USER_CHANGE_STATUS(10, R.string.notification_event_change_status);

    private int code;
    private boolean interested;
    private boolean registered;
    private boolean general;
    private String preText;

    NotificationType(int code, boolean interested, boolean registered, boolean general, String preText) {
        this.code = code;
        this.interested = interested;
        this.registered = registered;
        this.general = general;
        this.preText = preText;
    }


    public int getCode() {
        return code;
    }

    public boolean isInterested() {
        return interested;
    }

    public boolean isRegistered() {
        return registered;
    }

    public boolean isGeneral() {
        return general;
    }

    public String getPreText() {
        return preText;
    }

    public static NotificationType getNotificationType(int code) {
        return NotificationType.values()[code-1];
    }

}
