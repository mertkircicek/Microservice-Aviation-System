package com.aviation.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    // Spring Boot arka planda findAll(), save(), delete() gibi kodları bizim için
    // yazar.
}
