package HyldeTest;

import controller.Controller;
import model.Hylde;
import model.Lager;
import model.Reol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import static org.junit.jupiter.api.Assertions.*;

public class TilføjFadTest {

    private ListStorage storage;
    private Hylde hylde;

    @BeforeEach
    void setUp() {
        storage = new ListStorage();
        Controller.setStorage(storage);
        Lager lager = Controller.opretLager("Lager 1", "Adresse 1", 230, 500, 7);
        Reol reol = Controller.opretReol(lager, 1, 15, 3);
        hylde = Controller.opretHylde(reol, 1);
    }

    @Test
    void testTilføjLilleFadTilFyldtArray() {
        //Arrange
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.tilføjFad("LILLE");
        //Assert
        assertEquals(true, result);
    }

    @Test
    public void testTilføjMellemstortFadTilFuldtArray() {
        //Arrange
        hylde.tilføjFad("MELLEM");
        hylde.tilføjFad("MELLEM");
        hylde.tilføjFad("MELLEM");
        hylde.tilføjFad("MELLEM");
        hylde.tilføjFad("MELLEM");
        //Act
        boolean result = hylde.tilføjFad("MELLEM");
        //Assert
        assertEquals(true, result);
    }

    @Test
    public void testTilføjStortFadTilFuldtArray() {
        // Arrange
        for (int i = 0; i < hylde.getStørrelse(); i++) {
            hylde.tilføjFad("STOR");
        }

        // Act
        boolean result = hylde.tilføjFad("STOR");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testTilføjFadMedTomStørrelse() {

        // Arrange
        boolean[] pladserArray = {true, true, true, true, true};
        // Act
        boolean resultat = hylde.tilføjFad("");

        // Assert
        assertFalse(resultat);
    }

    @Test
    public void testTilføjLilleFadTilEnPladsArray() {
        // Arrange
        boolean[] pladserArray = {true};


        // Act
        boolean resultat = hylde.tilføjFad("LILLE");

        // Assert
        assertTrue(resultat);
        assertEquals(true, pladserArray[0]);
    }

    @Test
    public void testTilføjMellemFadTilEnPladsArray() {
        // Arrange
        boolean[] pladserArray = {true, true};
        //Act
        boolean resultat = hylde.tilføjFad("MELLEM");

        //Assert
        assertTrue(resultat);
        assertEquals(true, pladserArray[0]);
        assertEquals(true, pladserArray[1]);
    }
}