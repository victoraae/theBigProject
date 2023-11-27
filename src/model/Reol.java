package model;

import java.util.ArrayList;

public class Reol {
    private final int størrelse;
    private final int nummer;
    private final int maxAntalHylder;
    private final ArrayList<Hylde> hylder = new ArrayList<>();

    public Reol(int størrelse, int nummer, int maxAntalHylder) {
        this.størrelse = størrelse;
        this.nummer = nummer;
        this.maxAntalHylder = maxAntalHylder;
    }

    public int getStørrelse() {
        return størrelse;
    }

    public int getNummer() {
        return nummer;
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
}
