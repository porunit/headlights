package com.headlightbackend.services;

import com.headlightbackend.data.domain.Brand;
import com.headlightbackend.data.domain.BrandModels;
import com.headlightbackend.data.domain.CarModelGeneration;
import com.headlightbackend.data.dto.*;
import com.headlightbackend.repositories.BrandRepository;
import com.headlightbackend.repositories.CarGenerationRepository;
import com.headlightbackend.repositories.HeadlightRepository;
import com.headlightbackend.repositories.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "Filters")
@RequiredArgsConstructor
public class FilterService {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final CarGenerationRepository carGenerationRepository;
    private final HeadlightRepository headlightRepository;

    @Cacheable(value = "brands", key = "'AllBrands'")
    public List<MenuFilterDTO> getAllBrands() {
        List<MenuFilterDTO> brands = new ArrayList<>();
        brandRepository.findAll()
                .forEach(x -> brands.add(
                        new MenuFilterDTO(x.getId(), x.getName())
                ));
        return brands;
    }


    @Cacheable(value = "models", key ="'AllModels'.concat(#brandId)")
    public List<MenuFilterDTO> getModelsByBrandId(String brandId) {
        List<MenuFilterDTO> models = new ArrayList<>();
        modelRepository.findAllByBrandId(brandId)
                .forEach(x -> models.add(
                        new MenuFilterDTO(x.getId(), x.getName())
                ));
        return models;
    }

    @Cacheable(value = "generations", key = "'AllGenerations'.concat(#modelId)")
    public List<MenuFilterDTO> getGenerationsByModelId(String modelId) {
        List<MenuFilterDTO> generations = new ArrayList<>();
        carGenerationRepository.findAllByBrandModelId(modelId)
                .forEach(x -> generations.add(
                        new MenuFilterDTO(Long.toString(x.getId()), x.getPeriod())
                ));
        return generations;
    }

    @Cacheable(value = "criteria", key = "'BrandCriteria'.concat(#brandId)")
    public FiltersMapDTO getAllCriteriaByBrand(String brandId) {
        Map<String, FilterDTO> criteria = new HashMap<>();
        criteria.put("manufacturer", new FilterDTO("Производитель",headlightRepository.findManufacturersWithCarBrandId(brandId)));
        criteria.put("lightType", new FilterDTO("Тип света",headlightRepository.findLightTypeWithCarBrandId(brandId)));
        criteria.put("frontOrBack", new FilterDTO("Сторона",headlightRepository.findFrontOrBackWithCarBrandId(brandId)));
        criteria.put("leftOrRight", new FilterDTO("Сторона",headlightRepository.findLeftOrRightWithCarBrandId(brandId)));
        criteria.put("lamps", new FilterDTO("Тип лампы",headlightRepository.findLampsWithCarBrandId(brandId)));
        return new FiltersMapDTO(criteria);
    }

    @Cacheable(value = "criteria", key = "'TypeCriteria'.concat(#type)")
    public FiltersMapDTO getAllCriteriaByCommonType(String type) {
        Map<String, FilterDTO> criteria = new HashMap<>();
        criteria.put("manufacturer", new FilterDTO("Производитель", headlightRepository.findManufacturersWithCommonType(type)));
        criteria.put("lightType", new FilterDTO("Тип света",headlightRepository.findLightTypeWithCommonType(type)));
        criteria.put("frontOrBack", new FilterDTO("Сторона",headlightRepository.findFrontOrBackWithCommonType(type)));
        criteria.put("leftOrRight", new FilterDTO("Сторона",headlightRepository.findLeftOrRightWithCommonType(type)));
        criteria.put("lamps", new FilterDTO("Тип лампы",headlightRepository.findLampsWithCommonType(type)));
        return new FiltersMapDTO(criteria);
    }

    @Cacheable(value = "criteria", key = "'AllCriteria'")
    public FiltersMapDTO getAllCriteria() {
        Map<String, FilterDTO> criteria = new HashMap<>();
        criteria.put("manufacturer", new FilterDTO("Производитель", headlightRepository.findManufacturers()));
        criteria.put("lightType", new FilterDTO("Тип света", headlightRepository.findLightType()));
        criteria.put("frontOrBack", new FilterDTO("Сторона", headlightRepository.findFrontOrBack()));
        criteria.put("leftOrRight", new FilterDTO("Сторона", headlightRepository.findLeftOrRight()));
        criteria.put("lamps", new FilterDTO("Тип лампы", headlightRepository.findLamps()));
        return new FiltersMapDTO(criteria);
    }
    @CacheEvict(value = "brands", allEntries = true)
    public void saveBrand(BrandSaveDTO brandDTO) {
        Brand brand = Brand.builder()
                .name(brandDTO.getName())
                .build();
        brandRepository.save(brand);
    }
    @CacheEvict(value = "models", allEntries = true)
    public void saveModel(ModelSaveDTO modelDTO) {
        BrandModels model = BrandModels.builder()
                .name(modelDTO.getModelName())
                .brand(brandRepository.findFirstById(modelDTO.getBrandID()))
                .build();
        modelRepository.save(model);
    }
    @CacheEvict(value = "generations", allEntries = true)
    public void saveGeneration(GenerationSaveDTO genDTO){
        CarModelGeneration gen = CarModelGeneration.builder()
                .brandModel(modelRepository.findFirstById(genDTO.getModelID()))
                .period(genDTO.getPeriod())
                .build();
        carGenerationRepository.save(gen);
    }
}