package com.drodriguln.cero.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class Player {
    public enum Status {
        INITIALIZE,
        START,
        END,
        WON,
    }

    private Status status;
    private List<Card> cards;

    public Player() {
        this.status = Status.INITIALIZE;
        this.cards = new ArrayList<>();
    }

    public Status getStatus() {
        if (this.cards.size() == 0) {
            this.setStatus(Status.WON);
        }
        return this.status;
    }

    public void draw(Deck deck) {
        this.cards.add(deck.draw());
    }

    public void firstDraw(Deck deck) {
        for (int i = 1; i <= 7; i++) {
            this.draw(deck);
        }
    }

    public void remove(Card card) {
        this.cards.remove(card);
    }

    public void discard(Discard discard, Card card) {
        discard.place(card);
        this.remove(card);
    }

    public Optional<Card> findMatch(Card card) {
        return cards.stream()
            .filter((c) -> c.matches(card))
            .findAny();
    }

    public void startTurn() {
        this.status = Status.START;
    }

    public void endTurn() {
        this.status = Status.END;
    }
}
