package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reol implements Serializable {
    private final int størrelse;
    private final int nummer;
    private final int maxAntalHylder;
    // komposition 1 --> 0..* Hylde
    private final List<Hylde> hylder = new ArrayList<>();
    private final Lager lager;


    public Reol(Lager lager, int nummer, int størrelse, int maxAntalHylder) {
        this.nummer = nummer;
        this.størrelse = størrelse;
        this.maxAntalHylder = maxAntalHylder;
        this.lager = lager;
    }

    public int getNummer() {
        return nummer;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public int getMaxAntalHylder() {
        return maxAntalHylder;
    }

    public ArrayList<Hylde> getHylder() {
        return new ArrayList<>(hylder);
    }

    public void tilføjHylde(Hylde hylde){
        hylder.add(hylde);
    }

    public void sletHylde(Hylde hylde){
        hylder.remove(hylde);
    }

    public Lager getLager() {
        return lager;
    }

    @Override
    public String toString(){
        String result = "Reol nr. " + nummer + ", står på lager " + lager.getNavn()
                + ". Antal pladser: " + størrelse + ", max antal hylder: " + maxAntalHylder + ".";

        return result;
    }
}
