package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Whisky {
    private final String navn;
    private final String ansvarlig;
    private final LocalDate dato;
    private final double antalLiter;
    private final double alkoholProcent;
    private final String kvalitetsstempel;
    //Aggregation 0..1 ---> 1..* NewMake
    private final List<NewMake> newMakes;
    //Aggregation 1 ---> 0..* Flaske
    private final List<Flaske> flasker = new ArrayList<>();
    private int år;

    public Whisky(String navn, String ansvarlig, LocalDate dato, double antalLiter, double alkoholProcent, String kvalitetsstempel, List<NewMake> newMakes) {
        this.navn = navn;
        this.ansvarlig = ansvarlig;
        this.dato = dato;
        this.antalLiter = antalLiter;
        this.alkoholProcent = alkoholProcent;
        this.kvalitetsstempel = kvalitetsstempel;
        this.newMakes = newMakes;
        this.år = beregnAlderPåWhisky(this.getAlleNewMakesRekursiv(newMakes), this.dato);
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

    public String getKvalitetsstempel() {
        return kvalitetsstempel;
    }

    public List<NewMake> getNewMakes() {
        return new ArrayList<>(newMakes);
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
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

    /**
     * hjælpemetode til WhiskyGuiController og flaske historik generering
     */
    public static List<NewMake> getAlleNewMakesRekursiv(List<NewMake> newMakes){
        List<NewMake> result = new ArrayList<>();

        alleNewMakesHelper(newMakes, result);

        return result;
    }

    private static List<NewMake> alleNewMakesHelper(List<NewMake> newMakes, List<NewMake> result){
        result.addAll(newMakes);
        for(NewMake nm : newMakes){
            if(nm.getNewMakesLiter().size()!=0){ //stop klodsen her er de 'yderste' newMakes som er dem der oprettes i paafyld vinduet
                alleNewMakesHelper(nm.getNewMakesFraNMLiters(), result);
            }
        }
        return result;
    }

    /**
     * hjælpemetode til WhiskyGuiController og flaske historik generering.
     * Den finder den seneste dato fra de oprindelige New Makes (altså dem, der bliver oprettet i systemet
     * når man påfylder destillat), og beregner hvor mange år der er gået til datoen whiskyen blev tappet på flasker.
     */
    public static int  beregnAlderPåWhisky(List<NewMake> newMakes, LocalDate dato) {
        LocalDate yngsteDato = LocalDate.of(1000, 1, 1); //En meget gammel dato, der bliver overskrevet i metoden
        if (newMakes != null) {
            for (int i = 0; i < newMakes.size(); i++) {
                NewMake newMake = newMakes.get(i);
                List<Mængde> mængder = newMake.getMængder();
                if (!mængder.isEmpty()) {
                    if (newMake.getDatoForPåfyldning().isAfter(yngsteDato)) {
                        yngsteDato = newMake.getDatoForPåfyldning();
                        System.out.println("Test 2: " + yngsteDato);
                    }
                }
            }
        }
        Period period = Period.between(yngsteDato, dato);
        return period.getYears();
    }

    @Override
    public String toString() {
        return navn;
    }
}
