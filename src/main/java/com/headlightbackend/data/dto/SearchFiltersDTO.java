package com.headlightbackend.data.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class SearchFiltersDTO implements Serializable {
    private List<String> commonType;
    private List<String> brand;
    private List<String> manufacturer;
    private List<String> carModelGenerationId;
    private List<String> frontOrBack;
    private List<String> lightType;
    private List<String> lamps;
    private List<String> leftOrRight;
}
