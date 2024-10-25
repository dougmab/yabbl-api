package com.github.dougmab.yabbl.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByHandle(String handle);
    Optional<User> findByEmail(String email);
    Boolean existsByHandle(String handle);
    Boolean existsByEmail(String email);
}
