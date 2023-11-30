package gui;

import controller.Controller;
import javafx.application.Application;
import storage.ListStorage;

public class Main {
    public static ÅbenVinduer åbenVinduer;

    public static void main(String[] args) {
        åbenVinduer = new ÅbenVinduer();
       Controller.setStorage(new ListStorage());
        Application.launch(HovedVindue.class);
    }
}
