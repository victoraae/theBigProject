package model;

import java.util.ArrayList;

public class Reol {
    private final int størrelse;
    private final int nummer;
    private final int maxAntalHylder;
    // komposition 1 --> 0..* Hylde
    private final ArrayList<Hylde> hylder = new ArrayList<>();

    public Reol(int nummer, int størrelse, int maxAntalHylder) {
        this.nummer = nummer;
        this.størrelse = størrelse;
        this.maxAntalHylder = maxAntalHylder;
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
}
