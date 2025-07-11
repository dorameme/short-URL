package com.shorturl.shorturl.presentation;

import com.shorturl.shorturl.domain.ShortenURL;

public class ShortenUrlInformationDto {
    private String originalUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public ShortenUrlInformationDto(ShortenURL shortenURL) {

        this.originalUrl =shortenURL.getOriginalURL();
        this.shortenUrlKey = shortenURL.getShortenURLKey();
        this.redirectCount = shortenURL.getRedirectCount();
    }

    public String getShortenUrl() {
        return shortenUrlKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
