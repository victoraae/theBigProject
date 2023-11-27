package controller;

import model.*;

public abstract class Controller {
    private static Storage storage;
    public static void setStorage(Storage storage){
        Controller.storage = storage;
    }
    public static Lager opretLager(String navn, String adresse, double størrelse, int kapacitet, int maxAntalReoler){
        Lager lager = new Lager(navn, adresse, størrelse, kapacitet, maxAntalReoler);
        storage.tilføjLager(lager);
        return lager;
    }
    public static Reol opretReol(Lager lager, int størrelse, int nummer, int maxAntalHylder){
        Reol reol = new Reol(størrelse, nummer, maxAntalHylder);
        lager.tilføjReol(reol);
        return reol;
    }
    public static Hylde opretHylde(Reol reol, int nummer, int størrelse){
        Hylde hylde = new Hylde(nummer, størrelse);
        reol.tilføjHylde(hylde);
        return hylde;
    }
    public static void opdaterLager(Lager lager, String navn){
        lager.setNavn(navn);
    }
    public static Hylde sletHylde(Reol reol, Hylde hylde){
        reol.sletHylde(hylde);
        return hylde;
    }
    public static void tilføjFad(Hylde hylde){}
    //TODO: LAV FAD eller destillat objekt som retur type i sletFad
    public static void sletFad(Hylde hylde){}
    public static Lager sletLager(Lager lager){
        for(Reol reol : lager.getReoler()){
            Controller.sletReol(reol);
        }
        storage.sletLager(lager);
        return lager; //Skal dette maaske være en kopi i stedet for, eller noget hvor forbindelserne er bevaret???
    }
    public static Reol sletReol(Reol reol){
        for(Hylde hylde : reol.getHylder()){
            Controller.sletHylde(reol, hylde);
        }
        return reol;
    }



}
