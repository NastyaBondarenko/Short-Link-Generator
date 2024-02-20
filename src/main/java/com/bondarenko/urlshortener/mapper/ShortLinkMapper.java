package com.bondarenko.urlshortener.mapper;

import com.bondarenko.urlshortener.dto.ShortLinkResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ShortLinkMapper {

    ShortLinkResponse toShortLinkResponse(String shortLink);
}
