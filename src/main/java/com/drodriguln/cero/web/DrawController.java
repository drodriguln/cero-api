package com.drodriguln.cero.web;

import com.drodriguln.cero.domain.SessionRepository;
import com.drodriguln.cero.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/sessions/{sessionId}/draw")
public class DrawController {
    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<Player> postDiscard(@PathVariable String sessionId) {
        Optional<Session> sessionOpt = sessionRepository.findById(sessionId);
        if (sessionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Session session = sessionOpt.get();
        Player player = session.getPlayer();

        player.draw(session.getDeck());

        sessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.OK).body(player);
    }
}