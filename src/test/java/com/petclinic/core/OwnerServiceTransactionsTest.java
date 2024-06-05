package com.petclinic.core;

import com.petclinic.core.entities.Owner;
import com.petclinic.core.owner.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class OwnerServiceTransactionsTest {
    private final OwnerService ownerService;

    @Autowired
    public OwnerServiceTransactionsTest(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Test @Transactional
    void shouldRunInTransaction() {
        assertThat(TransactionSynchronizationManager.isActualTransactionActive()).isTrue();
    }

    @Test @Transactional
    void shouldTransferFunds() {
        var ownerToCredit = ownerService.save(new Owner(0L, "Jimi","Hendrix", 0d));
        var ownerToDebit = ownerService.save(new Owner(0L, "Robert","Plant",1000d));

        ownerService.transferFunds(ownerToCredit, ownerToDebit, 200);

        var ownerToCreditRetrieved = ownerService.findByFirstName("Jimi").orElseThrow(RuntimeException::new);
        var ownerToDebitRetrieved = ownerService.findByFirstName("Robert").orElseThrow(RuntimeException::new);

        assertThat(ownerToCreditRetrieved.getAccountStatement()).isEqualTo(200);
        assertThat(ownerToDebitRetrieved.getAccountStatement()).isEqualTo(800);
    }

    @Test @Transactional
    void shouldFailTransferBecauseAmountBelowZero() {
        var ownerToCredit = ownerService.save(new Owner(0L, "Jimi","Hendrix", 0d));
        var ownerToDebit = ownerService.save(new Owner(0L, "Robert","Plant",1000d));

        assertThatThrownBy(() -> ownerService.transferFunds(ownerToCredit, ownerToDebit, 2000))
                .isInstanceOf(RuntimeException.class);
    }
}
