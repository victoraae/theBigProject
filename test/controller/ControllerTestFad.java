package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ListStorage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTestFad {
    private Lager lager1;
    private Lager lager2;
    private Reol reol;
    private Hylde hylde;
    private String leverandør1 = "The Jolly Barrel";
    private String leverandør2 = "Enormes Barriles";
    private String land1 = "Storbritannien";
    private String land2 = "Spanien";
    private String materiale = "egetræ";

    @BeforeEach
    void setUp() {
        Controller.setStorage(new ListStorage());
        Lager lager1 = Controller.opretLager("Lager1", "Adresse1", 50, 100, 0);
        Lager lager2 = Controller.opretLager("Lager2", "Adresse2", 100, 300, 5);
        Reol reol = Controller.opretReol(lager2, 1, 10, 6);
        Hylde hylde = Controller.opretHylde(reol, 1);
    }

    @Test
    void opretFad() {
        // TC1 -- fad på lager uden reoler og hylder, størrelse: lille (50L)
        // Act
        Fad fad1 = Controller.opretFad(lager1, null, leverandør1, land1, materiale, "rødvin", FadStørrelser.L50);
        // Assert
        List<Fad> fade = Controller.getFade();
        assertEquals(fad1, fade.get(fade.size() - 1));

        // TC2 -- fad på lager med reoler og hylder, størrelse: mellem (100L)
        // Act
        Fad fad2 = Controller.opretFad(lager2, hylde, leverandør2, land2, materiale, "sherry", FadStørrelser.L100);
        // Assert
        fade = Controller.getFade();
        assertEquals(fad2, fade.get(fade.size() - 1));

        // TC3 -- fad på lager med reoler og hylder, størrelse: stor (250L)
        // Act
        Fad fad3 = Controller.opretFad(lager2, hylde, leverandør2, land2, materiale, "bourbon", FadStørrelser.L250);
        // Assert
        fade = Controller.getFade();
        assertEquals(fad3, fade.get(fade.size() - 1));
    }
}