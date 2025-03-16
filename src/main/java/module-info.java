module eus.ehu.bummer4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires okhttp3;

    opens eus.ehu.bummer4 to javafx.fxml;
    exports eus.ehu.bummer4;

    opens eus.ehu.bummer4.domain to com.google.gson;
}