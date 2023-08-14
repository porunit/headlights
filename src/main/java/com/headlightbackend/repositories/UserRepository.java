package com.headlightbackend.repositories;


import com.headlightbackend.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String name);

    boolean existsByUsername(String name);
}
