package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTestNewMake {
    /*private Lager lager;
    private Fad fad ;
    private Korn korn;
    private Destillat destillat;


    @BeforeEach
    void setUp() {
        Controller.setStorage(new ListStorage());
        lager = Controller.opretLager("Lager1", "Adresse1", 50, 50, 0);
        fad = Controller.opretFad(lager, null, "The Jolly Barrel", "Storbritannien", "egetræ",
                "sherry", FadStørrelser.L100);
        korn = Controller.opretKorn("Irina", "Bob Bobsson", 2023, "Mosevang",
                "Tromlespiret d. 23/09/2023 på xxx malteri i Nordjylland.");
        destillat = Controller.opretDestillat(LocalDate.parse("2023-10-01"),
                63.5, "Snævar", 300, 2, "", korn);
    }


    @Test
    void opretNewMake1() {
        // TC1
        // Act
        NewMake newMake1 = Controller.opretNewMake("NM1", 60.5, "Snævar", 50, fad, destillat);

        // Assert
        List<NewMake> newMakes = Controller.getNewMakes();
        assertEquals(newMake1, newMakes.get(newMakes.size() - 1));
        assertEquals(fad, newMake1.getFad());
        assertEquals(fad, newMake1.getFad());
    }

    @Test
    void opretNewMake2() {
        // TC2
        // Act
        NewMake newMake2 = Controller.opretNewMake("NM2", 60.5, "Snævar", 100, fad, destillat);

        // Assert
        List<NewMake> newMakes = Controller.getNewMakes();
        assertEquals(newMake2, newMakes.get(newMakes.size() - 1));
    }*/
}