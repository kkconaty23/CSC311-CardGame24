module org.example.csc311cardgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.csc311cardgame to javafx.fxml;
    exports org.example.csc311cardgame;
}