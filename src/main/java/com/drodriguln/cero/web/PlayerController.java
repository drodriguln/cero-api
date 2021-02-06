package com.drodriguln.cero.web;

import com.drodriguln.cero.model.Player;
import com.drodriguln.cero.model.Session;
import com.drodriguln.cero.domain.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.drodriguln.cero.web.SessionController.COOKIE_NAME;

@CrossOrigin
@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@CookieValue(COOKIE_NAME) String sessionId, @PathVariable String id) {
        Optional<Session> sessionOpt = sessionRepository.findById(sessionId);
        if (sessionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Player player = "opponent".equals(id)
            ? sessionOpt.get().getOpponent()
            : sessionOpt.get().getPlayer();

        return ResponseEntity.status(HttpStatus.OK).body(player);
    }
}