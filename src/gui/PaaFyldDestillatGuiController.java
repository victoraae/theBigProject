package gui;

import controller.Controller;
import controller.Storage;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
    private ListView<Mængde> lvwMængder;
    @FXML
    private Label lblAntalLiterPaaFad;
    @FXML
    private TextField txfAntalLiter;
    @FXML
    private TextField txfAnsvarligNavn;
    @FXML
    private TextField txfNewMakeNavn;
    private Destillat destillat;
    private Fad fad;
    private ArrayList<Mængde> mængder = new ArrayList<>();

    public void initialize() {
        lvwFade.getItems().setAll(Controller.getFade());
        lvwDestillater.getItems().setAll(Controller.getDestillater());
        ChangeListener<Destillat> listener = (ov, o, n) -> this.opdaterDestillatLiter();
        lvwDestillater.getSelectionModel().selectedItemProperty().addListener(listener);

        ChangeListener<Fad> listener2 = (ov, o, n) -> this.opdaterValgtFad();
        lvwFade.getSelectionModel().selectedItemProperty().addListener(listener2);
    }

    private void opdaterValgtFad() {
        fad = lvwFade.getSelectionModel().getSelectedItem();
    }

    private void opdaterDestillatLiter() {
        destillat = lvwDestillater.getSelectionModel().getSelectedItem();
    }


    @FXML
    void gemAction() {

        String navn = txfNewMakeNavn.getText().trim();
        String ansvarlig = txfAnsvarligNavn.getText().trim();

        if (navn.isBlank()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Navn på NewMake kan ikke være tom");
            return;
        }

        if (ansvarlig.isBlank()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Navn på ansvarlig kan ikke være tom");
            return;
        }
        if (!txfNewMakeNavn.getText().matches(".*[a-zA-Z]+.*")) {
            HovedVindue.setFejlBesked(lblFejlBesked, "NewMakes navn må kun indeholde bogstaver");
            return;
        }
        if (!txfAnsvarligNavn.getText().matches(".*[a-zA-Z]+.*")) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Ansvarlig navn må kun indeholde bogstaver");
            return;
        }
        if (lvwMængder.getItems() != null) {
            Controller.paafyldDestillat(navn, ansvarlig, mængder, fad);
            Stage stage = (Stage) btnGem.getScene().getWindow();
            stage.close();

        }

    }

    void opdaterLvwMængder() {
        lvwMængder.getItems().setAll(mængder);
    }

    @FXML
    void paaFyldAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        Destillat destillat1 = lvwDestillater.getSelectionModel().getSelectedItem();
        if (fad == null) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Vælg et fad");
            return;
        }
        if (destillat1 == null) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Vælg et destillat");
            return;
        }
        double antalLiter = 0;
        try {
            antalLiter = Double.parseDouble(txfAntalLiter.getText());
        } catch (NumberFormatException e) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Antal liter på fad må kun indeholde numeriske værdier");
            return;
        }
        if (fad.getStørrelse().getInt() < antalLiter) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Det indtastede antal liter er ugyldigt, tjek fadstørrelse");
            return;
        }
        if (antalLiter <= 0) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Det indtastede antal liter er ugyldigt, prøv antalLiter>=1");
            return;
        }
        if (destillat.getLiter() < antalLiter || destillat.getLiter() < antalLiter + sumLiter()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke nok destillats væske, tjek destillats antal liter");
            return;
        }
        if (fad.getStørrelse().getInt() < sumLiter() + antalLiter) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke nok plads i fadet, til de eksisterende mængder og den nye");
            return;
        }


        mængder.add(new Mængde(antalLiter, destillat1));
        opdaterLvwMængder();
        lblFejlBesked.setVisible(false);
        txfAntalLiter.clear();
    }

    public ArrayList<Destillat> getDestillaterFraMængder() {
        ArrayList<Destillat> result = new ArrayList<>();
        for (Mængde mængde : mængder) {
            result.add(mængde.getDestillat());
        }
        return result;
    }

    public double sumLiter() {
        double result = 0;

        for (Mængde mængde : mængder) {
            result += mængde.getMængde();
        }
        return result;
    }
}


