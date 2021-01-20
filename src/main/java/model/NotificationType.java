package model;

public enum NotificationType {

    EVENT_ACTIVITY_ADDED(1, false, true, false),
    EVENT_ACTIVITY_UPDATED(2, false, true, false),
    EVENT_CHANGE_STATUS(3, false, true, false);
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

    NotificationType(int code, boolean interested, boolean registered, boolean general) {
        this.code = code;
        this.interested = interested;
        this.registered = registered;
        this.general = general;
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

    public static NotificationType getNotificationType(int code) {
        return NotificationType.values()[code-1];
    }

}
