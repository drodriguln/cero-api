package com.drodriguln.cero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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

    private String id;
    private String color;
    private String value;

    public Card(Color color, String value) {
        this.id = UUID.randomUUID().toString();
        this.color = color.toString().toLowerCase();
        this.value = value;
    }

    public boolean isSkipValue() {
        return "skip".equals(this.value) || "reverse".equals(this.value);
    }

    public boolean matches(Card card) {
        return this.value.equals(card.getValue()) || this.color.equals(card.getColor());
    }
}