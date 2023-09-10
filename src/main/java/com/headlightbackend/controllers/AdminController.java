package com.headlightbackend.controllers;

import com.headlightbackend.data.domain.OrderState;
import com.headlightbackend.data.dto.BrandSaveDTO;
import com.headlightbackend.data.dto.GenerationSaveDTO;
import com.headlightbackend.data.dto.HeadlightSaveDTO;
import com.headlightbackend.data.dto.ModelSaveDTO;
import com.headlightbackend.services.FilterService;
import com.headlightbackend.services.HeadlightService;
import com.headlightbackend.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdminController {
    private final OrderService orderService;
    private final HeadlightService headlightService;
    private final FilterService filterService;

    @PostMapping("/brand")
    public ResponseEntity<?> saveBrand(@RequestBody @Valid BrandSaveDTO brandDTO) {
        filterService.saveBrand(brandDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/model")
    public ResponseEntity<?> saveModel(@RequestBody @Valid ModelSaveDTO modelSaveDTO) {
        filterService.saveModel(modelSaveDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/generation")
    public ResponseEntity<?> saveModel(@RequestBody @Valid GenerationSaveDTO generationSaveDTO) {
        filterService.saveGeneration(generationSaveDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllByStateOrders(@RequestParam("state") OrderState state) {
        if (state != OrderState.ALL) {
            return ResponseEntity.ok(orderService.getAllOrdersByState(state));
        }
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/orders")
    public ResponseEntity<?> changeState(@RequestParam("id") Long id, @RequestParam(value = "state", required = false) String state, @RequestParam(value = "address", required = false) String address) {
        if (state == null) {
            orderService.changeAddress(id, address);
        } else {
            orderService.changeState(id, OrderState.valueOf(state));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/orders")
    public ResponseEntity<?> deleteById(@RequestParam("id") Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/headlight")
    public ResponseEntity<?> saveHeadlight(@RequestBody @Valid HeadlightSaveDTO headlightSaveDTO) {
        headlightService.saveHeadlight(headlightSaveDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
