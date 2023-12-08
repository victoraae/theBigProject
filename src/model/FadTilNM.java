package model;

public class FadTilNM {
    private double liter;
    //Assosiation 0..* ---> 1 Fad
    private Fad fad;
    //Assosiation 1..* ---> 1 NewMake
    private NewMake newMake;
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

    public NewMake getNewMake() {
        return newMake;
    }
}
