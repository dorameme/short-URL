package com.shorturl.shorturl.application;

import com.shorturl.shorturl.domain.ShortenURL;
import com.shorturl.shorturl.domain.ShortenUrlRepository;
import com.shorturl.shorturl.presentation.ShortenUrlCreateRequestDto;
import com.shorturl.shorturl.presentation.ShortenUrlCreateResponseDto;
import com.shorturl.shorturl.presentation.ShortenUrlInformationDto;
import org.springframework.stereotype.Service;

@Service
public class SimpleShortenService {
    private ShortenUrlRepository shortenUrlRepository;


    public SimpleShortenService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrlCreateResponseDto generateShortUrl(ShortenUrlCreateRequestDto shortenUrlCreateRequestDto) {
        String originalUrl= shortenUrlCreateRequestDto.getOriginalUrl();
        String shortenUrlKey= ShortenURL.generateShortenUrlKey();

        ShortenURL shortenUrl=new ShortenURL(originalUrl, shortenUrlKey);
        shortenUrlRepository.saveShortenUrl(shortenUrl);

        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto=new ShortenUrlCreateResponseDto(shortenUrl);

        return shortenUrlCreateResponseDto;
    }
public String getOriginalUrlByShortenUrlKey(String shortenurlKey){
        ShortenURL shortenURL = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenurlKey);
        shortenURL.increaseRedirectCount();
        shortenUrlRepository.saveShortenUrl(shortenURL);
        return shortenURL.getOriginalURL();
}
    public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
    ShortenURL shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
    ShortenUrlInformationDto shortenUrlInformationDto =new ShortenUrlInformationDto(shortenUrl);
    return shortenUrlInformationDto;
    }
}
