package com.headlightbackend.services;

import com.headlightbackend.data.domain.Headlight;
import com.headlightbackend.data.dto.HeadlightCatalogDTO;
import com.headlightbackend.data.dto.HeadlightDTO;
import com.headlightbackend.data.dto.SearchFiltersDTO;
import com.headlightbackend.mappers.HeadlightMapper;
import com.headlightbackend.repositories.HeadlightRepository;
import com.headlightbackend.util.HeadlightSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@CacheConfig(cacheNames = "Headlights")
public class HeadlightService {
    private final HeadlightRepository headlightRepository;
    private final HeadlightMapper mapper = Mappers.getMapper(HeadlightMapper.class);
    @Cacheable(value = "headlightCatalogDTOS", key = "#type.concat(#page)")
    public List<HeadlightCatalogDTO> getListOfHeadlightsByCommonType(String type, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id"));
        Page<Headlight> headlightsPage = headlightRepository.findAllByCommonTypeName(type, pageable);
        log.info("GetMapping by category: {" + type + "} get " + headlightsPage.getNumberOfElements() + " headlights");
        List<HeadlightCatalogDTO> headlightCatalogDTOS = pageValidation(headlightsPage);
        return headlightCatalogDTOS;

    }

    @Cacheable(value = "headlightCatalogDTOS", key = "#brandId.concat(#page)")
    public List<HeadlightCatalogDTO> getListOfHeadlightsByBrand(String brandId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id"));
        Page<Headlight> headlightsPage = headlightRepository.findAllByCarModelGenerationBrandModelBrandId(brandId, pageable);
        log.info("GetMapping by brand: {" + brandId + "} get " + headlightsPage.getNumberOfElements() + " headlights");
        List<HeadlightCatalogDTO> headlightCatalogDTOS = pageValidation(headlightsPage);
        return headlightCatalogDTOS;
    }
    @Cacheable(value = "headlightCatalogDTOS", key = "#generationId.toString().concat(#page)")
    public List<HeadlightCatalogDTO> getListOfHeadlightsByGeneration(String generationId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id"));

        Page<Headlight> headlightsPage = headlightRepository.findAllByCarModelGenerationId(Long.parseLong(generationId), pageable);
        log.info("GetMapping by generation: {" + generationId + "} get " + headlightsPage.getNumberOfElements() + " headlights");
        List<HeadlightCatalogDTO> headlightCatalogDTOS = pageValidation(headlightsPage);
        return headlightCatalogDTOS;
    }

    @Cacheable(value = "headlightCatalogDTOS", key = "'headlights'.concat(#page)")
    public List<HeadlightCatalogDTO> getListOfHeadlightsCatalog(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id"));
        Page<Headlight> headlightsPage = headlightRepository.findAll(pageable);
        log.info("GetMapping by all: get " + headlightsPage.getNumberOfElements() + " headlights");
        List<HeadlightCatalogDTO> headlightCatalogDTOS = pageValidation(headlightsPage);
        return headlightCatalogDTOS;
    }
    @Cacheable(value = "headlight", key = "#id")
    public HeadlightDTO getHeadlightById(long id) {
        HeadlightDTO headlight = mapper.toDTO(headlightRepository.findFirstById(id));
        return headlight;
    }
    @Cacheable(value = "headlightCatalogDTOS", key = "#filtersDTO.hashCode() + #page") //TODO override hashcode
    public List<HeadlightCatalogDTO> getListOfHeadlightsBySearchDTO(SearchFiltersDTO filtersDTO, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id"));
        Page<Headlight> headlightsPage = headlightRepository.findAll(
                HeadlightSpecification.matchesSearchFilters(filtersDTO), pageable
        );
        List<HeadlightCatalogDTO> headlightCatalogDTOS = pageValidation(headlightsPage);
        return headlightCatalogDTOS;
    }

    private List<HeadlightCatalogDTO> pageValidation(Page<Headlight> headlightsPage) {
        List<HeadlightCatalogDTO> dtoList = mapper.toCatalogDTOList(headlightsPage.getContent());
        return dtoList;
    }
}
