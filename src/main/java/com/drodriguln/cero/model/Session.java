package com.drodriguln.cero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Session")
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
        this.player.setActivity("start");

        this.opponent.firstDraw(this.deck);
        this.opponent.setActivity("end");

        this.discard.place(deck.draw());
    }
}
