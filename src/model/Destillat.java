package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Destillat implements Serializable {
    private LocalDate dato;
    private double alkoholProcent;
    private String ansvarlig;
    private double liter;
    private int antalGange;
    private String rygemateriale;
    //association 0..* --> 1 korn
    private Korn korn;
    //association 1 --> 0..* newmake
    private List<NewMake> newMakes = new ArrayList<>();

    public Destillat(LocalDate dato, double alkoholProcent, String ansvarlig, double liter, int antalGange, String rygemateriale, Korn korn) {
        this.dato = dato;
        this.alkoholProcent = alkoholProcent;
        this.ansvarlig = ansvarlig;
        this.liter = liter;
        this.antalGange = antalGange;
        this.rygemateriale = rygemateriale;
        this.korn = korn;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getAnsvarlig() {
        return ansvarlig;
    }

    public double getLiter() {
        return liter;
    }

    public int getAntalGange() {
        return antalGange;
    }

    public String getRygemateriale() {
        return rygemateriale;
    }

    public void setAntalGange(int antalGange) {
        this.antalGange = antalGange;
    }


    @Override
    public String toString(){
        String result = liter + "l destillat lavet d." + dato.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
                + " af " + ansvarlig + ", " + alkoholProcent + "%, destilleret " + antalGange + " gange. Lavet med "
                + korn.getSort() + " kornsort";

        if (!rygemateriale.isBlank()){
            result += " og rygemateriale " + rygemateriale + ".";
        } else {
            result += ".";
        }
        return result;
    }
}
