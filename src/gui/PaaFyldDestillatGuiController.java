package gui;

import controller.Controller;
import controller.Storage;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private Button btnLuk;
    @FXML
    private Button btnPaafyld;
    @FXML
    private Label lblFejlBesked;
    @FXML
    private ListView<FadTilNM> lvwFadTilNM;
    @FXML
    private ListView<Fad> lvwFade;
    @FXML
    private TextField txfAntalLiter;
    @FXML
    private Label lblNMLiter;
    @FXML
    private Label lblFadeLiter;

    private Fad fad;
    private ArrayList<FadTilNM> fadeTilNM = new ArrayList<>();
    public static NewMake newMake;
    private double fadeAntalLiter;

    public void initialize() {
        HovedVindue.setFejlBesked(lblNMLiter, newMake.getLiter() + "");
        HovedVindue.setFejlBesked(lblFadeLiter, 0 + "");


        lvwFade.getItems().setAll(Controller.getFade());

        ChangeListener<Fad> listener = (ov, o, n) -> this.opdaterValgtFad();
        lvwFade.getSelectionModel().selectedItemProperty().addListener(listener);

    }

    private void opdaterValgtFad() {
        fad = lvwFade.getSelectionModel().getSelectedItem();
    }

    private void opdaterLvwFadTilNM() {
        lvwFadTilNM.getItems().setAll(fadeTilNM);
        HovedVindue.setFejlBesked(lblFadeLiter, this.antalLiterFraFTilNM() + "");
    }

    @FXML
    public void gemAction() {

        Controller.tilføjFTilNMtilNM(fadeTilNM, newMake);
        newMake.setLiter(fadeAntalLiter);
        newMake.setLiterTilbage(fadeAntalLiter);

        Stage stage = (Stage) lblFejlBesked.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void lukAction() {
        Controller.sletNewMake(newMake);

        Stage stage = (Stage) lblFejlBesked.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void paaFyldAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad == null) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Vælg et fad");
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
        if (fad.getStørrelse().getInt() < antalLiterFraFTilNM() + antalLiter) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke nok plads i fadet, til de eksisterende mængder og den nye");
            return;
        }
        if (newMake.getLiter() < antalLiterFraFTilNM() + antalLiter) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke tilstrækkelig mængde væske i NewMake, tjek NewMakes liter");
            return;
        }


        fadeTilNM.add(new FadTilNM(antalLiter, fad, newMake));
        this.fadeAntalLiter += antalLiter;
        opdaterLvwFadTilNM();
        lblFejlBesked.setVisible(false);
        txfAntalLiter.clear();
    }


    /**
     * hjæple metode til gui
     */
    private double antalLiterFraFTilNM() {
        double result = 0;
        for (FadTilNM f : fadeTilNM) {
            result += f.getLiter();
        }
        return result;
    }
}


