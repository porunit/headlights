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
public class GenerationSaveDTO {
    @NotNull
    @NotBlank
    String modelID;
    @NotNull
    @NotBlank
    String period;
}
