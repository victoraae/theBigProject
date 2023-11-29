package model;

import java.util.Arrays;

public class Hylde {
    private final int nummer;
    private final boolean[] pladserArray;
    private boolean erLedig;
    private final int størrelse;
    private final Reol reol;

    public Hylde(Reol reol, int nummer, int størrelse) {
        this.nummer = nummer;
        this.størrelse = størrelse;
        pladserArray = new boolean[størrelse];
        this.reol = reol;
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

    public boolean erPladsLedig(int index) {
        return pladserArray[index];
    }

    /**
     *
     * @return returnerer true hvis fad blev tilføjet, false hvis der ikke er plads på hylden
     */
    public boolean tilføjFad(String størrelse) {
        boolean result = false;

        if (størrelse.equals("LILLE")) {
            for (int i = 0; i < pladserArray.length && !result; i++) {
                if (pladserArray[i]) {
                    result = true;
                    pladserArray[i] = false;
                }
            }
        }

        if (størrelse.equals("MELLEM")) {
            for (int i = 0; i < pladserArray.length - 1 && !result; i++) {
                if (pladserArray[i] && pladserArray[i + 1] && (i+1)%2==0) {
                    result = true;
                    pladserArray[i] = false;
                    pladserArray[i + 1] = false;
                }
            }
        }

        if (størrelse.equals("STOR")) {
            for (int i = 0; i < pladserArray.length - 3 && !result; i++) {
                if (pladserArray[i] && pladserArray[i + 1] && pladserArray[i + 2] && pladserArray[i + 3] && (i+1)%4==0) {
                    result = true;
                    pladserArray[i] = false;
                    pladserArray[i + 1] = false;
                    pladserArray[i + 2] = false;
                    pladserArray[i + 3] = false;
                }
            }
        }
        return result;
    }

    /**
     * Hjælpemetode tiltænkt GUI
     */
    public boolean erDerPladsTilFad(String størrelse) {
        boolean result = false;

        if (størrelse.equals("LILLE")) {
            for (int i = 0; i < pladserArray.length && !result; i++) {
                if (pladserArray[i]) {
                    result = true;
                }
            }
        }

        if (størrelse.equals("MELLEM")) {
            for (int i = 0; i < pladserArray.length - 1 && !result; i++) {
                if (pladserArray[i] && pladserArray[i + 1] && (i+1)%2==0) {
                    result = true;
                }
            }
        }

        if (størrelse.equals("STOR")) {
            for (int i = 0; i < pladserArray.length - 3 && !result; i++) {
                if (pladserArray[i] && pladserArray[i + 1] && pladserArray[i + 2] && pladserArray[i + 3] && (i+1)%4==0) {
                    result = true;
                }
            }
        }
        return result;
    }

    public Reol getReol() {
        return reol;
    }
}
