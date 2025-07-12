package com.shorturl.shorturl.presentation;

import com.shorturl.shorturl.domain.NotFoundShortenUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundShortenUrlException.class)
    public ResponseEntity<String> handleNotFoundShortenUrlException(NotFoundShortenUrlException ex){
     return new ResponseEntity<>("단축 URL 찾지못함", HttpStatus.NOT_FOUND);
    }
}
