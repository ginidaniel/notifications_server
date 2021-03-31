package com.inspiring.solutions.notifications.server.model;

public enum Role {

    LEADER(0),
    FOLLOWER(1);

    private int role;

    Role(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

}

