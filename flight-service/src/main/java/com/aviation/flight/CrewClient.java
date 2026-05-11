package com.aviation.flight;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "crew-service")
public interface CrewClient {

    @GetMapping("/api/crews/{id}")
    Object getCrewById(@PathVariable("id") Long id);
}