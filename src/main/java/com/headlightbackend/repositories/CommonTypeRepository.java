package com.headlightbackend.repositories;

import com.headlightbackend.data.domain.CommonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonTypeRepository extends JpaRepository<CommonType, Long> {
}
