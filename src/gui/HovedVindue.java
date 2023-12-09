package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;

public class HovedVindue extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlFileName = this.getClass().getResource("fxmls/hovedOversigt.fxml");
        if (fxmlFileName == null) throw new NoSuchElementException("FXML file not found");
        stage.setResizable(false);
        Parent root = FXMLLoader.load(fxmlFileName);
        stage.setMinWidth(root.minWidth(-1));
        stage.setMinHeight(root.minHeight(-1));
        stage.setTitle("Sporbarhed i produktionen og fadlagerstyring hos Sall Whisky Distillery");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        ÅbenVinduer.setParent(stage);
        stage.show();
    }

    /**
     * En hjælpe metode de andre klasser kan bruge til at sætte fejlbesked i labels
     */
    public static void setFejlBesked(Label lblFB, String besked){
        int index = lblFB.getText().indexOf(':');
        lblFB.setText(lblFB.getText().substring(0, index+1) + " " + besked);
        lblFB.setVisible(true);
    }
}

