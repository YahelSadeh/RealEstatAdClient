module org.example.realestateadsclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens org.example.realestateadsclient to javafx.fxml;
    opens org.example.realestateadsclient.model to com.google.gson;
    exports org.example.realestateadsclient;
    exports org.example.realestateadsclient.model;
    exports org.example.realestateadsclient.windows;
    opens org.example.realestateadsclient.windows to javafx.fxml;
    exports org.example.realestateadsclient.server;
    opens org.example.realestateadsclient.server to javafx.fxml;

}