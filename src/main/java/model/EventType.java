package model;

public enum EventType {

    FESTIVAL(1),
    MARATHON(2),
    ENCUENTRO(3),
    FESTIVAL_MARATHON(4),
    FESTIVAL_CHAMPIONSHIP(5);

    private int type;

    EventType(int type) {
        this.type = type;
    }

    public int type() {
        return type;
    }

    public static EventType getType(int type) {
        switch (type) {
            case 2:
                return MARATHON;
            case 3:
                return ENCUENTRO;
            case 4:
                return FESTIVAL_MARATHON;
            case 5:
                return FESTIVAL_CHAMPIONSHIP;
            default:
                return FESTIVAL;
        }
    }
}
