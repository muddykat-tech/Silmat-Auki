package muddykat.silmat.auki;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import muddykat.silmat.auki.application.EyeMessage;
import muddykat.silmat.auki.application.EyeMessages;
import muddykat.silmat.auki.modules.CipherModule;

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
    Button updateEyeGraphics;

    @FXML
    TextArea outputTextPane;
    @FXML
    MenuButton btnCipher;

    @FXML
    ToggleButton overlayEyes;

    @FXML
    public void initialize() {
        initializeUI();
        initializeModules();
    }

    private void initializeModules(){
        CipherModule cipherModule = new CipherModule();
        cipherModule.initializeButtons(eyeRawText, btnCipher, outputTextPane);

    }

    private void initializeUI(){

        trigrams.setOnAction(event -> {
            System.out.println("Testing");
        });

        setupEyeMessageButton();


    }

    private static boolean overlayMode = false;
    private void setupEyeMessageButton(){

        overlayEyes.setOnAction(event -> {
            overlayMode = overlayEyes.isSelected();
            if (!overlayEyes.isSelected()){
                displayGrid.getChildren().clear();
            }
        });


        for (EyeMessages e : EyeMessages.values()) {
            MenuItem messageItem = new MenuItem(e.name());

            messageItem.setOnAction(event -> {
                if(!overlayMode) {
                    displayGrid.getChildren().clear();
                }
                eyeRawText.setText(e.getMessage().getRawString());
                e.getMessage().setDisplayPane(displayGrid);
                eyeMessageOptions.setText("Selected: " + e.name());
            });

            eyeMessageOptions.getItems().add(messageItem);
        }

        updateEyeGraphics.setOnAction(event -> {
            if(!overlayMode) {
                displayGrid.getChildren().clear();
            }
            eyeMessageOptions.setText("Selected: CUSTOM");
            String rawText = eyeRawText.getText().replaceAll("\n", "5").replaceAll("[^\\d.]", "").replaceAll(" ", "");
            EyeMessage custom = new EyeMessage(rawText);
            custom.setDisplayPane(displayGrid);
        });



    }


}
