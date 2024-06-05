package com.petclinic.core;

import com.petclinic.core.entities.Owner;
import com.petclinic.core.entities.Pet;
import com.petclinic.core.owner.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OwnerServiceTest {
    private final OwnerService ownerService;

    @Autowired
    public OwnerServiceTest(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @BeforeEach
    public void setup() {
        var owner = new Owner(0L, "joe", "satriani", 1000d);
        owner.add(new Pet(0L, "dog", "Luna"));
        owner.add(new Pet(0L, "cat", "Miro"));
        this.ownerService.save(owner);
    }

    @Test @Transactional
    void shouldFindOwnerByFirstName() {
        Optional<Owner> owner = ownerService.findByFirstName("joe");
        assertThat(owner).isPresent();
        assertThat(owner.get().getFirstName()).isEqualTo("joe");
    }

    @Test @Transactional
    void shouldFindOwnerWithPets() {
        Optional<Owner> owner = ownerService.findByFirstName("joe");
        assertThat(owner).isPresent();
        assertThat(owner.get().getPets()).hasSize(2);
        assertThat(owner.get().getPets()).extracting(Pet::getName).containsExactly("Luna", "Miro");
    }
}