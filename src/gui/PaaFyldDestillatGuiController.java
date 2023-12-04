package gui;

import controller.Controller;
import controller.Storage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.*;
import storage.ListStorage;

import java.util.ArrayList;
import java.util.List;

public class PaaFyldDestillatGuiController {

    @FXML
    private Button btnGem;

    @FXML
    private Label lblFejlBesked;

    @FXML
    private ListView<Destillat> lvwDestillater;

    @FXML
    private ListView<Fad> lvwFade;

    @FXML
    private TextField txfAntalLiter;
    private Destillat destillat;

    public void initialize() {
        opdaterListViewDestillat();
        opdaterListViewFad();
    }

    @FXML
    void btnGemAction() {

    }

    @FXML
    void lblFejlBeskedAction() {

    }

    @FXML
    void lvwDistillaterAction() {

    }

    @FXML
    void lvwFadeAction() {

    }

    @FXML
    void txfAntalLiterAction() {

    }

    public void opdaterListViewFad() {
        lvwFade.getItems().setAll(Controller.getFade());
    }


    public void opdaterListViewDestillat() {
        lvwDestillater.getItems().setAll(Controller.getDestillater());
    }
}



