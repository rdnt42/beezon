module com.beezon.code_handler {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    requires com.dlsc.formsfx;
    requires io.nayuki.qrcodegen;
    requires java.desktop;

    opens com.beezon.code_handler to javafx.fxml;
    exports com.beezon.code_handler;
    exports com.beezon.code_handler.controller;
    opens com.beezon.code_handler.controller to javafx.fxml;
}