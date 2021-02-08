package com.drodriguln.cero.web;

import com.drodriguln.cero.model.Session;
import com.drodriguln.cero.domain.SessionRepository;
import com.drodriguln.cero.model.UISession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<UISession> postSession() {
        Session session = new Session();
        session.initialize();
        sessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UISession(session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable String id) {
        sessionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}