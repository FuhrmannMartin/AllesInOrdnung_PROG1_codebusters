module group.two.allesinordnung {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens group.two.allesinordnung to javafx.fxml;
    exports group.two.allesinordnung;
}