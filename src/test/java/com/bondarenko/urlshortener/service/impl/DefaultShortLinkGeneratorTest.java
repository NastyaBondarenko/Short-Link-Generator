package com.bondarenko.urlshortener.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultShortLinkGeneratorTest {

    private final DefaultShortLinkGenerator shortLinkGenerator = new DefaultShortLinkGenerator();

    @Test
    @DisplayName("should generate short link successively with two links batches")
    void shouldGenerateShortLinkSuccessivelyWithTwoLinksBatches() {

        List<String> expectedLinksWithNumbers = List.of("0000", "1000", "2000", "3000", "4000", "5000", "6000", "7000");
        List<String> expectedLinksWithLowerCaseLetters = List.of("a000", "b000", "c000", "d000", "e000", "f000", "g000");
        List<String> expectedLinksWithUpperCaseLetters = List.of("A000", "B000", "C000", "D000", "E000", "F000", "G000");
        List<String> expectedLinksWithFewNumbers = List.of("0100", "1100", "2100", "3100", "4100", "5100", "6100", "7100");
        List<String> expectedLinksWithNumberAndLowerCaseLetter = List.of("a100", "b100", "c100", "d100", "e100", "f100");
        List<String> expectedLinksWithNumberAndUpperCaseLetter = List.of("A100", "B100");

        List<String> linksWithNumberAndUpperCaseLetter = List.of("C100", "D100", "E100", "F100", "G100", "H100", "I100");
        List<String> linksWithNumberAndLowerCaseLetter = List.of("c200", "d200", "e200", "f200", "g200", "h200", "i200");

        List<String> batchLinksFirst = shortLinkGenerator.generateShortLink();

        assertEquals(expectedLinksWithNumbers, batchLinksFirst.subList(0, 8));
        assertEquals(expectedLinksWithLowerCaseLetters, batchLinksFirst.subList(10, 17));
        assertEquals(expectedLinksWithUpperCaseLetters, batchLinksFirst.subList(36, 43));
        assertEquals(expectedLinksWithFewNumbers, batchLinksFirst.subList(62, 70));
        assertEquals(expectedLinksWithNumberAndLowerCaseLetter, batchLinksFirst.subList(72, 78));
        assertEquals(expectedLinksWithNumberAndUpperCaseLetter, batchLinksFirst.subList(98, 100));

        List<String> batchLinksSecond = shortLinkGenerator.generateShortLink();

        assertEquals(linksWithNumberAndUpperCaseLetter, batchLinksSecond.subList(0, 7));
        assertEquals(linksWithNumberAndLowerCaseLetter, batchLinksSecond.subList(36, 43));
    }

    @Test
    @DisplayName("when generate short links than batch with one hundred links return")
    void whenGenerateShortLinks_thenBatchWithOneHundredLinksReturn() {
        int expectedBatchLinksSize = 100;

        List<String> batchLinksFirst = shortLinkGenerator.generateShortLink();
        List<String> batchLinksSecond = shortLinkGenerator.generateShortLink();
        List<String> batchLinksThird = shortLinkGenerator.generateShortLink();

        assertEquals(expectedBatchLinksSize, batchLinksFirst.size());
        assertEquals(expectedBatchLinksSize, batchLinksSecond.size());
        assertEquals(expectedBatchLinksSize, batchLinksThird.size());
    }

    @Test
    @DisplayName("should generate link correctly with appropriate length")
    void shouldGenerateLinkCorrectlyWithAppropriateLength() {
        int expectedLinkLength = 4;
        String expectedLink = "0000";

        String generatedLink = shortLinkGenerator.getGeneratedLink();

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

        char actualCharFirst = shortLinkGenerator.generateLinkSymbol(0);
        char actualCharSecond = shortLinkGenerator.generateLinkSymbol(1);
        char actualCharThird = shortLinkGenerator.generateLinkSymbol(2);
        char actualCharFourth = shortLinkGenerator.generateLinkSymbol(3);

        assertEquals(expectedCharFirst, actualCharFirst);
        assertEquals(expectedCharSecond, actualCharSecond);
        assertEquals(expectedCharThird, actualCharThird);
        assertEquals(expectedCharFourth, actualCharFourth);
    }

    @Test
    @DisplayName("should generate link symbol successfully")
    void shouldGenerateLinkSymbolSuccessfully() {
        char expectedCharFirst = '0';
        char actualCharFirst = shortLinkGenerator.generateLinkSymbol(0);

        assertEquals(expectedCharFirst, actualCharFirst);
    }
}
