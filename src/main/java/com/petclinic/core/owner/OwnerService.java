package com.petclinic.core.owner;

import com.petclinic.core.entities.Owner;
import com.petclinic.core.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired // Pas obligatoire ici car injection de dépendance auto par Spring
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Optional<Owner> findByFirstName(String firstName) {
        return ownerRepository.findByFirstName(firstName);
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Transactional
    public void transferFunds(Owner ownerToCredit, Owner ownerToDebit, double amount) {
        creditAmount(ownerToCredit, amount);
        debitAmount(ownerToDebit, amount);
    }

    private void debitAmount(Owner ownerToDebit, double amount) {
        double ownerToDebitNewAmount = ownerToDebit.getAccountStatement() - amount;
        if (ownerToDebitNewAmount < 0)
            throw new RuntimeException("account value cannot be below zero");
        ownerToDebit.setAccountStatement(ownerToDebitNewAmount);
        this.ownerRepository.save(ownerToDebit);
    }

    private void creditAmount(Owner ownerToCredit, double amount) {
        double ownerToCreditNewAmount = ownerToCredit.getAccountStatement() + amount;
        ownerToCredit.setAccountStatement(ownerToCreditNewAmount);
        this.ownerRepository.save(ownerToCredit);
    }

    public Owner findById(Long id) {
        return this.ownerRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
