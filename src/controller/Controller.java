package controller;

import model.*;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Controller {
    private static Storage storage;

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }

    public static Lager opretLager(String navn, String adresse, double størrelse, int kapacitet, int maxAntalReoler) {
        if (størrelse > 0) {
            Lager lager = new Lager(navn, adresse, størrelse, kapacitet, maxAntalReoler);
            storage.tilføjLager(lager);
            return lager;
        } else return null;
    }

    /**
     * Pre: nummer>0, maxAntalHylder>0
     */
    public static Reol opretReol(Lager lager, int nummer, int størrelse, int maxAntalHylder) {
        int i = lager.getAntalReolerNu();
        if (lager.getReoler().size() < lager.getMaxAntalReoler()) {
            Reol reol = new Reol(lager, i, størrelse, maxAntalHylder);
            lager.tilføjReol(reol);
            return reol;
        }
        return null;
    }


    public static Hylde opretHylde(Reol reol, int nummer) {
        if (reol.getHylder().size() < reol.getMaxAntalHylder()) {
            int næsteHyldeNummer = reol.getHylder().size() + 1;
            if (næsteHyldeNummer <= reol.getMaxAntalHylder()) {
                Hylde hylde = new Hylde(reol, næsteHyldeNummer, reol.getStørrelse());
                reol.tilføjHylde(hylde);
                return hylde;
            }
        }
        return null;
    }

    public static void opdaterLager(Lager lager, String navn) {
        lager.setNavn(navn);
    }


    public static Lager sletLager(Lager lager) {
        storage.sletLager(lager);
        return lager; //Skal dette måske være en kopi i stedet for, eller noget hvor forbindelserne er bevaret???
    }

    public static Reol sletReol(Reol reol) {
        Lager lager = reol.getLager();
        lager.sletReol(reol);

        return reol;
    }

    public static Hylde sletHylde(Hylde hylde) {
        Reol reol = hylde.getReol();
        reol.sletHylde(hylde);
        return hylde;
    }

    /**
     * pre: hylden skal have plads til dette fad
     *
     * @param hylde nullable
     */
    public static Fad opretFad(Lager lager, Hylde hylde, String leverandør, String oprindeslesland, String materiale, String tidligereIndhold, FadStørrelse størrelse) {
        Fad fad = new Fad(leverandør, oprindeslesland, materiale, tidligereIndhold, størrelse, lager, hylde);
        storage.tilføjFad(fad);
        if (hylde != null) {
            hylde.tilføjFad(størrelse.getStørrelse());
        } else {
            lager.tilføjFad(størrelse.getStørrelse());
        }

        return fad;
    }


    public static List<Lager> getLagre() {
        return storage.getLagre();
    }

    public static List<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    public static List<Korn> getKorn() {
        return storage.getKorn();
    }

    public static List<Fad> getFade() {
        return storage.getFade();
    }

    public static List<NewMake> getNewMakes() {
        return storage.getNewMakes();
    }

    public static List<Whisky> getWhiskyer() {
        return storage.getWhiskyer();
    }


    public static Korn opretKorn(String sort, String bondemand, int år, String mark, String maltningsprocess) {
        Korn korn = new Korn(sort, bondemand, år, mark, maltningsprocess);
        storage.tilføjKorn(korn);
        return korn;
    }

    /**
     * pre: dato <= dagens dato, 0 <= alkoholprocent <= 100, liter > 0, antal gange > 0
     */
    public static Destillat opretDestillat(LocalDate dato, double alkoholProcent, String ansvarlig, double liter, int antalGange, String rygemateriale, Korn korn) {
        Destillat destillat = new Destillat(dato, alkoholProcent, ansvarlig, liter, antalGange, rygemateriale, korn);
        storage.tilføjDestillat(destillat);
        return destillat;
    }

    /**
     * Pre: 0 <= alkoholprocent <= 100,
     * Destillaterne i mængder skal have tilstrækkeligt literTilbage
     * param: newMakesLiter kan være et tomt map
     */
    public static NewMake paafyldDestillat(String navn, String ansvarlig, List<Mængde> mængder, Map<NewMake, Double> newMakesLiter, LocalDate dato) {
        double alkoholProcent = beregnAlkoholProcent(mængder);
        NewMake newMake = new NewMake(navn, dato, alkoholProcent, ansvarlig, newMakesLiter);

        for (Mængde mængde : mængder) {
            mængde.setNewMake(newMake);
            newMake.tilføjMængde(mængde);
        }
        storage.tilføjNewMake(newMake);
        return newMake;
    }

    public static double beregnAlkoholProcent(List<Mængde> mængder) {
        double totalLiter = 0;
        double alkoholLiter = 0;
        for (Mængde m : mængder) {
            totalLiter += m.getMængde();
            alkoholLiter += m.getMængde() * (m.getDestillat().getAlkoholProcent() / 100);
        }

        return alkoholLiter / totalLiter * 100;
    }

    public static double beregnAlkoholProcentWhisky(List<NewMake> newMakes, double literVand) {
        double totalLiter = literVand;
        double alkoholLiter = 0;
        for (NewMake newMake : newMakes) {
            totalLiter += newMake.getLiter();
            alkoholLiter += newMake.getLiter() * (newMake.getAlkoholprocent() / 100);
        }

        return alkoholLiter / totalLiter * 100;
    }

    /**
     * Pre: den yngste newMake skal mindst have lagret i 3 år
     */
    public static Whisky opretWhisky(String navn, String ansvarlig, double literVand, List<NewMake> newMakes) {
        String kvalitetsstempel = newMakes.size() > 1 ? "single malt" : "single cask";

        Whisky whisky = new Whisky(navn, ansvarlig, LocalDate.now(), Controller.sumLiter(newMakes), beregnAlkoholProcentWhisky(newMakes, literVand), kvalitetsstempel, newMakes);

        Double antalFlasker2 = Controller.sumLiter(newMakes) * 0.7;
        int antalFlasker = antalFlasker2.intValue();

        for (int i = 1; i <= antalFlasker; i++) {
            whisky.opretFlaske(i, antalFlasker);
        }

        storage.tilføjWhisky(whisky);
        return whisky;
    }

    /**
     * hjælpe metode til controller
     */
    private static double sumLiter(List<NewMake> newMakes) {
        double result = 0;

        for (NewMake newMake : newMakes) {
            result += newMake.getLiter();
        }
        return result;
    }

    //TODO:: noget med alderen på vores whisky, hvor mange år den har modnet
    //TODO:: controller metode med tilføj fad på newMake der søger for at literTilbage ikke falder under 0
    //TODO:: kode, som kan tømme vores fade når man laver en whisky + inkrementerer gangeBrugt
    //TODO:: kode, som kan tomme vores fade når man omhælder

    /**
     * Pre: FadTilNM's antal liter må ikke overskride newMake'ts antal liter
     */
    public static void tilføjFTilNMtilNM(List<FadTilNM> fnm, NewMake nm) {
        for (FadTilNM fadTilNM : fnm) {
            if(fadTilNM.getFad().getIndhold()==null){
                nm.tilføjFad(fadTilNM);
                fadTilNM.getFad().setIndhold(fadTilNM);
            } else fadTilNM.getFad().getIndhold().incLiter(fadTilNM.getLiter());
            System.out.println("TEST1");
//            if(!nm.getFad().contains(fadTilNM)) nm.tilføjFad(fadTilNM);
//            else {
//                if (fadTilNM.getFad().getIndhold() == null) {
//                    fadTilNM.getFad().setIndhold(fadTilNM);
//                } else fadTilNM.getFad().getIndhold().incLiter(fadTilNM.getLiter());
//            }
        }
    }

    /**
     * metode til paafyld gui trin 2, hvis man trykker fortryd
     */
    public static void sletNewMake(NewMake newMake) {
        storage.sletNewMake(newMake);
    }

    /**
     * Pre: NewMakes liter tilbage må ikke gå under 0
     */
    public static NewMake omhældNewMake(Map<NewMake,Double> newMakes, LocalDate dato, String ansvarlig) {
        String navn = "";
        double antalLiter = 0;
        for (Map.Entry<NewMake,Double> entry : newMakes.entrySet()) {
            navn += entry.getKey().getNavn() + ", ";
            antalLiter += entry.getValue();
            entry.getKey().decLiterTilbage(entry.getValue());
        }
        navn = navn.substring(0, navn.length() - 2);
        ArrayList<NewMake> temp = new ArrayList<>(newMakes.keySet());
        NewMake newMake = new NewMake(navn,dato,beregnAlkoholProcentWhisky(temp, 0),ansvarlig,newMakes);
        newMake.setLiter(antalLiter);
        newMake.setLiterTilbage(antalLiter);
        storage.tilføjNewMake(newMake);
        return newMake;
    }

    //Bruges til paafyld og omhæld, det skal ske på tomme fade, inkluderer ikke fade der brugt 3 gange
    public static List<Fad> getTommeFade(){
        List<Fad> result = new ArrayList<>();
        for(Fad fad : Controller.getFade()){
            if((fad.getIndhold()==null || fad.getIndhold().getLiter() == 0) && fad.getGangeBrugt()<3) {
                result.add(fad);
            }
        }
        return result;
    }

    public static List<FadTilNM> getIkkeTommeFade(){
        return new ArrayList<>(); //virker ikke...
    }

    public static List<NewMake> getIkkeTommeNewMakes(){
        List<NewMake> result = new ArrayList<>();
        for(NewMake nm : Controller.getNewMakes()){
            if(nm.getLiterTilbage()!=0)result.add(nm);
        }
        return result;
    }

}

