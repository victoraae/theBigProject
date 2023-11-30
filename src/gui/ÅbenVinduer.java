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

    public void åbenDestillatVindue() {
        Stage stage = new Stage();
        URL fxmlFileName = this.getClass().getResource("opretDestillat.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

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
        stage.setTitle("Destillat");

        stage.showAndWait();
    }

    public void åbenKornVindue() {
        Stage stage = new Stage();
        URL fxmlFileName = this.getClass().getResource("kornVindue.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

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
        stage.setTitle("Korn");

        stage.showAndWait();
    }

    public void åbenFadVindue() {
        Stage stage = new Stage();
        URL fxmlFileName = this.getClass().getResource("opretFad.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

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
        stage.setTitle("Opret fad");


        stage.showAndWait();
    }

    public void åbenLagerVindue() {
        Stage stage = new Stage();
        URL fxmlFileName = this.getClass().getResource("opretLager.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

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
        stage.setTitle("Opret lager");

        stage.showAndWait();
    }

    public static void setParent(Stage parent) {
        ÅbenVinduer.parent = parent;
    }


    public void åbenHyldeVindue() {
        Stage stage = new Stage();
        URL fxmlFileName = this.getClass().getResource("opretHylde.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

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
        stage.setTitle("Opret hylde");

        stage.showAndWait();
    }
}

