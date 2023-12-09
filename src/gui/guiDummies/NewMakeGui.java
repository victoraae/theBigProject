package gui.guiDummies;

import model.NewMake;

public class NewMakeGui {
    private String navn;
    private double literTilbage;

    public NewMakeGui(NewMake newMake){
        this.literTilbage = newMake.getLiterTilbage();
        this.navn = newMake.getNavn();
    }

    public void decLiterTilbage(double liter){
        this.literTilbage -= liter;
    }

    public String toStringKort() {
        return "New make " + navn + ", liter tilbage: " + literTilbage;
    }
}
