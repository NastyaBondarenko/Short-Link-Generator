package com.bondarenko.urlshortener.service.impl;

import com.bondarenko.urlshortener.service.ShortLinkGenerator;
import com.google.common.annotations.VisibleForTesting;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@AllArgsConstructor
public class DefaultShortLinkGenerator implements ShortLinkGenerator {

    private static final String LINK_SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LINK_MAX_COMBINATIONS = 14_776_336;
    private static final int LINK_LIMIT_LOWER = 0;
    private static final int LINK_LIMIT_UPPER = 4;
    private AtomicInteger generatedCombinationsCount;
    @Value("${links.batch.size}")
    private int linksBatchSize;

    @Override
    public List<String> generateShortLinks() {

        generatedCombinationsCount.compareAndSet(LINK_MAX_COMBINATIONS, 0);//refactor

        int startIndex = generatedCombinationsCount.get();
        generatedCombinationsCount.compareAndSet(startIndex, startIndex + linksBatchSize);

        List<String> result = new ArrayList<>();

        for (int i = 0; i < linksBatchSize; i++) {
            result.add(getGeneratedLink(startIndex++));
        }

        return result;
    }

    @VisibleForTesting
    String getGeneratedLink(int valueIndex) {
        return IntStream.range(LINK_LIMIT_LOWER, LINK_LIMIT_UPPER)
                .mapToObj(symbolIndex -> generateLinkSymbol(symbolIndex, valueIndex))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    @VisibleForTesting
    char generateLinkSymbol(int symbolIndex, int valueIndex) {
        double symbolMaxCombinations = Math.pow(LINK_SYMBOLS.length(), symbolIndex);
        double symbolPosition = (valueIndex / symbolMaxCombinations) % LINK_SYMBOLS.length();
        return LINK_SYMBOLS.charAt((int) symbolPosition);
    }
}
