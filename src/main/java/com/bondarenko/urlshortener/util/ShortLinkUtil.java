package com.bondarenko.urlshortener.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ShortLinkUtil {

    public static final String NUMBERS = "0123456789";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final int LINK_LIMIT_LOWER = 0;
    public static final int LINK_LIMIT_UPPER = 4;

    public static String getSymbolsForShortLinkGeneration() {
        return String.join("", NUMBERS, LETTERS, LETTERS.toUpperCase());
    }
}
