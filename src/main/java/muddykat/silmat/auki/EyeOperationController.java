package muddykat.silmat.auki;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import muddykat.silmat.auki.application.EyeMessage;
import muddykat.silmat.auki.application.EyeMessages;
import muddykat.silmat.auki.modules.CipherModule;
import muddykat.silmat.auki.util.EyeMode;

public class EyeOperationController {
    @FXML
    Button btnBack;

    @FXML
    ChoiceBox<String> messageA, messageB, operation;

    @FXML
    TextArea rawData;
    @FXML
    TextArea displayData;

    @FXML
    public void switchToBase() {
        EyeApplication.selectedMode = EyeMode.BASE;
        try {
            EyeApplication.reloadScene();
        } catch (Exception exception){
            System.out.println("Error:" + exception.getLocalizedMessage());
        }
    }


    @FXML
    public void calculateOperation(){

    }

}
