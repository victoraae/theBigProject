package formelleTests;

import controller.Controller;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerOpretFad {
    private ListStorage storage;
    private Lager lager;

    @BeforeEach
    void setUp() {
        storage = new ListStorage();
        Controller.setStorage(storage);

    }

    //arrange
    @Test
    void testOpretFad_LagerOprettet_HyldeOprettet_FadOprettetMedHylde() {
        // Arrange
        Lager lager = Controller.opretLager("Lager 2", "Adresse 2", 230, 500, 7);
        Reol reol = Controller.opretReol(lager, 1, 25, 20);
        Hylde hylde = Controller.opretHylde(reol, 1);

        // Act
        Fad fad = Controller.opretFad(lager, hylde, "ACME", "Danmark", "Plast", "Tomt", FadStørrelse.L250);

        // Assert
        assertNotNull(fad);
        assertNotNull(fad.getHylde());
    }

    @Test
    void testOpretFad_AlleParametreGyldige_FadOprettetMedDeAngivneParametre() {
        // Arrange
        Lager lager = Controller.opretLager("Lager 2", "Adresse 2", 230, 500, 7);
        Reol reol = Controller.opretReol(lager, 1, 25, 20);
        Hylde hylde = Controller.opretHylde(reol, 1);

        // Act
        Fad fad = Controller.opretFad(lager, hylde, "ACME", "Danmark", "Plast", "Tomt", FadStørrelse.L250);

        // Assert
        assertNotNull(fad);
        assertEquals("ACME", fad.getLeverandør());
        assertEquals("Danmark", fad.getOprindeslesland());
        assertEquals("Plast", fad.getMateriale());
        assertEquals("Tomt", fad.getTidligereIndhold());
        assertEquals(FadStørrelse.L250, fad.getStørrelse());
        assertEquals(lager, fad.getLager());
        assertEquals(hylde, fad.getHylde());
    }

    @Test
    void testOpretFadUdenHylde() {
        // Arrange
        Lager lager = Controller.opretLager("Lager 2", "Adresse 2", 230, 500, 7);
        Hylde hylde = null;

        // Act
        Fad fad =Controller.opretFad(lager, hylde, "ACME", "Danmark", "Plast", "Tomt", FadStørrelse.L250);

        // Assert
        assertNotNull(fad);
        assertEquals(lager, fad.getLager());
        assertEquals("ACME", fad.getLeverandør());
        assertEquals("Danmark", fad.getOprindeslesland());
        assertEquals("Plast", fad.getMateriale());
        assertEquals("Tomt", fad.getTidligereIndhold());
    }
}



