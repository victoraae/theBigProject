package gui.guiDummies;

import model.Destillat;
import model.Korn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DestillatGui {
        private LocalDate dato;
        private double literTilbage;
        private double alkoholProcent;
        private Korn korn;
        private String rygemateriale;
        private Destillat original;

    public DestillatGui(Destillat destillat) {
        this.dato = destillat.getDato();
        this.literTilbage = destillat.getLiterTilbage();
        this.alkoholProcent = destillat.getAlkoholProcent();
        this.korn = destillat.getKorn();
        this.rygemateriale = destillat.getRygemateriale();
        this.original = destillat;
    }

    public void decLiterTilbage(double liter){
        literTilbage -= liter;
    }

    public double getLiterTilbage() {
        return literTilbage;
    }

    public Destillat getOriginal() {
        return original;
    }

    @Override
    public String toString(){
        String result = "";
        if (dato != null) {
            result = literTilbage + " liter tilbage lavet d." + dato.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
                    + ", " + alkoholProcent + "%, Lavet med " + korn.getSort() + " kornsort";

            if (!rygemateriale.isBlank()) {
                result += " og rygemateriale " + rygemateriale + ".";
            } else {
                result += ".";
            }
        }
        return result;
    }
}
