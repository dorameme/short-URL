package com.shorturl.shorturl.presentation;

public class ShortenUrlCreateResponseDto {
    private String originalUrl;
    private String shortenUrlKey;

    public ShortenUrlCreateResponseDto(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }
}
