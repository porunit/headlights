package com.headlightbackend.data.dto;

import com.headlightbackend.data.domain.CommonType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HeadlightSaveDTO {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Min(value = 1, message = "Price should be higher than 0")
    private Integer price;
    @NotNull
    @NotBlank
    private String articul;
    @NotNull
    private Long commonType;
    @NotNull
    @NotBlank
    private String country;
    @NotNull
    @NotBlank
    private String frontOrBackSideType;
    @NotNull
    @NotBlank
    private String manufacturer;
    @NotNull
    @NotBlank
    private String lightType;
    @NotNull
    @NotBlank
    private String lamps;
    @NotNull
    @NotBlank
    private String corrector;
    @NotNull
    @NotBlank
    private String headlightReleasePeriod;
    @NotNull
    @NotBlank
    private String leftOrRightSideType;
    @NotNull
    private Boolean sendableByRuPost;
    @NotNull
    private Boolean isOrigNumberReplaced;
    private String PTComplection;
    private String PTMeasure;
    private Boolean TUInCase;
    private String TUMoldColour;
    private String TUTuning;
    private String imageBASE64;
    @NotNull
    private Long carModelGenerationId;
}
