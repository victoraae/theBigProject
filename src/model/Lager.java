package model;

import java.util.ArrayList;

public class Lager {
    private String navn;
    private String adresse;
    private double størrelse;
    private int kapacitet;
    private final ArrayList<Reol> reoler = new ArrayList<>();

    public Lager(String navn, String adresse, double størrelse, int kapacitet) {
        this.navn = navn;
        this.adresse = adresse;
        this.størrelse = størrelse;
        this.kapacitet = kapacitet;
    }


}
