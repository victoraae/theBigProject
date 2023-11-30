package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import storage.ListStorage;

public class Main {
    public static ÅbenVinduer åbenVinduer;

    public static void main(String[] args) {
        åbenVinduer = new ÅbenVinduer();
        Storage storage = ListStorage.læsStorage();
        if (storage == null) {
            storage = new ListStorage();
            System.out.println("Tom ListStorage oprettet");
        }

        Controller.setStorage(storage);

        initStorage();
        System.out.println("Storage er initialiseret");

        Application.launch(HovedVindue.class);

        ListStorage.gemStorage(storage);
    }

    public static void initStorage() {
        // TODO
    }
}
