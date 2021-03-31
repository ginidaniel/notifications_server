package com.inspiring.solutions.notifications.server.model;

public enum PictureType {

    EVENT_LOGO("/event_profile/logo/", ".png"),
    EVENT_COVER("/event_profile/cover/", ".jpg"),
    USER_PICTURE("/user_profile/picture/", ".png");

    private String path;
    private String ext;

    PictureType(String path, String ext) {
        this.path = path;
        this.ext=ext;
    }

    public String path() {
        return path;
    }
    public String ext() {
        return ext;
    }

}

