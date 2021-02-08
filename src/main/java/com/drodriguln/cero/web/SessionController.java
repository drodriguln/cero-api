package com.drodriguln.cero.web;

import com.drodriguln.cero.model.Session;
import com.drodriguln.cero.domain.SessionRepository;
import com.drodriguln.cero.model.UISession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/sessions")
public class SessionController {
    public static final String COOKIE_NAME = "CERO_SESSION";

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<UISession> postSession(HttpServletResponse response) {
        Session session = new Session();
        session.initialize();
        sessionRepository.save(session);

        Cookie cookie = new Cookie(COOKIE_NAME, session.getId());
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UISession(session));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSession(@CookieValue(COOKIE_NAME) String sessionId, HttpServletResponse response) {
        sessionRepository.deleteById(sessionId);

        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}