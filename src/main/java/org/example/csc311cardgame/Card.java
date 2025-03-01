package org.example.csc311cardgame;

import javafx.scene.image.Image;

public class Card {
    private Image card;
    private int value;

    public Card(Image card, int value) {
        this.card = card;
        this.value = value;

    }
    public Image getCard() {
        return card;
    }
    public int getValue() {
        return value;
    }
}
