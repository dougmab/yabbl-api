package com.github.dougmab.yabbl.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByHandle(String handle);
    Optional<User> findByEmail(String email);
    Page<User> findByHandleContaining(String handle, Pageable pageable);
    Boolean existsByHandle(String handle);
    Boolean existsByEmail(String email);
}
