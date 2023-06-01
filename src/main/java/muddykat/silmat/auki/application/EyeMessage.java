package muddykat.silmat.auki.application;

import javafx.beans.binding.Bindings;
import javafx.scene.CacheHint;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import muddykat.silmat.auki.EyeApplication;
import muddykat.silmat.auki.EyeController;
import muddykat.silmat.auki.util.EyeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static muddykat.silmat.auki.util.EyeUtil.getResource;

public class EyeMessage {
    private final ArrayList<ArrayList<Integer>> data;

    public EyeMessage(String stringData){
        this.data = new ArrayList<ArrayList<Integer>>();

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

    public void setDisplayPane(AnchorPane displayPane, double posX, double posY, ToggleButton trigrams) {
        for (int lineIndex = 0; lineIndex < this.data.size(); lineIndex++) {
            ArrayList<Integer> line = this.data.get(lineIndex);
            for (int symbolIndex = 0; symbolIndex < line.size(); symbolIndex++) {
                Integer symbol = line.get(symbolIndex);
                ImageView view = new ImageView(EyeUtil.getImageFromIndex(symbol));



                int xoffset = (symbolIndex * 24) + (lineIndex % 2 == 1 ? 12 : 0);

                view.setLayoutX(posX);
                view.setLayoutY(posY);
                view.setX(xoffset);
                view.setY(lineIndex * 16);
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-1.0);
                if(trigrams.isSelected()) {
                    colorAdjust.setBrightness(0);
                    colorAdjust.setHue(Color.RED.getHue()) ;
                    colorAdjust.setSaturation(Color.RED.getSaturation());
                }

                view.setEffect(colorAdjust);
                displayPane.getChildren().add(view);

                if(trigrams.isSelected()) {
//                    if (symbolIndex < line.size() * 0.66) {
//                        if (lineIndex < (this.data.size() * 0.5)) {
//                            ImageView trigram = new ImageView(new Image(getResource(EyeApplication.class, "util/trigram-selector.png")));
//                            trigram.setLayoutX(posX);
//                            trigram.setLayoutY(posY);
//                            trigram.setX(12 + (symbolIndex * 23) + (symbolIndex * 13));
//                            trigram.setY(8 + lineIndex * 32);
//                            trigram.setScaleX(2);
//                            trigram.setScaleY(symbolIndex % 2 == 1 ? 2 : -2);
//
//                            displayPane.getChildren().add(trigram);
//                        }
//                    }
                }
            }
        }
    }
}