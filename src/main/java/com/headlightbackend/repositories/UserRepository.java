package com.headlightbackend.repositories;


import com.headlightbackend.data.domain.Role;
import com.headlightbackend.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT m.id FROM User m WHERE m.username = :name")
    Long getUserIdByUsername(String name);
    User findFirstByUsername(String name);
    User findFirstById(Long id);
    boolean existsByUsername(String name);
}
