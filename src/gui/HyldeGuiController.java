package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

    @FXML
    private TextField txfStorrelse;
    private Lager lager;

    public void initialize() {
        if (lager != null) {
            lager = LagerGuiController.valgtLager;
            lvwVaelgReol.getItems().setAll(lager.getReoler());
        }
    }

    public void btnOpretReol() {
        try {
            int nummer = 1;
            int størrelse = Integer.parseInt(txfStorrelse.getText());
            int maxAntalHylder = Integer.parseInt(txfAntalHylder.getText());

            if (lager != null) {
                Reol reol = Controller.opretReol(lager, nummer, størrelse, maxAntalHylder);
                if (reol != null) {
                    lager.tilføjReol(reol);
                    opdaterListView();
                    System.out.println("Test" + reol);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void opdaterListView() {
        if (lager != null) {
            lvwVaelgReol.getItems().setAll(lager.getReoler());
        }
    }
}
