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
    private int index = 0;

    @Override
    public ShortLinkResponse generateShortLink() {
        log.info("Start generate short link");
        String symbolsForGeneration = ShortLinkUtil.getSymbolsForShortLinkGeneration();

        String shortLink = IntStream.range(LIMIT_LOWER, LIMIT_UPPER)
                .mapToObj(i -> findIndexValue(symbolsForGeneration, i))
                .map(String::valueOf)
                .collect(Collectors.joining());
        index++;
        log.info("Short link was generated: {}", shortLink);

        return shortLinkMapper.toShortLinkResponse(shortLink);
    }

    private char findIndexValue(String symbols, int i) {
        double valuePosition = index / Math.pow(symbols.length(), i) % symbols.length();
        return symbols.charAt((int) valuePosition);
    }
}
