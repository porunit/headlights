package com.headlightbackend.controllers;

import com.headlightbackend.data.domain.OrderState;
import com.headlightbackend.data.dto.OrderDTO;
import com.headlightbackend.services.AuthService;
import com.headlightbackend.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final AuthService authService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO,
                                  @RequestHeader("Authorization") String authorizationHeader) {
        String username = authService.getUsernameFromHeader(authorizationHeader);
        orderService.saveOrder(orderDTO, username);
        return ResponseEntity.ok("Saved");
    }

    @GetMapping
    public ResponseEntity<?> getAllUsersOrders(@RequestHeader("Authorization") String authorizationHeader) {
        String username = authService.getUsernameFromHeader(authorizationHeader);
        return ResponseEntity.ok(orderService.getOrdersByUser(username));
    }

}
