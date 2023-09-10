package com.headlightbackend.data.dto;

import com.headlightbackend.data.domain.Role;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class AuthResponseDTO {
    private final String accessToken;
    private final String tokenType = "Bearer ";
    private final Role role;
}

