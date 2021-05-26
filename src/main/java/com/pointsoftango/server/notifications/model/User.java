package com.pointsoftango.server.notifications.model;

import com.google.cloud.Timestamp;

import java.util.Objects;

public class User implements Comparable<User> {

    private String username;
    private String email;
    private String name;
    private String surname;

    private String phone;
    private String phoneCode;

    private boolean picture;
    private long since;

    private String birthCity;
    private String birthCountry;
    private String resCity;
    private String resCountry;
    private String address;
    private String postCode;

    private String facebook;
    private boolean organiser;
    private boolean whoIsIn;

    private Profile profile;
    private Role role;

    private Timestamp timestampDoB;

    private boolean darkMode;
    private boolean wiiEnabled = true;
    private boolean chatEnabled = true;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPicture() {
        return picture;
    }

    public void setPicture(boolean picture) {
        this.picture = picture;
    }

    public long getSince() {
        return since;
    }

    public void setSince(long since) {
        this.since = since;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getResCity() {
        return resCity;
    }

    public void setResCity(String resCity) {
        this.resCity = resCity;
    }

    public String getResCountry() {
        return resCountry;
    }

    public void setResCountry(String resCountry) {
        this.resCountry = resCountry;
    }

    public boolean isWhoIsIn() {
        return whoIsIn;
    }

    public void setWhoIsIn(boolean whoIsIn) {
        this.whoIsIn = whoIsIn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public boolean isOrganiser() {
        return organiser;
    }

    public void setOrganiser(boolean organiser) {
        this.organiser = organiser;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public Timestamp getTimestampDoB() {
        return timestampDoB;
    }

    public void setTimestampDoB(Timestamp doB) {
        this.timestampDoB = doB;
    }

    public String fullName() {
        return getName() + " " + getSurname();
    }

    @Override
    public int compareTo(User user) {
        return username.compareTo(user.username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public boolean isChatEnabled() {
        return chatEnabled;
    }

    public void setChatEnabled(boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }

    public boolean isWiiEnabled() {
        return wiiEnabled;
    }

    public void setWiiEnabled(boolean wiiEnabled) {
        this.wiiEnabled = wiiEnabled;
    }
}
