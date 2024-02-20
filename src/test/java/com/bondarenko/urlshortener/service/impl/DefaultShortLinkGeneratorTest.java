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

        assertEquals("00000", actualShortLink[0].getShortLink());
        assertEquals("10000", actualShortLink[1].getShortLink());
        assertEquals("20000", actualShortLink[2].getShortLink());

        assertEquals("a0000", actualShortLink[10].getShortLink());
        assertEquals("b0000", actualShortLink[11].getShortLink());
        assertEquals("c0000", actualShortLink[12].getShortLink());

        assertEquals("A0000", actualShortLink[36].getShortLink());
        assertEquals("B0000", actualShortLink[37].getShortLink());
        assertEquals("C0000", actualShortLink[38].getShortLink());

        assertEquals("01000", actualShortLink[62].getShortLink());
        assertEquals("11000", actualShortLink[63].getShortLink());
        assertEquals("21000", actualShortLink[64].getShortLink());

        assertEquals("a1000", actualShortLink[72].getShortLink());
        assertEquals("b1000", actualShortLink[73].getShortLink());
        assertEquals("c1000", actualShortLink[74].getShortLink());

        assertEquals("A1000", actualShortLink[98].getShortLink());
        assertEquals("B1000", actualShortLink[99].getShortLink());
        assertEquals("C1000", actualShortLink[100].getShortLink());

        assertEquals("a2000", actualShortLink[134].getShortLink());
        assertEquals("b2000", actualShortLink[135].getShortLink());
        assertEquals("c2000", actualShortLink[136].getShortLink());

        assertEquals("A2000", actualShortLink[160].getShortLink());
        assertEquals("B2000", actualShortLink[161].getShortLink());
        assertEquals("C2000", actualShortLink[162].getShortLink());
    }
}
