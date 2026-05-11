package com.aviation.crew;

import javax.persistence.*;

@Entity
@Table(name = "crew_members")
public class CrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role; 
    private String status;

    // JPA için gerekli boş constructor
    public CrewMember() {
    }

    // Parametreli constructor
    public CrewMember(String name, String role, String status) {
        this.name = name;
        this.role = role;
        this.status = status;
    }

    // Getter ve Setter Metotları
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}