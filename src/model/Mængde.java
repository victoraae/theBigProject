package model;

public class Mængde {
    private double mængde;
    private NewMake newMake;
    private Destillat destillat;

    public Mængde(double mængde, NewMake newMake, Destillat destillat) {
        this.mængde = mængde;
        this.newMake = newMake;
        this.destillat = destillat;
    }

    public double getMængde() {
        return mængde;
    }

    public NewMake getNewMake() {
        return newMake;
    }

    public Destillat getDestillat() {
        return destillat;
    }
}
