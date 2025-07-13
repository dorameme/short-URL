package com.shorturl.shorturl.presentation;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.shorturl.shorturl.application.SimpleShortenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ShortenURLRestController.class)
class ShortenURLRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SimpleShortenService simpleShortenService;

    @TestConfiguration
    static class TestConfig {

        @Bean
        public SimpleShortenService simpleShortenService() {
            return Mockito.mock(SimpleShortenService.class);
        }
    }

//    @Test
//    @DisplayName("원래의 URL로 리다이렉트 되어야 한다.")
//    void redirectShortUrl() throws Exception {
//        String expectedOriginalUrl = "https://www.google.com";
//
//        // 모킹
//        Mockito.when(simpleShortenService.getOriginalUrlByShortenUrlKey(any()))
//            .thenReturn(expectedOriginalUrl);
//
//        mockMvc.perform(get("/any-key"))
//            .andExpect(status().isMovedPermanently())
//            .andExpect(header().string("Location", expectedOriginalUrl));
//    }
}
