package com.shorturl.shorturl.presentation;


import com.shorturl.shorturl.application.SimpleShortenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenURLRestController {
    private SimpleShortenService simpleShortenService;

    public ShortenURLRestController(SimpleShortenService simpleShortenService) {
        this.simpleShortenService = simpleShortenService;
    }

    @RequestMapping(value = "/shortenUrl",method = RequestMethod.POST)
    public ResponseEntity<ShortenUrlCreateResponseDto>  createShortenUrl(@Valid @RequestBody ShortenUrlCreateRequestDto shortenUrlCreateRequestDto){
        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto=
                simpleShortenService.generateShortUrl(shortenUrlCreateRequestDto);
        return ResponseEntity.ok().body(shortenUrlCreateResponseDto);
    }

    @RequestMapping(value = "/{shortenUrlKey}" , method =  RequestMethod.GET)
    public ResponseEntity<?> redirectShortenUrl(        @PathVariable String shortenUrlKey){
        return ResponseEntity.ok().body(null);
    }
    @RequestMapping(value = "/shortenUrl/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity<?>  getShortenUrlInformation(        @PathVariable String shortenUrlKey){
        return ResponseEntity.ok().body(null);
    }

}
