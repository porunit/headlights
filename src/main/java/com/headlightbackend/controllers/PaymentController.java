package com.headlightbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/payment")
public class PaymentController {

}
