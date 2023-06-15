package muddykat.silmat.auki.scenes;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import muddykat.silmat.auki.ApplicationControl;
import muddykat.silmat.auki.utility.ApplicationMode;

import static muddykat.silmat.auki.utility.AukiUtility.getResource;

public class SelectionView {

    @FXML
    public ImageView iconView;

    @FXML
    public VBox btnModeSelection;

    public void initialize(){
        iconView.setImage(new Image(getResource(ApplicationControl.class, "images/icon.png")));

        btnModeSelection.setSpacing(10.0);
        for (ApplicationMode mode : ApplicationMode.values()) {
            if(!mode.equals(ApplicationMode.selected)) {
                Button item = new Button(mode.name().substring(0,1).toUpperCase() + mode.name().substring(1) + " Analysis");
                item.setMinWidth(100);
                item.setPadding(new Insets(10,10,10,10));
                item.setOnAction(e -> {
                    ApplicationControl.setMode(mode);
                });
                btnModeSelection.getChildren().add(item);
            }
        }
    }

}