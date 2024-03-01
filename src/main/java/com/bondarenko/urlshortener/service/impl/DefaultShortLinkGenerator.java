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

import static com.bondarenko.urlshortener.util.ShortLinkUtil.LINK_LIMIT_LOWER;
import static com.bondarenko.urlshortener.util.ShortLinkUtil.LINK_LIMIT_UPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultShortLinkGenerator implements ShortLinkGenerator {
    private final ShortLinkMapper shortLinkMapper;
    private int currentIndex = 0;

    @Override
    public ShortLinkResponse generateShortLink() {

        String linkGenerationSymbols = ShortLinkUtil.getSymbolsForShortLinkGeneration();
        validateIndex(linkGenerationSymbols);

        String shortLink = IntStream.range(LINK_LIMIT_LOWER, LINK_LIMIT_UPPER)
                .mapToObj(i -> findIndexValue(linkGenerationSymbols, i))
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
        double maxLinkCombinations = Math.pow(symbolsForGeneration.length(), LINK_LIMIT_UPPER);
        currentIndex = currentIndex >= maxLinkCombinations ? 0 : currentIndex;
    }
}
