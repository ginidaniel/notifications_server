package com.inspiring.solutions.notifications.server.model;

import com.google.cloud.Timestamp;

import java.util.Date;

public class UserNotificationItem {

    String id;
    boolean read = false;
    Timestamp updated;

    public UserNotificationItem() {
        updated = Timestamp.of(new Date());
    }

    public UserNotificationItem(String id) {
        updated = Timestamp.of(new Date());
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

}
