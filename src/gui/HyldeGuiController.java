package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label lblFejlBesked;

    @FXML
    private TextField txfStorrelse;
    private Lager lager;

    public void initialize() {
        lager = LagerGuiController.valgtLager;

        if (lager != null) {
            lvwVaelgReol.getItems().setAll(lager.getReoler());
        }
    }

    @FXML
    public void opretReolAction() {
        try {
            int nummer = 1;
            int størrelse = Integer.parseInt(txfIStorrelse.getText());
            int maxAntalHylder = Integer.parseInt(txfAntalHylder.getText());

            if(størrelse<1 || maxAntalHylder <1) setFejlBesked(lblFejlBesked, "størrelse og maxAntalHylder skal være større end nul");

            if (lager != null) {
                Reol reol = Controller.opretReol(lager, nummer, størrelse, maxAntalHylder);
                if (reol != null) {
                    //lager.tilføjReol(reol);
                    opdaterListView();
                    System.out.println("Test" + reol);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Test fejl: " + e.toString());
        }
    }

    @FXML
    public void opdaterListView() {
        if (lager != null) {
            lvwVaelgReol.getItems().setAll(lager.getReoler());
        }
    }

    public void setFejlBesked(Label lblFB, String besked){
        int index = lblFB.getText().indexOf(':');
        lblFB.setText(lblFB.getText().substring(0, index) + " " + besked);
        lblFB.setVisible(true);
    }
}
