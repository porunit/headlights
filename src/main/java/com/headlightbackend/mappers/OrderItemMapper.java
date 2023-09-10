package com.headlightbackend.mappers;

import com.headlightbackend.data.domain.BucketItem;
import com.headlightbackend.data.dto.BucketItemDTO;
import com.headlightbackend.repositories.HeadlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.convert.Bucket;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {
    private final HeadlightRepository repository;
    public BucketItem fromDTO(BucketItemDTO dto){
        return BucketItem.builder()
                .headlight(repository.findFirstById(dto.getHeadlightID()))
                .count(dto.getCount())
                .build();
    }

    public BucketItemDTO toDTO(BucketItem item){
        return BucketItemDTO.builder()
                .headlightID(item.getHeadlight().getId())
                .count(item.getCount())
                .build();

    }
}
