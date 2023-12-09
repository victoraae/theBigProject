package gui.guiControllers;

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
    private Button btnLavWhisky;

    @FXML
    private Button btnHistorik;
    @FXML
    private Button btnOmhæld;

    public void initialize() {

    }
    @FXML
    public void åbenPaafyldDestillatAction() {
        Main.åbenVinduer.åbenPaaFyldTrinEtVindue();
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
    public void åbenLavWhiskyVindueAction() {
        Main.åbenVinduer.åbenLavWhiskyVindue();
    }

    @FXML
    public void åbenHistorikVindueAction(){
        Main.åbenVinduer.åbenHistorikVindue();
    }

    @FXML
    public void åbenPaafyldDestillatTrin2(){
        Main.åbenVinduer.åbenPaafyldDestillatVindue();
    }

    @FXML
    public void åbenOmhældTrinEtVindue(){
        Main.åbenVinduer.åbenOmhældTrinEtVindue();
    }
}

