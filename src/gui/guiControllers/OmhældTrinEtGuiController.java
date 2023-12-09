package gui.guiControllers;

import controller.Controller;
import gui.HovedVindue;
import gui.Main;
import gui.guiDummies.DestillatGui;
import gui.guiDummies.NewMakeGui;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Destillat;
import model.NewMake;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private ListView<NewMakeGui> lvwNewMakes;

    @FXML
    private TextField txfAnsvarligNavn;

    @FXML
    private TextField txfAntalLiter;

    private static Map<NewMake, Double> newMakeLiter = new HashMap<>();

    private final List<NewMakeGui> newMakeGuier = new ArrayList<>(konverterTilGuiDummy(Controller.getIkkeTommeNewMakes()));

    public OmhældTrinEtGuiController() {
    }


    public void initialize() {
        lvwNewMakes.getItems().setAll(newMakeGuier);


//        lvwNewMakes.setCellFactory(new Callback<ListView<NewMake>, ListCell<NewMake>>() {
//            @Override
//            public ListCell<NewMake> call(ListView<NewMake> newMakeListView) {
//                return new ListCell<>() {
//                    @Override
//                    public void updateItem(NewMake newMake, boolean empty) {
//                        super.updateItem(newMake, empty);
//                        if (empty || newMake == null) {
//                            setText(null);
//                        } else {
//                            setText(newMake.toStringKort());
//                        }
//                    }
//                };
//            }
//        });
    }

    @FXML
    public void fortrydAction() {
        newMakeLiter = new HashMap<>(); //clear den her statiske feltvariabel til næste gang vinduet åbnes

        Stage stage = (Stage) lblFejlBesked.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void tilføjLiterAction() {
        double antalLiter = 0;
        try {
            antalLiter = Double.parseDouble(txfAntalLiter.getText());
        } catch (NumberFormatException ex) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Forkert format på indtastet tal");
            return;
        }
        NewMakeGui newMakeFake = lvwNewMakes.getSelectionModel().getSelectedItem();
        NewMake newMake = newMakeFake.getOriginal();

        if (newMake == null) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Vælg en New Make fra listen");
            return;
        }
        if (antalLiter <= 0) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Det indtastede antal liter er ugyldigt, prøv antalLiter >= 1");
            return;
        }
        if (newMakeFake.getLiterTilbage() < antalLiter) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke nok New Make væske, tjek New Makes antal liter tilbage");
            return;
        }

        if(!newMakeLiter.containsKey(newMake)) {
            newMakeLiter.put(newMake, antalLiter);
        }else {
            double oldVal = newMakeLiter.get(newMake);
            newMakeLiter.replace(newMake, oldVal+antalLiter);
        }
        newMakeFake.decLiterTilbage(antalLiter);
        opdaterListviews();
        lblFejlBesked.setVisible(false);
        txfAntalLiter.clear();
    }

    public double sumLiter(NewMake newMake) {
        double result = 0;

        for (Map.Entry<NewMake, Double> nml : newMakeLiter.entrySet()) {
            if(newMake.equals(nml.getKey()))result += nml.getValue();
        }
        return result;
    }

    private void opdaterListviews() {
        lvwLiterTilBlanding.getItems().setAll(newMakeLiter.entrySet());
        lvwNewMakes.getItems().setAll(newMakeGuier);
    }

    @FXML
    public void næsteKnapAction() {
        String ansvarlig = txfAnsvarligNavn.getText().trim();
        LocalDate dato = datoVælger.getValue();

        if (ansvarlig.isBlank()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Navn på ansvarlig kan ikke være tom");
            return;
        }

        if (!ansvarlig.matches(".*[a-zA-Z]+.*")) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Ansvarlig navn må kun indeholde bogstaver");
            return;
        }
        if (lvwLiterTilBlanding.getItems().isEmpty() || newMakeLiter.size() == 1) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der skal som minimum vælges 2 New Makes til at skabe en blanding");
            return;
        }
        if(dato==null || dato.isAfter(LocalDate.now()) ){
            HovedVindue.setFejlBesked(lblFejlBesked, "Ugyldig dato, dato skal være idag eller tidligere, og dato skal vælges");
            return;
        }

        NewMake newMake = Controller.omhældNewMake(newMakeLiter, dato, ansvarlig);
        OmhældTrinToGuiController.newMake = newMake;
        Main.åbenVinduer.åbenOmhældTrinToVindue();
        fortrydAction(); //Lukke vinduet når trin 2  vinduet lukkes
    }

    private List<NewMakeGui> konverterTilGuiDummy(List<NewMake> newMakes){
        List<NewMakeGui> result = new ArrayList<>();
        for(NewMake nm : newMakes){
            result.add(new NewMakeGui(nm));
        }
        return result;
    }

    /**
     * hjælpe metode lavet til brug i slutningen af omhæld trin to gui controller
     */
    public static void opdaterNewMakes(){
        for(Map.Entry<NewMake, Double> entry : newMakeLiter.entrySet()){
            entry.getKey().decLiterTilbage(entry.getValue());
       }
    }

}
