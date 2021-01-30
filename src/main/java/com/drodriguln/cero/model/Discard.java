package com.drodriguln.cero.model;

import com.drodriguln.cero.error.OutOfCardsException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.LinkedList;

@Data
@AllArgsConstructor
public class Discard {
    private LinkedList<Card> cards;

    public Discard() {
        this.cards = new LinkedList<>();
    }

    public Card getTopCard() {
        if (this.cards.peekLast() == null) {
            throw new OutOfCardsException("No cards exist in the discard pile");
        }
        return this.cards.peekLast();
    }

    public void place(Card card) {
        this.cards.add(card);
    }

    public LinkedList<Card> purge() {
        Card topCard = this.getTopCard();
        LinkedList<Card> purgedCards = this.cards;
        this.cards = new LinkedList<>(Arrays.asList(topCard));
        return purgedCards;

    }

    public boolean willAccept(Card card) {
        return this.getTopCard().matches(card);
    }
}
