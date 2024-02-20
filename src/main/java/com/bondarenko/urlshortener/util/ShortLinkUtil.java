package com.bondarenko.urlshortener.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ShortLinkUtil {

    public static final String NUMBERS = "0123456789";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";

    public static String getSymbolsForShortLinkGeneration() {
        return String.join("", NUMBERS, LETTERS, LETTERS.toUpperCase());
    }
}
