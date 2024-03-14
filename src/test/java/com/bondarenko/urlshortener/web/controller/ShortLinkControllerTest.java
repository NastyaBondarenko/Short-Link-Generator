package com.bondarenko.urlshortener.web.controller;

import com.bondarenko.urlshortener.service.ShortLinksGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShortLinkController.class)
public class ShortLinkControllerTest {

    private final List<String> SHORT_LINKS = new ArrayList<>(List.of("0000", "1000", "2000", "3000", "4000", "5000"));
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShortLinksGenerator shortLinkGenerator;

    @Test
    @DisplayName("should generate short link successfully")
    void shouldGenerateShortLinkSuccessfully() throws Exception {

        when(shortLinkGenerator.generateShortLinks()).thenReturn(SHORT_LINKS);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/short-links/generate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("""
                                [
                                "0000",
                                "1000",
                                "2000",
                                "3000",
                                "4000",
                                "5000"
                                ]
                                """))
                .andExpect(status().isOk());
    }
}