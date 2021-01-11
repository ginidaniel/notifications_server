package model;

public class NotificationSett {

    private boolean fromInterested = true;
    private boolean fromRegistered = true;

    public NotificationSett() {
    }

    public boolean getFromInterested() {
        return fromInterested;
    }

    public void setFromInterested(boolean fromInterested) {
        this.fromInterested = fromInterested;
    }

    public boolean getFromRegistered() {
        return fromRegistered;
    }

    public void setFromRegistered(boolean fromRegistered) {
        this.fromRegistered = fromRegistered;
    }
}
