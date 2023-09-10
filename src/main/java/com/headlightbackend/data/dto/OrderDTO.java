package com.headlightbackend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private List<BucketItemDTO> items;
    private String date;
    private String address;
    private String orderDetails;
    private String state;
    private String username;
}
