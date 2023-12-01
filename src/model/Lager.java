package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lager implements Serializable {
    private String navn;
    private final String adresse;
    private final double størrelse;
    private int kapacitet;
    private int pladserTilbage;     // bruges med lagre, der ikke har reoler
    private final int maxAntalReoler;
    // komposition 1 --> 0..* Reol
    private final List<Reol> reoler = new ArrayList<>();
    private int antalReolerNu = 0;

    public Lager(String navn, String adresse, double størrelse, int kapacitet, int maxAntalReoler) {
        this.navn = navn;
        this.adresse = adresse;
        this.størrelse = størrelse;
        this.kapacitet = kapacitet;
        this.pladserTilbage = kapacitet;
        this.maxAntalReoler = maxAntalReoler;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public double getStørrelse() {
        return størrelse;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public ArrayList<Reol> getReoler() {
        return new ArrayList<>(reoler);
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void tilføjReol(Reol reol){
        reoler.add(reol);
    }

    public void sletReol(Reol reol){
        reoler.remove(reol);
    }

    /**
     *
     * @return returnerer true hvis fad blev tilføjet, false hvis der ikke er plads på lageret
     */
    public boolean tilføjFad(String størrelse) {
        boolean result = false;

        if (størrelse.equals("LILLE")){
            if (pladserTilbage >= 1){
                result = true;
                pladserTilbage--;
            }
        } else if (størrelse.equals("MELLEM")){
            if (pladserTilbage >= 2){
                result = true;
                pladserTilbage -= 2;
            }
        } else if (størrelse.equals("STOR")){
            if (pladserTilbage >= 4){
                result = true;
                pladserTilbage -= 4;
            }
        }

        return result;
    }

    /**
     * Hjælpemetode tiltænkt GUI
     */
    public boolean erDerPladsTilFad(String størrelse) {
        boolean result = false;

        if (størrelse.equals("LILLE")){
            if (pladserTilbage >= 1){
                result = true;
            }
        } else if (størrelse.equals("MELLEM")){
            if (pladserTilbage >= 2){
                result = true;
            }
        } else if (størrelse.equals("STOR")){
            if (pladserTilbage >= 4){
                result = true;
            }
        }

        return result;
    }

    @Override
    public String toString(){
        String result = "Lager " + navn + ", adresse: " + adresse
                + ". Areal: " + størrelse + " m^2, kapacitet: " + kapacitet + " pladser.";
        if (maxAntalReoler != 0){
            result += " Max antal reoler: " + maxAntalReoler + ".";
        } else {
            result += " Ledige pladser tilbage: " + pladserTilbage + ".";
        }

        return result;
    }

    public int getAntalReolerNu() {
        return ++antalReolerNu;
    }
}
