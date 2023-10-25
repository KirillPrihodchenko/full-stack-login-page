package com.auth.fullstackloginpage.repository;

import com.auth.fullstackloginpage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<com.auth.fullstackloginpage.model.User, UUID> {

    Optional<User> findUserByEmail(String email);
}
