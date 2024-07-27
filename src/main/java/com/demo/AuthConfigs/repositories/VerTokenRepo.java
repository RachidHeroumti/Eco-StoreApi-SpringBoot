package com.demo.AuthConfigs.repositories;

import com.demo.AuthConfigs.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerTokenRepo extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);
}
