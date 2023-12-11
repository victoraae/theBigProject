package model;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhiskyAlderTest {
    private List<NewMake> newMakes = new ArrayList<>();
    private NewMake nm1;
    private NewMake nm2;
    private NewMake nm3;
    private NewMake nm4;
    private Destillat destillat1;
    private Destillat destillat2;
    private Destillat destillat3;

    @BeforeEach
    void setUp() {
        Controller.setStorage(new ListStorage());
        destillat1 = Controller.opretDestillat(LocalDate.parse("2017-10-01"),
                63.5, "Snævar", 300, 2, "", Controller.opretKorn("Irina", "Bob Bobsson", 2017, "Mosevang",
                        "Tromlespiret d. 23/09/2023 på xxx malteri i Nordjylland."));
        destillat2 = Controller.opretDestillat(LocalDate.parse("2019-10-01"),
                63.5, "Snævar", 300, 2, "", Controller.opretKorn("Irina", "Bob Bobsson", 2019, "Mosevang",
                        "Tromlespiret d. 23/09/2023 på xxx malteri i Nordjylland."));
        destillat3 = Controller.opretDestillat(LocalDate.parse("2021-10-01"),
                63.5, "Snævar", 300, 2, "", Controller.opretKorn("Irina", "Bob Bobsson", 2021, "Mosevang",
                        "Tromlespiret d. 23/09/2023 på xxx malteri i Nordjylland."));
    }

    @Test
    void beregnAlderPåWhiskyTC1() {
        // TC1
        // Arrange
        List<Mængde> mængder1 = new ArrayList<>();
        mængder1.add(new Mængde(10, destillat1));
        nm1 = Controller.paafyldDestillat("NM77p", "Billy", mængder1, new HashMap<NewMake, Double>(), LocalDate.of(2017, 1, 1));
        List<NewMake> newMakes = new ArrayList<>();
        newMakes.add(nm1);

        // Act
        Whisky whisky = Controller.opretWhisky("Whisky1", "Billy", 5, newMakes);

        // Assert
        assertEquals(6, whisky.getÅr());
    }

    @Test
    void beregnAlderPåWhiskyTC2() {
        // TC2
        // Arrange
        List<Mængde> mængder1 = new ArrayList<>();
        mængder1.add(new Mængde(25, destillat1));
        nm1 = Controller.paafyldDestillat("NM77p", "Billy", mængder1, new HashMap<NewMake, Double>(), LocalDate.of(2017, 1, 1));
        Map<NewMake,Double> newMakesMap = new HashMap<>();
        newMakesMap.put(nm1, 15.0);
        nm2 = Controller.omhældNewMake(newMakesMap, LocalDate.of(2021, 1, 1), "Jimmy");
        List<NewMake> newMakes = new ArrayList<>();
        newMakes.add(nm2);

        // Act
        Whisky whisky = Controller.opretWhisky("Whisky1", "Billy", 5, newMakes);

        // Assert
        assertEquals(6, whisky.getÅr());
    }

    @Test
    void beregnAlderPåWhiskyTC3() {
        // TC3
        // Arrange
        List<Mængde> mængder1 = new ArrayList<>();
        mængder1.add(new Mængde(10, destillat2));
        nm1 = Controller.paafyldDestillat("NM77p", "Billy", mængder1, new HashMap<NewMake, Double>(), LocalDate.of(2019, 1, 1));
        List<Mængde> mængder2 = new ArrayList<>();
        mængder2.add(new Mængde(10, destillat3));
        nm2 = Controller.paafyldDestillat("NM78p", "Jimmy", mængder2, new HashMap<NewMake, Double>(), LocalDate.of(2020, 1, 1));
        List<NewMake> newMakes = new ArrayList<>();
        newMakes.add(nm1);
        newMakes.add(nm2);

        // Act
        Whisky whisky = Controller.opretWhisky("Whisky1", "Billy", 10, newMakes);

        // Assert
        assertEquals(3, whisky.getÅr());
    }

    @Test
    void beregnAlderPåWhiskyTC4() {
        // TC4
        // Arrange
        List<Mængde> mængder1 = new ArrayList<>();
        mængder1.add(new Mængde(25, destillat1));
        nm1 = Controller.paafyldDestillat("NM77p", "Billy", mængder1, new HashMap<NewMake, Double>(), LocalDate.of(2018, 1, 1));
        Map<NewMake,Double> newMakesMap = new HashMap<>();
        newMakesMap.put(nm1, 15.0);
        nm2 = Controller.omhældNewMake(newMakesMap, LocalDate.of(2021, 1, 1), "Jimmy");

        List<Mængde> mængder2 = new ArrayList<>();
        mængder2.add(new Mængde(25, destillat2));
        nm3 = Controller.paafyldDestillat("NM77p", "Billy", mængder2, new HashMap<NewMake, Double>(), LocalDate.of(2020, 1, 1));
        Map<NewMake,Double> newMakesMap2 = new HashMap<>();
        newMakesMap2.put(nm3, 18.0);
        nm4 = Controller.omhældNewMake(newMakesMap2, LocalDate.of(2022, 1, 1), "Jimmy");

        List<NewMake> newMakes = new ArrayList<>();
        newMakes.add(nm2);
        newMakes.add(nm4);

        // Act
        Whisky whisky = Controller.opretWhisky("Whisky1", "Billy", 8, newMakes);

        // Assert
        assertEquals(3, whisky.getÅr());
    }

    @Test
    void beregnAlderPåWhiskyTC5() {
        // TC5
        // Arrange
        List<Mængde> mængder1 = new ArrayList<>();
        mængder1.add(new Mængde(25, destillat1));
        nm1 = Controller.paafyldDestillat("NM77p", "Billy", mængder1, new HashMap<NewMake, Double>(), LocalDate.of(2017, 1, 1));
        List<Mængde> mængder2 = new ArrayList<>();
        mængder2.add(new Mængde(20, destillat3));
        nm2 = Controller.paafyldDestillat("NM77p", "Billy", mængder2, new HashMap<NewMake, Double>(), LocalDate.of(2019, 1, 1));
        Map<NewMake,Double> newMakesMap = new HashMap<>();
        newMakesMap.put(nm2, 15.0);
        nm3 = Controller.omhældNewMake(newMakesMap, LocalDate.of(2023, 10, 14), "Johnny");
        List<NewMake> newMakes = new ArrayList<>();
        newMakes.add(nm1);
        newMakes.add(nm3);

        // Act
        Whisky whisky = Controller.opretWhisky("Whisky1", "Billy", 5, newMakes);

        // Assert
        assertEquals(4, whisky.getÅr());
    }
}