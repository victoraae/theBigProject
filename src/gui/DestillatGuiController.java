package gui;

import controller.Controller;
import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Destillat;
import model.Fad;
import model.Korn;

import java.time.LocalDate;
import java.util.Optional;

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
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        Destillat destillat = new Destillat(dato, alko, navnPåAnsvarlig, antalLiter, 2, rygeMateriale, kornsort);
        alert.setContentText(destillat.toString());
        alert.setHeaderText("Bekræft oplysninger");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.YES){
           Controller.opretDestillat(dato, alko, navnPåAnsvarlig, antalLiter, 2, rygeMateriale, kornsort);
           lukAction();
        }
    }

    @FXML
    public void lukAction(){
        Stage stage = (Stage) lblFBDestillat.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void åbenKornVindueAction(){
        Main.åbenVinduer.åbenKornVindue();
        if(kornsort!=null) txfValgtKorn.setText(kornsort.toString());
    }

    public void setFejlBesked(Label lblFB, String besked){
        int index = lblFB.getText().indexOf(':');
        lblFB.setText(lblFB.getText().substring(0, index+1) + " " + besked);
        lblFB.setVisible(true);
    }
}
