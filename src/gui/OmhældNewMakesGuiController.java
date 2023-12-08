package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Fad;
import model.FadTilNM;
import model.NewMake;


public class OmhældNewMakesGuiController {
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

    @FXML
    void gemAction() {

    }

    @FXML
    void lukAction() {

    }

    @FXML
    void omhaeldAction() {

    }
}
