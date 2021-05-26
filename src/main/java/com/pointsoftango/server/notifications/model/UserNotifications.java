package com.pointsoftango.server.notifications.model;

import com.google.cloud.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserNotifications {

    String username;
    List<String> tokens;
    NotificationSett settings;
    String timeZone;
    String language;
    Timestamp lastUpdate;

    //To load Items when needed.
    List<UserNotificationItem> notifications;

    public UserNotifications() {
        notifications = new ArrayList<>();
        tokens = new ArrayList<>();
        lastUpdate = Timestamp.of(new Date());
        settings = new NotificationSett();
    }

    public NotificationSett getSettings() {
        return settings;
    }

    public void setSettings(NotificationSett settings) {
        this.settings = settings;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setNotifications(List<UserNotificationItem> notifications) {
        this.notifications = notifications;
    }

    public List<UserNotificationItem> getNotifications() {
        return notifications;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean areThereUnreadNotifications() {
        return (notifications.stream().filter(n -> !n.isRead()).findFirst().orElse(null)!=null);
    }
}
