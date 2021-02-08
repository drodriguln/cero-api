package com.drodriguln.cero.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UISession {
    private String id;
    private Card discard;
    private Player player;
    private UIOpponent opponent;

    public UISession(Session session) {
        this.id = session.getId();
        this.discard = session.getDiscard().getTopCard();
        this.player = session.getPlayer();
        this.opponent = new UIOpponent(session.getOpponent());
    }
}
