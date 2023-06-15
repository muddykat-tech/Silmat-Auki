module silmat.muddykat.auki {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    exports muddykat.silmat.auki;
    opens muddykat.silmat.auki.scenes to javafx.fxml;
    exports muddykat.silmat.auki.scenes;
}