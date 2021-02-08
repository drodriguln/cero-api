package com.drodriguln.cero.web;

import com.drodriguln.cero.model.Session;
import com.drodriguln.cero.domain.SessionRepository;
import com.drodriguln.cero.model.UISession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSession(@PathVariable String id) {
        Optional<Session> session = sessionRepository.findById(id);
        if (session.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(session.get());
    }

    @PostMapping
    public ResponseEntity<UISession> postSession() {
        Session session = new Session();
        session.initialize();
        sessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UISession(session));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UISession> putSession(@PathVariable String id, @RequestBody UISession uiSession) {
        //sessionRepository.save(session);
        System.out.println("Mock PUT: " + uiSession.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(uiSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable String id) {
        sessionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}