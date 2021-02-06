package com.drodriguln.cero.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class CookieService {
    public static final String COOKIE_NAME = "CERO";
    public static final int TTL = 604800; // 1 week

    public Cookie create(String sessionId) {
        Cookie cookie = new Cookie(COOKIE_NAME, sessionId);
        cookie.setMaxAge(TTL);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public Cookie createExpired() {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        return cookie;
    }
}
