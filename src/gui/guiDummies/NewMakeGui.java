package gui.guiDummies;

import model.NewMake;

public class NewMakeGui {
    private String navn;
    private double literTilbage;
    private NewMake original;

    public NewMakeGui(NewMake newMake){
        this.literTilbage = newMake.getLiterTilbage();
        this.navn = newMake.getNavn();
        this.original = newMake;
    }

    public void decLiterTilbage(double liter){
        this.literTilbage -= liter;
    }

    public double getLiterTilbage() {
        return literTilbage;
    }

    public NewMake getOriginal() {
        return original;
    }

    @Override
    public String toString() {
        return "New make " + navn + ", liter tilbage: " + literTilbage;
    }
}
