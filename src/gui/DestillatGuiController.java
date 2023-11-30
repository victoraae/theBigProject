package gui;

import controller.Controller;
import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Destillat;
import model.Korn;

import java.time.LocalDate;

public class DestillatGuiController {
    @FXML
    private Button btnDestilleriGem;

    @FXML
    private Button btnÅbenKorn;

    @FXML
    private DatePicker dateSelector;

    @FXML
    private TextField txfAlkoholProcent;

    @FXML
    private TextField txfAntalLiter;

    @FXML
    private TextField txfNavnPaaAnsvarlig;

    @FXML
    private TextField txfRygeMateriale;

    @FXML
    private TextField txfValgtKorn;

    @FXML
    private Label lblFBDestillat;

    @FXML
    private Button btnDestillatLuk;

     public static Korn kornsort;

    @FXML
    public void opretDestillatAction(){
        LocalDate dato = dateSelector.getValue();
        double alko = 0;
        int antalLiter = 0;
        try {
            alko = Double.parseDouble(txfAlkoholProcent.getText());
            antalLiter = Integer.parseInt(txfAntalLiter.getText());
        }catch(NumberFormatException ex){
            setFejlBesked(lblFBDestillat, "Forkert tal format");
        }
        String navnPåAnsvarlig = txfNavnPaaAnsvarlig.getText();
        String rygeMateriale = txfRygeMateriale.getText();

        if(kornsort==null) {
            setFejlBesked(lblFBDestillat, "vælg kornsort");
        }


        Destillat destillat = Controller.opretDestillat(dato, alko, navnPåAnsvarlig, antalLiter, 2, rygeMateriale, kornsort);
    }

    @FXML
    public void åbenKornVindueAction(){
        Main.åbenVinduer.åbenKornVindue();
    }

    public void setFejlBesked(Label lblFB, String besked){
        int index = lblFB.getText().indexOf(':');
        lblFB.setText(lblFB.getText().substring(0, index) + " " + besked);
        lblFB.setVisible(true);
    }
}
