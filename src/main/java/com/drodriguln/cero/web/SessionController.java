package com.drodriguln.cero.web;

import com.drodriguln.cero.model.Session;
import com.drodriguln.cero.domain.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/session/{id}")
    public ResponseEntity<Session> getSession(@PathVariable String id) {
        Optional<Session> session = sessionRepository.findById(id);
        if (session.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(session.get());
    }

    @PostMapping("/session")
    public ResponseEntity<Session> postSession(@RequestBody Session session) {
        session.setId(UUID.randomUUID().toString());
        sessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @PutMapping("/session/{id}")
    public ResponseEntity<Session> putSession(@PathVariable String id, @RequestBody Session session) {
        sessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @DeleteMapping("/session/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable String id) {
        sessionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}