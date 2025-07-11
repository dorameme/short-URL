package com.shorturl.shorturl.presentation;


import com.shorturl.shorturl.application.SimpleShortenService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity<?> redirectShortUrl( @PathVariable String shortenUrlKey) throws URISyntaxException
    {
        String originalUrl = simpleShortenService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
        URI redirectUri = new URI(originalUrl);
        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);

    }
    @RequestMapping(value = "/shortenUrl",method = RequestMethod.POST)
    public ResponseEntity<ShortenUrlCreateResponseDto>  createShortenUrl(@Valid @RequestBody ShortenUrlCreateRequestDto shortenUrlCreateRequestDto){
        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto=
                simpleShortenService.generateShortUrl(shortenUrlCreateRequestDto);
        return ResponseEntity.ok().body(shortenUrlCreateResponseDto);
    }


    @RequestMapping(value = "/shortenUrl/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity<?>  getShortenUrlInformation(        @PathVariable String shortenUrlKey){
        ShortenUrlInformationDto shortenUrlInformationDto=
            simpleShortenService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey);
        return ResponseEntity.ok(shortenUrlInformationDto);
    }

}
