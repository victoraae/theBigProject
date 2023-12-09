package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;

public class ÅbenVinduer {
    private static Stage parent;

    public static void setParent(Stage parent) {
        ÅbenVinduer.parent = parent;
    }

    public void åbenDestillatVindue() {åbenVindue("fxmls/opretDestillat.fxml", "Opret destillat");}

    public void åbenKornVindue() {åbenVindue("fxmls/kornVindue.fxml", "Opret eller vælg korn");}

    public void åbenFadVindue() {åbenVindue("fxmls/opretFad.fxml", "Opret fad");}

    public void åbenLagerVindue() {åbenVindue("fxmls/opretLager.fxml", "Opret lager");}

    public void åbenHyldeVindue() {åbenVindue("fxmls/opretHylde.fxml", "Opret hylde");}

    public void åbenPaaFyldTrinToVindue() {åbenVindue("fxmls/paafyldTrinTo.fxml", "Påfyld Destillat");}

    public void åbenNewMakeVindue() {åbenVindue("fxmls/listeNewmakes.fxml", "Liste Over New Makes");}

    public void åbenLavWhiskyVindue() {åbenVindue("fxmls/lavWhisky.fxml", "Lav whisky");}

    public void åbenHistorikVindue() {åbenVindue("fxmls/whiskyHistorik.fxml", "Whisky Historik");}

    public void åbenPaaFyldTrinEtVindue() {åbenVindue("fxmls/paafyldTrinEt.fxml", "Påfyld Destillat: Trin 1");}

    public void åbenOmhældTrinEtVindue() {åbenVindue("fxmls/omhaeldTrinEt.fxml", "Omhæld Trin 1: Lav en blanding fra New Makes");}

    public void åbenOmhældTrinToVindue() {åbenVindue("fxmls/omhaeldTrinTo.fxml", "Omhæld blanding af New Makes");}

    private void åbenVindue(String filNavn, String titel) {
        Stage stage = new Stage();
        URL fxmlFileName = this.getClass().getResource(filNavn);
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");
        stage.setResizable(false);
        Parent root = null;
        try {
            root = FXMLLoader.load(fxmlFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        stage.initOwner(parent);
        stage.setScene(scene);
        stage.setTitle(titel);

        stage.showAndWait();
    }
}



