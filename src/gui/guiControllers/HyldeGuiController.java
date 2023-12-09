package gui.guiControllers;

import controller.Controller;
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
    private Label lblAntalPladserTilbage;
    @FXML
    private TextField txfStorrelse;
    private Lager lager;
    private Reol reol;
    private Reol valgtReol;

    public void initialize() {
        lager = LagerGuiController.valgtLager;
        int index = lblValgtLager.getText().indexOf('.');
        lblValgtLager.setText(lblValgtLager.getText().substring(0, index) + " " + lager.getNavn());

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
            if (lager != null) {
                String textStørrelse = txfIStorrelse.getText();
                String textAntalHylder = txfAntalHylder.getText();
                if (reol != null) {
                    if (lager.getReoler().size() >= lager.getMaxAntalReoler()) {
                        setFejlBesked(lblFejlBesked, "Der er ikke plads til flere reoler på lageret");
                        return;
                    }
                }


                if (textStørrelse.isEmpty() || textAntalHylder.isEmpty()) {
                    setFejlBesked(lblFejlBesked, "Du skal udfylde textfelterne");
                } else {
                    int størrelse = Integer.parseInt(textStørrelse);
                    int maxAntalHylder = Integer.parseInt(textAntalHylder);

                    if (størrelse < 1 || maxAntalHylder < 1) {
                        setFejlBesked(lblFejlBesked, "størrelse og maxAntalHylder skal være større end nul");
                    } else {
                        Reol reol = Controller.opretReol(lager, nummer, størrelse, maxAntalHylder);
                        if (reol != null) {
                            opdaterListViewReol();
                            this.reol = reol;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            setFejlBesked(lblFejlBesked, "Du kan kun indsætte numeriske værdier");
        }
    }

    @FXML
    public void opretHyldeAction() {
        if (reol == null) {
            setFejlBesked(lblFejlBesked, "Du har ikke valgt en reol");
            return;
        }

        if (reol.getHylder().size() >= reol.getMaxAntalHylder()) {
            setFejlBesked(lblFejlBesked, "Der er ikke plads til flere hylder på reolen");
            return;
        }

        Hylde hylde = Controller.opretHylde(reol, 1);
        if (hylde != null) {
            opdaterListViewHylde();
            lblFejlBesked.setVisible(false);
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

    public void setFejlBesked(Label lblFB, String besked) {
        String text = lblFB.getText();
        int index = text.indexOf(':');

        if (index != -1 && index < text.length()) {
            lblFB.setText(text.substring(0, index + 1) + " " + besked);
            lblFB.setVisible(true);
        } else {
            lblFB.setText(text + ": " + besked);
            lblFB.setVisible(true);
        }
    }

    public void btnLukAction() {
        Stage stage = (Stage) txfLuk.getScene().getWindow();
        stage.close();
    }
}

