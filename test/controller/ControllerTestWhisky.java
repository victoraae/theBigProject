package controller;

import model.*;
import org.junit.jupiter.api.*;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTestWhisky {
    // uformel test, der virkede i de tidligere iterationer, men virker ikke efter Construction iteration 1/2,
    // da der blev lavet mange ændringer i Controller metoderne, der opretter objekterne.
    private List<NewMake> newMakes = new ArrayList<>();
    private NewMake nm1;
    private NewMake nm2;
    private Fad fad;
    private Fad fad2;
    private Destillat destillat;
    private
    @BeforeEach
    void setUp() {
//       Controller.setStorage(new ListStorage());
//        Lager lager = Controller.opretLager("Lager1", "Adresse1", 50, 50, 0);
//        fad = Controller.opretFad(lager, null, "The Jolly Barrel", "Storbritannien", "egetræ",
//                "sherry", FadStørrelse.L100.L100);
//        fad2 = Controller.opretFad(lager, null, "Bawelwol", "England", "egetræ",
//                "sherry", FadStørrelse.L250);
//
//        destillat = Controller.opretDestillat(LocalDate.now(), 70, "Bob", 300, 2, "Tørv",
//                new Korn("t", "t", 1776, "t", "t"));
//
//
//        List<Mængde> mængder1 = new ArrayList<>();
//        mængder1.add(new Mængde(100, destillat));
//
//        List<Mængde> mængder2 = new ArrayList<>();
//        mængder2.add(new Mængde(200, destillat));
//
//        nm1 = Controller.paafyldDestillat("NM77p", "Billy", mængder1, fad);
//        nm2 = Controller.paafyldDestillat("NM78p", "Billy", mængder1, fad2);
//
//        newMakes.add(nm1);
//        newMakes.add(nm2);
    }

    @Test
    void opretWhisky() {
        // TC1
        // Act
        Whisky whisky = Controller.opretWhisky("Whisky1", "Billy", 50, newMakes);


        // Assert
        List<Whisky> whiskyer = Controller.getWhiskyer();
        assertEquals(whisky, whiskyer.get(0));
        assertEquals(nm1, whiskyer.get(0).getNewMakes().get(0));
        assertEquals(nm2, whiskyer.get(0).getNewMakes().get(1));
        NewMake nmTemp = whiskyer.get(0).getNewMakes().get(0);
        assertEquals(fad, nmTemp.getFad());
        assertEquals(destillat, nmTemp.getMængder().get(0).getDestillat());
    }


}
