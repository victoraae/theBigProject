package model;

import java.io.Serializable;
import java.util.Arrays;

public class Hylde implements Serializable {
    private final int nummer;
    private final boolean[] pladserArray;
    private final boolean erLedig;
    private final int størrelse;
    //komposition 0..* ---> 1 Reol
    private final Reol reol;

    public Hylde(Reol reol, int nummer, int størrelse) {
        this.nummer = nummer;
        this.størrelse = størrelse;
        pladserArray = new boolean[størrelse];
        for (int i = 0; i < pladserArray.length; i++) {
            pladserArray[i] = true;
        }
        this.erLedig = true;
        this.reol = reol;
    }

    public int getNummer() {
        return nummer;
    }

    public int getStørrelse() {
        return størrelse;
    }


    public void tilføjFad(String størrelse) {
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
                if (pladserArray[i] && pladserArray[i + 1]) {
                    result = true;
                    pladserArray[i] = false;
                    pladserArray[i + 1] = false;
                }
            }
        }

        if (størrelse.equals("STOR")) {
            for (int i = 0; i < pladserArray.length - 3 && !result; i++) {
                if (pladserArray[i] && pladserArray[i + 1] && pladserArray[i + 2] && pladserArray[i + 3]) {
                    result = true;
                    pladserArray[i] = false;
                    pladserArray[i + 1] = false;
                    pladserArray[i + 2] = false;
                    pladserArray[i + 3] = false;
                }
            }
        }
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
                if (pladserArray[i] && pladserArray[i + 1]) {
                    result = true;
                }
            }
        }

        if (størrelse.equals("STOR")) {
            for (int i = 0; i < pladserArray.length - 3 && !result; i++) {
                if (pladserArray[i] && pladserArray[i + 1] && pladserArray[i + 2] && pladserArray[i + 3]) {
                    result = true;
                }
            }
        }
        return result;
    }

    public Reol getReol() {
        return reol;
    }

    @Override
    public String toString() {
        String result = "Hylde nr. " + nummer + " Reol nr: " + reol.getNummer() + ", antal pladser: " + størrelse + ", har ledige pladser: " + erLedig + ".";

        return result;
    }
}
