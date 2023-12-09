package model;

public enum FadStørrelse {
    L30("LILLE", 30),
    L50("LILLE", 50),
    L100("MELLEM", 100),
    L190("STOR", 190),
    L250("STOR", 250);

    private final String størrelse;
    private final int strInt;

    public String getStørrelse() {
        return størrelse;
    }

    public int getInt() {
        return strInt;
    }

    private FadStørrelse(String størrelse, int str) {
        this.størrelse = størrelse;
        this.strInt = str;
    }
}
