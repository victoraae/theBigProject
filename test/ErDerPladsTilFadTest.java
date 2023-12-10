
import controller.Controller;
import model.*;
import org.junit.jupiter.api.*;
import storage.ListStorage;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
public class ErDerPladsTilFadTest {
    private Hylde hylde;

    @BeforeEach
    void setUp() {
        Controller.setStorage(new ListStorage());
        Lager lager = new Lager("Lager Test", "Test adresse", 400, 400, 20);
        Reol reol = new Reol(lager, 1, 5, 1);
        hylde = new Hylde(reol, 1, 5);
    }

    //Test case 1: Ingen pladser optaget, input "LILLE"
    @Test
    void TestCase1(){
        //Arrange
        //Act
        boolean result = hylde.erDerPladsTilFad("LILLE");
        //Assert
        assertEquals(true , result);
    }

    //Test case 2: Ingen pladser optaget, input "MELLEM"
    @Test
    void TestCase2(){
        //Arrange
        //Act
        boolean result = hylde.erDerPladsTilFad("MELLEM");
        //Assert
        assertEquals(true , result);
    }

    //Test case 3: Ingen pladser optaget, input "STOR"
    @Test
    void TestCase3(){
        //Arrange
        //Act
        boolean result = hylde.erDerPladsTilFad("STOR");
        //Assert
        assertEquals(true , result);
    }

    //Test case 4: Én plads optaget, input "LILLE"
    @Test
    void TestCase4(){
        //Arrange
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.erDerPladsTilFad("LILLE");
        //Assert
        assertEquals(true , result);
    }

    //Test case 5: Én plads optaget, input "MELLEM"
    @Test
    void TestCase5(){
        //Arrange
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.erDerPladsTilFad("MELLEM");
        //Assert
        assertEquals(true , result);
    }

    //Test case 6: Én plads optaget, input "STOR"
    @Test
    void TestCase6(){
        //Arrange
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.erDerPladsTilFad("STOR");
        //Assert
        assertEquals(true , result);
    }

    //Test case 7: Alle pladser optaget, input "LILLE"
    @Test
    void TestCase7(){
        //Arrange
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.erDerPladsTilFad("LILLE");
        //Assert
        assertEquals(false , result);
    }

    //Test case 8: Alle pladser optaget, input "MELLEM"
    @Test
    void TestCase8(){
        //Arrange
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.erDerPladsTilFad("MELLEM");
        //Assert
        assertEquals(false , result);
    }

    //Test case 9: Alle pladser optaget, input "STOR"
    @Test
    void TestCase9(){
        //Arrange
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.erDerPladsTilFad("STOR");
        //Assert
        assertEquals(false , result);
    }

    //Test case 10: Sidste to pladser ledige, input "LILLE"
    @Test
    void TestCase10(){
        //Arrange
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.erDerPladsTilFad("LILLE");
        //Assert
        assertEquals(true , result);
    }

    //Test case 11: Sidste to pladser ledige, input "MELLEM"
    @Test
    void TestCase11(){
        //Arrange
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.erDerPladsTilFad("MELLEM");
        //Assert
        assertEquals(true , result);
    }

    //Test case 12: Sidste to pladser ledige, input "STOR"
    @Test
    void TestCase12(){
        //Arrange
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        hylde.tilføjFad("LILLE");
        //Act
        boolean result = hylde.erDerPladsTilFad("STOR");
        //Assert
        assertEquals(false , result);
    }
}
