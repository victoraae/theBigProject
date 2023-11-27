package model;

import java.util.ArrayList;

public class Reol {
    private int størrelse;
    private int nummer;
    private int maxAntalHylder;
    private final ArrayList<Hylde> hylder = new ArrayList<>();

    public Reol(int størrelse, int nummer) {
        this.størrelse = størrelse;
        this.nummer = nummer;
    }
}
