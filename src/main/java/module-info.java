module org.maps {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.maps to javafx.fxml;
    exports org.maps;
}