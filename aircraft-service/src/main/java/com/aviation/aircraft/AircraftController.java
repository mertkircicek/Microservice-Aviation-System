package com.aviation.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/aircrafts")
public class AircraftController {

    // Veritabanı işçimizi (Repository) buraya çağırıyoruz
    @Autowired
    private AircraftRepository aircraftRepository;

    // Tüm uçakları veritabanından çekip listeleyecek
    @GetMapping
    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    // Sisteme dışarıdan yeni uçak eklemek için (Opsiyonel ama test için çok
    // faydalı)
    @PostMapping
    public Aircraft addAircraft(@RequestBody Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    // Sadece belirli bir ID'ye sahip uçağı getirir
    @GetMapping("/{id}")
    public Aircraft getAircraftById(@PathVariable Long id) {
        return aircraftRepository.findById(id).orElse(null);
    }
}