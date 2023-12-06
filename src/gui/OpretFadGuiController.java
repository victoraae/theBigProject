package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.FadStørrelser;
import model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class OpretFadGuiController {
    @FXML
    private Button btnOpretFad;
    @FXML
    private ComboBox<Lager> cmbVælgLager;
    @FXML
    private Button btnFortryd;
    @FXML
    private ComboBox<Hylde> cmbVælgHylde;
    @FXML
    private ComboBox<FadStørrelser> cmbVælgStorelse;
    @FXML
    private Label lblFejlBesked;
    @FXML
    private TextField txfMateriale;
    @FXML
    private TextField txfNavnPaaLeveradoer;
    @FXML
    private TextField txfOprindelsesland;
    @FXML
    private TextField txfTidligereIndhold;

    public void initialize(){
        cmbVælgStorelse.getItems().setAll(FadStørrelser.class.getEnumConstants());
        cmbVælgLager.getItems().setAll(Controller.getLagre());

        ChangeListener<Lager> listener = (ov, o, n) -> this.opdaterCmbHylder();
        cmbVælgLager.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private void opdaterCmbHylder(){
        Lager lager = cmbVælgLager.getValue();
        ArrayList<Hylde> hylder = new ArrayList<>();

        if(lager!=null){
            for(Reol reol : lager.getReoler()){
                hylder.addAll(reol.getHylder());
            }
            cmbVælgHylde.getItems().setAll(hylder);
        }
    }

    @FXML
    public void opretFadAction(){
        Lager lager = cmbVælgLager.getValue();
        Hylde hylde = cmbVælgHylde.getValue();
        FadStørrelser størrelse = cmbVælgStorelse.getValue();


        String materiale = txfMateriale.getText();
        String leverandør = txfNavnPaaLeveradoer.getText();
        String oprindLand =  txfOprindelsesland.getText();
        String tidlIndhold = txfTidligereIndhold.getText();

        if(materiale.isBlank() ||leverandør.isBlank()||materiale.isBlank() ||materiale.isBlank() ){
            HovedVindue.setFejlBesked(lblFejlBesked, "Alle felter skal udfyldes");
            return;
        }


        if(hylde==null && !lager.erDerPladsTilFad(størrelse.getStørrelse())){
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke plads på det valgte lager");
            return;
        }

        if(hylde!=null && !hylde.erDerPladsTilFad(størrelse.getStørrelse())){
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke plads på den valgte hylde");
            return;
        }


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        Fad fad = new Fad(leverandør, oprindLand, materiale, tidlIndhold, størrelse, lager, hylde);
        alert.setContentText(fad.toString());
        alert.setHeaderText("Bekræft oplysninger");
        alert.showAndWait();

        Controller.opretFad(lager, hylde, leverandør, oprindLand, materiale, tidlIndhold, størrelse);
        fortrydAction();
    }

    @FXML
    public void fortrydAction(){
        Stage stage = (Stage) btnFortryd.getScene().getWindow();
        stage.close();
    }

}
