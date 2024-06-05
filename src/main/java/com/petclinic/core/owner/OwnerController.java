package com.petclinic.core.owner;

import com.petclinic.core.entities.Owner;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    Owner findById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @GetMapping("/search")
    Owner findByFirstName(@RequestParam String firstName) {
        return ownerService.findByFirstName(firstName).orElseThrow();
    }

    @PostMapping()
    ResponseEntity<Owner> save(@RequestBody Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Owner savedOwner = ownerService.save(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOwner);
    }
}
