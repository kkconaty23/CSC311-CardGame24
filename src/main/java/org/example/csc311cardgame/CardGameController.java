package org.example.csc311cardgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.script.ScriptException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.csc311cardgame.ExpressionEvaluator.evaluate;

public class CardGameController {

    @FXML
    private GridPane baseLayer;

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

    @FXML
    private Button hintButton;

    @FXML
    private Button verifyButton;

    @FXML
    private Text checkText;



    @FXML
    public void initialize() {
        cardMapping(); // makes sure card mapping is ready when the UI is loaded
        baseLayer.setStyle("-fx-background-color: #228B22;");


    }

    //array of all the card images
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
    void onHintButtonClick(ActionEvent event) {


    }

    @FXML
    void verifyButtonClick(ActionEvent event) throws ScriptException {

        if(playerEntry.getText().equals("") || playerEntry.getText() == null) {
            checkText.setText("No equation");
        }


        //once verify button is clicked it evaluates the player input and checks if it equals 24 using xp4j
        double result = evaluate(playerEntry.getText());
        if(result == 24){
            checkText.setText("Correct!");
        }
        else{
            checkText.setText("Incorrect!");
        }
    }




    @FXML
    void refreshButtonClick(ActionEvent event) {
        //chooses random image from inside the array of images and assigns it
        Card leftMost = chooseImage();
        Card leftMiddle = chooseImage();
        Card rightMiddle = chooseImage();
        Card rightMost = chooseImage();

        //setting the images to which ever random card was generated
        if (leftMost != null) leftMostCard.setImage(leftMost.getCard());
        if (leftMiddle != null) leftMiddleCard.setImage(leftMiddle.getCard());
        if (rightMiddle != null) rightMiddleCard.setImage(rightMiddle.getCard());
        if (rightMost != null) rightMostCard.setImage(rightMost.getCard());

        // print assigned values
        System.out.println("Left Most Card Value: " + leftMost.getValue());
        System.out.println("Left Middle Card Value: " + leftMiddle.getValue());
        System.out.println("Right Middle Card Value: " + rightMiddle.getValue());
        System.out.println("Right Most Card Value: " + rightMost.getValue());
        System.out.println();



    }

    private Card chooseImage() {
        int index = (int) (Math.random() * imagePath.length); // generate random index

        String imagePath = "/card_images/" + this.imagePath[index]; // set image path
        String imageFileName = this.imagePath[index];//just the image name used for mapping to values


        InputStream stream = getClass().getResourceAsStream(imagePath); // load image
        if (stream == null) {//error detection
            System.out.println("Error: Image not found at " + imagePath);
            return null; // handle errors
        }



        Image image = new Image(stream); // create image
        int cardValue = cardValues.get(imageFileName); //gets card value from the map

        return new Card(image, cardValue); //makes card object from random selection
    }

    //map for all cards and their values
    private final Map<String, Integer> cardValues = new HashMap<>();

    public void cardMapping() {
        cardValues.put("ace_of_spades.png", 1);
        cardValues.put("ace_of_clubs.png", 1);
        cardValues.put("ace_of_hearts.png", 1);
        cardValues.put("ace_of_diamonds.png", 1);

        cardValues.put("2_of_spades.png", 2);
        cardValues.put("2_of_clubs.png", 2);
        cardValues.put("2_of_hearts.png", 2);
        cardValues.put("2_of_diamonds.png", 2);

        cardValues.put("3_of_spades.png", 3);
        cardValues.put("3_of_clubs.png", 3);
        cardValues.put("3_of_hearts.png", 3);
        cardValues.put("3_of_diamonds.png", 3);

        cardValues.put("4_of_spades.png", 4);
        cardValues.put("4_of_clubs.png", 4);
        cardValues.put("4_of_hearts.png", 4);
        cardValues.put("4_of_diamonds.png", 4);

        cardValues.put("5_of_spades.png", 5);
        cardValues.put("5_of_clubs.png", 5);
        cardValues.put("5_of_hearts.png", 5);
        cardValues.put("5_of_diamonds.png", 5);

        cardValues.put("6_of_spades.png", 6);
        cardValues.put("6_of_clubs.png", 6);
        cardValues.put("6_of_hearts.png", 6);
        cardValues.put("6_of_diamonds.png", 6);

        cardValues.put("7_of_spades.png", 7);
        cardValues.put("7_of_clubs.png", 7);
        cardValues.put("7_of_hearts.png", 7);
        cardValues.put("7_of_diamonds.png", 7);

        cardValues.put("8_of_spades.png", 8);
        cardValues.put("8_of_clubs.png", 8);
        cardValues.put("8_of_hearts.png", 8);
        cardValues.put("8_of_diamonds.png", 8);

        cardValues.put("9_of_spades.png", 9);
        cardValues.put("9_of_clubs.png", 9);
        cardValues.put("9_of_hearts.png", 9);
        cardValues.put("9_of_diamonds.png", 9);

        cardValues.put("10_of_spades.png", 10);
        cardValues.put("10_of_clubs.png", 10);
        cardValues.put("10_of_hearts.png", 10);
        cardValues.put("10_of_diamonds.png", 10);

        cardValues.put("jack_of_spades.png", 11);
        cardValues.put("jack_of_clubs.png", 11);
        cardValues.put("jack_of_hearts.png", 11);
        cardValues.put("jack_of_diamonds.png", 11);

        cardValues.put("queen_of_spades.png", 12);
        cardValues.put("queen_of_clubs.png", 12);
        cardValues.put("queen_of_hearts.png", 12);
        cardValues.put("queen_of_diamonds.png", 12);

        cardValues.put("king_of_spades.png", 13);
        cardValues.put("king_of_clubs.png", 13);
        cardValues.put("king_of_hearts.png", 13);
        cardValues.put("king_of_diamonds.png", 13);
    }



}