package model;

public class FadTilNM {
    private double liter;
    private Fad fad;
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
