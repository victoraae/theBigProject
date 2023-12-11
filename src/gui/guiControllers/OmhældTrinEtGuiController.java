package gui.guiControllers;

import controller.Controller;
import gui.HovedVindue;
import gui.Main;
import gui.guiDummies.DestillatGui;
import gui.guiDummies.FadTilNMGui;
import gui.guiDummies.NewMakeGui;
import javafx.beans.value.ChangeListener;
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

    @FXML
    private ComboBox<FadTilNMGui> cmbValgtFad;

    private static Map<FadTilNM, Double> fadeÆndret = new HashMap<>();

    private Map<NewMake, List<FadTilNMGui>> hjælpeMap = new HashMap<>();


    public void initialize() {
        lvwNewMakes.getItems().setAll(newMakeGuier);
        initHjælpeMap();

        lvwLiterTilBlanding.setCellFactory(new Callback<ListView<Map.Entry<NewMake, Double>>, ListCell<Map.Entry<NewMake, Double>>>() {
            @Override
            public ListCell<Map.Entry<NewMake, Double>> call(ListView<Map.Entry<NewMake, Double>> newMakeLiterListView) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Map.Entry<NewMake, Double> entry, boolean empty) {
                        super.updateItem(entry, empty);
                        if (empty || entry == null) {
                            setText(null);
                        } else {
                            setText("Liter: " + entry.getValue() + ", NewMake: " + entry.getKey().getNavn());
                        }
                    }
                };
            }
        });

        ChangeListener<NewMakeGui> listener = (ov, o, n) -> this.opdaterValgtNewMake();
        lvwNewMakes.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private void opdaterValgtNewMake() {
        NewMakeGui newMakeGui = lvwNewMakes.getSelectionModel().getSelectedItem();
        if(newMakeGui!=null ){
            NewMake newMake = findOriginal(newMakeGui);
            cmbValgtFad.getItems().setAll(hjælpeMap.get(newMake));
        }
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
        FadTilNMGui ftnmFake = cmbValgtFad.getSelectionModel().getSelectedItem();

        if (newMakeFake == null) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Vælg en New Make fra listen");
            return;
        }
        NewMake newMake = newMakeFake.getOriginal();

        if (antalLiter <= 0) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Det indtastede antal liter er ugyldigt, prøv antalLiter >= 1");
            return;
        }
        if (newMakeFake.getLiterTilbage() < antalLiter) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der er ikke nok New Make væske, tjek New Makes antal liter tilbage");
            return;
        }
        if(ftnmFake == null){
            HovedVindue.setFejlBesked(lblFejlBesked, "Vælg et fad");
            return;
        }
        if(ftnmFake.getLiter()<antalLiter){
            HovedVindue.setFejlBesked(lblFejlBesked, "Det indtastede antal liter overstiger fadets antal liter");
        }

        if(!newMakeLiter.containsKey(newMake)) {
            newMakeLiter.put(newMake, antalLiter);
        }else {
            double oldVal = newMakeLiter.get(newMake);
            newMakeLiter.replace(newMake, oldVal+antalLiter);
        }
        newMakeFake.decLiterTilbage(antalLiter);
        ftnmFake.decLiter(antalLiter);
        fadeÆndret.put(ftnmFake.getOriginal(), antalLiter);
        opdaterListviews();
        opdaterValgtNewMake();
        lblFejlBesked.setVisible(false);
        txfAntalLiter.clear();
        lvwNewMakes.getSelectionModel().clearSelection();
        cmbValgtFad.getSelectionModel().clearSelection();
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
        if (lvwLiterTilBlanding.getItems().isEmpty() || erNMsGyldige()) {
            HovedVindue.setFejlBesked(lblFejlBesked, "Der skal som minimum vælges 2 forskellige New Makes til at skabe en blanding");
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

    /**
     * hjælpe metode lavet til brug i slutningen af omhæld trin to gui controller
     */
    public static void opdaterFade(){
        for(Map.Entry<FadTilNM, Double> entry : fadeÆndret.entrySet()){
            entry.getKey().decLiter(entry.getValue());
        }
    }


    private boolean erNMsGyldige(){
        boolean result = false;
        NewMake nm = null;
        for(Map.Entry<NewMake, Double> entry : newMakeLiter.entrySet()){
            if(nm!=null && !nm.equals(entry.getKey())){
                result = true;
            }
        }

        return result;
    }

    private NewMake findOriginal(NewMakeGui newMakeGui){
        NewMake result = null;
        for(NewMake nm : Controller.getIkkeTommeNewMakes()){
            if(newMakeGui.getOriginal().equals(nm)){
                result = nm;
            }
        }
        return result;
    }

    private List<FadTilNMGui> konverterFtnmTilGui(List<FadTilNM> fade){
        List<FadTilNMGui> result = new ArrayList<>();
        for(FadTilNM ftnm : fade){
            result.add(new FadTilNMGui(ftnm));
        }
        return result;
    }

    private void initHjælpeMap(){
        for(NewMake nm : Controller.getIkkeTommeNewMakes()){
            hjælpeMap.put(nm, konverterFtnmTilGui(nm.getFad()));
        }
    }
}
