package controller;

import model.*;

public abstract class Controller {
    public static Reol opretReol(Lager lager){
        return null;
    }
    public static Hylde opretHylde(Hylde hylde){
        return null;
    }
    public static void opdaterLager(Lager lager, String navn){}
    public static Hylde sletHylde(Reol reol, Hylde hylde){
        return null;
    }
    public static void tilføjFad(Hylde hylde){}
    //TODO: LAV FAD eller destillat objekt som retur type i sletFad
    public static void sletFad(Hylde hylde){}
    public static Lager sletLager(Lager lager){
        return null;
    }
    public static Reol sletReol(Reol reol){
        return null;
    }



}
