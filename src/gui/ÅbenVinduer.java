package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;

public class ÅbenVinduer {
    private static Stage parent;

    public static void setParent(Stage parent) {
        ÅbenVinduer.parent = parent;
    }

    public void åbenDestillatVindue() {
        åbenVindue("opretDestillat.fxml", "Opret destillat");
    }

    public void åbenKornVindue() {
        åbenVindue("kornVindue.fxml", "Opret eller vælg korn");
    }

    public void åbenFadVindue() {
        åbenVindue("opretFad.fxml", "Opret fad");
    }

    public void åbenLagerVindue() {
        åbenVindue("opretLager.fxml", "Opret lager");
    }

    public void åbenHyldeVindue() {
        åbenVindue("opretHylde.fxml", "Opret hylde");
    }

    public void åbenPaafyldDestillatVindue() {
        åbenVindue("paaFyldDestillat.fxml", "Påfyld Destillat");
    }

    public void åbenNewMakeVindue() {
        åbenVindue("listeNewmakes.fxml", "Liste Over New Makes");
    }

    public void åbenLavWhiskyVindue() {
        åbenVindue("lavWhisky.fxml", "Lav whisky");
    }

    public void åbenHistorikVindue() {
        åbenVindue("whiskyHistorik.fxml", "Whisky Historik");
    }

    public void åbenPaaFyldTrinEtVindue() {
        åbenVindue("paafyldTrinEt.fxml", "Påfyld Destillat: Trin 1");
    }

    public void åbenOmhældTrinEtVindue() {
        åbenVindue("omhaeldTrinEt.fxml", "Omhæld Trin 1: Lav en blanding fra New Makes");
    }

    public void åbenOmhældNewMakesVindue() {
        åbenVindue("omhaeldNewMakes.fxml", "Omhæld blanding af New Makes");
    }
    private void åbenVindue(String filNavn, String titel){
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



