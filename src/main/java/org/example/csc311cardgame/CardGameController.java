package org.example.csc311cardgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.script.ScriptException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.csc311cardgame.ExpressionEvaluator.evaluate;

public class CardGameController {

    @FXML
    public Label hintLabel;
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
    private List<Integer> currentCardValues;


    private Card leftMost;
    private Card rightMost;
    private Card leftMiddle;
    private Card rightMiddle;


    /**
     * initialize the game space
     */
    @FXML
    public void initialize() {
        cardMapping(); // makes sure card mapping is ready when the UI is loaded

        if (baseLayer != null) {
            baseLayer.setStyle("-fx-background-color: #228B22;");
        } else {
            System.out.println("Error: baseLayer is null!");
        }


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

    /**
     * hint button will provide player with a hint for the cards shown
     * @param event
     */
    @FXML
    void onHintButtonClick(ActionEvent event) {
        try {
            //make the hint string from the card values
            String hint = generateSolutionFormat(currentCardValues);

            // load  hint window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/csc311cardgame/hints.fxml"));
            Parent root = loader.load();

            // set hint text
            HintController hintController = loader.getController();
            hintController.setHintText(hint);

            //show new window
            Stage stage = new Stage();
            stage.setTitle("Hint");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * finds an equation that results in 24 and returns its format with placeholders
     */
    private String generateSolutionFormat(List<Integer> cardValues) { //takes in the 4 present card valuers
        if (cardValues.size() != 4) {
            return "Hint: A valid solution requires exactly 4 numbers.";
        }

        List<String> operators = Arrays.asList("+", "-", "*", "/"); //put the operators in a list
        List<int[]> permutations = generatePermutations(cardValues);
        List<String[]> operatorCombinations = generateOperatorCombinations();

        // mix different number orders and operator combinations
        for (int[] numbers : permutations) {
            for (String[] ops : operatorCombinations) {
                String equation = "(" + numbers[0] + ops[0] + numbers[1] + ")" + ops[1] + numbers[2] + ops[2] + numbers[3];

                if (evaluate(equation) == 24) {
                    // replace numbers with underscores for the hint format
                    String hint = equation.replaceAll("\\d+", "_"); // \\d+ replaces all numbers with _
                    return "Hint: " + hint;
                }
            }
        }

        return "Hint: No solution found."; //no possible solution returns
    }

    private List<int[]> generatePermutations(List<Integer> cardValues) {
        List<int[]> permutations = new ArrayList<>();
        Integer[] arr = cardValues.toArray(new Integer[0]);
        permute(arr, 0, permutations);
        return permutations;
    }

    private void permute(Integer[] arr, int index, List<int[]> permutations) {
        if (index == arr.length) {
            permutations.add(Arrays.stream(arr).mapToInt(Integer::intValue).toArray());
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            permute(arr, index + 1, permutations);
            swap(arr, i, index);
        }
    }

    private void swap(Integer[] arr, int i, int j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Generates all possible operator combinations.
     */
    private List<String[]> generateOperatorCombinations() {
        List<String[]> combinations = new ArrayList<>();
        String[] operators = {"+", "-", "*", "/"};
        for (String op1 : operators) {
            for (String op2 : operators) {
                for (String op3 : operators) {
                    combinations.add(new String[]{op1, op2, op3});
                }
            }
        }
        return combinations;
    }





    @FXML
    void verifyButtonClick(ActionEvent event) throws ScriptException {

        String input = playerEntry.getText().trim();//get rid of any unnecessary white space

        if (input.isEmpty()) {
            checkText.setText("No equation entered");
            return;
        }

        // Extract numbers from input
        List<Integer> inputValues = Arrays.stream(input.split("[^0-9]+")) // splits by non-numeric characters
                .filter(s -> !s.isEmpty()) // remove empty strings
                .map(Integer::parseInt) // convert to int
                .collect(Collectors.toList());

        //  all input values are not from the displayed cards show error
        if (!new HashSet<>(currentCardValues).containsAll(inputValues)) {
            checkText.setText("Use only displayed card values!");
            return;
        }

        // Evaluate the expression
        double result = evaluate(input);
        if (result == 24) {
            checkText.setText("Correct!");
        } else {
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

        currentCardValues = Arrays.asList(leftMost.getValue(), leftMiddle.getValue(), rightMiddle.getValue(), rightMost.getValue());
        System.out.println("Selected Card Values: " + currentCardValues);
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


    public void closeWindow(ActionEvent actionEvent) {

    }
}