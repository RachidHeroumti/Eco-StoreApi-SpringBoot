package com.demo.AuthConfigs.controllers;

import com.demo.AuthConfigs.DTO.PaymentDto;
import com.demo.AuthConfigs.models.Payment;
import com.demo.AuthConfigs.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService ;
    @PostMapping("create-payment")
    ResponseEntity<Payment> CreatePayment (@RequestBody PaymentDto paymentDto){

        return  paymentService.CreatePayment(paymentDto);
    }
}
