package com.headlightbackend.repositories;

import com.headlightbackend.data.domain.Order;
import com.headlightbackend.data.domain.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>>findAllByUserId(Long id);
    @Transactional
    @Modifying
    @Query("UPDATE Order m SET m.state = :value WHERE m.id = :id")
    void updateState(@Param("id") Long id, @Param("value") OrderState value);
    @Transactional
    @Modifying
    @Query("UPDATE Order m SET m.address = :value WHERE m.id = :id")
    void updateAddress(@Param("id") Long id, @Param("value") String value);
    List<Order> findAllByState(OrderState state);
}
