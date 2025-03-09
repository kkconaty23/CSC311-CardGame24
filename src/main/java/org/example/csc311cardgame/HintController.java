package org.example.csc311cardgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HintController {
    @FXML
    private Label hintLabel;

    public void setHintText(String hint) {
        hintLabel.setText(hint);
    }

    @FXML
    void closeWindow() {
        Stage stage = (Stage) hintLabel.getScene().getWindow();
        stage.close();
    }
}
