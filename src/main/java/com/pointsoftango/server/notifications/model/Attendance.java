package com.pointsoftango.server.notifications.model;

import java.util.List;

public class Attendance extends Interested {

    private int status;
    private List<AttStatus> history;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AttStatus> getHistory() {
        return history;
    }

    public void setHistory(List<AttStatus> history) {
        this.history = history;
    }

    public Attendance() {
    }

}
