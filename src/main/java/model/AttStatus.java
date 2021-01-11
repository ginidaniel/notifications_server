package model;


import com.google.cloud.Timestamp;

public class AttStatus {

    int status;
    Timestamp timesStamp;

    public AttStatus() {
    }

    public AttStatus(int status, Timestamp timesStamp) {
        this.status = status;
        this.timesStamp = timesStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getTimesStamp() {
        return timesStamp;
    }

    public void setTimesStamp(Timestamp timesStamp) {
        this.timesStamp = timesStamp;
    }
}
