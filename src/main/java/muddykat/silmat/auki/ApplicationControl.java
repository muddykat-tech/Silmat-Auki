package muddykat.silmat.auki;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import muddykat.silmat.auki.utility.ApplicationMode;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

import static muddykat.silmat.auki.utility.AukiUtility.VERSION;
import static muddykat.silmat.auki.utility.AukiUtility.getResource;
public class ApplicationControl extends Application {

    static final HashMap<ApplicationMode, String> stageMap = new HashMap<>();
    public static HashMap<Character, Image> eyeImageMap = new HashMap<>();

    static Stage stage;
    static Image icon;
    static Image eye_0, eye_1, eye_2, eye_3, eye_4, eye_empty;

    public static void main(String[] args) {
        launch(args);
    }

    public static Image getEyeImage(char value) {
        return eyeImageMap.get(value);
    }


    public void start(Stage stage) {
        ApplicationControl.stage = stage;
        stage.setTitle("Silmat Auki - " + VERSION);
        stage.getIcons().add(ApplicationControl.icon);
        reload();
        stage.show();
    }

    public static void reload() {
        try {
            FXMLLoader loader = new FXMLLoader();
            InputStream fxmlStream = getResource(ApplicationControl.class, stageMap.get(ApplicationMode.selected));
            Pane root = loader.load(fxmlStream);
            Scene scene = new Scene(root, 1024, 640);
            scene.getStylesheets().add(Objects.requireNonNull(ApplicationControl.class.getResource("stylesheet/font.css")).toExternalForm());
            ApplicationControl.stage.setScene(scene);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static void setMode(ApplicationMode mode) {
        ApplicationMode.selected = mode;
        reload();
    }


    static {
        stageMap.put(ApplicationMode.selection, "scenes/selection-view.fxml");
        //stageMap.put(ApplicationMode.basic, "scenes/basic-view.fxml");
        stageMap.put(ApplicationMode.trigram, "scenes/trigram-view.fxml");

        icon = new Image(getResource(ApplicationControl.class, "images/icon.png"));
        eye_0 = new Image(getResource(ApplicationControl.class, "images/eyes/eye_0.png"));
        eye_1 = new Image(getResource(ApplicationControl.class, "images/eyes/eye_1.png"));
        eye_2 = new Image(getResource(ApplicationControl.class, "images/eyes/eye_2.png"));
        eye_3 = new Image(getResource(ApplicationControl.class, "images/eyes/eye_3.png"));
        eye_4 = new Image(getResource(ApplicationControl.class, "images/eyes/eye_4.png"));
        eye_empty = new Image(getResource(ApplicationControl.class, "images/eyes/eye_empty.png"));
        eyeImageMap.put('0', eye_0);
        eyeImageMap.put('1', eye_1);
        eyeImageMap.put('2', eye_2);
        eyeImageMap.put('3', eye_3);
        eyeImageMap.put('4', eye_4);
        eyeImageMap.put('6', eye_empty);
    }
}

