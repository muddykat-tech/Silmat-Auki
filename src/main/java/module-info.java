module muddykat.silmat.auki {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;

    opens muddykat.silmat.auki to javafx.fxml;
    exports muddykat.silmat.auki;
    opens muddykat.silmat.auki.modules to javafx.fxml;
}