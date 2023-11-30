package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lager implements Serializable {
    private String navn;
    private final String adresse;
    private final double størrelse;
    private int kapacitet;
    private final int maxAntalReoler;
    // komposition 1 --> 0..* Reol
    private final List<Reol> reoler = new ArrayList<>();

    public Lager(String navn, String adresse, double størrelse, int kapacitet, int maxAntalReoler) {
        this.navn = navn;
        this.adresse = adresse;
        this.størrelse = størrelse;
        this.kapacitet = kapacitet;
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
}
