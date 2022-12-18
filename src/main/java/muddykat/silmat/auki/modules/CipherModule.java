package muddykat.silmat.auki.modules;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CipherModule {

    public void initializeButtons(TextArea inputText, MenuButton btnCipher, TextArea outputTextPane){
        btnCipher.setOnAction(event -> {
            String ascii = inputText.getText();

        });
    }
}
