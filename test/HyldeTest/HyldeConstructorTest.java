package HyldeTest;

import controller.Controller;
import model.Hylde;
import model.Lager;
import model.Reol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HyldeConstructorTest {
    @BeforeEach
    void setUp() {
        Controller.setStorage(new ListStorage());
    }

    @Test
    void Hylde(){
        //TC1
        //Arrange
        Lager lager = Controller.opretLager("Lager1", "Adresse1", 20, 5, 5);
        Reol reol = Controller.opretReol(lager, 1, 10, 5);

        //Act
        Hylde hylde = new Hylde(reol, 1, reol.getStørrelse());

        //Assert
        assertEquals(reol, hylde.getReol());
        assertEquals(reol.getStørrelse(), hylde.getStørrelse());
        assertEquals(1, hylde.getNummer());
    }
}
