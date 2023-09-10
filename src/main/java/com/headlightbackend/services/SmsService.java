package com.headlightbackend.services;

import com.headlightbackend.exceptions.SmsServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Log4j2
public class SmsService {
    private final String API_KEY = {API_KEY};
    private final String PROJECT = {PROJECT};
    private final String BASE_URL = "https://sms.notisend.ru/api/message/send";
    private final RestTemplate restTemplate;
    private final StringRedisTemplate redisTemplate;
    private static final String SMS_CODE_PREFIX = "sms:code:";
    private static final long CODE_EXPIRATION_SECONDS = 600; // 10 минут

    public String sendMassage(String phone) {
        URI url = null;
        String message = Integer.toString(generateCode());
        redisTemplate.opsForValue().set(SMS_CODE_PREFIX + phone, message, CODE_EXPIRATION_SECONDS, TimeUnit.SECONDS);
        try {
            url = new URI(BASE_URL + "?project=" + PROJECT + "&recipients=" + phone + "&message=" + message + "&apikey=" + API_KEY);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (!Objects.requireNonNull(response.getBody()).toString().contains("success")) {
            throw new SmsServiceException("Error while sending sms" + response.getBody());
        }
        return message;
    }

    public void verifyCode(String phone, String code) {
        String storedCode = redisTemplate.opsForValue().get(SMS_CODE_PREFIX + phone);
        if(!code.equals(storedCode)){
            throw new SmsServiceException("Wrong code");
        }
    }
    private int generateCode() {
        Random random = new Random();

        int min = 1001;
        int max = 9999;

        int randomNum = random.nextInt(max - min + 1) + min;
        return randomNum;
    }
}
