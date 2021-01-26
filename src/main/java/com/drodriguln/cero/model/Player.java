package com.drodriguln.cero.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Player {
    private String activity;
    private List<Card> cards;

    public Player() {
        this.activity = "initialize";
        this.cards = new ArrayList<>();
    }

    public void draw(Deck deck) {
        this.cards.add(deck.draw());
    }

    public void firstDraw(Deck deck) {
        for (int i = 1; i <= 7; i++) {
            this.draw(deck);
        }
    }
}
