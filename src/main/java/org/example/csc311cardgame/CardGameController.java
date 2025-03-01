package org.example.csc311cardgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class CardGameController {

    @FXML
    private ImageView leftMiddleCard;

    @FXML
    private ImageView leftMostCard;

    @FXML
    private TextField playerEntry;

    @FXML
    private Button refreshBtn;

    @FXML
    private ImageView rightMiddleCard;

    @FXML
    private ImageView rightMostCard;

    @FXML
    private TextField solution;

    String [] imagePath = {
            "ace_of_clubs.png", "ace_of_diamonds.png", "ace_of_hearts.png", "ace_of_spades.png",
            "2_of_clubs.png", "2_of_diamonds.png", "2_of_hearts.png", "2_of_spades.png",
            "3_of_clubs.png", "3_of_diamonds.png", "3_of_hearts.png", "3_of_spades.png",
            "4_of_clubs.png", "4_of_diamonds.png", "4_of_hearts.png", "4_of_spades.png",
            "5_of_clubs.png", "5_of_diamonds.png", "5_of_hearts.png", "5_of_spades.png",
            "6_of_clubs.png", "6_of_diamonds.png", "6_of_hearts.png", "6_of_spades.png",
            "7_of_clubs.png", "7_of_diamonds.png", "7_of_hearts.png", "7_of_spades.png",
            "8_of_clubs.png", "8_of_diamonds.png", "8_of_hearts.png", "8_of_spades.png",
            "9_of_clubs.png", "9_of_diamonds.png", "9_of_hearts.png", "9_of_spades.png",
            "10_of_clubs.png", "10_of_diamonds.png", "10_of_hearts.png", "10_of_spades.png",
            "jack_of_clubs.png", "jack_of_diamonds.png", "jack_of_hearts.png", "jack_of_spades.png",
            "queen_of_clubs.png", "queen_of_diamonds.png", "queen_of_hearts.png", "queen_of_spades.png",
            "king_of_clubs.png", "king_of_diamonds.png", "king_of_hearts.png", "king_of_spades.png"
    };

    @FXML
    void refreshButtonClick(ActionEvent event) {
        leftMostCard.setImage(getImage());
    }

    private Image getImage() {
        int index = (int) (Math.random() * imagePath.length);

        // Construct the correct resource path
        String imagePath = "/images/" + this.imagePath[index];

        // Debugging: Print the path
        System.out.println("Loading image from: " + imagePath);

        // Load image from classpath
        InputStream stream = getClass().getResourceAsStream(imagePath);
        if (stream == null) {
            System.out.println("Error: Image not found at " + imagePath);
        }

        return new Image(stream);
    }


}