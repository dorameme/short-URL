package com.shorturl.shorturl.application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.shorturl.shorturl.domain.LackOfShortenUrlKeyException;
import com.shorturl.shorturl.domain.ShortenURL;
import com.shorturl.shorturl.domain.ShortenUrlRepository;
import com.shorturl.shorturl.presentation.ShortenUrlCreateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SimpleShortenUrlServiceUnitTest {
    @Mock
    private ShortenUrlRepository shortenUrlRepository;

    @InjectMocks
    private SimpleShortenService simpleShortenService;

    @Test
    @DisplayName("단축 URL이 계속 중복되면 LackOfShortenUrlKeyException 예외가 발생한다.")
    void throwLackOfShortenUrlKeyExceptionTest(){
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto= new ShortenUrlCreateRequestDto(null);
        when(shortenUrlRepository.findShortenUrlByShortenUrlKey(any())).thenReturn(new ShortenURL(null,null));
        assertThrows(LackOfShortenUrlKeyException.class, ()-> {
            simpleShortenService.generateShortUrl(shortenUrlCreateRequestDto);
        });
    }
}
