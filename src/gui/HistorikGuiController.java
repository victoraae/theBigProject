package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

public class HistorikGuiController {

    @FXML
    private Button btnLuk;

    @FXML
    private ComboBox<Whisky> cmbWhisky;

    @FXML
    private TextArea txaHistorik;


    public void initialize(){
        cmbWhisky.getItems().setAll(Controller.getWhiskyer());
        txaHistorik.setWrapText(true);

        ChangeListener<Whisky> listener = (ov, o, n) -> this.opdaterTxaHistorik();
        cmbWhisky.getSelectionModel().selectedItemProperty().addListener(listener);

    }

    private void opdaterTxaHistorik() {
        Whisky whisky = cmbWhisky.getSelectionModel().getSelectedItem();
        txaHistorik.setText(whisky.getFlasker().get(0).toString()); //Vi viser historikken for den første flaske
    }

    @FXML
    public void btnLukAction() {
        Stage stage = (Stage) txaHistorik.getScene().getWindow();
        stage.close();
    }
}
