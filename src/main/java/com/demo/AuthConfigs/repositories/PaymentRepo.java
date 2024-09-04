package com.demo.AuthConfigs.repositories;

import com.demo.AuthConfigs.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
