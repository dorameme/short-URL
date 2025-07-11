package com.shorturl.shorturl.domain;

import java.util.Random;

public class ShortenURL {
    private String originalURL;
    private String shortenURLKey;
    private Long redirectCount;

    public ShortenURL(String originalURL, String shortenURLKey) {
        this.originalURL = originalURL;
        this.shortenURLKey = shortenURLKey;
        this.redirectCount = 0L;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public String getShortenURLKey() {
        return shortenURLKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public static String generateShortenUrlKey() {//왜 암호화를 도메인에 넣어야하는지? //왜 base56을 쓰는지?
        String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        Random random = new Random();
        StringBuilder shortenUrlKey = new StringBuilder();

        for(int count=0;count<8;count++){
            int base56CharactersIndex = random.nextInt(0,base56Characters.length());
            char base56Character = base56Characters.charAt(base56CharactersIndex);
            shortenUrlKey.append(base56Character);
        }
        return shortenUrlKey.toString();
    }

    public void increaseRedirectCount() {
        this.redirectCount++;
    }
}
