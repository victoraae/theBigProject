package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Whisky {
    private String navn;
    private String ansvarlig;
    private LocalDate dato;
    private double antalLiter;
    private double alkoholProcent;
    private String kvalitetsstempel;
    //Aggregation 0..1 ---> 1..* NewMake
    private final List<NewMake> newMakes;
    //Aggregation 1 ---> 0..* Flaske
    private final List<Flaske> flasker = new ArrayList<>();
    private final int år = 2018;

    public Whisky(String navn, String ansvarlig, LocalDate dato, double antalLiter, double alkoholProcent, String kvalitetsstempel, List<NewMake> newMakes) {
        this.navn = navn;
        this.ansvarlig = ansvarlig;
        this.dato = dato;
        this.antalLiter = antalLiter;
        this.alkoholProcent = alkoholProcent;
        this.kvalitetsstempel = kvalitetsstempel;
        this.newMakes = newMakes;
    }

    public String getNavn() {
        return navn;
    }

    public String getAnsvarlig() {
        return ansvarlig;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getAntalLiter() {
        return antalLiter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getKvalitetsstempel() {
        return kvalitetsstempel;
    }

    public List<NewMake> getNewMakes() {
        return new ArrayList<>(newMakes);
    }

    public List<Flaske> getFlasker() {
        return new ArrayList<>(flasker);
    }
    public void opretFlaske(int nummer, int antalFlasker){
        flasker.add(new Flaske(nummer, this, antalFlasker));
    }

    public int getÅr() {
        return år;
    }

    @Override
    public String toString() {
        return navn;
    }
}
