package com.aviation.flight;

import javax.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber; // Örn: TK1903
    private String origin;       // Nereden
    private String destination;  // Nereye
    
    // Sadece ID'lerini tutuyoruz, gerçek veriler kendi servislerinde!
    private Long aircraftId;     
    private Long pilotId;

    public Flight() {}

    // Getter ve Setter Metotları
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public Long getAircraftId() { return aircraftId; }
    public void setAircraftId(Long aircraftId) { this.aircraftId = aircraftId; }

    public Long getPilotId() { return pilotId; }
    public void setPilotId(Long pilotId) { this.pilotId = pilotId; }
}
