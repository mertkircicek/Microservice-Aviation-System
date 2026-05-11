package com.aviation.crew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/crews")
public class CrewController {

    @Autowired
    private CrewRepository crewRepository;

    // Tüm mürettebat listesini veritabanından getirir
    @GetMapping
    public List<CrewMember> getAllCrews() {
        return crewRepository.findAll();
    }

    // Veritabanına yeni bir mürettebat üyesi kaydeder
    @PostMapping
    public CrewMember addCrew(@RequestBody CrewMember crewMember) {
        return crewRepository.save(crewMember);
    }

    // Sadece belirli bir ID'ye sahip mürettebatı getirir
    @GetMapping("/{id}")
    public CrewMember getCrewById(@PathVariable Long id) {
        return crewRepository.findById(id).orElse(null);
    }

    // Mevcut bir mürettebat üyesini günceller
    @PutMapping("/{id}")
    public CrewMember updateCrew(@PathVariable Long id, @RequestBody CrewMember updatedCrew) {
        return crewRepository.findById(id).map(crew -> {
            crew.setName(updatedCrew.getName());
            crew.setRole(updatedCrew.getRole());
            crew.setStatus(updatedCrew.getStatus());
            return crewRepository.save(crew);
        }).orElse(null);
    }

    // Belirli bir mürettebatı siler
    @DeleteMapping("/{id}")
    public void deleteCrew(@PathVariable Long id) {
        crewRepository.deleteById(id);
    }
}
