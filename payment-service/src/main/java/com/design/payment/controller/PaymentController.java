package com.design.payment.controller;

import com.design.dto.PaymentRequestDTO;
import com.design.dto.PaymentResponseDTO;
import com.design.payment.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/debit")
    public PaymentResponseDTO debit(@RequestBody PaymentRequestDTO requestDTO){
        return this.service.debit(requestDTO);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody PaymentRequestDTO requestDTO){
        this.service.credit(requestDTO);
    }

}
