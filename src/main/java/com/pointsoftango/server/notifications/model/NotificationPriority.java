package com.pointsoftango.server.notifications.model;

public enum NotificationPriority {

    LOW(1),
    MEDIUM(2),
    HIGH(3),
    URGENT(4);

    private int code;

    NotificationPriority(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
