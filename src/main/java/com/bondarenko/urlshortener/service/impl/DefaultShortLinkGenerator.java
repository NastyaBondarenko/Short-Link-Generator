package com.bondarenko.urlshortener.service.impl;

import com.bondarenko.urlshortener.dto.ShortLinkResponse;
import com.bondarenko.urlshortener.mapper.ShortLinkMapper;
import com.bondarenko.urlshortener.service.ShortLinkGenerator;
import com.bondarenko.urlshortener.util.ShortLinkUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultShortLinkGenerator implements ShortLinkGenerator {

    private static final int LIMIT_LOWER = 0;
    private static final int LIMIT_UPPER = 5;
    private final ShortLinkMapper shortLinkMapper;
    private int currentIndex = 0;

    @Override
    public ShortLinkResponse generateShortLink() {

        String symbolsForGeneration = ShortLinkUtil.getSymbolsForShortLinkGeneration();
        validateIndex(symbolsForGeneration);

        String shortLink = IntStream.range(LIMIT_LOWER, LIMIT_UPPER)
                .mapToObj(i -> findIndexValue(symbolsForGeneration, i))
                .map(String::valueOf)
                .collect(Collectors.joining());
        currentIndex++;

        return shortLinkMapper.toShortLinkResponse(shortLink);
    }

    private char findIndexValue(String symbolsForGeneration, int i) {
        double indexMaxCombinations = Math.pow(symbolsForGeneration.length(), i);
        double valueSegment = currentIndex / indexMaxCombinations;
        double valuePosition = valueSegment % symbolsForGeneration.length();
        return symbolsForGeneration.charAt((int) valuePosition);
    }

    private void validateIndex(String symbolsForGeneration) {
        double maxCombinationsCount = Math.pow(symbolsForGeneration.length(), LIMIT_UPPER);
        currentIndex = currentIndex >= maxCombinationsCount ? 0 : currentIndex;
    }
}
