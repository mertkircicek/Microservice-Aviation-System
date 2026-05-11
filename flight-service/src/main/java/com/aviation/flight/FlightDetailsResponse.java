package com.aviation.flight;

public class FlightDetailsResponse {
    private Flight flight;
    private Object aircraft;
    private Object pilot;

    public FlightDetailsResponse(Flight flight, Object aircraft, Object pilot) {
        this.flight = flight;
        this.aircraft = aircraft;
        this.pilot = pilot;
    }

    // Getter ve Setter'lar
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Object getAircraft() {
        return aircraft;
    }

    public void setAircraft(Object aircraft) {
        this.aircraft = aircraft;
    }

    public Object getPilot() {
        return pilot;
    }

    public void setPilot(Object pilot) {
        this.pilot = pilot;
    }
}