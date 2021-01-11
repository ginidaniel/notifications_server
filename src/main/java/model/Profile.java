package model;

public enum Profile {

    GUEST("GUEST"),
    ADMIN("ADMIN"),
    DANCER("DANCER"),
    DJ("DJ"),
    PERFORMER("PERFORMER"),
    ORGANISER("ORGANISER");

    private String profile;

    Profile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }
}

