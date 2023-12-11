package controller;

import model.*;
import org.junit.jupiter.api.*;
import storage.ListStorage;

import java.util.List;
import java.util.NoSuchElementException;

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

        // udskriver alle lagre - en slags test af Lagers toString()-metode
        for (Lager l : lagre){
            System.out.println(l);
        }
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


        // udskriver alle reoler - en slags test af Reols toString()-metode
        for (Reol r : reoler){
            System.out.println(r);
        }
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


        // udskriver alle hylder - en slags test af Hyldes toString()-metode
        for (Hylde h : hylder){
            System.out.println(h);
        }
    }

    @Test
    void sletLager() {
        //Testcase 1
        //Arrange
        Lager lager = Controller.opretLager("Lager2", "Adresse1", 5, 0, 5);
        //Act
        Controller.sletLager(lager);
        //Assert
        List<Lager> lagre = Controller.getLagre();
        assertEquals(0, lagre.size());

        //Testcase 2
        //Arrange
        Lager lager2 = Controller.opretLager("Lager3", "Adresse1", 5, 0, 5);
        Lager lager3 = Controller.opretLager("Lager4", "Adresse1", 5, 0, 5);
        //Act
        Controller.sletLager(lager2);
        //Assert
        lagre = Controller.getLagre();
        assertEquals(1, lagre.size());
        assertEquals(lager3, lagre.get(0));

        //Testcase 3
        //Arrange
        Controller.sletLager(lager3);
        //Act
        Controller.sletLager(lager2);
        Controller.sletLager(lager3);
        //Assert
        lagre = Controller.getLagre();
        assertEquals(0, lagre.size());
    }

    @Test
    void sletReol() {
        //Arrange
        Lager lager = Controller.opretLager("Lager1", "Adresse1", 5, 5, 5);

        //Testcase 1
        Reol reol = Controller.opretReol(lager, 0, 1, 5);
        //Act
        Controller.sletReol(reol);
        //Assert
        List<Reol> reoler = Controller.getLagre().get(0).getReoler();
        assertEquals(0, reoler.size());

        //Testcase 2
        reol = Controller.opretReol(lager, 1, 1, 5);
        Reol reol2 = Controller.opretReol(lager, 2, 1, 5);
        //Act
        Controller.sletReol(reol);
        //Assert
        reoler = Controller.getLagre().get(0).getReoler();
        assertEquals(1, reoler.size());
        assertEquals(reol2, reoler.get(0));

        //Testcase 3
        //Arrange
        Controller.sletReol(reol2);
        //Act
        Controller.sletReol(reol);
        Controller.sletReol(reol2);
        //Assert
        reoler = Controller.getLagre().get(0).getReoler();
        assertEquals(0, reoler.size());
    }

    @Test
    void sletHylde() {
        //Arrange
        Lager lager = Controller.opretLager("Lager1", "Adresse1", 5, 5, 5);
        Reol reol = Controller.opretReol(lager, 0, 1, 5);


        //Testcase 1
        //Arrange
        Hylde hylde = Controller.opretHylde(reol, 0);
        //Act
        Controller.sletHylde(hylde);
        //Assert
        List<Hylde> hylder = Controller.getLagre().get(0).getReoler().get(0).getHylder();
        assertEquals(0,hylder.size());

        //Testcase 2
        //Arrange
        hylde = Controller.opretHylde(reol, 0);
        Hylde hylde2 = Controller.opretHylde(reol, 0);
        //Act
        Controller.sletHylde(hylde);
        //Assert
        hylder = Controller.getLagre().get(0).getReoler().get(0).getHylder();
        assertEquals(1,hylder.size());

        //Testcase 3
        //Arrange
        Controller.sletHylde(hylde);
        Controller.sletHylde(hylde2);
        //Act
        Controller.sletHylde(hylde2);
        //Assert
        hylder = Controller.getLagre().get(0).getReoler().get(0).getHylder();
        assertEquals(0,hylder.size());
    }
}