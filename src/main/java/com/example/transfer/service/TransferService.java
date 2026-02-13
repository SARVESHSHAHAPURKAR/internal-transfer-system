package com.example.transfer.service;

import com.example.transfer.model.Account;
import com.example.transfer.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final AccountRepository repository;

    @Transactional
    public void createAccount(Long id, BigDecimal balance) {
        if (repository.existsById(id)) throw new RuntimeException("Account " + id + " already exists.");
        repository.save(new Account(id, balance));
    }

    public Account getAccount(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Account " + id + " not found."));
    }

    @Transactional
    public void transfer(Long from, Long to, BigDecimal amount) {
        if (from.equals(to)) throw new RuntimeException("Cannot transfer to the same account.");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new RuntimeException("Amount must be positive.");

        // Deadlock Prevention: Sort IDs to always lock in the same order
        Long first = Math.min(from, to);
        Long second = Math.max(from, to);

        repository.findByIdForUpdate(first);
        repository.findByIdForUpdate(second);

        Account source = repository.findById(from).get();
        Account dest = repository.findById(to).get();

        if (source.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds in account: " + from);
        }

        source.setBalance(source.getBalance().subtract(amount));
        dest.setBalance(dest.getBalance().add(amount));

        repository.save(source);
        repository.save(dest);
    }
}
