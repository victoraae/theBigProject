package gui.guiControllers;

import controller.Controller;
import gui.HovedVindue;
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
    public void opretDestillatAction() {
        try {
            LocalDate dato = dateSelector.getValue();
            double alko = 0;
            int antalLiter = 0;
            alko = Double.parseDouble(txfAlkoholProcent.getText());
            antalLiter = Integer.parseInt(txfAntalLiter.getText());

            String navnPåAnsvarlig = txfNavnPaaAnsvarlig.getText();
            String rygeMateriale = txfRygeMateriale.getText();

            if (kornsort == null) {
                HovedVindue.setFejlBesked(lblFBDestillat, "vælg kornsort");
                return;
            }
            if (dato != null) {
                if (dato.isAfter(LocalDate.now())) {
                    HovedVindue.setFejlBesked(lblFBDestillat, "dato skal være i dag eller tidligere");
                    return;
                }
            } else {
                HovedVindue.setFejlBesked(lblFBDestillat, "Du skal angive en dato");
            }
            if (!txfNavnPaaAnsvarlig.getText().matches(".*[a-zA-Z]+.*")) {
                HovedVindue.setFejlBesked(lblFBDestillat, "Navn på ansvarlig skal indeholde bogstaver");
                return;
            }
            if (!txfRygeMateriale.getText().matches(".*[a-zA-Z]+.*")) {
                HovedVindue.setFejlBesked(lblFBDestillat, "Rygemateriale skal indeholde bogstaver");
                return;
            }
            if (navnPåAnsvarlig.isBlank() || rygeMateriale.isBlank() || txfAntalLiter.getText().isBlank() || txfAlkoholProcent.getText().isBlank()) {
                HovedVindue.setFejlBesked(lblFBDestillat, "Alle felter skal udfyldes");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
            Destillat destillat = new Destillat(dato, alko, navnPåAnsvarlig, antalLiter, 2, rygeMateriale, kornsort);
            alert.setContentText(destillat.toString());
            alert.setHeaderText("Bekræft oplysninger");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                Controller.opretDestillat(dato, alko, navnPåAnsvarlig, antalLiter, 2, rygeMateriale, kornsort);
                lukAction();
            }
        } catch (NumberFormatException e) {
            HovedVindue.setFejlBesked(lblFBDestillat, "AlkoholProcent og/eller antal liter skal være numeriske værdier");
        }

    }

    @FXML
    public void lukAction() {
        Stage stage = (Stage) lblFBDestillat.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void åbenKornVindueAction() {
        Main.åbenVinduer.åbenKornVindue();
        if (kornsort != null) txfValgtKorn.setText(kornsort.toString());
    }
}
