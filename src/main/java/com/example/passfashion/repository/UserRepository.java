package com.example.passfashion.repository;

import com.example.passfashion.model.Address;

import com.example.passfashion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPwd(String email, String pwd);

    Optional<User> findById(Long id);

    boolean existsByIdAndIsDeleted(Long id, boolean isDeleted);
}
