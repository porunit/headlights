package com.headlightbackend.mappers;

import com.headlightbackend.data.domain.Headlight;
import com.headlightbackend.data.dto.HeadlightCatalogDTO;
import com.headlightbackend.data.dto.HeadlightDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper
public interface HeadlightMapper {

    @Mapping(source = "commonType.name", target = "commonType")
    @Mapping(source = "carModelGeneration.brandModel.name", target = "model")
    @Mapping(source = "carModelGeneration.brandModel.brand.name", target = "brand")
    HeadlightDTO toDTO(Headlight headlight);

    @Mapping(source = "commonType.name", target = "commonType")
    @Mapping(source = "headlightReleasePeriod", target = "headlightReleasePeriod")
    @Mapping(source = "carModelGeneration.brandModel.name", target = "model")
    @Mapping(source = "carModelGeneration.brandModel.brand.name", target = "brand")
    HeadlightCatalogDTO toCatalogDTO(Headlight headlight);

    @IterableMapping(elementTargetType = HeadlightCatalogDTO.class)
    List<HeadlightCatalogDTO> toCatalogDTOList(List<Headlight> headlights);

    default Page<HeadlightCatalogDTO> toCatalogDTOPage(Page<Headlight> headlightsPage) {
        List<HeadlightCatalogDTO> dtoList = toCatalogDTOList(headlightsPage.getContent());
        return new PageImpl<>(dtoList, headlightsPage.getPageable(), headlightsPage.getTotalElements());
    }
}

