package com.drodriguln.cero.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UISession {
    private Card discard;
    private Player player;
    private Player opponent;

    public UISession(Session session) {
        this.discard = session.getDiscard().getTopCard();
        this.player = session.getPlayer();
        this.opponent = session.getOpponent();
    }
}
