package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Hylde;
import model.Lager;
import model.Reol;

public class HyldeGuiController {
    @FXML
    private Button btnOpretHylde;

    @FXML
    private Button btnOpretReol;

    @FXML
    private Button btnVaelgHylde;

    @FXML
    private ListView<Hylde> lvwVaelgHylder;

    @FXML
    private ListView<Reol> lvwVaelgReol;

    @FXML
    private TextField txfAntalHylder;

    @FXML
    private TextField txfIStorrelse;

    @FXML
    private Button txfLuk;

    @FXML
    private Label lblFejlBesked;

    @FXML
    private Label lblValgtLager;

    @FXML
    private TextField txfStorrelse;
    private Lager lager;
    private Reol reol;
    private Reol valgtReol;

    public void initialize() {
        lager = LagerGuiController.valgtLager;
        int index = lblValgtLager.getText().indexOf(':');
        lblValgtLager.setText(lblValgtLager.getText().substring(0, index) + " " + lager);

        if (lager != null) {
            lvwVaelgReol.getItems().setAll(lager.getReoler());
            lvwVaelgReol.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    reol = newValue;
                    opdaterListViewHylde();
                }
            });
        }
    }


    @FXML
    public void opretReolAction() {
        try {
            int nummer = 1;
            int størrelse = Integer.parseInt(txfIStorrelse.getText());
            int maxAntalHylder = Integer.parseInt(txfAntalHylder.getText());

            if (størrelse < 1 || maxAntalHylder < 1)
                HovedVindue.setFejlBesked(lblFejlBesked, "størrelse og maxAntalHylder skal være større end nul");

            if (lager != null) {
                Reol reol = Controller.opretReol(lager, nummer, størrelse, maxAntalHylder);
                if (reol != null) {
                    opdaterListViewReol();
                    this.reol = reol;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    public void opretHyldeAction() {
        if (reol != null) {
            Hylde hylde = Controller.opretHylde(reol, 1);
            if (hylde != null) {
                opdaterListViewHylde();
            }
        }
    }

    public void opdaterListViewHylde() {
        if (reol != null) {
            lvwVaelgHylder.getItems().setAll(reol.getHylder());
        }
    }

    public void opdaterListViewReol() {
        if (lager != null) {
            lvwVaelgReol.getItems().setAll(lager.getReoler());
        }
    }

    public void btnLukAction() {
        Stage stage = (Stage) txfLuk.getScene().getWindow();
        stage.close();
    }
}

