package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import model.*;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static ÅbenVinduer åbenVinduer;

    public static void main(String[] args) {
        åbenVinduer = new ÅbenVinduer();
        Storage storage = null; //ListStorage.læsStorage();
        
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
        Controller.opretDestillat(LocalDate.of(23,4,12),42,"Karsten",20,1,"Weed",korn);
        Fad fad = Controller.opretFad(lager,hylde,"Karsten","Danmark","Jern","Weed",FadStørrelser.L50);

        //--------------------- Til whisky historik test ----------------------
//        Destillat destillat3 = Controller.opretDestillat(LocalDate.now(), 70, "Bob", 300, 2, "Tørv",
//                new Korn("t", "t", 1776, "t", "t"));
//        List<Mængde> mængder1 = new ArrayList<>();
//        mængder1.add(new Mængde(100, destillat3));
//        List<Mængde> mængder2 = new ArrayList<>();
//        mængder2.add(new Mængde(200, destillat3));
//        NewMake nm1 = Controller.paafyldDestillat("NM77p", "Billy", mængder1, fad);
//        NewMake nm2 = Controller.paafyldDestillat("NM78p", "Billy", mængder2, fad);
//        List<NewMake> newMakes = new ArrayList<>();
//        newMakes.add(nm1);
//        newMakes.add(nm2);
//        Controller.opretWhisky("Whisky1", "Billy", 50, newMakes);
//
//        Destillat destillat4 = Controller.opretDestillat(LocalDate.now(), 70, "Bob", 300, 2, "Tørv",
//                new Korn("t2", "t", 1776, "t", "t"));
//        List<Mængde> mængder3 = new ArrayList<>();
//        mængder3.add(new Mængde(100, destillat3));
//        List<Mængde> mængder4 = new ArrayList<>();
//        mængder4.add(new Mængde(200, destillat3));
//        NewMake nm3 = Controller.paafyldDestillat("NM79p", "Billy", mængder3, fad);
//        List<NewMake> newMakes2 = new ArrayList<>();
//        newMakes2.add(nm3);
//        Controller.opretWhisky("Whisky2", "Billy", 50, newMakes2);

    }
}
