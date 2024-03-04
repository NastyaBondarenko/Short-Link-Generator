package com.bondarenko.urlshortener.web.controller;

import com.bondarenko.urlshortener.service.ShortLinkGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ShortLinkController.class)
public class ShortLinkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShortLinkGenerator shortLinkGenerator;

    @Test
    @DisplayName("should generate short link successfully")
    void shouldGenerateShortLinkSuccessfully() throws Exception {
        String generatedLink = "1234";
        when(shortLinkGenerator.generateShortLink()).thenReturn(generatedLink);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/short-link/generate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(generatedLink));
    }
}