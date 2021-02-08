package com.drodriguln.cero.web;

import com.drodriguln.cero.domain.SessionRepository;
import com.drodriguln.cero.error.OutOfCardsException;
import com.drodriguln.cero.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/sessions/{sessionId}/discard")
public class DiscardController {
    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<UISession> postDiscard(@PathVariable String sessionId, @RequestBody Card card) {
        Optional<Session> sessionOpt = sessionRepository.findById(sessionId);
        if (sessionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Session session = sessionOpt.get();
        Player player = session.getPlayer();
        Player opponent = session.getOpponent();

        player.discard(session.getDiscard(), card);

        if (!Card.Value.SKIP.equals(card.getValue())) {
            player.endTurn();
            opponent.startTurn();
            executeAI(session);
        }

        sessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.OK).body(new UISession(session));
    }

    private void executeAI(Session session) {
        Deck deck = session.getDeck();
        Discard discard = session.getDiscard();
        Player player = session.getPlayer();
        Player opponent = session.getOpponent();

        boolean isFinishedTurn = false;
        while (!isFinishedTurn) {
            Optional<Card> matchedCardOpt = opponent.findMatch(discard.getTopCard());
            if (matchedCardOpt.isEmpty()) {
                try {
                    opponent.draw(deck);
                } catch (OutOfCardsException e) {
                    deck.add(discard);
                }
                continue;
            }

            Card matchedCard = matchedCardOpt.get();
            opponent.remove(matchedCard);
            discard.place(matchedCard);

            if (Card.Value.SKIP.equals(matchedCard.getValue())) {
                continue;
            }

            player.startTurn();
            opponent.endTurn();

            isFinishedTurn = true;
        }
    }
}