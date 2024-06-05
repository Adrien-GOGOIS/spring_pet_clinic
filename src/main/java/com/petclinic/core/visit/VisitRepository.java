package com.petclinic.core.visit;

import com.petclinic.core.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Optional<Visit> findByReferenceNumber(String referenceNumber); // JPA comprends la requête à générer grace au nom de la méthode et paramètre
}
