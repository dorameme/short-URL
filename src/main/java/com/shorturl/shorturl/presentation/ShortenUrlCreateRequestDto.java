package com.shorturl.shorturl.presentation;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class ShortenUrlCreateRequestDto {

    @NotNull
    @URL(regexp = "^(https?)://.*$", message = "URL은 http:// 또는 https:// 로 시작해야 합니다.")
    private String originalUrl;

    public ShortenUrlCreateRequestDto() {
    }

    public ShortenUrlCreateRequestDto(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

}
