package com.shorturl.shorturl.application;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.shorturl.shorturl.domain.NotFoundShortenUrlException;
import com.shorturl.shorturl.presentation.ShortenUrlCreateRequestDto;
import com.shorturl.shorturl.presentation.ShortenUrlCreateResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleShortenServiceTest {

    @Autowired
    private SimpleShortenService simpleShortenService;
@Test
    @DisplayName("URL을 단축한 후 UTL키로 조회하면 원래의 UTL이 조회되어야한다.")
    void shortenUrlAddTest() {
        String expectedOriginalUrl = "https://www.google.com";

        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto =
            new ShortenUrlCreateRequestDto(expectedOriginalUrl);

        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = simpleShortenService.generateShortUrl(
            shortenUrlCreateRequestDto);
        String shortenUrlKey = shortenUrlCreateResponseDto.getShortenUrlKey();
        String orginalUrl = simpleShortenService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
    }
@Test
    @DisplayName("해당 단축URL이 존재하지 않는다.")
    void notExist() {
        //given
        String notValidUrl = "존재하지않는 키";
        //when & then
        assertThrows(NotFoundShortenUrlException.class, () -> simpleShortenService.getOriginalUrlByShortenUrlKey(notValidUrl));

    }
}