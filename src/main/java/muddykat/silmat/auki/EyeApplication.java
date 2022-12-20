package muddykat.silmat.auki;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import muddykat.silmat.auki.util.EyeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static muddykat.silmat.auki.util.EyeUtil.getResource;
public class EyeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        initialize(stage);
        InputStream iconIS = getResource(getClass(), "icon.png");
        Image image = new Image(iconIS);
        stage.getIcons().add(image);
        stage.show();
    }

    private void initialize(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        InputStream fxmlStream = getResource(getClass(), "EyeRoom.fxml");
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Silmat Auki - V0.2.1");
    }
    public static void main(String[] args) {
        launch();
    }



}