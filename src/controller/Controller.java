package controller;

import model.*;

import java.util.List;

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
    public static Reol opretReol(Lager lager, int nummer, int størrelse, int maxAntalHylder){
        Reol reol = new Reol(lager,nummer, størrelse, maxAntalHylder);
        lager.tilføjReol(reol);
        return reol;
    }
    public static Hylde opretHylde(Reol reol, int nummer){
        Hylde hylde = new Hylde(reol, nummer, reol.getStørrelse());
        reol.tilføjHylde(hylde);
        return hylde;
    }
    public static void opdaterLager(Lager lager, String navn){
        lager.setNavn(navn);
    }


    public static Lager sletLager(Lager lager){
        storage.sletLager(lager);
        return lager; //Skal dette måske være en kopi i stedet for, eller noget hvor forbindelserne er bevaret???
    }
    public static Reol sletReol(Reol reol){
        Lager lager = reol.getLager();
        lager.sletReol(reol);

        return reol;
    }

    public static Hylde sletHylde(Hylde hylde){
        Reol reol = hylde.getReol();
        reol.sletHylde(hylde);
        return hylde;
    }



    public static void tilføjFad(Hylde hylde){}
    //TODO: LAV FAD eller destillat objekt som retur type i sletFad
    public static void sletFad(Hylde hylde){}

    public static List<Lager> getLagre(){
        return storage.getLagre();
    }
}
