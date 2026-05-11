package com.aviation.flight;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Eureka'da "AIRCRAFT-SERVICE" adıyla kayıtlı olan servise gideceğini belirtir
@FeignClient(name = "aircraft-service")
public interface AircraftClient {

    // Gittiği servisin /api/aircrafts/{id} adresine istek atar
    @GetMapping("/api/aircrafts/{id}")
    Object getAircraftById(@PathVariable("id") Long id);
}