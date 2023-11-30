package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ÅbenVinduer {
    private static Stage parent;

    private void openSubWindow(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sub_fxml_file.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.initOwner(parent);
        stage.setScene(scene);
        stage.setTitle("Subvindue");

        stage.show();
    }
    public static void setParent(Stage parent) {
        ÅbenVinduer.parent = parent;
    }
}
