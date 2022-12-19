package muddykat.silmat.auki.modules;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import muddykat.silmat.auki.util.VigenereCipher;

import java.util.ArrayList;

public class CipherModule {

    public void initializeButtons(TextArea inputText, MenuButton btnCipher, TextArea outputTextPane, TextField key){
        ObservableList<MenuItem> cipherOptions = btnCipher.getItems();
        for (Ciphers c : Ciphers.values()) {
            MenuItem item = new MenuItem(c.name());
            item.setOnAction(event -> {
                String input = inputText.getText();
                outputTextPane.setText(VigenereCipher.decrypt(input, key.getText()));
            });

            cipherOptions.add(item);
        }
    }

}
enum Ciphers {
    vingenere;

    Ciphers(){

    }
}
