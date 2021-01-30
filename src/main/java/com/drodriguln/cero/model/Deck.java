package com.drodriguln.cero.model;

import com.drodriguln.cero.error.OutOfCardsException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class Deck {
    private LinkedList<Card> cards;

    public Deck() {
        this.createCards();
        this.shuffle();
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new OutOfCardsException("Cannot draw from the deck because it is empty");
        }
        return this.cards.pop();
    }

    public void add(Discard discard) {
        this.cards.addAll(discard.purge());
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    private void createCards() {
        this.cards = new LinkedList<>();

        for (int i = 0; i <= 3; i++) {
            Card.Color color = Card.Color.values()[i];
            this.cards.add(new Card(color, "0"));
            for (int j = 1; j <= 9; j++) {
                for (int k = 1; k <= 2; k++) {
                    this.cards.add(new Card(color, Integer.toString(j)));
                }
            }
            this.cards.add(new Card(color, "reverse"));
            this.cards.add(new Card(color, "skip"));
        }
    }
}
