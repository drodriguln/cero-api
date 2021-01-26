package com.drodriguln.cero.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

@Data
@AllArgsConstructor
public class Discard {
    private LinkedList<Card> cards;

    public Discard() {
        this.cards = new LinkedList<>();
    }

    public void place(Card card) {
        this.cards.add(card);
    }
}
