module org.example.csc311cardgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires org.json;
    requires java.scripting;
    requires org.graalvm.sdk;
    requires exp4j;


    opens org.example.csc311cardgame to javafx.fxml;
    exports org.example.csc311cardgame;
}