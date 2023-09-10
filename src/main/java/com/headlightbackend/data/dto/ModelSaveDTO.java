package com.headlightbackend.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ModelSaveDTO {
    @NotNull
    @NotBlank
    String brandID;
    @NotNull
    @NotBlank
    String modelName;
}
