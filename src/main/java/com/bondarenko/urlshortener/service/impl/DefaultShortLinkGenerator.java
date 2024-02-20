package com.bondarenko.urlshortener.service.impl;

import com.bondarenko.urlshortener.dto.ShortLinkResponse;
import com.bondarenko.urlshortener.mapper.ShortLinkMapper;
import com.bondarenko.urlshortener.service.ShortLinkGenerator;
import com.bondarenko.urlshortener.util.ShortLinkUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultShortLinkGenerator implements ShortLinkGenerator {

    private static final int INDEX_FIRST = 0;
    private static final int INDEX_LAST = 5;
    private static int INDEX = 0;
    private final ShortLinkMapper shortLinkMapper;

    @Override
    public ShortLinkResponse generateShortLink() {
        log.info("Start generate short link");
        String symbols = ShortLinkUtil.getSymbolsForShortLinkGeneration();
        StringBuilder shortLinkBuilder = new StringBuilder();

        long currentIndex = INDEX++;
        for (int i = INDEX_FIRST; i < INDEX_LAST; i++) {
            long symbolIndex = currentIndex % symbols.length();
            shortLinkBuilder.insert(0, symbols.charAt((int) symbolIndex));
            currentIndex /= symbols.length();
        }
        return shortLinkMapper.toShortLinkResponse(shortLinkBuilder.toString());
    }
}
