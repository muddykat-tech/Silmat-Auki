package muddykat.silmat.auki.modules;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import muddykat.silmat.auki.util.VigenereCipher;

import java.util.ArrayList;

public class CipherModule {

    private static Ciphers selectedCipher = Ciphers.vingenere;
    public void initializeButtons(TextArea inputText, MenuButton btnCipher, TextArea outputTextPane, TextField key, Button btnEncrypt, Button btnDecrypt){
        ObservableList<MenuItem> cipherOptions = btnCipher.getItems();
        ToggleGroup group = new ToggleGroup();
        for (Ciphers c : Ciphers.values()) {
            RadioMenuItem item = new RadioMenuItem(c.name());
            item.setToggleGroup(group);
            item.setOnAction(event -> {
                selectedCipher = c;
                key.setDisable(!c.requiresKey);

            });
            cipherOptions.add(item);
        }

        btnDecrypt.setOnAction(event -> {
            switch(selectedCipher){
                case vingenere -> {
                    String input = inputText.getText();
                    outputTextPane.setText(VigenereCipher.decrypt(input, key.getText()));
                }
                case test_case -> {
                    //Testing
                    System.out.println("Testing");
                }
            }
        });

        btnEncrypt.setOnAction(event -> {
            switch(selectedCipher){
                case vingenere -> {
                    String input = inputText.getText();
                    outputTextPane.setText(VigenereCipher.encrypt(input, key.getText()));
                }
                case test_case -> {
                    //Testing
                    System.out.println("Testing");
                }
            }
        });

    }

}
enum Ciphers {
    vingenere(true),
    test_case(false);

    final boolean requiresKey;
    Ciphers(boolean keyRequired){
        requiresKey = keyRequired;
    }
}
