package com.aviation.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AircraftClient aircraftClient; // Yeni Elçi

    @Autowired
    private CrewClient crewClient; // Yeni Elçi

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    // YENİ EKLENEN ORKESTRASYON METODU
    @GetMapping("/{id}/details")
    public FlightDetailsResponse getFlightDetails(@PathVariable Long id) {
        // 1. Adım: Uçuşu kendi veritabanımızdan (MSSQL) bul
        Flight flight = flightRepository.findById(id).orElse(null);

        if (flight != null) {
            // 2. Adım: Elçileri diğer servislere yolla ve verileri getir
            Object aircraft = aircraftClient.getAircraftById(flight.getAircraftId());
            Object pilot = crewClient.getCrewById(flight.getPilotId());

            // 3. Adım: Gelen verileri birleştirip ekrana sun
            return new FlightDetailsResponse(flight, aircraft, pilot);
        }
        return null;
    }
}