package com.drodriguln.cero.web;

import com.drodriguln.cero.model.Session;
import com.drodriguln.cero.domain.SessionRepository;
import com.drodriguln.cero.model.UISession;
import com.drodriguln.cero.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.drodriguln.cero.service.CookieService.COOKIE_NAME;

@CrossOrigin
@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private CookieService cookieService;

    @PostMapping
    public ResponseEntity<UISession> postSession(HttpServletResponse response) {
        Session session = new Session();
        session.initialize();
        sessionRepository.save(session);

        Cookie cookie = cookieService.create(session.getId());
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UISession(session));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSession(@CookieValue(COOKIE_NAME) String sessionId, HttpServletResponse response) {
        sessionRepository.deleteById(sessionId);

        Cookie expiredCookie = cookieService.createExpired();
        response.addCookie(expiredCookie);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}