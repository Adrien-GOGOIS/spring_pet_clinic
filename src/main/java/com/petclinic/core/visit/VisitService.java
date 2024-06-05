package com.petclinic.core.visit;

import com.petclinic.core.entities.Visit;
import com.petclinic.core.visit.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    @Autowired // Pas obligatoire ici car injection de dépendance auto par Spring
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Optional<Visit> findByReferenceNumber(String referenceNumber) {
        return visitRepository.findByReferenceNumber(referenceNumber);
    }

    public void save(Visit visit) {
        visitRepository.save(visit);
    }

    public Visit findById(Long id) {
        return visitRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
