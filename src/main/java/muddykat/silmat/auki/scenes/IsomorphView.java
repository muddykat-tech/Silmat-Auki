package muddykat.silmat.auki.scenes;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import muddykat.silmat.auki.ApplicationControl;
import muddykat.silmat.auki.eyes.EyeMessages;
import muddykat.silmat.auki.utility.ApplicationMode;

import java.util.Arrays;
import java.util.Objects;

public class IsomorphView {
    @FXML
    ScrollPane gridScrollPane;

    @FXML
    MenuButton btnDataType;

    GridPane gridEyeData = new GridPane();

    private CheckMenuItem activeSelection;

    private TrigramView.DataForm dataForm = TrigramView.DataForm.trigramValue;

    public void initialize(){
        createGrid();

        for (TrigramView.DataForm data : TrigramView.DataForm.values()) {
            CheckMenuItem item = new CheckMenuItem(data.name());
            item.setOnAction(e -> {
                activeSelection = item;
                dataForm = data;
                fillGrid();
            });
            btnDataType.getItems().add(item);
        }

        btnDataType.setOnHiding(event -> {
            btnDataType.getItems().forEach(item -> {
                if(item instanceof CheckMenuItem checkItem){
                    if(!checkItem.equals(activeSelection)){
                        checkItem.setSelected(false);
                    }
                }
            });
        });

        fillGrid();
    }

    private void fillGrid() {
        gridEyeData.getChildren().clear();
        Arrays.stream(EyeMessages.values()).sequential().forEach((message) -> {
            switch (dataForm) {
                case rawMsg -> {
                    String[] rawData = message.getData().getRawEyeData().replaceAll("\n", "").split(",");
                    for (int i = 0; i < rawData.length; i++) {
                        String value = rawData[i].trim();
                        Text label = new Text(value);
                        gridEyeData.add(label, i, message.ordinal());
                    }
                }
                case trigramValue -> {
                    String[] trigramValues = message.getData().getTrigramValues().split(",");
                    for (int i = 0; i < trigramValues.length; i++) {
                        String value = trigramValues[i].trim();
                        gridEyeData.add(new Text(value), i, message.ordinal());
                    }
                }
                case rawTrigram -> {
                    String[] trigramValues = message.getData().getRawTrigramData().split(",");
                    for (int i = 0; i < trigramValues.length; i++) {
                        String value = trigramValues[i].trim();
                        gridEyeData.add(new Text(value), i, message.ordinal());
                    }
                }
                case trigramAscii -> {
                    String[] trigramValues = message.getData().getTrigramValues().split(",");
                    for (int i = 0; i < trigramValues.length; i++) {
                        String value = trigramValues[i].trim();
                        gridEyeData.add(new Text(TrigramView.convertDecimalToAscii(value)), i, message.ordinal());
                    }
                }
            }
        });
    }

    @FXML
    public void onPressedBack(){
        ApplicationControl.setMode(ApplicationMode.selection);
    }

    private void resizeGrid(){
        gridEyeData.getColumnConstraints().clear();
        int gridSize = gridEyeData.getColumnCount();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.NEVER);
        columnConstraints.setMaxWidth(30);
        columnConstraints.setMinWidth(30);
        columnConstraints.setHalignment(HPos.CENTER);
        for (int i = 0; i < gridSize; i++) {
            gridEyeData.getColumnConstraints().add(columnConstraints);
        }
    }

    private void createGrid(){
        // create new constraints for columns and set their percentage
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.NEVER);
        columnConstraints.setMaxWidth(30);
        columnConstraints.setMinWidth(30);
        columnConstraints.setHalignment(HPos.CENTER);

        // create new constraints for row and set their percentage
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.NEVER);
        rowConstraints.setMaxHeight(30);
        rowConstraints.setMinHeight(30);
        rowConstraints.setValignment(VPos.CENTER);

        // don't set preferred size or anything on gridpane
        for(int i = 0; i < 10; i++) {
            gridEyeData.getRowConstraints().add(rowConstraints);
        }

        for(int i = 0; i < 150; i++) {
            gridEyeData.getColumnConstraints().add(columnConstraints);
        }

        // suppose your scroll pane id is scrollPane
        gridScrollPane.setContent(gridEyeData);
    }
}
