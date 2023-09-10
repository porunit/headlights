package com.headlightbackend.services;

import com.headlightbackend.data.domain.Headlight;
import com.headlightbackend.data.dto.HeadlightCatalogDTO;
import com.headlightbackend.data.dto.HeadlightDTO;
import com.headlightbackend.data.dto.HeadlightSaveDTO;
import com.headlightbackend.data.dto.SearchFiltersDTO;
import com.headlightbackend.exceptions.FileProcessingException;
import com.headlightbackend.mappers.HeadlightMapper;
import com.headlightbackend.repositories.CarGenerationRepository;
import com.headlightbackend.repositories.CommonTypeRepository;
import com.headlightbackend.repositories.HeadlightRepository;
import com.headlightbackend.util.HeadlightSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@CacheConfig(cacheNames = "Headlights")
public class HeadlightService {
    private final HeadlightRepository headlightRepository;
    private final CarGenerationRepository carModelGeneration;
    private final CommonTypeRepository commonTypeRepository;

    private String UPLOAD_DIR = "./src/main/resources/static/images/";
    private String WEB_PATH = "/images/";

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

    public HeadlightCatalogDTO getHeadlightByArticul(String articul) {
        return mapper.toCatalogDTO(headlightRepository.findFirstByArticul(articul));
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

    @CacheEvict(value = "headlightCatalogDTOS", allEntries = true)
    public void saveHeadlight(HeadlightSaveDTO headlightSaveDTO) {
        byte[] imageBytes = Base64.getDecoder().decode(headlightSaveDTO.getImageBASE64());
        String path = UPLOAD_DIR;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes)) {
            BufferedImage image = ImageIO.read(bis);
            File outputfile = new File(path, "/" + headlightSaveDTO.getArticul() + ".png");

            ImageIO.write(image, "png", outputfile);

        } catch (IOException e) {
            throw new FileProcessingException("Error while loading image " + e.getMessage());
        }
        Headlight headlight = Headlight.builder()
                .articul(headlightSaveDTO.getArticul())
                .carModelGeneration(carModelGeneration.findFirstById(headlightSaveDTO.getCarModelGenerationId()))
                .lamps(headlightSaveDTO.getLamps())
                .commonType(commonTypeRepository.findFirstById(headlightSaveDTO.getCommonType()))
                .price(headlightSaveDTO.getPrice())
                .manufacturer(headlightSaveDTO.getManufacturer())
                .lightType(headlightSaveDTO.getLightType())
                .leftOrRightSideType(headlightSaveDTO.getLeftOrRightSideType())
                .isOrigNumberReplaced(headlightSaveDTO.getIsOrigNumberReplaced())
                .name(headlightSaveDTO.getName())
                .sendableByRuPost(headlightSaveDTO.getSendableByRuPost())
                .PTComplection(headlightSaveDTO.getPTComplection())
                .PTMeasure(headlightSaveDTO.getPTMeasure())
                .TUTuning(headlightSaveDTO.getTUTuning())
                .TUMoldColour(headlightSaveDTO.getTUMoldColour())
                .TUInCase(headlightSaveDTO.getTUInCase())
                .corrector(headlightSaveDTO.getCorrector())
                .country(headlightSaveDTO.getCountry())
                .frontOrBackSideType(headlightSaveDTO.getFrontOrBackSideType())
                .headlightReleasePeriod(headlightSaveDTO.getHeadlightReleasePeriod())
                .imageUrl(WEB_PATH + headlightSaveDTO.getArticul()+".png")
                .build();

        headlightRepository.save(headlight);
    }

    private List<HeadlightCatalogDTO> pageValidation(Page<Headlight> headlightsPage) {
        List<HeadlightCatalogDTO> dtoList = mapper.toCatalogDTOList(headlightsPage.getContent());
        return dtoList;
    }
}
