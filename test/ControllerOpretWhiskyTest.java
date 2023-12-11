
import model.*;
import org.junit.jupiter.api.*;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class ControllerOpretWhiskyTest {
    private NewMake newMake1;
    private NewMake newMake2;
    private NewMake newMake3;
    private NewMake newMake4;
    @BeforeEach
    void setUp() {
        Korn korn = new Korn("Sort 1", "Bondemande 1", 2010, "Mark 1", "Maltningprocess 1");
        Destillat destillat1 = new Destillat(LocalDate.of(2011, 1, 1), 70, "Desti ansvarlig 1"
        , 300, 2, "Rygemateriale 1", korn);
        Destillat destillat2 = new Destillat(LocalDate.of(2011, 1, 2), 77, "Desti ansvarlig 2"
                , 300, 2, "Rygemateriale 2", korn);
        newMake1 = new NewMake("NewMake1", LocalDate.of(2015, 1, 1), 70, "Ansvarlig nm1",
                new HashMap<>());
        newMake1.tilføjMængde(new Mængde(50, destillat1));

        newMake2 = new NewMake("NewMake2", LocalDate.of(2012, 1, 1), 77, "Ansvarlig nm1",
                new HashMap<>());
        newMake2.tilføjMængde(new Mængde(70, destillat2));

        Map<NewMake, Double> mapTemp = new HashMap<>();
        mapTemp.put(newMake1, 20.0);
        mapTemp.put(newMake2, 20.0);

        newMake3 = new NewMake("NewMake3", LocalDate.now(), 73.5, "Ansvarlig nm1",
                mapTemp);

        mapTemp = new HashMap<>();
        mapTemp.put(newMake1, 30.0);
        mapTemp.put(newMake2, 50.0);

        newMake4 = new NewMake("NewMake4", LocalDate.now(), 74.375, "Ansvarlig nm1",
                mapTemp);
    }
}
