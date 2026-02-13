package com.example.transfer.controller;

import com.example.transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final TransferService service;

    @PostMapping("/accounts")
    public ResponseEntity<?> create(@RequestBody Map<String, Object> req) {
        service.createAccount(Long.valueOf(req.get("account_id").toString()), new BigDecimal(req.get("initial_balance").toString()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAccount(id));
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> transfer(@RequestBody Map<String, Object> req) {
        service.transfer(
                Long.valueOf(req.get("source_account_id").toString()),
                Long.valueOf(req.get("destination_account_id").toString()),
                new BigDecimal(req.get("amount").toString())
        );
        return ResponseEntity.ok().build();
    }
}
