package model;

import java.util.Arrays;

public class Hylde {
    private final int nummer;
    private final boolean[] pladserArray;
    private boolean erLedig;
    private final int størrelse;

    public Hylde(int nummer, int størrelse) {
        this.nummer = nummer;
        this.størrelse = størrelse;
        pladserArray  = new boolean[størrelse];
    }

    public int getNummer() {
        return nummer;
    }

    public boolean[] getPladserArray() {
        return Arrays.copyOf(pladserArray, størrelse);
    }

    public boolean erLedig() {
        return erLedig;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public boolean erPladsLedig(int index){
        return pladserArray[index];
    }

    public void skiftPladsStatus(int index){
        boolean value = pladserArray[index];
        pladserArray[index] = !value;
    }
}
