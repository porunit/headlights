package com.headlightbackend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.headlightbackend.data.dto.HeadlightCatalogDTO;
import com.headlightbackend.data.dto.HeadlightDTO;
import com.headlightbackend.data.dto.SearchFiltersDTO;
import com.headlightbackend.exceptions.FilterDecodingException;
import com.headlightbackend.services.HeadlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/catalog")
public class HeadlightsController {
    private final HeadlightService headlightService;
    private final String PAGE_SIZE = "25";

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(value = "filters", required = false) String filters,
                                    @RequestParam(defaultValue = PAGE_SIZE) int size) {
        List<HeadlightCatalogDTO> result;
        if (filters == null) {
            result = headlightService.getListOfHeadlightsCatalog(page, size);
        } else {
            SearchFiltersDTO filtersDTO = decodeFiltersFromBASE64(filters);
            result = headlightService.getListOfHeadlightsBySearchDTO(filtersDTO, page, size);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("articul/{articul}")
    public ResponseEntity<?> getOneByArticul(@PathVariable String articul){
        return ResponseEntity.ok(
                headlightService.getHeadlightByArticul(articul)
        );
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getAllByType(@PathVariable("category") String type,
                                          @RequestParam(value = "filters", required = false) String filters,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = PAGE_SIZE) int size) {
        List<HeadlightCatalogDTO> result;
        if (filters == null) {
            result = headlightService.getListOfHeadlightsByCommonType(type.toUpperCase(), page, size);
        } else {
            SearchFiltersDTO filtersDTO = decodeFiltersFromBASE64(filters);
            List<String> types = new ArrayList<>();
            types.add(type);
            filtersDTO.setCommonType(types);
            result = headlightService.getListOfHeadlightsBySearchDTO(filtersDTO, page, size);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/brand/{brand}")
    public ResponseEntity<?> getAllByBrand(@PathVariable("brand") String brandId,
                                           @RequestParam(value = "filters", required = false) String filters,
                                           @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = PAGE_SIZE) int size) {
        List<HeadlightCatalogDTO> result;
        if (filters == null) {
            result = headlightService.getListOfHeadlightsByBrand(brandId, page, size);
        } else {
            SearchFiltersDTO filtersDTO = decodeFiltersFromBASE64(filters);
            List<String> brands = new ArrayList<>();
            brands.add(brandId);
            filtersDTO.setBrand(brands);
            result = headlightService.getListOfHeadlightsBySearchDTO(filtersDTO, page, size);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/generation/{id}")
    public ResponseEntity<?> getAllByGeneration(@PathVariable("id") String generationId,
                                                @RequestParam(value = "filters", required = false) String filters,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = PAGE_SIZE) int size) {
        List<HeadlightCatalogDTO> result;
        if (filters == null) {
            result = headlightService.getListOfHeadlightsByGeneration(generationId, page, size);
        } else {
            SearchFiltersDTO filtersDTO = decodeFiltersFromBASE64(filters);
            List<String> generationIds = new ArrayList<>();
            generationIds.add(generationId);
            filtersDTO.setCarModelGenerationId(generationIds);
            result = headlightService.getListOfHeadlightsBySearchDTO(filtersDTO, page, size);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/headlight/{id}")
    public ResponseEntity<HeadlightDTO> getHeadlight(@PathVariable("id") int id) {
        HeadlightDTO headlight = headlightService.getHeadlightById(id);
        return ResponseEntity.ok(headlight);
    }

    private SearchFiltersDTO decodeFiltersFromBASE64(String data) {
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
        try {
            SearchFiltersDTO filters = new ObjectMapper().readValue(decodedString, SearchFiltersDTO.class);
            log.info(filters.toString());
            return filters;
        } catch (JsonProcessingException e) {
            throw new FilterDecodingException("Failed to decode filters from base64 " + e.getMessage());
        }
    }

}
