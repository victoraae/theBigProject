package model;

import java.io.Serializable;
import java.time.LocalDate;

public class NewMake implements Serializable {
    private String navn;
    private final LocalDate datoForPåfyldning;
    private double alkoholprocent;
    private final String ansvarlig;
    private double liter;
    private final Fad fad;
    private final Destillat destillat;

    public NewMake(String navn, LocalDate datoForPåfyldning, double alkoholprocent, String ansvarlig, double liter, Fad fad, Destillat destillat) {
        this.navn = navn;
        this.datoForPåfyldning = datoForPåfyldning;
        this.alkoholprocent = alkoholprocent;
        this.ansvarlig = ansvarlig;
        this.liter = liter;
        this.fad = fad;
        this.destillat = destillat;
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

    public Destillat getDesillat() {
        return destillat;
    }
}
