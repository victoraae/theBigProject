package gui.guiControllers;

import controller.Controller;
import gui.HovedVindue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;
import java.util.Optional;

public class PaaFyldTrinToGuiController {
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


        lvwFade.getItems().setAll(Controller.getTommeFade()); //TODO: skal være tomme fade

        ChangeListener<Fad> listener = (ov, o, n) -> this.opdaterValgtFad();
        lvwFade.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private void opdaterValgtFad() {
        fad = lvwFade.getSelectionModel().getSelectedItem();
    }

    private void opdaterLvwFadTilNM() {
        lvwFadTilNM.getItems().setAll(fadeTilNM);
        HovedVindue.setFejlBesked(lblFadeLiter, antalLiterFraFTilNM() + "");
    }

    @FXML
    public void gemAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bekræft", ButtonType.YES, ButtonType.NO);
        alert.setContentText("Er du sikker på, at du vil påfylde og lave en ny New Make fra destillat?");
        alert.setHeaderText("Bekræft oplysninger");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Controller.tilføjFTilNMtilNM(fadeTilNM, newMake);
            newMake.setLiter(fadeAntalLiter);
            newMake.setLiterTilbage(fadeAntalLiter);
            lukUdenSlet();
            PaaFyldTrinEtGuiController.decLiterTilbageDestillater(); //Trække de liter fra destillater i trin et vinduet, når det gemmes til sidst
        }
    }
    @FXML
    public void lukAction() {
        Controller.sletNewMake(newMake);

        lukUdenSlet();
    }

    private void lukUdenSlet(){
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
        fadeAntalLiter += antalLiter;
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


