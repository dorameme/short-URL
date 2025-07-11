package com.shorturl.shorturl.domain;

public interface ShortenUrlRepository {
    void saveShortenUrl(ShortenURL shortenURL);

    ShortenURL findShortenUrlByShortenUrlKey(String shortenUrlKey);
}
