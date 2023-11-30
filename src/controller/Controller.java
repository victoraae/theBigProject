package controller;

import model.*;

import java.time.LocalDate;
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
            Reol reol = new Reol(lager, nummer, størrelse, maxAntalHylder);
            lager.tilføjReol(reol);
            return reol;
        } else return null;
    }
    public static Hylde opretHylde(Reol reol, int nummer){
        if (reol.getHylder().size() < reol.getMaxAntalHylder()){
            Hylde hylde = new Hylde(reol, nummer, reol.getStørrelse());
            reol.tilføjHylde(hylde);
            return hylde;
        } else return null;
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

    /**
     * pre: hylden skal have plads til dette fad
     * @param hylde nullable
     */
    public static Fad opretFad(Lager lager, Hylde hylde, String leverandør, String oprindeslesland, String materiale, String tidligereIndhold, FadStørrelser størrelse){
        Fad fad = new Fad(leverandør, oprindeslesland, materiale, tidligereIndhold, størrelse, lager, hylde);
        storage.tilføjFad(fad);
        if (hylde != null){
            hylde.tilføjFad(størrelse.getStørrelse());
        } else {
            lager.tilføjFad(størrelse.getStørrelse());
        }

        return fad;
    }


    public static List<Lager> getLagre(){
        return storage.getLagre();
    }

    public static List<Destillat> getDestillater(){
        return storage.getDestillater();
    }

    public static List<Korn> getKorn(){
        return storage.getKorn();
    }

    public static List<Fad> getFade(){
        return storage.getFade();
    }


    public static Korn opretKorn(String sort, String bondemand, int år, String mark, String maltningsprocess){
        Korn korn = new Korn(sort, bondemand, år, mark, maltningsprocess);
        storage.tilføjKorn(korn);
        return korn;
    }

    /**
     * pre: dato <= dagens dato, 0 <= alkoholprocent <= 100, liter > 0, antal gange > 0 */
    public static Destillat opretDestillat(LocalDate dato, double alkoholProcent, String ansvarlig, double liter, int antalGange, String rygemateriale, Korn korn){
        Destillat destillat = new Destillat(dato, alkoholProcent, ansvarlig, liter, antalGange, rygemateriale, korn);
        storage.tilføjDestillat(destillat);
        return destillat;
    }
}
