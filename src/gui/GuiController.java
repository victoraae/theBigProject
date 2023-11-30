import controller.Controller;
import gui.Main;
import gui.ÅbenVinduer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

import java.text.NumberFormat;
import java.time.LocalDate;

public class GuiController {
    @FXML
    private Button btnOpretDestillat;
    @FXML
    private Button btnOpretFad;
    @FXML
    private Button btnOpretLager;

    @FXML
    public void åbenDestillatVindueAction() {
        Main.åbenVinduer.åbenDestillatVindue();
    }

    //------------------------------------- opretDestillat vindue ------------------------------------------
    @FXML
    private Button btnDestilleriGem;

    @FXML
    private Button btnÅbenKorn;

    @FXML
    private DatePicker dateSelector;

    @FXML
    private TextField txfAlkoholProcent;

    @FXML
    private TextField txfAntalLiter;

    @FXML
    private TextField txfNavnPaaAnsvarlig;

    @FXML
    private TextField txfRygeMateriale;

    @FXML
    private TextField txfValgtKorn;

    @FXML
    private Label lblFBDestillat;

    @FXML
    private Button btnDestillatLuk;

    private Korn kornsort;
    @FXML
    public void opretDestillatAction(){
        LocalDate dato = dateSelector.getValue();
        double alko = 0;
        int antalLiter = 0;
                try {
                   alko = Double.parseDouble(txfAlkoholProcent.getText());
                   antalLiter = Integer.parseInt(txfAntalLiter.getText());
                }catch(NumberFormatException ex){
                    setFejlBesked(lblFBDestillat, "Forkert tal format");
                }
                String navnPåAnsvarlig = txfNavnPaaAnsvarlig.getText();
                String rygeMateriale = txfRygeMateriale.getText();

        if(kornsort==null) {
            setFejlBesked(lblFBDestillat, "vælg kornsort");
        }


        Destillat destillat = Controller.opretDestillat(dato, alko, navnPåAnsvarlig, antalLiter, 2, rygeMateriale, kornsort);
    }

    @FXML
    public void åbenKornVindueAction(){
        Main.åbenVinduer.åbenKornVindue();
    }

    //------------------------------------- Korn vindue ------------------------------------------

    @FXML
    private Button btnVælgKorn;

    @FXML
    private Button btnOpretKorn;

    @FXML
    private Label lblFBKorn;

    @FXML
    private ListView<Korn> lvwKorn;

    @FXML
    private TextArea txaMalkningsprocess;

    @FXML
    private TextField txfAar;

    @FXML
    private TextField txfBondemand;

    @FXML
    private TextField txfNavnPaaMark;

    @FXML
    private TextField txfSort;

    @FXML
    public void vælgKornAction(){
        Korn korn = lvwKorn.getSelectionModel().getSelectedItem();
        if(korn == null){
            setFejlBesked(lblFBKorn, "vælg en kornsort fra listen, eller opret en ny");
        }
    }

    @FXML
    public void opretKornAction(){
        String sort = txfSort.getText();
        String navnPåMark = txfNavnPaaMark.getText();


    }






//    @FXML
//    private void btnGemAction() {
//      if (!txfLagerNavn.getText().isEmpty() && !txfAdresse.getText().isEmpty() &&
//                !txfKapacitet.getText().isEmpty() && !txfStørrelse.getText().isEmpty() &&
//                !txfMaksReoler.getText().isEmpty()) {
//            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
//            dialog.setContentText("KVITTERING:\n" + "--------------\n" + "Lagernavn: " + txfLagerNavn.getText() + "\nadresse: "
//                    + txfAdresse.getText() + "\nstørrelse pr. m^2: " + txfKapacitet.getText() + "\npladser på lager: " + txfStørrelse.getText() + "\nmaksimale antaler reoler: " + txfMaksReoler.getText() + "\n--------------");
//            dialog.showAndWait();
//            dialog.setHeaderText("Bekræft oplysninger:");
//            dialog.setTitle("Er du sikker?");
//            double størrelse = Double.parseDouble(txfStørrelse.getText());
//            int kapacitet = Integer.parseInt(txfKapacitet.getText());
//            int maxAntalHylder = Integer.parseInt(txfMaksReoler.getText());
//            Controller.opretLager(txfLagerNavn.getText(), txfAdresse.getText(), størrelse, kapacitet, maxAntalHylder);
//        } else {
//            Alert dialog = new Alert(Alert.AlertType.ERROR);
//            dialog.setTitle("Du har ikke udfyldt alle felterne");
//            dialog.setContentText("Venligst udfyld alle felterne, før du fortsætter.");
//            dialog.showAndWait();
//        }
//
//    }
//    @FXML
//    private void btnLukAction() {
//        Stage stage = (Stage) btnLuk.getScene().getWindow();
//        stage.close();
//    }


    public void setFejlBesked(Label lblFB, String besked){
        int index = lblFB.getText().indexOf(':');
        lblFB.setText(lblFB.getText().substring(0, index) + " " + besked);
        lblFB.setVisible(true);
    }
}




