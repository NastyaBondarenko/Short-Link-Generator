package com.bondarenko.urlshortener.service.impl;

import com.bondarenko.urlshortener.service.ShortLinkGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class DefaultShortLinkGenerator implements ShortLinkGenerator {

    public static final String LINK_SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int LINK_MAX_COMBINATIONS = 14776336;
    public static final int LINKS_BATCH_SIZE = 100;
    public static final int LINK_LIMIT_LOWER = 0;
    public static final int LINK_LIMIT_UPPER = 4;
    private AtomicInteger currentIndex = new AtomicInteger(0);

    @Override
    public List<String> generateShortLink() {
        currentIndex.compareAndSet(LINK_MAX_COMBINATIONS, 0);

        List<String> shortLinks = new ArrayList<>();
        for (int i = 0; i < LINKS_BATCH_SIZE; i++) {
            String shortLink = getGeneratedLink();
            currentIndex.incrementAndGet();
            shortLinks.add(shortLink);
        }
        return shortLinks;
    }

    private String getGeneratedLink() {
        return IntStream.range(LINK_LIMIT_LOWER, LINK_LIMIT_UPPER)
                .mapToObj(this::generateLinkSymbol)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private char generateLinkSymbol(int index) {
        double symbolMaxCombinations = Math.pow(LINK_SYMBOLS.length(), index);
        double symbolPosition = (currentIndex.get() / symbolMaxCombinations) % LINK_SYMBOLS.length();
        return LINK_SYMBOLS.charAt((int) symbolPosition);
    }
}
