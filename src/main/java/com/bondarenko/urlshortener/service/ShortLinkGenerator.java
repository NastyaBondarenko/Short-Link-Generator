package com.bondarenko.urlshortener.service;

import com.bondarenko.urlshortener.dto.ShortLinkResponse;

public interface ShortLinkGenerator {

    ShortLinkResponse generateShortLink();

}