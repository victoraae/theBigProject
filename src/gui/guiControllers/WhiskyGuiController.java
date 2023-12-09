package gui.guiControllers;

import controller.Controller;
import gui.HovedVindue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WhiskyGuiController {
    @FXML
    private Button btnGem;
    @FXML
    private ListView<NewMake> lvwNewMakes;
    @FXML
    private TextField txfAnsvarligNavn;
    @FXML
    private TextField txfLiterVand;
    @FXML
    private TextField txfWhiskyNavn;
    @FXML
    private Button btnLuk;
    @FXML
    private Label lblFejlBesked;
    private List<NewMake> newMakes;

    public void initialize() {
        lvwNewMakes.getItems().setAll(Controller.getNewMakes());
        ChangeListener<NewMake> listener = (ov, o, n) -> this.opdaterValgtNewMake();
        lvwNewMakes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwNewMakes.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private void opdaterValgtNewMake() {
        newMakes = lvwNewMakes.getSelectionModel().getSelectedItems();
    }

    @FXML
    void gemAction() {
        String navn = txfWhiskyNavn.getText().trim();
        String ansvarlig = txfAnsvarligNavn.getText().trim();

        if (navn.isBlank()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Navn på whiskyen kan ikke være tom");
            return;
        }

        if (ansvarlig.isBlank()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Navn på ansvarlig kan ikke være tom");
            return;
        }

        double literVand = 0;
        String vand = txfLiterVand.getText().trim();
        if (!vand.isBlank()) {
            try {
                literVand = Double.parseDouble(vand);
            } catch (NumberFormatException e) {
                HovedVindue.setFejlBesked(lblFejlBesked, "Indtast et gyldigt nummer");
                return;
            }
        }
        if (literVand < 0) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Indtast et gyldigt nummer");
            return;
        }
        if (!måViLaveWhisky(newMakes)) {
            HovedVindue.setFejlBesked(lblFejlBesked,
                    "Du må ikke lave whisky ud fra New Makes der IKKE har lagret i mindst 3 år!");
            return;
        }
        if (newMakes != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bekræft", ButtonType.YES, ButtonType.NO);
            alert.setContentText("Er du sikker på, at du vil oprette denne whisky?\n" + navn + ", lavet af: " + ansvarlig);
            alert.setHeaderText("Bekræft oplysninger");
            alert.showAndWait();

            Controller.opretWhisky(navn, ansvarlig, literVand, newMakes);
            Stage stage = (Stage) btnGem.getScene().getWindow();
            stage.close();
        } else {
            HovedVindue.setFejlBesked(lblFejlBesked,
                    "Du mangler at vælge et newMake");
        }
        if (txfLiterVand.getText().isBlank()) {
            HovedVindue.setFejlBesked(lblFejlBesked,
                    "Du skal indtaste liter vand");
            return;
        }
    }

    // hjælpemetode der tjekker om alle
    //TODO:: en omhlædig skaber newmakes der har tomme mængder lister
    @FXML
    public boolean måViLaveWhisky(List<NewMake> newMakes) {
        boolean result = true;
        if (newMakes != null) {
            for (int i = 0; i < newMakes.size() && result; i++) {
                NewMake newMake = newMakes.get(i);
                List<Mængde> mængder = newMake.getMængder();
                if (!mængder.isEmpty()) {
                    if (newMake.getDatoForPåfyldning().isAfter(LocalDate.now().minusYears(3))) {
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    @FXML
    public void lukAction() {
        Stage stage = (Stage) lblFejlBesked.getScene().getWindow();
        stage.close();
    }
}
