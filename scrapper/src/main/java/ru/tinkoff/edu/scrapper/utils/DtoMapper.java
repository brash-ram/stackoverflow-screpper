package ru.tinkoff.edu.scrapper.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.scrapper.data.entity.Link;
import ru.tinkoff.edu.scrapper.dto.response.LinkResponse;

@Component
@RequiredArgsConstructor
public class DtoMapper {

    private final ModelMapper modelMapper;

    public LinkResponse convertLinkToLinkResponse(Link link) {
        return modelMapper.map(link, LinkResponse.class);
    }
}
