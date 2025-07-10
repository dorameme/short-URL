package com.shorturl.shorturl.application;

import com.shorturl.shorturl.presentation.ShortenUrlCreateRequestDto;
import com.shorturl.shorturl.presentation.ShortenUrlCreateResponseDto;
import org.springframework.stereotype.Service;

@Service
public class SimpleShortenService {
    private ShortenUrlRepository shortenUrlRepository;

    public SimpleShortenService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrlCreateResponseDto generateShortUrl(ShortenUrlCreateRequestDto shortenUrlCreateRequestDto) {
        return null
    }
}
