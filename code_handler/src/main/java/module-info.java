module com.beezon.code_handler {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.beezon.code_handler to javafx.fxml;
    exports com.beezon.code_handler;
}