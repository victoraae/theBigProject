package controller;

import model.Fad;
import model.Korn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTestKorn {
    private String bondemand = "Bob Bobsson";
    private int år = 2023;
    private String mark1 = "Stadsgaard";
    private String mark2 = "Mosevang";
    private String maltningsprocess = "Tromlespiret d. 23/09/2023 på xxx malteri i Nordjylland.";

    @BeforeEach
    void setUp() {
        Controller.setStorage(new ListStorage());
    }

    @Test
    void opretKorn() {
        // TC1
        // Act
        Korn korn1 = Controller.opretKorn("Evergreen", bondemand, år, mark1, maltningsprocess);
        // Assert
        List<Korn> korn = Controller.getKorn();
        assertEquals(korn1, korn.get(korn.size() - 1));

        // TC2
        // Act
        Korn korn2 = Controller.opretKorn("Stairway", bondemand, år, mark1, maltningsprocess);
        // Assert
        korn = Controller.getKorn();
        assertEquals(korn2, korn.get(korn.size() - 1));

        // TC3
        // Act
        Korn korn3 = Controller.opretKorn("Irina", bondemand, år, mark1, maltningsprocess);
        // Assert
        korn = Controller.getKorn();
        assertEquals(korn3, korn.get(korn.size() - 1));
        assertEquals(3, korn.size());

        // udskriver alle korn - en slags test af Korns toString()-metode
        for (Korn k : korn){
            System.out.println(k);
        }
    }
}