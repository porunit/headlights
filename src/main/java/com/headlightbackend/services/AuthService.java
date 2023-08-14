package com.headlightbackend.services;


import com.headlightbackend.data.domain.Role;
import com.headlightbackend.data.domain.User;
import com.headlightbackend.data.dto.UserDTO;
import com.headlightbackend.jwt.JwtProvider;
import com.headlightbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public void register(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }

    public String getUsernameFromToken(String token) {
        return jwtProvider.getUsernameFromJwt(token);
    }
}
