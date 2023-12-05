package gui;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.NewMake;

public class NewMakesGuiController {

    @FXML
    private Button btnLuk;

    @FXML
    private ListView<NewMake> lvwOpretNewmakes;

    public void initialize() {
        lvwOpretNewmakes.getItems().setAll(Controller.getNewMakes());
    }
    @FXML
    void btnLukAction() {
    Stage stage = (Stage) btnLuk.getScene().getWindow();
    stage.close();
    }
}

