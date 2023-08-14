package com.headlightbackend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class HeadlightCatalogDTO implements Serializable {
    private Long id;
    private String articul;
    private String commonType;
    private String name;
    private String model;
    private String price;
    private String brand;
    private String country;
    private String manufacturer;
    private String headlightReleasePeriod;
    private String imageUrl;
}
