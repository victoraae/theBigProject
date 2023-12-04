package gui;

import controller.Controller;
import controller.Storage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.*;
import storage.ListStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaaFyldDestillatGuiController {

    @FXML
    private Button btnGem;

    @FXML
    private Label lblFejlBesked;
    @FXML
    private Button btnPaafyld;

    @FXML
    private ListView<Destillat> lvwDestillater;

    @FXML
    private ListView<Fad> lvwFade;

    private ListView<String> lvwValgteFade;
    @FXML
    private TextField txfAntalLiter;
    private Destillat destillat;
    private HashMap<Fad, Double> valgteFade = new HashMap<>();

    public void initialize() {
        opdaterListViewDestillat();
        opdaterListViewFad();
    }

    @FXML
    void btnGemAction() {

    }

    @FXML
    void lblFejlBeskedAction() {

    }

    @FXML
    void lvwDistillaterAction() {

    }

    @FXML
    void lvwFadeAction() {

    }

    @FXML
    void txfAntalLiterAction() {

    }

    public void opdaterListViewFad() {
        lvwFade.getItems().setAll(Controller.getFade());
    }


    public void opdaterListViewDestillat() {
        lvwDestillater.getItems().setAll(Controller.getDestillater());
    }

    @FXML
    void lvwOpdaterValgteFade() {
        ArrayList<String> valgteFade = new ArrayList<>();
        for (Map.Entry<Fad, Double> entry : this.valgteFade.entrySet()) {
            valgteFade.add(entry.getKey().toString() + ", liter: " + entry.getValue());
        }
        lvwValgteFade.getItems().setAll(valgteFade);
    }

    @FXML
    void btnPaaFyldAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad == null) {
            setFejlBesked(lblFejlBesked, "Vælg et fad");
            return;
        }
        double antalLiter = 0;
        try {
            antalLiter = Double.parseDouble(txfAntalLiter.getText());
        } catch (NumberFormatException e) {
            setFejlBesked(lblFejlBesked, "Indtast et gyldigt tal");
            return;
        }
        if (valgteFade.keySet().contains(fad)) {
         setFejlBesked(lblFejlBesked,"Det valgte fad er optaget");
         return;
        } else {
            valgteFade.put(fad,antalLiter);
            lvwOpdaterValgteFade();
        }
    }

    public void setFejlBesked(Label lblFB, String besked) {
        int index = lblFB.getText().indexOf(':');
        lblFB.setText(lblFB.getText().substring(0, index + 1) + " " + besked);
        lblFB.setVisible(true);
    }
}


