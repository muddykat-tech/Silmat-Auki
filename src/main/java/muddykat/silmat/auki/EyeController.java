package muddykat.silmat.auki;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import muddykat.silmat.auki.application.EyeMessage;
import muddykat.silmat.auki.application.EyeMessages;

public class EyeController {

    @FXML
    private Button trigrams;

    @FXML
    private MenuButton eyeMessageOptions;

    @FXML
    private SplitPane displaySplitPane;

    @FXML
    AnchorPane splitTop;
    @FXML
    AnchorPane splitBottom;

    @FXML
    TextArea eyeRawText;

    @FXML
    GridPane displayGrid;

    @FXML
    public void initialize() {
        trigrams.setOnAction(event -> {
            System.out.println("Testing");
        });

        setupEyeMessageButton();

    }

    private void setupEyeMessageButton(){
        for (EyeMessages e : EyeMessages.values()) {
            MenuItem messageItem = new MenuItem(e.name());

            messageItem.setOnAction(event -> {
                displayGrid.getChildren().clear();
                eyeRawText.setText(e.getMessage().getRawString());
                e.getMessage().setDisplayPane(displayGrid);
                eyeMessageOptions.setText("Selected: " + e.name());
            });

            eyeMessageOptions.getItems().add(messageItem);
        }



    }


}
