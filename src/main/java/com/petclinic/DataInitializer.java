package com.petclinic;

import com.petclinic.core.entities.Owner;
import com.petclinic.core.entities.Pet;
import com.petclinic.core.entities.Visit;
import com.petclinic.core.visit.VisitService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("dev")
@Component // ATTENTION, obligatoire si on veut appliquer un profile, il faut que ce soit un "component" Spring
public class DataInitializer {
    private final VisitService visitService;

    @Autowired
    public DataInitializer(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostConstruct // Launch after all beans are initialized (because we need VisitService here)
    public void initializeSampleData() {
        Owner owner = new Owner(0L, "joe", "satriani", 1000d);
        var pet1 = new Pet(0L, "dog", "luna");
        var visit = new Visit(0L, "V01-23", LocalDate.of(2013, 12, 21), "Teeth whitening");
        visit.setPet(pet1);
        owner.add(pet1);
        visit.setOwner(owner);
        this.visitService.save(visit);
    }
}
