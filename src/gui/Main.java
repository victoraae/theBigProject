package gui;

import controller.Controller;
import controller.Storage;
import javafx.application.Application;
import storage.ListStorage;

public class Main {
    public static void main(String[] args) {
       Controller.setStorage(new ListStorage());
        Application.launch(Gui.class);
    }
}
