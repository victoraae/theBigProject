package model;

import java.io.Serializable;

public enum FadStørrelser{
    L30("LILLE"),
    L50("LILLE"),
    L100("MELLEM"),
    L190("STOR"),
    L250("STOR");

    private String størrelse;

    public String getStørrelse() {
        return størrelse;
    }

    private FadStørrelser(String størrelse) {
       this.størrelse = størrelse;
    }
}
