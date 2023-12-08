package model;

public class Mængde {
    private double mængde;
    //Assositation 0..* ---> 1 NewMake
    private NewMake newMake;
    //Aggregation 0..* ---> 1 Destillat
    private Destillat destillat;

    public Mængde(double mængde, Destillat destillat) {
        this.mængde = mængde;
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

    public void setNewMake(NewMake newMake) {
        this.newMake = newMake;
    }

    @Override
    public String toString() {//TODO:: lav en navn variabel eller id i destillat klasse
        String result = "Mængde: " + mængde + ", destillat: " + destillat.getDato();
        if(newMake!=null) result += ", newMake: " + newMake.getNavn();
        return result;
    }
}
