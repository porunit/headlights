package com.headlightbackend.controllers;

import com.headlightbackend.services.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/sms")
@RequiredArgsConstructor
@Log4j2
public class SmsController {

    private final SmsService service;

    @GetMapping
    public ResponseEntity<?> sendSMS(@RequestParam("phone") String phone) {
        String message = service.sendMassage(phone);
        log.info(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifySMS(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        service.verifyCode(phone, code);
        return ResponseEntity.ok("Ahui");
    }

}
