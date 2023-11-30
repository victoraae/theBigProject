package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
            setFejlBesked(lblFBKorn, "vælg en kornsort fra listen, eller opret en ny");
        }
        DestillatGuiController.kornsort = korn;
    }

    @FXML
    public void opretKornAction(){
        String sort = txfSort.getText();
        String navnPåMark = txfNavnPaaMark.getText();
        String bondemand = txfBondemand.getText();
        String maltning = txaMaltningsprocess.getText();

        int år = 0;
        try {
            Integer.parseInt(txfAar.getText());
        }catch(NumberFormatException ex){
            setFejlBesked(lblFBKorn, "forkert tal format for årstal");
        }

        Controller.opretKorn(sort, bondemand, år, navnPåMark, maltning);
        opdaterListView();
    }

    public void opdaterListView(){
        lvwKorn.getItems().setAll(Controller.getKorn());
    }
    public void setFejlBesked(Label lblFB, String besked){
        int index = lblFB.getText().indexOf(':');
        lblFB.setText(lblFB.getText().substring(0, index) + " " + besked);
        lblFB.setVisible(true);
    }
}
