package com.drodriguln.cero.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UIOpponent {
    private Player.Status status;
    private int cardCount;

    public UIOpponent(Player player) {
        this.status = player.getStatus();
        this.cardCount = player.getCards().size();
    }
}
