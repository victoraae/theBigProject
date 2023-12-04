package gui;

import controller.Controller;
import controller.Storage;
import javafx.beans.value.ChangeListener;
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
    private Label lblDestillatLiter;
    @FXML
    private ListView<Destillat> lvwDestillater;
    @FXML
    private ListView<Fad> lvwFade;
    @FXML
    private ListView<String> lvwValgteFade;
    @FXML
    private Label lblAntalLiterPaaFad;
    @FXML
    private TextField txfAntalLiter;
    private Destillat destillat;
    private HashMap<Fad, Double> valgteFade = new HashMap<>();

    public void initialize() {
        lvwFade.getItems().setAll(Controller.getFade());
        lvwDestillater.getItems().setAll(Controller.getDestillater());
        ChangeListener<Destillat> listener = (ov, o, n) -> this.opdaterDestillatLiter();
        lvwDestillater.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public void opdaterDestillatLiter() {
        Destillat destillat = lvwDestillater.getSelectionModel().getSelectedItem();
        int index = lblDestillatLiter.getText().indexOf(':');
        lblDestillatLiter.setText(lblDestillatLiter.getText().substring(0, index + 1) + " " + destillat.getLiter());
    }

    public void opdaterFadeAntalLiter(){
        double total = 0;
        for(double d : valgteFade.values()){
            total += d;
        }

        int index = lblAntalLiterPaaFad.getText().indexOf(':');
        lblAntalLiterPaaFad.setText(lblAntalLiterPaaFad.getText().substring(0, index + 1) + " " + total);
    }

    @FXML
    void gemAction() {

    }

    void opdaterValgteFade() {
        ArrayList<String> valgteFade = new ArrayList<>();
        for (Map.Entry<Fad, Double> entry : this.valgteFade.entrySet()) {
            valgteFade.add(entry.getKey().toString() + ", liter: " + entry.getValue());
        }
        lvwValgteFade.getItems().setAll(valgteFade);
    }

    @FXML
    void paaFyldAction() {
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
            setFejlBesked(lblFejlBesked, "Det valgte fad er optaget");
            return;
        }

        valgteFade.put(fad, antalLiter);
        opdaterValgteFade();
        opdaterFadeAntalLiter();
    }

    public void setFejlBesked(Label lblFB, String besked) {
        int index = lblFB.getText().indexOf(':');
        lblFB.setText(lblFB.getText().substring(0, index + 1) + " " + besked);
        lblFB.setVisible(true);
    }
}


