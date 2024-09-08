package com.demo.AuthConfigs.services;


import com.demo.AuthConfigs.DTO.PaymentDto;
import com.demo.AuthConfigs.models.Payment;
import com.demo.AuthConfigs.repositories.PaymentRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo paymentRepo ;
    public ResponseEntity<Payment> CreatePayment(PaymentDto paymentDto) {
        Payment p=paymentRepo.save(convertToEntity(paymentDto) );
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }
    private Payment convertToEntity(PaymentDto paymentRequestDTO) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentRequestDTO, payment);

        return payment;
    }
}
