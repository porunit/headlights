package com.headlightbackend.repositories;

import com.headlightbackend.data.domain.CarModelGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarGenerationRepository extends JpaRepository<CarModelGeneration, Long> {
    List<CarModelGeneration> findAllByBrandModelId(String brandModel_id);
}
