package com.drodriguln.cero.web;

import com.drodriguln.cero.domain.SessionRepository;
import com.drodriguln.cero.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.drodriguln.cero.service.CookieService.COOKIE_NAME;

@CrossOrigin
@RestController
@RequestMapping("/players/{playerId}/draw")
public class DrawController {
    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<Player> postDiscard(@CookieValue(COOKIE_NAME) String sessionId, @PathVariable String playerId) {
        Optional<Session> sessionOpt = sessionRepository.findById(sessionId);
        if (sessionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Session session = sessionOpt.get();
        Player player = "player".equals(playerId)
            ? session.getPlayer()
            : session.getOpponent();

        player.draw(session.getDeck());

        sessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }
}