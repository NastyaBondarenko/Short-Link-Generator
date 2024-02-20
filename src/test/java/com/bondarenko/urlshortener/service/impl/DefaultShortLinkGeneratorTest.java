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
        assertEquals("00001", actualShortLink[1].getShortLink());
        assertEquals("00002", actualShortLink[2].getShortLink());

        assertEquals("0000a", actualShortLink[10].getShortLink());
        assertEquals("0000b", actualShortLink[11].getShortLink());
        assertEquals("0000c", actualShortLink[12].getShortLink());

        assertEquals("0000A", actualShortLink[36].getShortLink());
        assertEquals("0000B", actualShortLink[37].getShortLink());
        assertEquals("0000C", actualShortLink[38].getShortLink());

        assertEquals("00010", actualShortLink[62].getShortLink());
        assertEquals("00011", actualShortLink[63].getShortLink());
        assertEquals("00012", actualShortLink[64].getShortLink());

        assertEquals("0001a", actualShortLink[72].getShortLink());
        assertEquals("0001b", actualShortLink[73].getShortLink());
        assertEquals("0001c", actualShortLink[74].getShortLink());

        assertEquals("0001A", actualShortLink[98].getShortLink());
        assertEquals("0001B", actualShortLink[99].getShortLink());
        assertEquals("0001C", actualShortLink[100].getShortLink());

        assertEquals("0002a", actualShortLink[134].getShortLink());
        assertEquals("0002b", actualShortLink[135].getShortLink());
        assertEquals("0002c", actualShortLink[136].getShortLink());

        assertEquals("0002A", actualShortLink[160].getShortLink());
        assertEquals("0002B", actualShortLink[161].getShortLink());
        assertEquals("0002C", actualShortLink[162].getShortLink());
    }
}
