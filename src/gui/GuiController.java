import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GuiController {
    @FXML
    private Button btnGem;
    @FXML
    private Button btnLuk;
    @FXML
    private TextField txfAdresse;
    @FXML
    private TextField txfKapacitet;
    @FXML
    private TextField txfLagerNavn;
    @FXML
    private TextField txfMaksReoler;
    @FXML
    private TextField txfStørrelse;

    @FXML
    private void btnGemAction() {
      if (!txfLagerNavn.getText().isEmpty() && !txfAdresse.getText().isEmpty() &&
                !txfKapacitet.getText().isEmpty() && !txfStørrelse.getText().isEmpty() &&
                !txfMaksReoler.getText().isEmpty()) {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
            dialog.setContentText(txfLagerNavn.getText() + "\n"
                    + txfAdresse.getText() + "\n");
            dialog.showAndWait();
            dialog.setHeaderText("Bekræft oplysninger:");
            dialog.setTitle("Er du sikker?");
            double størrelse = Double.parseDouble(txfStørrelse.getText());
            int kapacitet = Integer.parseInt(txfKapacitet.getText());
            int maxAntalHylder = Integer.parseInt(txfMaksReoler.getText());
            Controller.opretLager(txfLagerNavn.getText(), txfAdresse.getText(), størrelse, kapacitet, maxAntalHylder);
        } else {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Du har ikke udfyldt alle felterne");
            dialog.setContentText("Venligst udfyld alle felterne, før du fortsætter.");
            dialog.showAndWait();
        }

    }
    @FXML
    private void btnLukAction() {
        Stage stage = (Stage) btnLuk.getScene().getWindow();
        stage.close();
    }
}




