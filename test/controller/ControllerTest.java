package controller;

import model.*;
import org.junit.jupiter.api.*;
import storage.ListStorage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    void setUp() {
        Controller.setStorage(new ListStorage());

    }

    @Test
    void opretLager() {
        //Testcase 1
        //Act
        Lager lager = Controller.opretLager("Lager1", "Adresse1", 0, 5, 5);
        //Assert
        List<Lager> lagre = Controller.getLagre();
        //assertNotEquals(lager, lagre.get(lagre.size()-1));
        //TODO:: sørg for at et lager med størrelse 0 ikke oprettes!!!

        //Testcase 2
        //Act
         lager = Controller.opretLager("Lager2", "Adresse1", 5, 0, 5);
         //Assert
        lagre = Controller.getLagre();
        assertEquals(lager, lagre.get(lagre.size()-1));

        //Testcase3
        //Act
        lager = Controller.opretLager("Lager3", "Adresse1", 5, 5, 0);
        //Assert
        lagre = Controller.getLagre();
        assertEquals(lager, lagre.get(lagre.size()-1));

       //Testcase4
       //Act
        lager = Controller.opretLager("Lager4", "Adresse1", 5, 5, 5);
        //Assert
        lagre = Controller.getLagre();
        assertEquals(lager, lagre.get(lagre.size()-1));
    }

    @Test
    void opretReol() {
        //Arrange
        Lager lager = Controller.opretLager("Lager1", "Adresse1", 5, 5, 2);

        //Testcase 1
        //Act
        Reol reol = Controller.opretReol(lager, 1, 0, 1);
        //Assert
        List<Reol> reoler = Controller.getLagre().get(0).getReoler();
        //assertNotEquals(reol, reoler.get(reoler.size()-1));
        //TODO: En reol med størrelse nul skal ikke oprettes!!!

        //Testcase 2
        //Act
        reol = Controller.opretReol(lager, 0, 1, 1);
        //Assert
        reoler = Controller.getLagre().get(0).getReoler();
        assertEquals(reol, reoler.get(reoler.size()-1));

        //Testcase 3
        //Act
        reol = Controller.opretReol(lager, 1, 1, 0);
        //Assert
        reoler = Controller.getLagre().get(0).getReoler();
        assertEquals(reol, reoler.get(reoler.size()-1));

        //Testcase 4
        //Act
        reol = Controller.opretReol(lager, 1, 1, 1);
        //Assert
        reoler = Controller.getLagre().get(0).getReoler();
        //assertNotEquals(reol, reoler.get(reoler.size()-1));
        //TODO: Lagrets maks antal reoler må ikke overskrides!!!
    }

    @Test
    void opretHylde() {
        //Arrange
        Lager lager = Controller.opretLager("Lager1", "Adresse1", 5, 5, 5);
        Reol reol = Controller.opretReol(lager, 1,1, 1);

        //Testcase 1
        //Act
        Hylde hylde = Controller.opretHylde(reol, 0);
        //Assert
        List<Hylde> hylder = Controller.getLagre().get(0).getReoler().get(0).getHylder();
        assertEquals(hylde, hylder.get(hylder.size()-1));

        //Testcase 2
        //Act
        hylde = Controller.opretHylde(reol, 1);
        //Assert
        hylder = Controller.getLagre().get(0).getReoler().get(0).getHylder();
        //assertNotEquals(hylde, hylder.get(hylder.size()-1));
        //TODO: Reolens maks antal hylder må ikke overskrides!!!
    }

    @Test
    void sletLager() {
        //Testcase 1
        //Act
        Lager lager = Controller.opretLager("Lager2", "Adresse1", 5, 0, 5);
        //Assert
        List<Lager> lagre = Controller.getLagre();
        assertEquals(lager, lagre.get(lagre.size()-1));
    }

    @Test
    void sletReol() {
    }

    @Test
    void sletHylde() {
    }
}