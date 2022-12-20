package muddykat.silmat.auki.application;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import muddykat.silmat.auki.util.EyeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class EyeMessage {
    private final ArrayList<ArrayList<Integer>> data;

    public EyeMessage(String stringData){
        this.data = new ArrayList<ArrayList<Integer>>();
        if(!stringData.matches("^[0-5]+$")){
            System.out.println("Data does not match format of integer's ranging from 0-5");
            return;
        }
        ArrayList<Integer> quickList = new ArrayList<>();
        for (Character c : stringData.toCharArray()) {
            if(c.equals('5')){
                ArrayList<Integer> dataLine = new ArrayList<Integer>(quickList);
                this.data.add(dataLine);
                quickList.clear();
            } else {
                quickList.add(Character.getNumericValue(c));
            }
        }
    }

    public String getRawString() {
        StringBuilder builder = new StringBuilder();

        for (ArrayList<Integer> line : this.data) {
            builder.append(line.toString() + "\n");
        }

        return builder.toString();
    }

    public void setDisplayPane(AnchorPane testPane) {

        for (int lineIndex = 0; lineIndex < this.data.size(); lineIndex++) {
            ArrayList<Integer> line = this.data.get(lineIndex);
            for (int symbolIndex = 0; symbolIndex < line.size(); symbolIndex++) {
                Integer symbol = line.get(symbolIndex);
                ImageView view = new ImageView(EyeUtil.getImageFromIndex(symbol));
                view.setX((symbolIndex * 24) + (lineIndex % 2 == 1 ? 12 : 0));
                view.setY(lineIndex * 16);
                testPane.getChildren().add(view);
            }
        }
    }
}