package com.zepp.rpp.exceptions;

/**
 * Created by darko on 4/22/2018.
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
    //u serviceimpl stavim notfound Exception
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}