package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewMake implements Serializable {
    private String navn;
    private final LocalDate datoForPåfyldning;
    private double alkoholprocent;
    private final String ansvarlig;
    private double liter;
    private final Fad fad;
    private List<Mængde> mængder = new ArrayList<>();

    public NewMake(String navn, LocalDate datoForPåfyldning, double alkoholprocent, String ansvarlig, Fad fad) {
        this.navn = navn;
        this.datoForPåfyldning = datoForPåfyldning;
        this.alkoholprocent = alkoholprocent;
        this.ansvarlig = ansvarlig;
        this.fad = fad;
    }

    // getter metoder laves på alle attributter, da de er alle vigtige for historiefortællingen
    public String getNavn() {
        return navn;
    }

    public LocalDate getDatoForPåfyldning() {
        return datoForPåfyldning;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }

    public String getAnsvarlig() {
        return ansvarlig;
    }

    public double getLiter() {
        return liter;
    }

    public Fad getFad() {
        return fad;
    }

    public List<Mængde> getMængder() {
        return new ArrayList<>(mængder);
    }

    public void tilføjMængde(Mængde mængde){
        mængder.add(mængde);
        liter += mængde.getMængde();
    }

    @Override
    public String toString() {
        return "NewMake: " + ", mængder: "+  mængder;
    }
}
