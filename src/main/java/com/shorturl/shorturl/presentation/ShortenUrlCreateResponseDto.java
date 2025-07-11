package com.shorturl.shorturl.presentation;

import com.shorturl.shorturl.domain.ShortenURL;

public class ShortenUrlCreateResponseDto {
    private String originalUrl;
    private String shortenUrlKey;

    public ShortenUrlCreateResponseDto(ShortenURL shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalURL();
        this.shortenUrlKey = shortenUrl.getShortenURLKey();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }
}
