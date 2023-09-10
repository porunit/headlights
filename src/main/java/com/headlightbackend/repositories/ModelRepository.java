package com.headlightbackend.repositories;

import com.headlightbackend.data.domain.BrandModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<BrandModels, String> {
    List<BrandModels> findAllByBrandId(String brand_id);

    BrandModels findFirstById(String id);
}
