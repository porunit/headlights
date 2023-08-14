package com.headlightbackend.repositories;

import com.headlightbackend.data.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, String> {
    @NonNull
    List<Brand> findAll();

    @Override
    @NonNull
    @Transactional
    Brand save(Brand brand);

    Brand findFirstById(String id);

    Brand findFirstByName(String name);
}
