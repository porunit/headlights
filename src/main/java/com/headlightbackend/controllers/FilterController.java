package com.headlightbackend.controllers;

import com.headlightbackend.services.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FilterController {
    private final FilterService filterService;

    @GetMapping("/brands")
    public ResponseEntity<?> getAllBrands() {
        return ResponseEntity.ok(filterService.getAllBrands());
    }

    @GetMapping("/{brand}/models")
    public ResponseEntity<?> getModelsByBrandId(@PathVariable("brand") String brandId) {
        return ResponseEntity.ok(filterService.getModelsByBrandId(brandId));
    }

    @GetMapping("/{model}/generations")
    public ResponseEntity<?> getGenerationsByModelId(@PathVariable("model") String modelId) {
        return ResponseEntity.ok(filterService.getGenerationsByModelId(modelId));
    }
    @GetMapping("/filters/brand/{brand}")
    public ResponseEntity<?> getAllCriteriaByBrand(@PathVariable String brand){
        return ResponseEntity.ok(filterService.getAllCriteriaByBrand(brand));
    }

    @GetMapping("/filters/category/{type}")
    public ResponseEntity<?> getAllCriteriaByType(@PathVariable String type){
        return ResponseEntity.ok(filterService.getAllCriteriaByCommonType(type.toUpperCase()));
    }

    @GetMapping("/filters")
    public ResponseEntity<?> getAllCriteria(){
        return ResponseEntity.ok(filterService.getAllCriteria());
    }
}
