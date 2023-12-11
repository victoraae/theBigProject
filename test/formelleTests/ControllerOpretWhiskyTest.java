package formelleTests;

import controller.Controller;
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
        Controller.setStorage(new ListStorage());

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
        newMake3.setLiter(40);

        mapTemp = new HashMap<>();
        mapTemp.put(newMake1, 30.0);
        mapTemp.put(newMake2, 50.0);

        newMake4 = new NewMake("NewMake4", LocalDate.now(), 74.375, "Ansvarlig nm1",
                mapTemp);
        newMake4.setLiter(80);
    }

    //Test case 1: vand=0, NewMakes.size=1, NewMakes=[newMake2]
    @Test
    void TestCase1(){
        //Arrange
        List<NewMake> list = new ArrayList<>();
        list.add(newMake2);
        //Act
        Whisky whisky = Controller.opretWhisky("Whisky 1", "Ansvarlig 1", 0, list);
        //Assert
        assertEquals("single cask", whisky.getKvalitetsstempel());
        assertEquals(11, whisky.getÅr());
        assertEquals(77.0, Math.floor(whisky.getAlkoholProcent()));
    }

    //Test case 2: vand=0, NewMakes.size=1, NewMakes=[newMake3]
    @Test
    void TestCase2(){
        //Arrange
        List<NewMake> list = new ArrayList<>();
        list.add(newMake3);
        //Act
        Whisky whisky = Controller.opretWhisky("Whisky 1", "Ansvarlig 1", 0, list);
        //Assert
        assertEquals("single cask", whisky.getKvalitetsstempel());
        assertEquals(8, whisky.getÅr());
        assertEquals(73.0, Math.floor(whisky.getAlkoholProcent()));
    }

    //Test case 3: vand=0, NewMakes.size>=2, NewMakes=[newMake1, newMake2]
    @Test
    void TestCase3(){
        //Arrange
        List<NewMake> list = new ArrayList<>();
        list.add(newMake1);
        list.add(newMake2);
        //Act
        Whisky whisky = Controller.opretWhisky("Whisky 1", "Ansvarlig 1", 0, list);
        //Assert
        assertEquals("single malt", whisky.getKvalitetsstempel());
        assertEquals(8, whisky.getÅr());
        assertEquals(74.0, Math.floor(whisky.getAlkoholProcent()));
    }

    //Test case 4: vand=0, NewMakes.size>=2, NewMakes=[newMake3, newMake4]
    @Test
    void TestCase4(){
        //Arrange
        List<NewMake> list = new ArrayList<>();
        list.add(newMake3);
        list.add(newMake4);
        //Act
        Whisky whisky = Controller.opretWhisky("Whisky 1", "Ansvarlig 1", 0, list);
        //Assert
        assertEquals("single malt", whisky.getKvalitetsstempel());
        assertEquals(8, whisky.getÅr());
        assertEquals(74.0, Math.floor(whisky.getAlkoholProcent()));
    }

    //Test case 5: vand=100, NewMakes.size=1, NewMakes = [newMake1]
    @Test
    void TestCase5(){
        //Arrange
        List<NewMake> list = new ArrayList<>();
        list.add(newMake1);

        //Act
        Whisky whisky = Controller.opretWhisky("Whisky 1", "Ansvarlig 1", 100, list);
        //Assert
        assertEquals("single cask", whisky.getKvalitetsstempel());
        assertEquals(8, whisky.getÅr());
        assertEquals(23.0, Math.floor(whisky.getAlkoholProcent()));
    }

    //Test case 6: vand=100, NewMakes.size=1, NewMakes = [newMake1]
    @Test
    void TestCase6(){
        //Arrange
        List<NewMake> list = new ArrayList<>();
        list.add(newMake3);
        //Act
        Whisky whisky = Controller.opretWhisky("Whisky 1", "Ansvarlig 1", 100, list);
        //Assert
        assertEquals("single cask", whisky.getKvalitetsstempel());
        assertEquals(8, whisky.getÅr());
        assertEquals(21.0, Math.floor(whisky.getAlkoholProcent()));
    }

    //Test case 7: vand=100, NewMakes.size>=2, NewMakes = [newMake1, newMake2]
    @Test
    void TestCase7(){
        //Arrange
        List<NewMake> list = new ArrayList<>();
        list.add(newMake1);
        list.add(newMake2);
        //Act
        Whisky whisky = Controller.opretWhisky("Whisky 1", "Ansvarlig 1", 100, list);
        //Assert
        assertEquals("single malt", whisky.getKvalitetsstempel());
        assertEquals(8, whisky.getÅr());
        assertEquals(40.0, Math.floor(whisky.getAlkoholProcent()));
    }

    //Test case 8: vand=100, NewMakes.size>=2, NewMakes = [newMake3, newMake4]
    @Test
    void TestCase8(){
        //Arrange
        List<NewMake> list = new ArrayList<>();
        list.add(newMake3);
        list.add(newMake4);
        //Act
        Whisky whisky = Controller.opretWhisky("Whisky 1", "Ansvarlig 1", 100, list);
        //Assert
        assertEquals("single malt", whisky.getKvalitetsstempel());
        assertEquals(8, whisky.getÅr());
        assertEquals(40.0, Math.floor(whisky.getAlkoholProcent()));
    }
}
