package gui;

import controller.Controller;
import controller.Storage;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import storage.ListStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaaFyldTrinEtGuiController {
    @FXML
    private Button btnFortryd;
    @FXML
    private Button btnNæste;
    @FXML
    private Button btnTilføjTilNM;
    @FXML
    private Label lblFejlBesked;
    @FXML
    private ListView<Destillat> lvwDestillater;
    @FXML
    private ListView<Mængde> lvwMængder;
    @FXML
    private TextField txfAntalLiter;

    public void initialize() {
        lvwDestillater.getItems().setAll(Controller.getDestillater());

        lvwDestillater.setCellFactory(new Callback<ListView<Destillat>, ListCell<Destillat>>() {
            @Override
            public ListCell<Destillat> call(ListView<Destillat> destillatListView) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Destillat destillat, boolean empty) {
                        super.updateItem(destillat, empty);
                        if (empty || destillat == null) {
                            setText(null);
                        } else {
                            setText(destillat.toString());
                        }
                    }
                };
            }
        });
    }

    @FXML
    public void tilføjMængdeAction() {
        double antalLiter = 0;
        try {
            antalLiter = Double.parseDouble(txfAntalLiter.getText());
        } catch (NumberFormatException ex) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Forkert format på indtastet tal");
            return;
        }


    }

}
