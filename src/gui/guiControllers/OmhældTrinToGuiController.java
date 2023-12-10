package gui.guiControllers;

import controller.Controller;
import gui.HovedVindue;
import gui.guiDummies.NewMakeGui;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OmhældTrinToGuiController {
    @FXML
    private Button btnGem;

    @FXML
    private Button btnLuk;

    @FXML
    private Button btnOmhæld;

    @FXML
    private Label lblFadeLiter;

    @FXML
    private Label lblFejlBesked;

    @FXML
    private Label lblNMLiter;

    @FXML
    private ListView<FadTilNM> lvwFadTilBlanding;

    @FXML
    private ListView<Fad> lvwFade;

    @FXML
    private TextField txfAntalLiter;

    public static NewMake newMake;
    private List<FadTilNM> fadeTilBlanding = new ArrayList<>();
    private double fadeAntalLiter;

    public void initialize(){
        lvwFade.getItems().setAll(Controller.getFade());
        HovedVindue.setFejlBesked(lblNMLiter, newMake.getLiterTilbage()+"");
    }

    @FXML
    void gemAction() {
        if (fadeTilBlanding.isEmpty()){
            HovedVindue.setFejlBesked(lblFejlBesked, "Du skal først omhælde blandingen til et eller flere fade!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bekræft", ButtonType.YES, ButtonType.NO);
        alert.setContentText("Er du sikker på, at du vil omhælde disse New Makes?");
        alert.setHeaderText("Bekræft oplysninger");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Controller.tilføjFTilNMtilNM(fadeTilBlanding, newMake);
            newMake.setLiter(fadeAntalLiter);
            newMake.setLiterTilbage(fadeAntalLiter);
            OmhældTrinEtGuiController.opdaterNewMakes();
            lukActionUdenAtSlette();
        }
    }

    @FXML
    void lukAction() {
        Controller.sletNewMake(newMake);

        lukActionUdenAtSlette();
    }

    void lukActionUdenAtSlette() {
        newMake = null;
        Stage stage = (Stage) lblFejlBesked.getScene().getWindow();
        stage.close();
    }

    @FXML
    void omhaeldAction() {
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
        if (newMake.getLiterTilbage() < antalLiterFraFTilNM() + antalLiter) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke tilstrækkelig mængde væske i NewMake, tjek NewMakes liter");
            return;
        }

        fadeTilBlanding.add(new FadTilNM(antalLiter, fad, newMake));
        this.fadeAntalLiter += antalLiter;
        opdaterLvwFadTilBlanding();
        opdaterLblFadeLiter();
        lblFejlBesked.setVisible(false);
        txfAntalLiter.clear();
    }

    private void opdaterLblFadeLiter() {
        HovedVindue.setFejlBesked(lblFadeLiter, fadeAntalLiter+"");
    }

    private void opdaterLvwFadTilBlanding() {
        lvwFadTilBlanding.getItems().setAll(fadeTilBlanding);
    }

    /**
     * hjæple metode til gui
     */
    private double antalLiterFraFTilNM() {
        double result = 0;
        for (FadTilNM f : fadeTilBlanding) {
            result += f.getLiter();
        }
        return result;
    }
}
