package com.bondarenko.urlshortener.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ShortLinkUtil {

    public static final String LINK_NUMBERS = "0123456789";
    public static final String LINK_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final int LINK_LIMIT_LOWER = 0;
    public static final int LINK_LIMIT_UPPER = 4;

    public static String getSymbolsForShortLinkGeneration() {
        return String.join("", LINK_NUMBERS, LINK_LETTERS, LINK_LETTERS.toUpperCase());
    }
}
