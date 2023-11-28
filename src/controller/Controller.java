package controller;

import model.*;

import java.util.List;

public abstract class Controller {
    private static Storage storage;
    public static void setStorage(Storage storage){
        Controller.storage = storage;
    }
    public static Lager opretLager(String navn, String adresse, double størrelse, int kapacitet, int maxAntalReoler){
        if (størrelse > 0){
            Lager lager = new Lager(navn, adresse, størrelse, kapacitet, maxAntalReoler);
            storage.tilføjLager(lager);
            return lager;
        } else return null;
    }
    public static Reol opretReol(Lager lager, int nummer, int størrelse, int maxAntalHylder){
        if (størrelse > 0){
            Reol reol = new Reol(nummer, størrelse, maxAntalHylder);
            lager.tilføjReol(reol);
            return reol;
        } else return null;
    }
    public static Hylde opretHylde(Reol reol, int nummer){
        if (reol.getHylder().size() < reol.getMaxAntalHylder()){
            Hylde hylde = new Hylde(nummer, reol.getStørrelse());
            reol.tilføjHylde(hylde);
            return hylde;
        } else return null;
    }
    public static void opdaterLager(Lager lager, String navn){
        lager.setNavn(navn);
    }


    public static Lager sletLager(Lager lager){
        for(Reol reol : lager.getReoler()){
            Controller.sletReol(reol);
        }
        storage.sletLager(lager);
        return lager; //Skal dette måske være en kopi i stedet for, eller noget hvor forbindelserne er bevaret???
    }
    public static Reol sletReol(Reol reol){
        for(Hylde hylde : reol.getHylder()){
            Controller.sletHylde(reol, hylde);
        }
        return reol;
    }

    public static Hylde sletHylde(Reol reol, Hylde hylde){
        reol.sletHylde(hylde);
        return hylde;
    }

    public static void tilføjFad(Hylde hylde){}
    //TODO: LAV FAD eller destillat objekt som retur type i sletFad
    public static void sletFad(Hylde hylde){}
}
