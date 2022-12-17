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
    private SplitMenuButton eyeMessageOptions;

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

    private EyeMessage selectedMessage = null;

    @FXML
    public void initialize() {
        trigrams.setOnAction(event -> {
            System.out.println("Testing");
        });

        for (EyeMessages e : EyeMessages.values()) {
            MenuItem messageItem = new MenuItem(e.name());
            messageItem.setOnAction(event -> {
                eyeMessageOptions.setText("Add Message: " + e.name());
                selectedMessage = e.getMessage();
            });

            eyeMessageOptions.getItems().add(messageItem);
        }

        eyeMessageOptions.setOnAction(event -> {
            eyeRawText.setText(selectedMessage.getRawString());
            selectedMessage.setDisplayPane(displayGrid);

        });
    }

}
