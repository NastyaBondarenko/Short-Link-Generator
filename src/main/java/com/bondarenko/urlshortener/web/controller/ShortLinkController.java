package com.bondarenko.urlshortener.web.controller;

import com.bondarenko.urlshortener.service.ShortLinksGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/short-links", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShortLinkController {

    private final ShortLinksGenerator shortLinkGenerator;

    @PostMapping("/generate")
    protected List<String> generateShortLinks() {
        return shortLinkGenerator.generateShortLinks();
    }
}
