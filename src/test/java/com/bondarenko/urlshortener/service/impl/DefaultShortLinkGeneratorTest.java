package com.bondarenko.urlshortener.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultShortLinkGeneratorTest {

    private final DefaultShortLinkGenerator shortLinkGenerator = new DefaultShortLinkGenerator(new AtomicInteger(0), 100);

    @Test
    @DisplayName("should generate short link successively with two links batches")
    void shouldGenerateShortLinkSuccessivelyWithTwoLinksBatches() {

        List<String> expectedLinksWithNumbers = List.of("0000", "1000", "2000", "3000", "4000", "5000", "6000", "7000");
        List<String> expectedLinksWithLowerCaseLetters = List.of("a000", "b000", "c000", "d000", "e000", "f000", "g000");

        List<String> batchLinksFirst = shortLinkGenerator.generateShortLinks();

        assertEquals(expectedLinksWithNumbers, batchLinksFirst.subList(0, 8));
        assertEquals(expectedLinksWithLowerCaseLetters, batchLinksFirst.subList(10, 17));
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
