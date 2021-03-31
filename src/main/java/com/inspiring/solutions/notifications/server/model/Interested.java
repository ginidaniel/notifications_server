package com.inspiring.solutions.notifications.server.model;


import com.google.cloud.Timestamp;

import java.util.Objects;

public class Interested implements Comparable<Interested> {

    private String username;
    private String event;
    private Timestamp lastUpdate;
    private Timestamp eventEnds;

    public Interested() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Timestamp getEventEnds() {
        return eventEnds;
    }

    public void setEventEnds(Timestamp eventEnds) {
        this.eventEnds = eventEnds;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int compareTo(Interested interested) {
        return username.compareTo(interested.username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Interested that = (Interested) o;
        return  Objects.equals(username, that.username) &&
                Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, event);
    }
}
