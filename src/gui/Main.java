package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import storage.ListStorage;

public class Main {
    public static void main(String[] args) {
        Storage storage = ListStorage.læsStorage();
        if (storage == null) {
            storage = new ListStorage();
            System.out.println("Tom ListStorage oprettet");
        }

        Controller.setStorage(storage);

        initStorage();
        System.out.println("Storage er initialiseret");

        Application.launch(Gui.class);

        ListStorage.gemStorage(storage);
    }

    public static void initStorage() {
        // TODO
    }
}
