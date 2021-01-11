package model;

public enum Process {

    LISTED(1),
    REGISTRATION(2),
    APPLICATION(3);

    private int status;

    Process(int status) {
        this.status = status;
    }

    public int status() {
        return status;
    }

    public static Process getProcess(int status) {
        switch (status) {
            case 3:
                return APPLICATION;
            case 2:
                return REGISTRATION;
            default:
                return LISTED;
        }
    }

}
