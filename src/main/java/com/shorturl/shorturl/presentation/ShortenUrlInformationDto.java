package com.shorturl.shorturl.presentation;

public class ShortenUrlInformationDto {
    private String originalUrl;
    private String shortenUrl;
    private Long redirectCount;

    public String getShortenUrl() {
        return shortenUrl;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }
}
