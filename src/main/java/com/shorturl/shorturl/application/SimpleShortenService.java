package com.shorturl.shorturl.application;

import com.shorturl.shorturl.domain.LackOfShortenUrlKeyException;
import com.shorturl.shorturl.domain.NotFoundShortenUrlException;
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

    public ShortenUrlCreateResponseDto generateShortUrl(
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto) {
        String originalUrl = shortenUrlCreateRequestDto.getOriginalUrl();
        String shortenUrlKey = getUniqueShortenUrlKey();

        ShortenURL.generateShortenUrlKey();

        ShortenURL shortenUrl = new ShortenURL(originalUrl, shortenUrlKey);
        shortenUrlRepository.saveShortenUrl(shortenUrl);

        return new ShortenUrlCreateResponseDto(shortenUrl);

    }

    private String getUniqueShortenUrlKey() {
        final int MAX_RETRY_COUNT = 5;
        int count = 0;
        while (count++ < MAX_RETRY_COUNT) {
            String shortenUrlKey = ShortenURL.generateShortenUrlKey();
            ShortenURL shortenURL = shortenUrlRepository.findShortenUrlByShortenUrlKey(
                shortenUrlKey);
            if (null == shortenURL) {
                return shortenUrlKey;
            }
        }
        throw new LackOfShortenUrlKeyException();

    }

    public String getOriginalUrlByShortenUrlKey(String shortenurlKey) {
        ShortenURL shortenURL = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenurlKey);
        if (shortenURL == null) {
            throw new NotFoundShortenUrlException();
        }
        shortenURL.increaseRedirectCount();
        shortenUrlRepository.saveShortenUrl(shortenURL);
        return shortenURL.getOriginalURL();
    }

    public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
        ShortenURL shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
        if (shortenUrl == null) {
            throw new NotFoundShortenUrlException();
        }
        ShortenUrlInformationDto shortenUrlInformationDto = new ShortenUrlInformationDto(
            shortenUrl);
        return shortenUrlInformationDto;
    }
}
