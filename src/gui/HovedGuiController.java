package gui;

import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HovedGuiController {
    @FXML
    private Button btnOpretDestillat;

    @FXML
    private Button btnOpretFad;

    @FXML
    private Button btnOpretLager;

    @FXML
    private Button btnPaafyldDestillat;

    @FXML
    private Button btnHistorik;

    public void initialize() {

    }
    @FXML
    public void åbenPaafyldDestillatAction() {
        Main.åbenVinduer.åbenPaafyldDestillatVindue();
    }
    @FXML
    public void åbenDestillatVindueAction() {
        Main.åbenVinduer.åbenDestillatVindue();
    }

    @FXML
    public void åbenFadVindueAction() {
        Main.åbenVinduer.åbenFadVindue();
    }

    @FXML
    public void åbenLagerVindueAction() {
        Main.åbenVinduer.åbenLagerVindue();
    }
    @FXML
    public void åbenNewMakesVindueAction() {
        Main.åbenVinduer.åbenNewMakeVindue();
    }

    @FXML
    public void åbenHistorikVindueAction(){
        Main.åbenVinduer.åbenHistorikVindue();
    }
}

