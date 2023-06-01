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

import java.util.*;

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

                if(trigrams.isSelected()) {
                    if (symbolIndex < line.size() * 0.66) {
                        if (lineIndex < (this.data.size() * 0.5)) {
                            ImageView trigram = new ImageView(new Image(getResource(EyeApplication.class, "util/trigram-selector.png")));
                            trigram.setLayoutX(posX);
                            trigram.setLayoutY(posY);
                            trigram.setX(2 + (symbolIndex * 23) + (symbolIndex * 13));
                            trigram.setY(2 + lineIndex * 32);
                            trigram.setScaleX(1);
                            trigram.setScaleY(symbolIndex % 2 == 1 ? 1 : -1);
                            ColorAdjust colorAdjust = new ColorAdjust();
                            ArrayList<Color> colors = new ArrayList<>();
                            colors.add(Color.RED);
                            colors.add(Color.BLUE);
                            colors.add(Color.GREEN);
                            colors.add(Color.YELLOW);
                            colors.add(Color.DARKORANGE);
                            colors.add(Color.AQUA);
                            Color color = colors.get((symbolIndex + lineIndex) % colors.size());
                            colorAdjust.setHue(color.getHue() / 180);
                            colorAdjust.setSaturation(-1 + (color.getSaturation() * 2));

                            trigram.setEffect(colorAdjust);

                            displayPane.getChildren().add(trigram);
                        }
                    }
                }

                ImageView view = new ImageView(EyeUtil.getImageFromIndex(symbol));
                int xoffset = (symbolIndex * 24) + (lineIndex % 2 == 1 ? 12 : 0);
                view.setLayoutX(posX);
                view.setLayoutY(posY);
                view.setX(xoffset);
                view.setY(lineIndex * 16);
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-1.0);
                view.setEffect(colorAdjust);
                displayPane.getChildren().add(view);


            }
        }
    }
}