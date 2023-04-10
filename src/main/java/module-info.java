module inventory.magnt {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    requires java.desktop;

    opens gui.mainform to javafx.fxml, javafx.graphics;
    opens gui.controllers to javafx.fxml, javafx.graphics;
}