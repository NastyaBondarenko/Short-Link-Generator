package com.bondarenko.urlshortener.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultShortLinksGeneratorTest {

    private final DefaultShortLinksGenerator shortLinkGenerator = new DefaultShortLinksGenerator(new AtomicInteger(0), 100);

    @Test
    @DisplayName("should generate short link successively with two links batches")
    void shouldGenerateShortLinkSuccessivelyWithTwoLinksBatches() {

        List<String> expectedLinks = List.of("0000", "0001", "0002", "0003", "0004", "0005", "0006", "0007");

        List<String> actualLinks = shortLinkGenerator.generateShortLinks();

        assertEquals(expectedLinks, actualLinks.subList(0, 8));
    }

    @Test
    @DisplayName("when generate short links than batch with one hundred links return")
    void whenGenerateShortLinks_thenBatchWithOneHundredLinksReturn() {
        int expectedBatchLinksSize = 100;

        List<String> batchLinksFirst = shortLinkGenerator.generateShortLinks();
        List<String> batchLinksSecond = shortLinkGenerator.generateShortLinks();
        List<String> batchLinksThird = shortLinkGenerator.generateShortLinks();

        assertEquals(expectedBatchLinksSize, batchLinksFirst.size());
        assertEquals(expectedBatchLinksSize, batchLinksSecond.size());
        assertEquals(expectedBatchLinksSize, batchLinksThird.size());
    }

    @Test
    @DisplayName("should generate link correctly with appropriate length")
    void shouldGenerateLinkCorrectlyWithAppropriateLength() {
        int expectedLinkLength = 4;
        String expectedLink = "0000";

        String generatedLink = shortLinkGenerator.getGeneratedLink(0);

        assertNotNull(generatedLink);
        assertEquals(expectedLink, generatedLink);
        assertEquals(expectedLinkLength, generatedLink.length());
    }

    @Test
    @DisplayName("should generate link correctly with appropriate length")
    void shouldGeneratesLinkCorrectlyWithAppropriateLength() {
        char expectedCharFirst = '0';
        char expectedCharSecond = '0';
        char expectedCharThird = '0';
        char expectedCharFourth = '0';

        char actualCharFirst = shortLinkGenerator.generateLinkSymbol(0, 0);
        char actualCharSecond = shortLinkGenerator.generateLinkSymbol(1, 1);
        char actualCharThird = shortLinkGenerator.generateLinkSymbol(2, 2);
        char actualCharFourth = shortLinkGenerator.generateLinkSymbol(3, 3);

        assertEquals(expectedCharFirst, actualCharFirst);
        assertEquals(expectedCharSecond, actualCharSecond);
        assertEquals(expectedCharThird, actualCharThird);
        assertEquals(expectedCharFourth, actualCharFourth);
    }

    @Test
    @DisplayName("should generate link symbol successfully")
    void shouldGenerateLinkSymbolSuccessfully() {
        char expectedCharFirst = '0';
        char actualCharFirst = shortLinkGenerator.generateLinkSymbol(0, 0);

        assertEquals(expectedCharFirst, actualCharFirst);
    }
}
