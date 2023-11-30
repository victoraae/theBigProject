package model;

import java.io.Serializable;

public class Fad implements Serializable {
    private static int antalFade = 0;      // statisk så den kan deles blandt alle forekomster af Fad-klassen
    private int nummer;
    private String leverandør;
    private String oprindeslesland;
    private String materiale;
    private int gangeBrugt;
    private String tidligereIndhold;
    private FadStørrelser størrelse;
    private Lager lager;
    //Association 0..* -> 1 Lager
    private Hylde hylde; //Nullable
    //Association 0..* -> 0..1 Hylde

    /**
     * Hylde kan være null, for et fad der er på lager uden reoler.
     */
    public Fad(String leverandør, String oprindeslesland, String materiale, String tidligereIndhold, FadStørrelser størrelse, Lager lager, Hylde hylde) {
        antalFade++;
        this.nummer = antalFade;
        this.leverandør = leverandør;
        this.oprindeslesland = oprindeslesland;
        this.materiale = materiale;
        this.tidligereIndhold = tidligereIndhold;
        this.størrelse = størrelse;
        this.lager = lager;
        this.hylde = hylde;
    }

    public int getNummer() {
        return nummer;
    }

    public String getLeverandør() {
        return leverandør;
    }

    public String getOprindeslesland() {
        return oprindeslesland;
    }

    public String getMateriale() {
        return materiale;
    }

    public int getGangeBrugt() {
        return gangeBrugt;
    }

    public String getTidligereIndhold() {
        return tidligereIndhold;
    }

    public FadStørrelser getStørrelse() {
        return størrelse;
    }

    public Lager getLager() {
        return lager;
    }

    public Hylde getHylde() {
        return hylde;
    }

    @Override
    public String toString(){
        String result = "Fad nr. " + nummer + ", størrelse: " + størrelse + ", lavet af " + materiale + ". Leverandør: '"
                + leverandør + "', land: " + oprindeslesland + ". Har tidligere indeholdt " + tidligereIndhold + ".";
        if (getHylde() != null){
            result += " Opbevares på " + lager.getNavn() + ", reol: " + hylde.getReol().getNummer()
                    + ", hylde: " + hylde.getNummer() + ".";
        } else {
            result += " Opbevares på " + lager.getNavn() + ".";
        }

        return result;
    }
}
