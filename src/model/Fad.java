package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fad implements Serializable {
    private static int antalFade = 0;      // statisk så den kan deles blandt alle forekomster af Fad-klassen
    private final int nummer;
    private final String leverandør;
    private final String oprindeslesland;
    private final String materiale;
    private int gangeBrugt;
    private final String tidligereIndhold;
    //Association 0..* ---> 1 FadStørrelse
    private final FadStørrelse størrelse;
    //Association 0..* ---> 1 Lager
    private final Lager lager;
    //Nullable
    //Association 0..* ---> 0..1 Hylde
    private final Hylde hylde;
    //Association 1 ---> 0..* FadTilNM
    private final List<FadTilNM> indhold = new ArrayList<>();
    private double literTilbage;

    /**
     * Hylde kan være null, for et fad der er på lager uden reoler.
     */
    public Fad(String leverandør, String oprindeslesland, String materiale, String tidligereIndhold, FadStørrelse størrelse, Lager lager, Hylde hylde) {
        antalFade++;
        this.nummer = antalFade;
        this.leverandør = leverandør;
        this.oprindeslesland = oprindeslesland;
        this.materiale = materiale;
        this.tidligereIndhold = tidligereIndhold;
        this.størrelse = størrelse;
        this.literTilbage = størrelse.getInt();
        this.lager = lager;
        this.hylde = hylde;
    }

    public FadStørrelse getStørrelse() {
        return størrelse;
    }

    public Lager getLager() {
        return lager;
    }

    public Hylde getHylde() {
        return hylde;
    }

    public void tilføjFadTilNM(FadTilNM fnm){
        indhold.add(fnm);
    }

    public void opdaterLiterTilbage(){
        for (FadTilNM ftnm : indhold){
            literTilbage -= ftnm.getLiter();
        }
    }
    @Override
    public String toString() {
            String result = "Fad nr. " + nummer + ", størrelse: " + størrelse + ", lavet af " + materiale + ". Leverandør: '"
                    + leverandør + "', land: " + oprindeslesland + ". Har tidligere indeholdt " + tidligereIndhold + ".";
            if (getHylde() != null) {
                result += " Opbevares på " + lager.getNavn() + ", reol: " + hylde.getReol().getNummer()
                        + ", hylde: " + hylde.getNummer() + ".";
                result += " Opbevares på " + lager.getNavn() + ".";
            }
        return result;
    }



    public String toStringKort(){
        String result = "nr: " + nummer +", str: " + størrelse + ", l tilbage:" + literTilbage + ", " + lager.getNavn();
        if (getHylde() != null) result += ", reol: " + hylde.getReol().getNummer();
        return result;
    }
}
