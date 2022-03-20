package com.example.jpatest.repositories;

import com.example.jpatest.entities.Medecin;
import com.example.jpatest.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin findByNom(String name);
}
