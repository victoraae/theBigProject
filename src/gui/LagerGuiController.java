package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Hylde;
import model.Lager;


public class LagerGuiController {
    @FXML
    private Button btnGem;

    @FXML
    private Button btnOpretHylde;

    @FXML
    private Button btnVaelgLager;

    @FXML
    private ListView<Lager> lvwVaelgLager;

    @FXML
    private TextField txfAdresse;

    @FXML
    private TextField txfKapacitet;

    @FXML
    private TextField txfLagerNavn;

    @FXML
    private TextField txfMaksReoler;

    @FXML
    private TextField txfStørrelse;

    public static Lager valgtLager;

    public static Hylde valgtHylde;
    public void initialize(){
        lvwVaelgLager.getItems().setAll(Controller.getLagre());
        ChangeListener<Lager> listener = (ov, o, n) -> this.opdaterValgtLager();
        lvwVaelgLager.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private void opdaterValgtLager() {
        valgtLager = lvwVaelgLager.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void btnGemAction() {
        if (!txfLagerNavn.getText().isEmpty() && !txfAdresse.getText().isEmpty() &&
                !txfKapacitet.getText().isEmpty() && !txfStørrelse.getText().isEmpty() &&
                !txfMaksReoler.getText().isEmpty()) {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
            dialog.setContentText("KVITTERING:\n" + "--------------\n" + "Lagernavn: " + txfLagerNavn.getText() + "\nadresse: "
                    + txfAdresse.getText() + "\nstørrelse pr. m^2: " + txfKapacitet.getText() + "\npladser på lager: " + txfStørrelse.getText() + "\nmaksimale antaler reoler: " + txfMaksReoler.getText() + "\n--------------");
            dialog.showAndWait();
            dialog.setHeaderText("Bekræft oplysninger:");
            dialog.setTitle("Er du sikker?");
            double størrelse = Double.parseDouble(txfStørrelse.getText());
            int kapacitet = Integer.parseInt(txfKapacitet.getText());
            int maxAntalHylder = Integer.parseInt(txfMaksReoler.getText());


            Lager lager = Controller.opretLager(txfLagerNavn.getText(), txfAdresse.getText(), størrelse, kapacitet, maxAntalHylder);

            opdaterListView();
        } else {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Du har ikke udfyldt alle felterne");
            dialog.setContentText("Venligst udfyld alle felterne, før du fortsætter.");
            dialog.showAndWait();
        }
    }

    @FXML
    public void btnÅbenHylde() {
    Main.åbenVinduer.åbenHyldeVindue();
    }
    public void opdaterListView() {
        lvwVaelgLager.getItems().setAll(Controller.getLagre());
    }
    public Lager getValgtLager() {
        return lvwVaelgLager.getSelectionModel().getSelectedItem();
    }
}




