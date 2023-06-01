package muddykat.silmat.auki;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import muddykat.silmat.auki.application.EyeMessage;
import muddykat.silmat.auki.application.EyeMessages;
import muddykat.silmat.auki.modules.CipherModule;
import muddykat.silmat.auki.util.EyeMode;

import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static muddykat.silmat.auki.util.EyeUtil.getResource;

public class EyeController {

    //Should these be private, probably.
    @FXML
    ToggleButton trigrams;
    @FXML
    MenuButton eyeMessageOptions;
    @FXML
    SplitPane displaySplitPane;
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
    TextField keyInput;
    @FXML
    Button outputToInput;

    @FXML
    Button btnEncrypt;

    @FXML
    Button btnDecrypt;

    @FXML
    Button homomorphicOperation;

    @FXML
    CheckBox chkOnlyMode;

    @FXML
    public void switchToOperation() {
        EyeApplication.selectedMode = EyeMode.OPERATION;
        try {
            EyeApplication.reloadScene();
        } catch (Exception exception){
            System.out.println("Error:" + exception.getMessage());
        }
    }

    @FXML
    public void initialize() {
        initializeUI();
        initializeModules();
    }

    double mouse_anchor_x, mouse_anchor_y;

    private void initializeModules(){
        CipherModule cipherModule = new CipherModule();
        cipherModule.initializeButtons(eyeRawText, btnCipher, outputTextPane, keyInput, btnEncrypt, btnDecrypt);
    }

    private void initializeUI(){
        setupEyeMessageButton();
    }

    private static boolean overlayMode = false;
    private void setupEyeMessageButton(){
        setupOutputShift();
        setupOverlayMode();
        setupEyeSelection();
        setupDragDrop();
        setupTrigrams();
    }

    private double posX = 0.0;
    private double posY = 0.0;

    private void setupDragDrop() {
        // Very dirty, has strange initial click behaviour, but works well enough.
        // TODO: fix bug when using overlay mode
        splitBottom.setOnMousePressed(event -> {
            mouse_anchor_x = event.getX();
            mouse_anchor_y = event.getY() + (splitBottom.getHeight() * 1.2);
        });

        splitBottom.setOnMouseDragged(event ->
        {
            splitBottom.getChildren().forEach((x) -> {
                if(x instanceof ImageView view){
                    posX = event.getSceneX() - mouse_anchor_x;
                    posY = event.getSceneY() - mouse_anchor_y;
                    view.setLayoutX(posX);
                    view.setLayoutY(posY);
                }
            });
        });
    }

    private void setupEyeSelection(){
        for (EyeMessages e : EyeMessages.values()) {
            MenuItem messageItem = new MenuItem(e.name());

            messageItem.setOnAction(event -> {
                String rawText = e.getMessage().getRawString();
                if(!overlayMode) {
                    splitBottom.getChildren().clear();
                } else if (chkOnlyMode.isSelected()) {
                    char[] oldData = eyeRawText.getText().replaceAll("\n", "5").replaceAll("[^\\d.]", "").replaceAll(" ", "").toCharArray();
                    char[] newData = e.getMessage().getRawString().replaceAll("\n", "5").replaceAll("[^\\d.]", "").replaceAll(" ", "").toCharArray();
                    char[] structuredData = new char[Math.max(eyeRawText.getText().length(), e.getMessage().getRawString().length())];

                    for(int i = 0; i < structuredData.length; i++) {
                        if (oldData.length > i && newData.length > i) {
                            structuredData[i] = (oldData[i] == newData[i] ? newData[i] : '6');
                        } else {
                            structuredData[i] = '6';
                        }
                    }
                    rawText = new String(structuredData);
                    System.out.println("oldData: " + new String(oldData));
                    System.out.println("newData: " + new String(newData));
                    System.out.println("structuredData: " + new String(structuredData));

                }


                EyeMessage custom = new EyeMessage(rawText.replaceAll("\n", "5").replaceAll("[^\\d.]", "").replaceAll(" ", ""));
                eyeRawText.setText(custom.getRawString());
                custom.setDisplayPane(splitBottom, posX, posY, trigrams);
                eyeMessageOptions.setText("Selected: " + e.name());
            });

            eyeMessageOptions.getItems().add(messageItem);
        }

        updateEyeGraphics.setOnAction(event -> {
            if(!overlayMode || chkOnlyMode.isSelected()) {
                splitBottom.getChildren().clear();
            }
            eyeMessageOptions.setText("Selected: CUSTOM");
            String rawText = eyeRawText.getText().replaceAll("\n", "5").replaceAll("[^\\d.]", "").replaceAll(" ", "");
            EyeMessage custom = new EyeMessage(rawText);
            custom.setDisplayPane(splitBottom, posX, posY, trigrams);
        });
    }

    private void setupOverlayMode(){
        overlayEyes.setOnAction(event -> {
            overlayMode = overlayEyes.isSelected();
            if (!overlayEyes.isSelected()){
                if(displayGrid != null && displayGrid.getChildren() != null)
                    displayGrid.getChildren().clear();
            }
        });
    }

    private void setupOutputShift(){
        outputToInput.setOnAction(event -> {
            String output = outputTextPane.getText();
            eyeRawText.setText(output);
            outputTextPane.clear();
        });
    }

    public void setupTrigrams(){
        trigrams.setOnAction(event -> {
            updateTrigrams();
        });
    }

    public void updateTrigrams(){
        ArrayList<ArrayList<String>> trigrams = extractTrigrams(eyeRawText.getText());

        StringBuilder trigramData = new StringBuilder();

        for (ArrayList<String> line : trigrams) {
            trigramData.append(line.toString() + "\n");
        }

        outputTextPane.setText(trigramData.toString());
    }

    public static ArrayList<ArrayList<String>> extractTrigrams(String rawData) {

        ArrayList<String> lineData = processRawData(rawData);

        ArrayList<ArrayList<String>> trigrams = new ArrayList<>();

        for(int i = 0; i < lineData.size(); i++) {
            trigrams.add(findTrigramInLineData(lineData.get(i)));
        }

        return trigrams;
    }

    private static ArrayList<String> findTrigramInLineData(String s) {

        String[] lines = s.split("\n");

        ArrayList<String> primitiveTrigrams = new ArrayList<>();
        ArrayList<String> line1 = formatLine(lines[0], true);
        ArrayList<String> line2 = formatLine(lines[1], false);
        for(int i = 0; i < line1.size(); i++){
            primitiveTrigrams.add(createTrigram(line1.get(i), line2.get(i)));
        }

        return primitiveTrigrams;
    }

    private static ArrayList<String> processRawData(String rawData) {
        String data = rawData.replaceAll("\n", "5").replaceAll("[^\\d.]", "").replaceAll(" ", "");
        StringBuilder output = new StringBuilder(data);
        int count = 0;
        int index = output.indexOf("5");
        while (index != -1) {
            count++;
            if (count % 2 == 0) {
                output.replace(index, index + 1, "\n\n");
            }
            index = output.indexOf("5", index + 1);
        }
        String modified = output.toString().replaceAll("5","\n");
        ArrayList<String> lines = new ArrayList<>();
        String[] splitLines = modified.split("\n\n");

        for (String line : splitLines) {
            if (!line.isEmpty()) {
                lines.add(line);
            }
        }

        return lines;
    }

    private static ArrayList<String> formatLine(String input, boolean flag) {
        ArrayList<String> substrings = new ArrayList<>();
        int length = input.length();
        int index = 0;

        while (index < length) {
            int substringLength = flag ? 2 : 1;
            if (index + substringLength <= length) {
                substrings.add(input.substring(index, index + substringLength));
                index += substringLength;
                flag = !flag;
            } else {
                substrings.add(input.substring(index));
                break;
            }
        }

        return substrings;
    }

    public static String createTrigram(String topLine, String botLine) {
        StringBuilder result = new StringBuilder();

        if(topLine.length() == 2){
            result.append(topLine);
            result.append(botLine);
        } else if(botLine.length() == 2){
            ArrayList<Character> bottomLine = new ArrayList<>();
            for (char c : botLine.toCharArray()) {
                bottomLine.add(c);
            }
            Collections.reverse(bottomLine);
            result.append(bottomLine.get(0));
            result.append(bottomLine.get(1));
            result.append(topLine);
        }

        return result.toString();
    }

}
