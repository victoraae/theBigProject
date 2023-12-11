package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Destillat implements Serializable {
    private final LocalDate dato;
    private final double alkoholProcent;
    private final String ansvarlig;
    private final double liter;
    private double literTilbage;
    private int antalGange;
    private final String rygemateriale;
    //association 0..* --> 1 korn
    private final Korn korn;

    public Destillat(LocalDate dato, double alkoholProcent, String ansvarlig, double liter, int antalGange, String rygemateriale, Korn korn) {
        this.dato = dato;
        this.alkoholProcent = alkoholProcent;
        this.ansvarlig = ansvarlig;
        this.liter = liter;
        this.antalGange = antalGange;
        this.rygemateriale = rygemateriale;
        this.korn = korn;
        this.literTilbage = liter;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public double getLiterTilbage() {
        return literTilbage;
    }

    public Korn getKorn() {
        return korn;
    }

    public String getRygemateriale() {
        return rygemateriale;
    }

    /**
     * pre: literTilbage-liter må ikke være mindre end nul
     */
    public void decLiterTilbage(double liter){
        literTilbage -= liter;
    }


    @Override
    public String toString() {
        String result = "";
        if (dato != null) {
            result = liter + " l destillat lavet d." + dato.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
                    + " af " + ansvarlig + ", " + alkoholProcent + "%, destilleret " + antalGange + " gange. Lavet med "
                    + korn.getSort() + " kornsort";

            if (!rygemateriale.isBlank()) {
                result += " og rygemateriale " + rygemateriale + ".";
            } else {
                result += ".";
            }
        }
        return result;
    }

    public String toStringKortere(){
        String result = "";
        if (dato != null) {
            result = literTilbage + " liter tilbage lavet d." + dato.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
                    + ", " + alkoholProcent + "%, Lavet med " + korn.getSort() + " kornsort";

            if (!rygemateriale.isBlank()) {
                result += " og rygemateriale " + rygemateriale + ".";
            } else {
                result += ".";
            }
        }
        return result;
    }
}
