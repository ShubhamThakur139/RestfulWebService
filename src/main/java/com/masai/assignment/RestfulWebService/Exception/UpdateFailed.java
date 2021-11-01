package com.masai.assignment.RestfulWebService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UpdateFailed extends RuntimeException{
    public UpdateFailed(String message) {
        super(message);
    }
}
