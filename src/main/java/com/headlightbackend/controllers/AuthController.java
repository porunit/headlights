package com.headlightbackend.controllers;


import com.headlightbackend.data.domain.Role;
import com.headlightbackend.data.dto.AuthResponseDTO;
import com.headlightbackend.data.dto.UserDTO;
import com.headlightbackend.exceptions.UsernameTakenException;
import com.headlightbackend.jwt.JwtProvider;
import com.headlightbackend.repositories.UserRepository;
import com.headlightbackend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(), userDTO.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token,
                userRepository.findFirstByUsername(userDTO.getUsername()).getRole()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UsernameTakenException("Username taken");
        }
        authService.register(userDTO);
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(), userDTO.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token, Role.USER));
    }

    @GetMapping("/role")
    public ResponseEntity<?> getRole(
            @RequestHeader("Authorization") String authorizationHeader){
        Role role = userRepository.findFirstByUsername(authService.getUsernameFromHeader(authorizationHeader)).getRole();
        return ResponseEntity.ok(role);
    }
    @GetMapping("/username")
    public ResponseEntity<?> getUsernameFromToken(
            @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(authService.getUsernameFromHeader(authorizationHeader));
    }

}
