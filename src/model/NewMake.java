package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewMake implements Serializable {
    private String navn;
    private final LocalDate datoForPåfyldning;
    private double alkoholprocent;
    private final String ansvarlig;
    private double liter;
    private final List<FadTilNM> fade = new ArrayList<>();
    private List<Mængde> mængder = new ArrayList<>();
    private boolean erAktiv;
    private double literTilbage;
    private final Map<NewMake, Double> newMakesLiter;

    public NewMake(String navn, LocalDate datoForPåfyldning, double alkoholprocent, String ansvarlig, Map<Fad, Double> fadeTilLiter
    , Map<NewMake, Double> newMakesLiter) {
        this.navn = navn;
        this.datoForPåfyldning = datoForPåfyldning;
        this.alkoholprocent = alkoholprocent;
        this.ansvarlig = ansvarlig;
        this.newMakesLiter = newMakesLiter;
        lavFadTilNM(fadeTilLiter);
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

    public List<FadTilNM> getFad() {
        return new ArrayList<>(fade);
    }

    public List<Mængde> getMængder() {
        return new ArrayList<>(mængder);
    }

    public void tilføjMængde(Mængde mængde){
        mængder.add(mængde);
        liter += mængde.getMængde();
    }

    /**
     * pre: literTilbage-liter != 0
     */
    public void tilføjFad(Fad fad, double liter){
        fade.add(new FadTilNM(liter, fad, this));
    }

    /**
     * hjælpe metode til constructoren
     */
    private void lavFadTilNM(Map<Fad, Double> fadeTilLiter){
        for(Map.Entry<Fad, Double> entry : fadeTilLiter.entrySet()){
            tilføjFad(entry.getKey(), entry.getValue());
        }
    }

    public boolean isErAktiv() {
        return erAktiv;
    }

    public double getLiterTilbage() {
        return literTilbage;
    }

    public Map<NewMake, Double> getNewMakesLiter() {
        return new HashMap<>(newMakesLiter);
    }

    @Override
    public String toString() {
        return "NewMake: " + ", mængder: "+  mængder;
    }
}
