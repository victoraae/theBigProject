package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import model.*;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static ÅbenVinduer åbenVinduer;

    public static void main(String[] args) {
        åbenVinduer = new ÅbenVinduer();
        Storage storage = ListStorage.læsStorage();
        
        boolean flag = storage==null;
        if (flag) {
            storage = new ListStorage();
            System.out.println("Tom ListStorage oprettet");
        }

        Controller.setStorage(storage);
        if(flag) initStorage();


        System.out.println("Storage er initialiseret");

        Application.launch(HovedVindue.class);

        ListStorage.gemStorage(storage);
    }

    public static void initStorage() {
        Lager lager = Controller.opretLager("Lager 1", "Adresse 1", 230, 500, 7);
        Controller.opretLager("Lager 2", "Adresse 2", 230, 500, 7);
        Reol reol = Controller.opretReol(lager, 1, 15, 3);
        Controller.opretReol(lager, 2, 15, 4);
        Hylde hylde =  Controller.opretHylde(reol, 1);
        Korn korn = Controller.opretKorn("Evergreen Byg", "Lars Larsen", 2017, "Mark 1", "Ristet lys");
        Controller.opretKorn("Irina Byg", "Keld Keldsen", 2018, "Mark 2", "Ristet mørk");
        Controller.opretDestillat(LocalDate.of(2023,4,12),42,"Billy",20,1,"",korn);
        Fad fad = Controller.opretFad(lager,hylde,"Barrel Company Inc.","USA","Fyrretræ","bourbon", FadStørrelse.L50);
        Fad fad2 = Controller.opretFad(lager,hylde,"The Jolly Barrel","Irland","egetræ","sherry", FadStørrelse.L250);
        Fad fad3 = Controller.opretFad(lager,hylde,"Des Enormes Barriles","Spanien","egetræ","rødvin", FadStørrelse.L100);

        Fad fad4 = Controller.opretFad(lager,hylde,"Das Barel","Tyskland","Asketræ","øl", FadStørrelse.L50);
        Fad fad5 = Controller.opretFad(lager,hylde,"Le petite barrillette","Frankrig","Fyrretræ","bourbon", FadStørrelse.L100);
        Fad fad6 = Controller.opretFad(lager,hylde,"FEJL","FEJL","FEJL","FEJL", FadStørrelse.L50); //Dette fad skal ikke ses i gui
        fad6.setGangeBrugt(3);
        Fad fad7 = Controller.opretFad(lager,hylde,"Dollar Barrels","Portugal","Bøgetræ","rødvin", FadStørrelse.L190);
        fad7.setGangeBrugt(2);

        Fad fad8 = Controller.opretFad(lager,hylde,"Bwitish Bawels","England","Asketræ","brandy", FadStørrelse.L250);


        //--------------------- Til whisky historik test ----------------------
        Destillat destillat3 = Controller.opretDestillat(LocalDate.of(2017, 1, 1), 70, "Bob", 300, 2, "Tørv",
                new Korn("Byg Irina", "Lars Larsen", 2017, "Mosevangen", "Maltning 1"));
        Destillat destillat4 = Controller.opretDestillat(LocalDate.of(2015, 1, 1), 67, "Henrik", 300, 2, "Rygemateriale 1",
                new Korn("Byg Stairway", "Bob Bobsson", 2015, "Stadsgaard", "Maltning 2"));

        List<Mængde> mængder1 = new ArrayList<>();
        mængder1.add(new Mængde(50, destillat3));
        List<Mængde> mængder2 = new ArrayList<>();
        mængder2.add(new Mængde(50, destillat4));
        NewMake nm1 = Controller.paafyldDestillat("NM77p", "Billy", mængder1, new HashMap<NewMake, Double>(), LocalDate.of(2017, 1, 1));
        List<FadTilNM> listFtnm1 = new ArrayList<>();
        listFtnm1.add(new FadTilNM(50, fad, nm1));
        Controller.tilføjFTilNMtilNM(listFtnm1, nm1);
        nm1.setLiter(50.0);
        nm1.setLiterTilbage(50.0);

        NewMake nm2 = Controller.paafyldDestillat("NM78p", "Billy", mængder2, new HashMap<NewMake, Double>(), LocalDate.of(2018, 1, 1));
        List<FadTilNM> listFtnm2 = new ArrayList<>();
        listFtnm2.add(new FadTilNM(50, fad2, nm2));
        Controller.tilføjFTilNMtilNM(listFtnm2, nm2);
        nm2.setLiter(50.0);
        nm2.setLiterTilbage(50.0);
        List<NewMake> newMakes = new ArrayList<>();
        newMakes.add(nm1);
        newMakes.add(nm2);
        Controller.opretWhisky("Whisky1", "Billy", 50, newMakes);


        List<Mængde> mængder3 = new ArrayList<>();
        mængder3.add(new Mængde(50.0, destillat4));
        List<Mængde> mængder4 = new ArrayList<>();
        mængder4.add(new Mængde(25.0, destillat4));

        NewMake nm3 = Controller.paafyldDestillat("NM79p", "Billy", mængder3, new HashMap<NewMake, Double>(), LocalDate.of(2019, 1, 1));
        List<FadTilNM> listFtnm3 = new ArrayList<>();
        listFtnm3.add(new FadTilNM(50, fad3, nm3));
        Controller.tilføjFTilNMtilNM(listFtnm3, nm3);
        nm3.setLiter(50.0);
        nm3.setLiterTilbage(50.0);

        NewMake nm4 = Controller.paafyldDestillat("NM80p", "Kimmy", mængder4, new HashMap<NewMake, Double>(), LocalDate.of(2015, 1, 4));
        List<FadTilNM> listFtnm4 = new ArrayList<>();
        listFtnm4.add(new FadTilNM(25, fad8, nm4));
        Controller.tilføjFTilNMtilNM(listFtnm4, nm4);
        nm4.setLiter(25.0);
        nm4.setLiterTilbage(25.0);

    }
}
