package model;

import java.time.LocalDate;

public class NewMake {
    private String navn;
    private final LocalDate datoForPåfyldning;
    private double alkoholprocent;
    private final String ansvarlig;
    private double liter;

    public NewMake(String navn, LocalDate datoForPåfyldning, double alkoholprocent, String ansvarlig, double liter) {
        this.navn = navn;
        this.datoForPåfyldning = datoForPåfyldning;
        this.alkoholprocent = alkoholprocent;
        this.ansvarlig = ansvarlig;
        this.liter = liter;
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
}
