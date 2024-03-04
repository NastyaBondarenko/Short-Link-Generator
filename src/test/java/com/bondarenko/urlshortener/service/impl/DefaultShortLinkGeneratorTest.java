package com.bondarenko.urlshortener.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultShortLinkGeneratorTest {

    private final DefaultShortLinkGenerator shortLinkGenerator = new DefaultShortLinkGenerator();

    @Test
    @DisplayName("should generate short link successively")
    void shouldGenerateShortLinkSuccessively() {

        int totalCombinations = 5000;
//        String values=[012abcABC]

        String[] actualShortLink = new String[totalCombinations];
        for (int i = 0; i < totalCombinations; i++) {
            actualShortLink[i] = shortLinkGenerator.generateShortLink();
        }

        assertEquals("0000", actualShortLink[0]);
        assertEquals("1000", actualShortLink[1]);
        assertEquals("2000", actualShortLink[2]);

        assertEquals("a000", actualShortLink[10]);
        assertEquals("b000", actualShortLink[11]);
        assertEquals("c000", actualShortLink[12]);

        assertEquals("A000", actualShortLink[36]);
        assertEquals("B000", actualShortLink[37]);
        assertEquals("C000", actualShortLink[38]);

        assertEquals("0100", actualShortLink[62]);
        assertEquals("1100", actualShortLink[63]);
        assertEquals("2100", actualShortLink[64]);

        assertEquals("a100", actualShortLink[72]);
        assertEquals("b100", actualShortLink[73]);
        assertEquals("c100", actualShortLink[74]);

        assertEquals("A100", actualShortLink[98]);
        assertEquals("B100", actualShortLink[99]);
        assertEquals("C100", actualShortLink[100]);

        assertEquals("a200", actualShortLink[134]);
        assertEquals("b200", actualShortLink[135]);
        assertEquals("c200", actualShortLink[136]);

        assertEquals("A200", actualShortLink[160]);
        assertEquals("B200", actualShortLink[161]);
        assertEquals("C200", actualShortLink[162]);
    }
}
