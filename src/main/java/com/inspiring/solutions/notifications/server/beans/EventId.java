package com.inspiring.solutions.notifications.server.beans;

public class EventId {

    String eventId;
    String country;
    String region;

    public EventId(String eventId, String country, String region) {
        this.eventId = eventId;
        this.country = country;
        this.region = region;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEventCode() {
        return eventId.split("_")[1];
    }

    public String getEventEdition() {
        return eventId.split("_")[0];
    }
}
