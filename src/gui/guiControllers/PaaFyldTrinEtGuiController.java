package gui.guiControllers;

import controller.Controller;
import gui.HovedVindue;
import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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
    @FXML
    private TextField txfAnsvarligNavn;
    @FXML
    private TextField txfNMNavn;
    @FXML
    private DatePicker datoVælger;

    private ArrayList<Mængde> mængder = new ArrayList<>();

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
                            setText(destillat.toStringKortere());
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

        Destillat destillat = lvwDestillater.getSelectionModel().getSelectedItem();

        if (destillat == null) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Vælg et destillat fra listen");
            return;
        }
        if (antalLiter <= 0) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Det indtastede antal liter er ugyldigt, prøv antalLiter>=1");
            return;
        }
        if (destillat.getLiterTilbage() < antalLiter || destillat.getLiterTilbage() < antalLiter + sumLiter()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke nok destillats væske, tjek destillats antal liter tilbage");
            return;
        }

        mængder.add(new Mængde(antalLiter, destillat));
        opdaterLvwMængder();
        destillat.decLiterTilbage(antalLiter);      // opdaterer hvor mange liter vi har tilbage fra destillatet
        opdaterLvwDestillater();
        lblFejlBesked.setVisible(false);
        txfAntalLiter.clear();
    }

    private void opdaterLvwDestillater() {
        lvwDestillater.getItems().setAll(Controller.getDestillater());
    }

    @FXML
    public void næsteKnapAction() {
        String navn = txfNMNavn.getText().trim();
        String ansvarlig = txfAnsvarligNavn.getText().trim();
        LocalDate dato = datoVælger.getValue();

        if (navn.isBlank()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Navn på NewMake kan ikke være tom");
            return;
        }
        if (ansvarlig.isBlank()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Navn på ansvarlig kan ikke være tom");
            return;
        }
        if (!navn.matches(".*[a-zA-Z]+.*")) {
            HovedVindue.setFejlBesked(lblFejlBesked, "NewMakes navn må kun indeholde bogstaver");
            return;
        }
        if (!ansvarlig.matches(".*[a-zA-Z]+.*")) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Ansvarlig navn må kun indeholde bogstaver");
            return;
        }
        if (lvwMængder.getItems().isEmpty()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der skal som minimum være en mængde til at skabe et NewMake");
            return;
        }
        if(dato==null || dato.isAfter(LocalDate.now()) ){
            HovedVindue.setFejlBesked(lblFejlBesked, "Ugyldig dato, dato skal være idag eller tidligere, og dato skal vælges");
            return;
        }

        NewMake newMake = Controller.paafyldDestillat(navn, ansvarlig, mængder, new HashMap<NewMake, Double>(), dato);
        PaaFyldTrinToGuiController.newMake = newMake;
        Main.åbenVinduer.åbenPaaFyldTrinToVindue();
        fortrydAction(); //Lukke vinduet når trin 2  vinduet lukkes
    }

    @FXML
    public void fortrydAction() {
        Stage stage = (Stage) lblFejlBesked.getScene().getWindow();
        stage.close();
    }

    private void opdaterLvwMængder() {
        lvwMængder.getItems().setAll(mængder);
    }

    public double sumLiter() {
        double result = 0;

        for (Mængde mængde : mængder) {
            result += mængde.getMængde();
        }
        return result;
    }
}
