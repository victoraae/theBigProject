package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;

public class HovedVindue extends Application {

    private Stage parent;
    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlFileName = this.getClass().getResource("hovedOversigt.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");

        Parent root = FXMLLoader.load(fxmlFileName);
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        parent = stage;
        stage.show();
    }

    private void openSubWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sub_fxml_file.fxml"));
        Stage root = loader.load();
        Scene scene = new Scene(root);
        root.initOwner(parent);
        root.setScene();
        root.setTitle("Subvindue");

        root.show();
    }
}

