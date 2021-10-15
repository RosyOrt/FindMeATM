package com.atm.FindMeATM.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoCoincidenceException extends RuntimeException  {

    public NoCoincidenceException(String message) {
        super(message);
    }
}
