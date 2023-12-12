package model;

import java.io.Serializable;
import java.util.Objects;

public class FadTilNM implements Serializable {
    private double liter;
    //Assosiation 0..* ---> 1 Fad
    private final Fad fad;
    //Assosiation 1..* ---> 1 NewMake
    private final NewMake newMake;
    public FadTilNM(double liter, Fad fad, NewMake newMake) {
        this.liter = liter;
        this.fad = fad;
        this.newMake = newMake;
    }

    public double getLiter() {
        return liter;
    }

    public Fad getFad() {
        return fad;
    }

    public final NewMake getNewMake() {
        return newMake;
    }

    /**
     * pre: liter>=0
     * bruges til at justere liter på fadet efter enten omhældning eller lavWhisky
     */
    public void setLiter(double liter) {
        this.liter = liter;
    }
    public void decLiter(double liter){
        this.liter -= liter;
    }

    public void incLiter(double liter){
        this.liter += liter;
    }

    @Override
    public String toString() {
        return "Liter: " + liter + ", Fad: " + fad.toStringKort() + ", NewMake: " + newMake.ekstraKortToStringTilFTNM();
    }

    public String toStringKort(){
        Fad f = this.getFad();
        String result = "Liter: " + liter + ", Fad str: " + f.getStørrelse().getInt() + ", Fad nr: " + f.getNummer() +", Lager: " + f.getLager().getNavn();;

        if(f.getHylde()!=null){
            result += "Reol: "  + f.getHylde().getReol().getNummer() + ", Hylde: " + f.getHylde().getNummer();
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FadTilNM fadTilNM = (FadTilNM) o;
        return Objects.equals(fad, fadTilNM.fad) && Objects.equals(newMake, fadTilNM.newMake);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fad, newMake);
    }
}
