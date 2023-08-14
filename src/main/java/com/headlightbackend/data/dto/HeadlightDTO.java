package com.headlightbackend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class HeadlightDTO implements Serializable {
    private Long id;
    private String articul;
    private String name;
    private String model;
    private String price;
    private String commonType;
    private String country;
    private String manufacturer;
    private String frontOrBackSideType;
    private String brand;
    private String lightType;
    private String lamps;
    private String corrector;
    private String headlightReleasePeriod;
    private String leftOrRightSideType;
    private Boolean sendableByRuPost;
    private Boolean isOrigNumberReplaced;
    private String PTComplection;
    private String PTMeasure;
    private Boolean TUInCase;
    private String TUMoldColour;
    private String TUTuning;
    private String imageUrl;
}
