package gui.guiControllers;

import controller.Controller;
import gui.HovedVindue;
import gui.Main;
import gui.guiDummies.DestillatGui;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaaFyldTrinEtGuiController {
    @FXML
    private Button btnFortryd = null;
    @FXML
    private Button btnNæste;
    @FXML
    private Button btnTilføjTilNM;
    @FXML
    private Label lblFejlBesked;
    @FXML
    private ListView<DestillatGui> lvwDestillater;
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
    private static Map<Destillat, Double> destillatDecLiters = new HashMap<>();
    private final List<DestillatGui> destillatGuier = new ArrayList<>(konverterTilGuiDummy(Controller.getIkkeTommeDestillater()));

    public void initialize() {
        opdaterLvwDestillater();

//        lvwDestillater.setCellFactory(new Callback<ListView<Destillat>, ListCell<Destillat>>() {
//            @Override
//            public ListCell<Destillat> call(ListView<Destillat> destillatListView) {
//                return new ListCell<>() {
//                    @Override
//                    public void updateItem(Destillat destillat, boolean empty) {
//                        super.updateItem(destillat, empty);
//                        if (empty || destillat == null) {
//                            setText(null);
//                        } else {
//                            setText(destillat.toStringKortere());
//                        }
//                    }
//                };
//            }
//        });
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

        DestillatGui destillat = lvwDestillater.getSelectionModel().getSelectedItem();

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

        Destillat destillat1 = findOriginal(destillat);

        mængder.add(new Mængde(antalLiter, destillat1));
        opdaterLvwMængder();
        destillat.decLiterTilbage(antalLiter); // opdaterer hvor mange liter vi har tilbage fra destillatet
        destillatDecLiters.put(destillat1, antalLiter);
        opdaterLvwDestillater();
        lblFejlBesked.setVisible(false);
        txfAntalLiter.clear();
    }

    private void opdaterLvwDestillater() {
        lvwDestillater.getItems().setAll(destillatGuier);
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

        NewMake newMake = Controller.paafyldDestillat(navn, ansvarlig, mængder, new HashMap<Fad, Double>(), new HashMap<NewMake, Double>(), dato);
        PaaFyldTrinToGuiController.newMake = newMake;
        Main.åbenVinduer.åbenPaaFyldTrinToVindue();
        destillatDecLiters = new HashMap<>(); //Mappet clearers da det er statisk og vi vil have andre værdi i næste gang
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

    private List<DestillatGui> konverterTilGuiDummy(List<Destillat> destillater){
        List<DestillatGui> result = new ArrayList<>();
        for(Destillat d : destillater){
            result.add(new DestillatGui(d));
        }
        return result;
    }

    private Destillat findOriginal(DestillatGui destillatGui){
        Destillat result = null;
        for(Destillat destillat : Controller.getIkkeTommeDestillater()){
            if(destillatGui.getOriginal().equals(destillat)){
                result = destillat;
            }
        }
        return result;
    }

    /**
     * hjælpemetode til at decrement liter fra literTilbage på destillaterne, til trin to vinduet
     */
    public static void decLiterTilbageDestillater(){
        for(Map.Entry<Destillat, Double> entry : destillatDecLiters.entrySet()){
            entry.getKey().decLiterTilbage(entry.getValue());
        }
    }
}
