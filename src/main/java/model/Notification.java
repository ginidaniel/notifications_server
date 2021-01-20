package model;

import com.google.cloud.Timestamp;

import java.util.*;

public class Notification {

    String id;

    boolean toShow = true;
    boolean toPush = true;
    boolean processed = false;


    int priority;
    NotificationType type;
    Map<String, String> data;
    Map<String, String> action;

    PictureType pictureType;
    String picture;

    Timestamp created;
    Timestamp updated;

    List<String> userNames;

    public Notification() {
        id = UUID.randomUUID().toString();
        data = new HashMap<>();
        created = Timestamp.of(new Date());
        updated = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isToShow() {
        return toShow;
    }

    public void setToShow(boolean show) {
        this.toShow = show;
    }

    public boolean isToPush() {
        return toPush;
    }

    public void setToPush(boolean toPush) {
        this.toPush = toPush;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public PictureType getPictureType() {
        return pictureType;
    }

    public void setPictureType(PictureType pictureType) {
        this.pictureType = pictureType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Map<String, String> getAction() {
        return action;
    }

    public void setAction(Map<String, String> action) {
        this.action = action;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return  Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
