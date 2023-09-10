package com.headlightbackend.repositories;

import com.headlightbackend.data.domain.BucketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<BucketItem, Long> {
    BucketItem findFirstById(Long id);
}
