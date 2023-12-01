package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private ListView lvwVaelgHylder;

    @FXML
    private ListView lvwVaelgReol;

    @FXML
    private TextField txfAntalHylder;

    @FXML
    private TextField txfIStorrelse;

    @FXML
    private Button txfLuk;
    private Lager lager;
    private Reol reol;

    @FXML
    public void initializeHylde() {
        if (reol != null) {
            lvwVaelgHylder.getItems().setAll(reol.getHylder());
        }
    }

    @FXML
    public void initializeReol() {
        lager = LagerGuiController.valgtLager;
        if (lager != null) {
            lvwVaelgReol.getItems().setAll(lager.getReoler());
        }
    }

    @FXML
    public void opretReolAction() {
        try {
            int nummer = 1;
            String storrelseText = txfIStorrelse.getText();
            String antalHylderText = txfAntalHylder.getText();

            if (!storrelseText.isEmpty() && !antalHylderText.isEmpty()) {
                int størrelse = Integer.parseInt(storrelseText);
                int maxAntalHylder = Integer.parseInt(antalHylderText);

                if (lager != null) {
                    Reol reol = Controller.opretReol(lager, nummer, størrelse, maxAntalHylder);
                    lager.tilføjReol(reol);
                    opdaterListViewReol();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void opretHyldeAction() {
        if (reol != null) {
            Hylde hylde = Controller.opretHylde(reol, 1);
            reol.tilføjHylde(hylde);
            opdaterListViewHylde();
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
}