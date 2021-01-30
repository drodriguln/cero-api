package com.drodriguln.cero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    public enum Color {
        BLUE,
        GREEN,
        RED,
        YELLOW,
    }

    public enum Value {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        REVERSE,
        SKIP,
    }

    private String id;
    private Color color;
    private Value value;

    public Card(Color color, Value value) {
        this.id = UUID.randomUUID().toString();
        this.color = color;
        this.value = value;
    }

    @JsonIgnore
    public boolean isSkipValue() {
        return Value.SKIP.equals(this.value) || Value.REVERSE.equals(this.value);
    }

    public boolean matches(Card card) {
        return this.value.equals(card.getValue()) || this.color.equals(card.getColor());
    }
}