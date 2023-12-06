package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Destillat;
import model.Fad;
import model.NewMake;

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

        if (navn.isBlank()){
            HovedVindue.setFejlBesked(lblFejlBesked, "Navn på whiskyen kan ikke være tom");
            return;
        }

        if (ansvarlig.isBlank()){
            HovedVindue.setFejlBesked(lblFejlBesked, "Navn på ansvarlig kan ikke være tom");
            return;
        }

        double literVand = 0;
        String vand = txfLiterVand.getText().trim();
        if (!vand.isBlank()){
            try {
                literVand = Double.parseDouble(vand);
            } catch (NumberFormatException e){
                HovedVindue.setFejlBesked(lblFejlBesked, "Indtast et gyldigt nummer");
                return;
            }
        }
        if (literVand < 0){
            HovedVindue.setFejlBesked(lblFejlBesked, "Indtast et gyldigt nummer");
            return;
        }

        Controller.opretWhisky(navn, ansvarlig, literVand, newMakes);
        Stage stage = (Stage) btnGem.getScene().getWindow();
        stage.close();
    }
}
