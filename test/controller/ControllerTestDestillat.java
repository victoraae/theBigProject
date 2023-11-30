package controller;

import model.Destillat;
import model.Korn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTestDestillat {
    private LocalDate dato = LocalDate.parse("2023-10-01");
    private Korn korn = new Korn("Irina", "Bob Bobsson", 2023, "Mosevang",
            "Tromlespiret d. 23/09/2023 på xxx malteri i Nordjylland.");

    @BeforeEach
    void setUp() {
        Controller.setStorage(new ListStorage());
    }

    @Test
    void opretDestillat() {
        // TC1
        // Act
        Destillat destillat1 = Controller.opretDestillat(dato, 63.5, "Snævar", 300, 2, "", korn);
        // Assert
        List<Destillat> destillater = Controller.getDestillater();
        assertEquals(destillat1, destillater.get(destillater.size() - 1));

        // TC2
        // Act
        Destillat destillat2 = Controller.opretDestillat(dato, 60, "Snævar", 200, 2, "materiale", korn);
        // Assert
        destillater = Controller.getDestillater();
        assertEquals(destillat2, destillater.get(destillater.size() - 1));

        // TC3
        // Act
        Destillat destillat3 = Controller.opretDestillat(dato, 65.2, "Snævar", 250, 2, "", korn);
        // Assert
        destillater = Controller.getDestillater();
        assertEquals(destillat3, destillater.get(destillater.size() - 1));
        assertEquals(3, destillater.size());

        // udskriver alle destillater - en slags test af Destillats toString()-metode
        for (Destillat d : destillater){
            System.out.println(d);
        }
    }
}