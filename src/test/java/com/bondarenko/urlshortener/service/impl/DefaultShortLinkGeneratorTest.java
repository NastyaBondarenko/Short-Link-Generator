package com.bondarenko.urlshortener.service.impl;

import com.bondarenko.urlshortener.dto.ShortLinkResponse;
import com.bondarenko.urlshortener.mapper.ShortLinkMapper;
import com.bondarenko.urlshortener.mapper.ShortLinkMapperImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultShortLinkGeneratorTest {
    private final ShortLinkMapper shortLinkMapper = new ShortLinkMapperImpl();

    private final DefaultShortLinkGenerator shortLinkGenerator = new DefaultShortLinkGenerator(shortLinkMapper);

    @Test
    @DisplayName("should generate short link successively")
    void shouldGenerateShortLinkSuccessively() {

        int totalCombinations = 5000;

        ShortLinkResponse[] actualShortLink = new ShortLinkResponse[totalCombinations];
        for (int i = 0; i < totalCombinations; i++) {
            actualShortLink[i] = shortLinkGenerator.generateShortLink();
        }

        assertEquals("0000", actualShortLink[0].getShortLink());
        assertEquals("1000", actualShortLink[1].getShortLink());
        assertEquals("2000", actualShortLink[2].getShortLink());

        assertEquals("a000", actualShortLink[10].getShortLink());
        assertEquals("b000", actualShortLink[11].getShortLink());
        assertEquals("c000", actualShortLink[12].getShortLink());

        assertEquals("A000", actualShortLink[36].getShortLink());
        assertEquals("B000", actualShortLink[37].getShortLink());
        assertEquals("C000", actualShortLink[38].getShortLink());

        assertEquals("0100", actualShortLink[62].getShortLink());
        assertEquals("1100", actualShortLink[63].getShortLink());
        assertEquals("2100", actualShortLink[64].getShortLink());

        assertEquals("a100", actualShortLink[72].getShortLink());
        assertEquals("b100", actualShortLink[73].getShortLink());
        assertEquals("c100", actualShortLink[74].getShortLink());

        assertEquals("A100", actualShortLink[98].getShortLink());
        assertEquals("B100", actualShortLink[99].getShortLink());
        assertEquals("C100", actualShortLink[100].getShortLink());

        assertEquals("a200", actualShortLink[134].getShortLink());
        assertEquals("b200", actualShortLink[135].getShortLink());
        assertEquals("c200", actualShortLink[136].getShortLink());

        assertEquals("A200", actualShortLink[160].getShortLink());
        assertEquals("B200", actualShortLink[161].getShortLink());
        assertEquals("C200", actualShortLink[162].getShortLink());
    }
}
