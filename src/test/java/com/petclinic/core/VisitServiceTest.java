package com.petclinic.core;

import com.petclinic.core.entities.Owner;
import com.petclinic.core.entities.Pet;
import com.petclinic.core.entities.Visit;
import com.petclinic.core.visit.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class VisitServiceTest {
    private final VisitService visitService;

    @Autowired
    public VisitServiceTest(VisitService visitService) {
        this.visitService = visitService;
    }

    @BeforeEach
    public void setup() {
        var visit = new Visit(0L, "V01-23", LocalDate.of(2013, 12, 21), "Teeth whitening");
        visit.setPet(new Pet(0L, "dog", "luna"));
        visit.setOwner(new Owner(0L, "joe", "satriani", 1000d));
        this.visitService.save(visit);
    }

    @Test @Transactional // Permet de rollback la DB après chaque test
    void shouldNotFindVisitWithReferenceNumber() {
        Optional<Visit> visit = visitService.findByReferenceNumber("toto");
        assertThat(visit).isEmpty();
    }

    @Test @Transactional
    void findByReferenceNumber() {
        Optional<Visit> visit = visitService.findByReferenceNumber("V01-23");
        assertThat(visit).isPresent();
        assertThat(visit.get().getReferenceNumber()).isEqualTo("V01-23");
    }

    @Test @Transactional
    void shouldFindVisitWithPet() {
        Optional<Visit> visit = visitService.findByReferenceNumber("V01-23");
        assertThat(visit).isPresent();
        assertThat(visit.get().getPet().getName()).isEqualTo("luna");
    }

    @Test @Transactional
    void shouldFindVisitWithOwner() {
        Optional<Visit> visit = visitService.findByReferenceNumber("V01-23");
        assertThat(visit).isPresent();
        assertThat(visit.get().getOwner().getFirstName()).isEqualTo("joe");
    }
}