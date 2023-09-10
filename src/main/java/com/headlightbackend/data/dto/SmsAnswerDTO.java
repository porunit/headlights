package com.headlightbackend.data.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SmsAnswerDTO {
    private String status;
    private List<String> recipients;
    private Integer parts;
    private Integer count;
    private String price;
    private String balance;
    private List<Long> messagesId;
    private Integer test;
}
