package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.NewMake;

import java.util.Map;

public class OmhældTrinEtGuiController {
    @FXML
    private Button btnFortryd;

    @FXML
    private Button btnNæste;

    @FXML
    private Button btnTilføjTilBlanding;

    @FXML
    private DatePicker datoVælger;

    @FXML
    private Label lblFejlBesked;

    @FXML
    private ListView<Map.Entry<NewMake, Double>> lvwLiterTilBlanding;

    @FXML
    private ListView<NewMake> lvwNewMakes;

    @FXML
    private TextField txfAnsvarligNavn;

    @FXML
    private TextField txfAntalLiter;
}
