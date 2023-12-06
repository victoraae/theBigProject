package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Korn;

public class KornGuiController {
    @FXML
    private Button btnVælgKorn;
    @FXML
    private Button btnOpretKorn;
    @FXML
    private Label lblFBKorn;
    @FXML
    private ListView<Korn> lvwKorn;
    @FXML
    private TextArea txaMaltningsprocess;
    @FXML
    private TextField txfAar;
    @FXML
    private TextField txfBondemand;
    @FXML
    private TextField txfNavnPaaMark;
    @FXML
    private TextField txfSort;

    public void initialize(){
        lvwKorn.getItems().setAll(Controller.getKorn());
    }

    @FXML
    public void vælgKornAction() {
        Korn korn = lvwKorn.getSelectionModel().getSelectedItem();
        if (korn == null) {
            HovedVindue.setFejlBesked(lblFBKorn, "vælg en kornsort fra listen, eller opret en ny");
        }
        DestillatGuiController.kornsort = korn;

        Stage stage = (Stage) lvwKorn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void opretKornAction(){
        String sort = txfSort.getText();
        String navnPåMark = txfNavnPaaMark.getText();
        String bondemand = txfBondemand.getText();
        String maltning = txaMaltningsprocess.getText();

        int år = 0;
        try {
            år = Integer.parseInt(txfAar.getText());
            if(txfAar.getText().trim().length()!=4){ //årstal skal være 4 cifre
                HovedVindue.setFejlBesked(lblFBKorn, "forkert tal format for årstal");
                return;
            }
        }catch(NumberFormatException ex){
            HovedVindue.setFejlBesked(lblFBKorn, "forkert tal format for årstal");
            return;
        }

        if(txfSort.getText().isBlank() ||txfBondemand.getText().isBlank() || txfNavnPaaMark.getText().isBlank() ||txaMaltningsprocess.getText().isBlank()){
            HovedVindue.setFejlBesked(lblFBKorn, "Alle felter skal udfyldes for at oprette korn");
            return;
        }

        lblFBKorn.setVisible(false);
        Controller.opretKorn(sort, bondemand, år, navnPåMark, maltning);
        opdaterListView();
        clearFelter();
    }

    public void opdaterListView(){
        lvwKorn.getItems().setAll(Controller.getKorn());
    }

    private void clearFelter(){
        txfAar.clear();
        txfBondemand.clear();
        txfSort.clear();
        txfNavnPaaMark.clear();
        txaMaltningsprocess.clear();
    }
}
