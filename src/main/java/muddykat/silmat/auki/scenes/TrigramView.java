package muddykat.silmat.auki.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import muddykat.silmat.auki.ApplicationControl;
import muddykat.silmat.auki.eyes.EyeData;
import muddykat.silmat.auki.eyes.EyeMessages;
import muddykat.silmat.auki.eyes.trigram.Trigram;
import muddykat.silmat.auki.eyes.trigram.TrigramReadMode;
import muddykat.silmat.auki.utility.ApplicationMode;

public class TrigramView {
    @FXML
    private MenuButton btnTrigramReadMethod, btnSelectedEyeData;

    @FXML
    Button btnImportMessage;

    @FXML
    private Text txtSelectedReadingMode, txtDisplayData;

    @FXML
    private TextArea dataA, dataB;

    @FXML
    private RadioButton dataASelector, dataBSelector, rbtnRawMsg, rbtnRawTrigram, rbtnTrigram, rbtnTrigramAscii;

    @FXML
    private ToggleButton btnLinkFields;

    @FXML
    private AnchorPane eyeDisplay;

    public static TrigramReadMode selectedMode = TrigramReadMode.acb;
    public static EyeData selectedMessageData;
    public static EyeData oldSelectedMessageData;

    private DataForm dataAform = DataForm.rawMsg;
    private DataForm dataBform = DataForm.rawMsg;

    public void initialize(){
        dataASelector.setOnAction(e -> {
            if(dataBSelector.isSelected()) {
                txtDisplayData.setText("Displaying: Data A");
                dataBSelector.setSelected(false);
                switch(dataAform){
                    case rawTrigram -> {
                        rbtnRawMsg.setSelected(false);
                        rbtnTrigram.setSelected(false);
                        rbtnTrigramAscii.setSelected(false);
                        rbtnRawTrigram.setSelected(true);
                    }
                    case rawMsg -> {
                        rbtnRawMsg.setSelected(true);
                        rbtnTrigram.setSelected(false);
                        rbtnTrigramAscii.setSelected(false);
                        rbtnRawTrigram.setSelected(false);
                    }
                    case trigramValue -> {
                        rbtnRawMsg.setSelected(false);
                        rbtnTrigram.setSelected(true);
                        rbtnTrigramAscii.setSelected(false);
                        rbtnRawTrigram.setSelected(false);
                    }
                    case trigramAscii-> {
                        rbtnRawMsg.setSelected(false);
                        rbtnTrigram.setSelected(false);
                        rbtnTrigramAscii.setSelected(true);
                        rbtnRawTrigram.setSelected(false);
                    }
                }
                selectedMessageData = parseTextData(dataA.getText(), dataAform);
                updateEyeDisplay();
            }
        });

        dataBSelector.setOnAction(e -> {
            if(dataASelector.isSelected()) {
                txtDisplayData.setText("Displaying: Data B");
                dataASelector.setSelected(false);
                switch(dataBform){
                    case rawTrigram -> {
                        rbtnRawMsg.setSelected(false);
                        rbtnTrigram.setSelected(false);
                        rbtnTrigramAscii.setSelected(false);
                        rbtnRawTrigram.setSelected(true);
                    }
                    case rawMsg -> {
                        rbtnRawMsg.setSelected(true);
                        rbtnTrigram.setSelected(false);
                        rbtnTrigramAscii.setSelected(false);
                        rbtnRawTrigram.setSelected(false);
                    }
                    case trigramValue -> {
                        rbtnRawMsg.setSelected(false);
                        rbtnTrigram.setSelected(true);
                        rbtnTrigramAscii.setSelected(false);
                        rbtnRawTrigram.setSelected(false);
                    }
                    case trigramAscii-> {
                        rbtnRawMsg.setSelected(false);
                        rbtnTrigram.setSelected(false);
                        rbtnTrigramAscii.setSelected(true);
                        rbtnRawTrigram.setSelected(false);
                    }
                }
                selectedMessageData = parseTextData(dataB.getText(), dataBform);
                updateEyeDisplay();
            }
        });

        rbtnRawMsg.setOnAction(e -> {
            if(rbtnRawTrigram.isSelected()) rbtnRawTrigram.setSelected(false);
            if(rbtnTrigram.isSelected()) rbtnTrigram.setSelected(false);
            if(rbtnTrigramAscii.isSelected()) rbtnTrigramAscii.setSelected(false);
            importData();
        });
        rbtnRawTrigram.setOnAction(e -> {
            if(rbtnRawMsg.isSelected()) rbtnRawMsg.setSelected(false);
            if(rbtnTrigram.isSelected()) rbtnTrigram.setSelected(false);
            if(rbtnTrigramAscii.isSelected()) rbtnTrigramAscii.setSelected(false);
            importData();
        });
        rbtnTrigram.setOnAction(e -> {
            if(rbtnRawMsg.isSelected()) rbtnRawMsg.setSelected(false);
            if(rbtnRawTrigram.isSelected()) rbtnRawTrigram.setSelected(false);
            if(rbtnTrigramAscii.isSelected()) rbtnTrigramAscii.setSelected(false);
            importData();
        });

        rbtnTrigramAscii.setOnAction(e -> {
            if(rbtnRawMsg.isSelected()) rbtnRawMsg.setSelected(false);
            if(rbtnRawTrigram.isSelected()) rbtnRawTrigram.setSelected(false);
            if(rbtnTrigram.isSelected()) rbtnTrigram.setSelected(false);
            importData();
        });


        for (TrigramReadMode mode : TrigramReadMode.values()) {
            MenuItem item = new MenuItem(mode.name());
            item.setOnAction(e->
            {
                txtSelectedReadingMode.setText("Method: " + mode.name());
                selectedMode = mode;
                importData();
                updateEyeDisplay();
            });

            btnTrigramReadMethod.getItems().add(item);
        }

        for(EyeMessages message : EyeMessages.values()){
            MenuItem item = new MenuItem(message.getName());
            item.setOnAction(e -> {
                btnSelectedEyeData.setText("Message: " + message.getName());
                selectedMessageData = message.getData();
                updateEyeDisplay();
                importData();
            });
            btnSelectedEyeData.getItems().add(item);
        }
    }

    private void importData(){
        if(selectedMessageData != null){
            TextArea selectedTextArea = dataASelector.isSelected() ? dataA : dataB;
            if(rbtnTrigram.isSelected()){
                selectedTextArea.setText(selectedMessageData.getTrigramValues());
                if(dataASelector.isSelected()) {
                    dataAform = DataForm.trigramValue;
                    dataASelector.setText("Data A: Trigram Value");
                }
                if(dataBSelector.isSelected()) {
                    dataBform = DataForm.trigramValue;
                    dataBSelector.setText("Data B: Trigram Value");
                }
            }
            if(rbtnRawTrigram.isSelected()){
                selectedTextArea.setText(selectedMessageData.getRawTrigramData());
                if(dataASelector.isSelected()) {
                    dataAform = DataForm.rawTrigram;
                    dataASelector.setText("Data A: Raw Trigrams");
                }
                if(dataBSelector.isSelected()) {
                    dataBform = DataForm.rawTrigram;
                    dataBSelector.setText("Data B: Raw Trigrams");
                }
            }
            if(rbtnRawMsg.isSelected()){
                selectedTextArea.setText(selectedMessageData.getRawEyeData());
                if(dataASelector.isSelected()) {
                    dataAform = DataForm.rawMsg;
                    dataASelector.setText("Data A: Raw Message");
                }
                if(dataBSelector.isSelected()) {
                    dataBform = DataForm.rawMsg;
                    dataBSelector.setText("Data B: Raw Message");
                }
            }
            if(rbtnTrigramAscii.isSelected()){
                selectedTextArea.setText(convertDecimalToAscii(selectedMessageData.getTrigramValues()));
                if(dataASelector.isSelected()) {
                    dataAform = DataForm.trigramAscii;
                    dataASelector.setText("Data A: Trigram Ascii");
                }
                if(dataBSelector.isSelected()) {
                    dataBform = DataForm.trigramAscii;
                    dataBSelector.setText("Data B: Trigram Ascii");
                }
            }
            updateEyeDisplay();
        }
    }
    
    private EyeData parseTextData(String primaryData, DataForm form) {
        primaryData = primaryData.trim();
        String[] rawMsg = null;
        switch (form) {
            case trigramAscii -> {
                primaryData = convertAsciiToDecimal(primaryData);
                String[] lines = primaryData.split("\n");
                String[] rawMessageLines = new String[lines.length*2];

                StringBuilder rawLine = new StringBuilder();
                StringBuilder rawLine1 = new StringBuilder();
                StringBuilder rawLine2 = new StringBuilder();

                for(int l = 0;  l < lines.length; l++) {
                    String line = lines[l];
                    String[] values = line.split(",");
                    for(int i = 0; i < values.length; i++) {
                        StringBuilder scrambledRawTrigram = new StringBuilder(Integer.toString(Integer.parseInt(values[i], 10), 5));
                        while(scrambledRawTrigram.length() < 3) {
                            scrambledRawTrigram.insert(0, "0");
                        }

                        String rawTrigram = selectedMode.convertToRaw(scrambledRawTrigram.charAt(0), scrambledRawTrigram.charAt(1), scrambledRawTrigram.charAt(2));
                        String abcTrigram = i % 2 == 0 ? rawTrigram : EyeData.reverseString(rawTrigram);

                        char[] trigramparts = abcTrigram.trim().toCharArray();
                        for(int v = 0; v < trigramparts.length; v++) {
                            rawLine.append(trigramparts[v]);
                        }
                    }
                    char[] rawlinecharacters = rawLine.toString().toCharArray();
                    for (int i = 0; i < rawlinecharacters.length; i++) {
                        if(i % 2 == 0) {
                            rawLine1.append(String.valueOf(rawlinecharacters[i]));
                        } else {
                            rawLine2.append(String.valueOf(rawlinecharacters[i]));
                        }
                    }
                    rawMessageLines[l * 2] = rawLine1.toString();
                    rawMessageLines[1 + (l * 2)] = rawLine2.toString();
                    rawLine = new StringBuilder();
                    rawLine1 = new StringBuilder();
                    rawLine2 = new StringBuilder();
                }
                rawMsg = rawMessageLines;
            }
            case trigramValue -> {
                String[] lines = primaryData.split("\n");
                String[] rawMessageLines = new String[lines.length*2];

                StringBuilder rawLine = new StringBuilder();
                StringBuilder rawLine1 = new StringBuilder();
                StringBuilder rawLine2 = new StringBuilder();

                for(int l = 0;  l < lines.length; l++) {
                    String line = lines[l];
                    String[] values = line.split(",");
                    for(int i = 0; i < values.length; i++) {
                        StringBuilder scrambledRawTrigram = new StringBuilder(Integer.toString(Integer.parseInt(values[i], 10), 5));
                        while(scrambledRawTrigram.length() < 3) {
                            scrambledRawTrigram.insert(0, "0");
                        }

                        String rawTrigram = selectedMode.convertToRaw(scrambledRawTrigram.charAt(0), scrambledRawTrigram.charAt(1), scrambledRawTrigram.charAt(2));
                        String abcTrigram = i % 2 == 0 ? rawTrigram : EyeData.reverseString(rawTrigram);

                        char[] trigramparts = abcTrigram.trim().toCharArray();
                        for(int v = 0; v < trigramparts.length; v++) {
                            rawLine.append(trigramparts[v]);
                        }
                    }
                    char[] rawlinecharacters = rawLine.toString().toCharArray();
                    for (int i = 0; i < rawlinecharacters.length; i++) {
                        if(i % 2 == 0) {
                            rawLine1.append(String.valueOf(rawlinecharacters[i]));
                        } else {
                            rawLine2.append(String.valueOf(rawlinecharacters[i]));
                        }
                    }
                    rawMessageLines[l * 2] = rawLine1.toString();
                    rawMessageLines[1 + (l * 2)] = rawLine2.toString();
                    rawLine = new StringBuilder();
                    rawLine1 = new StringBuilder();
                    rawLine2 = new StringBuilder();
                }
                rawMsg = rawMessageLines;
            }
            case rawTrigram -> {
                String[] lines = primaryData.split("\n");
                String[] rawMessageLines = new String[lines.length*2];
                StringBuilder rawLine = new StringBuilder();
                StringBuilder rawLine1 = new StringBuilder();
                StringBuilder rawLine2 = new StringBuilder();

                for(int l = 0;  l < lines.length; l++) {
                    String line = lines[l];
                    String[] values = line.split(",");
                    for(int i = 0; i < values.length; i++) {
                        StringBuilder scrambledRawTrigram = new StringBuilder(values[i]);
                        while(scrambledRawTrigram.length() < 3) {
                            scrambledRawTrigram.insert(0, "0");
                        }

                        String rawTrigram = selectedMode.convertToRaw(scrambledRawTrigram.charAt(0), scrambledRawTrigram.charAt(1), scrambledRawTrigram.charAt(2));
                        String abcTrigram = i % 2 == 0 ? rawTrigram : EyeData.reverseString(rawTrigram);

                        char[] trigramparts = abcTrigram.trim().toCharArray();
                        for(int v = 0; v < trigramparts.length; v++) {
                            rawLine.append(trigramparts[v]);
                        }
                    }
                    char[] rawlinecharacters = rawLine.toString().toCharArray();
                    for (int i = 0; i < rawlinecharacters.length; i++) {
                        if(i % 2 == 0) {
                            rawLine1.append(String.valueOf(rawlinecharacters[i]));
                        } else {
                            rawLine2.append(String.valueOf(rawlinecharacters[i]));
                        }
                    }
                    rawMessageLines[l * 2] = rawLine1.toString();
                    rawMessageLines[1 + (l * 2)] = rawLine2.toString();
                    rawLine = new StringBuilder();
                    rawLine1 = new StringBuilder();
                    rawLine2 = new StringBuilder();
                }
                rawMsg = rawMessageLines;
            }
            default -> {
                rawMsg = primaryData.replaceAll(",", "").split("\n");
            }
        }
        return new EyeData("custom", rawMsg);
    }

    public static String convertDecimalToAscii(String primaryData) {
        StringBuilder newData = new StringBuilder();

        String[] lines = primaryData.split("\n");
        for(int i = 0; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            for (String value : values) {
                newData.append((char) (Integer.parseInt(value) + 32));
            }
            newData.append("\n");
        }

        return newData.toString();
    }

    private String convertAsciiToDecimal(String primaryData) {
        StringBuilder newData = new StringBuilder();

        String[] lines = primaryData.split("\n");
        for(int i = 0; i < lines.length; i++) {
            char[] values = lines[i].toCharArray();
            for (char value : values) {
                newData.append(Character.valueOf(value) - 32);
                newData.append(",");    // Needed to ensure proper decoding later.
            }
            newData.append("\n");
        }

        return newData.toString();
    }

    @FXML
    public void onPressedBack(){
        ApplicationControl.setMode(ApplicationMode.selection);
    }

    @FXML
    public void updateDataAForm(){
        selectedMessageData = parseTextData(dataA.getText(), dataAform);
        btnSelectedEyeData.setText("Message: MODIFIED");
        if(btnLinkFields.isSelected()) {
            switch (dataBform){
                case rawTrigram -> dataB.setText(selectedMessageData.getRawTrigramData());
                case rawMsg -> dataB.setText(selectedMessageData.getRawEyeData());
                case trigramValue -> dataB.setText(selectedMessageData.getTrigramValues());
                case trigramAscii -> dataB.setText(convertDecimalToAscii(selectedMessageData.getTrigramValues()));
            };
        }
        updateEyeDisplay();
    }

    @FXML
    public void updateDataBForm(){
        selectedMessageData = parseTextData(dataB.getText(), dataBform);
        btnSelectedEyeData.setText("Message: MODIFIED");
        if(btnLinkFields.isSelected()) {
            switch (dataAform){
                case rawTrigram -> dataA.setText(selectedMessageData.getRawTrigramData());
                case rawMsg -> dataA.setText(selectedMessageData.getRawEyeData());
                case trigramValue -> dataA.setText(selectedMessageData.getTrigramValues());
                case trigramAscii -> dataA.setText(convertDecimalToAscii(selectedMessageData.getTrigramValues()));
            };
        }
        updateEyeDisplay();
    }

    enum DataForm {
        trigramValue,
        rawTrigram,
        rawMsg,
        trigramAscii
    }


    private void updateEyeDisplay(){
        eyeDisplay.getChildren().clear();

        String[] lines = selectedMessageData.getRawEyeData().split("\n");
        for (int i = 0; i < lines.length; i++) {
            char[] values = lines[i].toCharArray();
            for(int j = 0; j < values.length; j++) {
                ImageView view = new ImageView(ApplicationControl.getEyeImage(values[j]));
                int xoffset = (j * 12) + (i % 2 == 1 ? 12 : 0);
                //view.setLayoutX(posX);
                //view.setLayoutY(posY);
                view.setX(xoffset);
                view.setY(i * 14);
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-1.0);
                view.setEffect(colorAdjust);
                eyeDisplay.getChildren().add(view);
            }
        }
    }
}
