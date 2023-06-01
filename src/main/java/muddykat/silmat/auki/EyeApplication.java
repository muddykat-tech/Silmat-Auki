package muddykat.silmat.auki;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import muddykat.silmat.auki.util.EyeMode;
import muddykat.silmat.auki.util.EyeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static muddykat.silmat.auki.util.EyeUtil.getResource;
public class EyeApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        initialize(stage);
        InputStream iconIS = getResource(getClass(), "icon.png");
        Image image = new Image(iconIS);
        stage.getIcons().add(image);
        stage.show();
        primaryStage = stage;
    }

    public static Image trigramImage;
    private static Stage primaryStage;

    public static EyeMode selectedMode = EyeMode.BASE;
    private static final HashMap<EyeMode, String> streamHashMap = new HashMap<EyeMode, String>();

    private void initialize(Stage stage) {
        streamHashMap.put(EyeMode.BASE, "EyeRoom.fxml");
        streamHashMap.put(EyeMode.OPERATION, "EyeOperation.fxml");

        try {
            FXMLLoader loader = new FXMLLoader();
            InputStream fxmlStream = getResource(getClass(), streamHashMap.get(selectedMode));
            AnchorPane root = (AnchorPane) loader.load(fxmlStream);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Silmat Auki - V0.2.2");
        } catch (Exception ignored){

        }
    }

    public static void reloadScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        InputStream fxmlStream = getResource(EyeApplication.class, streamHashMap.get(selectedMode));
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Silmat Auki - V0.2.2");
    }

    public static void main(String[] args) {
        launch();
    }
}