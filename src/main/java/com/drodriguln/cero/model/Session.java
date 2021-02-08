package com.drodriguln.cero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "Session", timeToLive = 86400) // ttl = 1 day
@Data
@AllArgsConstructor
public class Session {
    @Id
    private String id;
    private Deck deck;
    private Discard discard;
    private Player player;
    private Player opponent;

    public Session() {
        this.deck = new Deck();
        this.discard = new Discard();
        this.player = new Player();
        this.opponent = new Player();
    }

    public void initialize() {
        this.player.firstDraw(this.deck);
        this.opponent.firstDraw(this.deck);

        this.opponent.endTurn();
        this.player.startTurn();

        this.discard.place(deck.draw());
    }
}
