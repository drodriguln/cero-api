package com.drodriguln.cero.web;

import com.drodriguln.cero.model.Player;
import com.drodriguln.cero.model.Session;
import com.drodriguln.cero.domain.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/session/{sessionId}/player")
public class PlayerController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable String sessionId, @PathVariable String id) {
        Optional<Session> session = sessionRepository.findById(sessionId);
        if (session.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Player player = "opponent".equals(id)
            ? session.get().getOpponent()
            : session.get().getPlayer();

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }
}