package bttger.webframeworkbenchmarks.spring.controller;

import bttger.webframeworkbenchmarks.spring.model.GetQueryResultError;
import bttger.webframeworkbenchmarks.spring.model.QueryError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@ControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(QueryError.class)
    public ResponseEntity<GetQueryResultError> handleError(QueryError e) {
        return new ResponseEntity<>(new GetQueryResultError(e.getCode(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
