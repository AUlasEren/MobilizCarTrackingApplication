package com.mobiliz.repository;


import com.mobiliz.repository.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {
    Optional<UserAuthentication> findByEmail(String email);
}
