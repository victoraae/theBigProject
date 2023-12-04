package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import model.*;
import storage.ListStorage;

import java.time.LocalDate;

public class Main {
    public static ÅbenVinduer åbenVinduer;

    public static void main(String[] args) {
        åbenVinduer = new ÅbenVinduer();
        Storage storage = ListStorage.læsStorage();
        
        boolean flag = storage==null;
        if (flag) {
            storage = new ListStorage();
            System.out.println("Tom ListStorage oprettet");
        }

        Controller.setStorage(storage);
        if(flag) initStorage();


        System.out.println("Storage er initialiseret");

        Application.launch(HovedVindue.class);

        ListStorage.gemStorage(storage);
    }

    public static void initStorage() {
        Lager lager = Controller.opretLager("Lager 1", "Adresse 1", 230, 500, 7);
        Controller.opretLager("Lager 2", "Adresse 2", 230, 500, 7);
        Controller.opretLager("Lager 3", "Adresse 2", 230, 4, 7);
        Reol reol = Controller.opretReol(lager, 1, 15, 3);
        Reol reol2 = Controller.opretReol(lager, 2, 5, 3);
        Controller.opretReol(lager, 2, 15, 4);
        Controller.opretHylde(reol, 1);
        Controller.opretHylde(reol2, 1);
        Controller.opretKorn("Evergreen Byg", "Lars Larsen", 2017, "Mark 1", "Ristet lys");
        Controller.opretKorn("Irina Byg", "Keld Keldsen", 2018, "Mark 2", "Ristet mørk");
        Controller.opretDestillat(LocalDate.of(23,4,12),42,"Karsten",20,1,"Weed",korn);
        Fad fad = Controller.opretFad(lager,hylde,"Karsten","Danmark","Jern","Weed",FadStørrelser.L50);

    }
}
