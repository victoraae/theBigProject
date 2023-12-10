package gui.guiDummies;

import model.Fad;
import model.FadTilNM;

public class FadTilNMGui {
    private Fad fad;
    private double liter;
    private FadTilNM original;

    public FadTilNMGui(FadTilNM ftnm) {
        this.fad = ftnm.getFad();
        this.liter = ftnm.getLiter();
        this.original = ftnm;
    }

    public void decLiter(double liter){
        this.liter -= liter;
    }
    public Fad getFad() {
        return fad;
    }
    public double getLiter() {
        return liter;
    }
    public FadTilNM getOriginal() {
        return original;
    }

    @Override
    public String toString(){
        Fad f = this.getFad();
        String result = "Liter: " + liter + ", Fad str: " + f.getStørrelse().getInt() + ", Lager: " + f.getLager().getNavn();;

        if(f.getHylde()!=null){
            result += "Reol: "  + f.getHylde().getReol().getNummer() + ", Hylde: " + f.getHylde().getNummer();
        }

        return result;
    }
}
