package com.shorturl.shorturl.infrastructure;

import com.shorturl.shorturl.domain.ShortenURL;
import com.shorturl.shorturl.domain.ShortenUrlRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class MapShortenUrlRepository implements ShortenUrlRepository {

    private Map<String, ShortenURL> shortenUrls = new ConcurrentHashMap<>();

    @Override
    public ShortenURL findShortenUrlByShortenUrlKey(String shortenUrlKey) {
        return shortenUrls.get(shortenUrlKey);
    }


    @Override
    public void saveShortenUrl(ShortenURL shortenURL) {
        shortenUrls.put(shortenURL.getShortenURLKey(), shortenURL);
    }
}
