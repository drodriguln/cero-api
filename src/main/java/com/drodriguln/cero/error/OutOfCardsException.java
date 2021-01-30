package com.drodriguln.cero.error;

public class OutOfCardsException extends RuntimeException {
    public OutOfCardsException(String errorMessage) {
        super(errorMessage);
    }
}
