package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewMake implements Serializable, Comparable<NewMake> {
    private String navn;
    private final LocalDate datoForPåfyldning;
    private final double alkoholprocent;
    private final String ansvarlig;
    private double liter;
    //Assosiation 1 ---> 1..* FadTilNM
    private final List<FadTilNM> fade = new ArrayList<>();
    //Assosiation 1 ---> 0..* Mængde
    private final List<Mængde> mængder = new ArrayList<>();
    private boolean erOpbrugt;
    private double literTilbage;
    //Assosiation 0..* --> 1 NewMake
    private final Map<NewMake, Double> newMakesLiter;

    //TODO: denne constructor er vidst nok unødvendig
    public NewMake(String navn, LocalDate datoForPåfyldning, double alkoholprocent, String ansvarlig, Map<Fad, Double> fadeTilLiter
    , Map<NewMake, Double> newMakesLiter) {
        this.navn = navn;
        this.datoForPåfyldning = datoForPåfyldning;
        this.alkoholprocent = alkoholprocent;
        this.ansvarlig = ansvarlig;
        this.newMakesLiter = newMakesLiter;
        this.erOpbrugt = false;
        lavFadTilNM(fadeTilLiter);
    }

    /**
     * vi kan ikke huske hvad fadeTilLiter i ovenstående konstruktor skal bruges til
     */
    public NewMake(String navn, LocalDate datoForPåfyldning, double alkoholprocent, String ansvarlig, Map<NewMake, Double> newMakesLiter) {
        this.navn = navn;
        this.datoForPåfyldning = datoForPåfyldning;
        this.alkoholprocent = alkoholprocent;
        this.ansvarlig = ansvarlig;
        this.newMakesLiter = newMakesLiter;
        this.erOpbrugt = false;
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
     * pre: literTilbage-liter må ikke være mindre end nul
     */
    public void tilføjFad(FadTilNM fnm){
        fade.add(fnm);
    }

    /**
     * hjælpe metode til constructoren
     */
    private void lavFadTilNM(Map<Fad, Double> fadeTilLiter){
        for(Map.Entry<Fad, Double> entry : fadeTilLiter.entrySet()){
            FadTilNM ftnm = findFadTilNMfraFad(entry.getKey());
            if(ftnm==null){
                tilføjFad(new FadTilNM(entry.getValue(), entry.getKey(), this));
            } else ftnm.incLiter(entry.getValue());

        }
    }

    public boolean erOpbrugt() {
        return erOpbrugt;
    }

    public void setErOpbrugt(boolean erOpbrugt) {
        this.erOpbrugt = erOpbrugt;
    }

    public double getLiterTilbage() {
        return literTilbage;
    }

    public void decLiterTilbage(double liter){
        literTilbage -= liter;
        if (literTilbage == 0){
            setErOpbrugt(true);
        }
    }

    public Map<NewMake, Double> getNewMakesLiter() {
        return new HashMap<>(newMakesLiter);
    }

    @Override
    public String toString() {
        String result = "NewMake: " + navn + ", påfyldt: " +  datoForPåfyldning.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
        if (!mængder.isEmpty()){
            result += ", mængder: " + mængder;
        }
        return result;
    }

    public void setLiterTilbage(double literTilbage) {
        this.literTilbage = literTilbage;
    }
    public void setLiter(double liter) {
        this.liter = liter;
    }

    public String toStringKort() {
        return "New make " + navn + ", liter tilbage: " + literTilbage + "l";
    }

    public String ekstraKortToStringTilFTNM(){
        return navn;
    }

    @Override
    public int compareTo(NewMake newMake){
        return datoForPåfyldning.compareTo(newMake.getDatoForPåfyldning());
    }

    public String toStringLiterTotal(){
        return "New make " + navn + ", total liter: " + liter+ "l";
    }

    /**
     * hjælpemetode
     * kan returne null hvis fad ikke optræder i nogle FadTilNM
     */
    private FadTilNM findFadTilNMfraFad(Fad fad){
        FadTilNM result = null;
        for(FadTilNM ftnm : fade){
            if(ftnm.getFad().equals(fad)){
                result = ftnm;
            }
        }

        return result;
    }
}
