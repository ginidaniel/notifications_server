package model;


import com.google.cloud.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Event implements Comparable<Event> {

    private String id;
    private String code;
    private int year;

    private List<String> topics;
    private List<String> keywords;

    private List<String> organisers;
    private String name;
    private String city;
    private String country;
    private String continent;

    private Timestamp created;
    private Timestamp lastUpdated;

    private String timeZone;
    private Timestamp from;
    private Timestamp to;
    private Timestamp open;
    private Timestamp close;

    private String cover;

    private String description;
    private String email;
    private String phone;
    private String phoneCode;
    private String link;

    private EventType type;
    private Process process;

    private int capacity;

    private boolean show = true;
    private boolean whoIsIn;
    private boolean eventFull;
    private boolean cancelled;

    public Event() {
    }

    public boolean registrationClosed() {
        long todaySec = new Date().getTime()/1000;
        return todaySec > to.getSeconds();
    }

    public boolean registrationOpen() {
        long todaySec = new Date().getTime()/1000;
        return todaySec > open.getSeconds() && !registrationClosed();
    }

    public boolean isEventFull() {
        return eventFull;
    }

    public void setEventFull(boolean eventFull) {
        this.eventFull = eventFull;
    }

    public boolean isWhoIsIn() {
        return whoIsIn;
    }

    public void setWhoIsIn(boolean whoIsIn) {
        this.whoIsIn = whoIsIn;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getOrganisers() {
        return organisers;
    }

    public void setOrganisers(List<String> organisers) {
        this.organisers = organisers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    public Timestamp getOpen() {
        return open;
    }

    public void setOpen(Timestamp open) {
        this.open = open;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String fromTo() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from.toDate());
//        int yearFrom = calendar.get(Calendar.YEAR);
        int monthFrom = calendar.get(Calendar.MONTH);
        int dayFrom = calendar.get(Calendar.DATE);

        calendar.setTime(to.toDate());
//        int yearTo = calendar.get(Calendar.YEAR);
        int monthTo = calendar.get(Calendar.MONTH);
        int dayTo = calendar.get(Calendar.DATE);

        if (monthFrom == monthTo)
            if (dayFrom == dayTo)
                return leading0s(dayFrom) + "/" + leading0s(monthFrom+1);
            else
                return leading0s(dayFrom) + "-" + leading0s(dayTo) + "/" + leading0s(monthFrom+1);
        else
            return leading0s(dayFrom) + "/" + leading0s(monthFrom+1) + "-" + leading0s(dayTo) + "/" + leading0s(monthTo+1);
    }

    private String leading0s(int number) {
        return (number>=10 ? String.valueOf(number) : "0"+number);
    }

    public void addTopic(String topic) {
        if (topics==null)
            topics = new ArrayList<>();

        if (!topics.contains(topic))
            topics.add(topic);
    }

    public void removeTopic(String topic) {
        if (topics!=null)
            topics.remove(topic);
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Timestamp getClose() {
        return close;
    }

    public void setClose(Timestamp close) {
        this.close = close;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public int compareTo(Event event) {
        return from.compareTo(event.getFrom());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event that = (Event) o;
        return  Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return fromTo() + " | " + getName() + " | <" + getCountry() + "> " + getCity();
    }

}
